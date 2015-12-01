package br.edu.ifsp.stmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import br.edu.ifsp.stmob.dao.FeedbackDAO;
import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Feedback;
import br.edu.ifsp.stmob.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        try {
            //Cria um usuario
            Usuario usuario = new Usuario();
            usuario.setUsuNome("Joao da Silva");
            usuario.setUsuEmail("joaodasilva@hotmail.com");
            //usuario.setUsuCod(0);

            //Salva no banco
            usuarioDao = new UsuarioDAO(getApplicationContext());

            usuarioDao.salvar(usuario);

            //Recupera o usuario no banco
            Usuario usuarioRecuperado = usuarioDao.getByEmail("joaodasilva@hotmail.com");

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
                System.out.println("RESULTADO USUARIO: " + objeto.getUsuNome());
            }

            //Lista o feedback do banco
            List<Feedback> listFeedback = feedbackDao.listAll();
            for (Feedback objeto : listFeedback) {
                System.out.println("RESULTADO FEEDBACK: " + objeto.getFeeDescricao());
                System.out.println("RESULTADO FEEDBACK COD USU: " + objeto.getFeeUsuario().getUsuCod());
            }

        }catch(Exception e){
            System.out.println("ERROR: " + e.getStackTrace());
        }finally{
            usuarioDao.close();
            feedbackDao.close();
        }
    }


    public void chamaBuscaAvisoActivity (View view){

        Intent buscaAvisoActivity = new Intent(this, BuscaAvisoExtraordinarioActivity.class);
        startActivity(buscaAvisoActivity);
    }

    public void chamaBuscarAtividadesActivity (View view){

        Intent buscarAtividadesActivity = new Intent(this, BuscarAtividadesActivity.class);
        startActivity(buscarAtividadesActivity);
    }

    public void chamaCadastrarParticipantesActivity (View view){

        Intent cadastrarParticipantesActivity = new Intent(this, CadastraParticipanteActivity.class);
        startActivity(cadastrarParticipantesActivity);
    }

//    public void chamaMostraAtividadeActivity (View view){
//
//        Intent mostraAtividadeActivity = new Intent(this, MostraAtividadeActivity.class);
//        startActivity(mostraAtividadeActivity);
//    }



}
