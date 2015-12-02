package br.edu.ifsp.stmob;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import br.edu.ifsp.stmob.modelo.GerenciadorSessao;

public class PerfilActivity extends AppCompatActivity {

    GerenciadorSessao sessao;
    TextView txtNomeUsuario;
    Button btnAvisoExtraordinarioAdmin;
    Button btnParticipanteAdmin;
    Button btnEmiteLembrete;

    private String nomeUsuario;
    private String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        sessao = new GerenciadorSessao(getApplicationContext());

        // get user data from session
        HashMap<String, String> user = sessao.pegarDadosUsuario();

        // nome
        nomeUsuario = user.get(GerenciadorSessao.CHAVE_NOME);

        //email
        emailUsuario = user.get(GerenciadorSessao.CHAVE_EMAIL);

        txtNomeUsuario = (TextView) findViewById(R.id.txtNomeUsuario);

        txtNomeUsuario.setText("Perfil de " + nomeUsuario);

        //Mostra ou nao os botoes qdo for admin
        btnAvisoExtraordinarioAdmin = (Button) findViewById(R.id.btnAvisoExtraordinarioAdmin);
        btnParticipanteAdmin = (Button) findViewById(R.id.btnParticipanteAdmin);

        //tipo
        String tipo = user.get(GerenciadorSessao.CHAVE_TIPO);

        //Se for admin mostra os botoes, senao nao
        if(tipo != null) {
            if (tipo.equals("Administrador")) {
                btnParticipanteAdmin.setVisibility(View.VISIBLE);
                btnAvisoExtraordinarioAdmin.setVisibility(View.VISIBLE);
            } else {
                btnParticipanteAdmin.setVisibility(View.INVISIBLE);
                btnAvisoExtraordinarioAdmin.setVisibility(View.INVISIBLE);
            }
        }else{
            btnParticipanteAdmin.setVisibility(View.INVISIBLE);
            btnAvisoExtraordinarioAdmin.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chamaAlterarInformacoesPessoais(View view) {
        Intent alteraInfo = new Intent(this, AlteraInformacoesPessoais.class);
        startActivity(alteraInfo);

    }

    public void chamaBuscaAvisoActivity (View view){

        Intent buscaAvisoActivity = new Intent(this, BuscaAvisoExtraordinarioActivity.class);
        startActivity(buscaAvisoActivity);
    }

    public void logout(View view) {
        sessao.limpaSessao();
    }

    public void emiteLembreteActivity(View view) {
        //Intent emiteLembreteActivity = new Intent(this, EmiteLembreteActivity.class);
        //startActivity(emiteLembreteActivity);

        try {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {emailUsuario}); //passa o email do usuario na sessao
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Semana de Tecnologia IFSP");
            emailIntent.putExtra(Intent.EXTRA_TEXT,"Não se esqueça o evento ocorrerá entre 19 e 22 de outubro de 2015 no câmpus São João da Boa Vista do Instituto Federal de Educação, Ciência e Tecnologia de São Paulo (IFSP-SBV). O tema deste ano será: \"Luz, Ciência e Vida\". Atividades com horários previstos das 8:30 às 22:00.");
            emailIntent.setType("message/rfc822");
            startActivity(emailIntent);
        }catch (ActivityNotFoundException anfe){
            Toast toast = Toast.makeText(this,"email não encontrado", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void enviaFeedbackActivity(View view) {
        //Intent enviaFeedbackActivity = new Intent(this, EnviaFeedbackActivity.class);
        //startActivity(enviaFeedbackActivity);

        try {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] {"ifsp.feedback@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback Semana de Tecnologia IFSP");
            // emailIntent.putExtra(Intent.EXTRA_TEXT,"STMob");
            emailIntent.setType("message/rfc822");
            startActivity(emailIntent);
        }catch (ActivityNotFoundException anfe){
            Toast toast = Toast.makeText(this,"email não encontrado", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
