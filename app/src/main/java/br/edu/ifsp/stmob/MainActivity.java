package br.edu.ifsp.stmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import br.edu.ifsp.stmob.dao.FeedbackDAO;
import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Feedback;
import br.edu.ifsp.stmob.modelo.GerenciadorSessao;
import br.edu.ifsp.stmob.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    GerenciadorSessao sessao;
    TextView txtBemVindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessao = new GerenciadorSessao(getApplicationContext());

        // get user data from session
        HashMap<String, String> user = sessao.pegarDadosUsuario();

        // nome
        String nomeUsuario = user.get(GerenciadorSessao.CHAVE_NOME);

        txtBemVindo = (TextView) findViewById(R.id.txtBemVindo);

        txtBemVindo.setText("Olá, " + nomeUsuario + "!");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void chamaLoginActivity (View view){

        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
    }


    public void chamaAlteraParticipanteActivity (View view){

        Intent alteraParticipanteActivity = new Intent(this, AlteraParticipanteActivity.class);
        startActivity(alteraParticipanteActivity);
    }

    public void chamaCadastraAvisoActivity (View view){

        Intent cadastraAvisoActivity = new Intent(this, CadastraAvisoExtraordinarioActivity.class);
        startActivity(cadastraAvisoActivity);

    }

    public void chamaTestarBanco (View view){

        UsuarioDAO usuarioDao = null;
        FeedbackDAO feedbackDao = null;

            //Cria um usuario
            Usuario usuario = new Usuario();
            usuario.setUsuNome("Filipe Navas");
            usuario.setUsuEmail("filipenavas@hmail.com");
            usuario.setUsuSenha("asd");
            //usuario.setUsuCod(0);

            //Salva no banco
            usuarioDao = new UsuarioDAO(getApplicationContext());

            usuarioDao.salvar(usuario);

            //Recupera o usuario no banco
            Usuario usuarioRecuperado = usuarioDao.getByEmail("filipenavas@hmail.com");

            //Cria um feedback
            Feedback feedback = new Feedback();
            //feedback.setFeeCod(0);
            feedback.setFeeDescricao("Feedback - Evento muito bom");
            feedback.setFeeUsuario(usuarioRecuperado);

            //Salva
            feedbackDao = new FeedbackDAO(getApplicationContext());
            feedbackDao.salvar(feedback);

            //Lista o usuario do banco
            List<Usuario> list = usuarioDao.listAll();
            for (Usuario objeto : list) {
                System.out.println("RESULTADO USUARIO: " + objeto.getUsuNome() + "  Cod: " + objeto.getUsuCod());
        }

            //Lista o feedback do banco
            List<Feedback> listFeedback = feedbackDao.listAll();
            for (Feedback objeto : listFeedback) {
                System.out.println("RESULTADO FEEDBACK: " + objeto.getFeeDescricao());
                System.out.println("RESULTADO FEEDBACK COD USU: " + objeto.getFeeUsuario().getUsuCod());
            }

    }


    public void chamaBuscarAtividadesActivity (View view){

        Intent buscarAtividadesActivity = new Intent(this, BuscarAtividadesActivity.class);
        startActivity(buscarAtividadesActivity);
    }

    public void chamaCadastrarParticipantesActivity (View view){

        Intent cadastrarParticipantesActivity = new Intent(this, CadastraParticipanteActivity.class);
        startActivity(cadastrarParticipantesActivity);
    }

    public void chamaAlterarInformacoesPessoais(View view) {
        Intent alteraInfo = new Intent(this, AlteraInformacoesPessoais.class);
        startActivity(alteraInfo);

    }

    public void chamaMeuPerfil(View view) {
        Intent meuPerfil = new Intent(this, PerfilActivity.class);
        startActivity(meuPerfil);
    }
/*
    public void chamaMostraAtividadeActivity (View view){
        Intent mostraAtividadeActivity = new Intent(this, MostraAtividadeActivity.class);
        startActivity(mostraAtividadeActivity);
   }
*/


}
