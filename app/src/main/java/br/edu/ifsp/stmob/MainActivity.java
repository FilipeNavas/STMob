package br.edu.ifsp.stmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

    public void chamaBuscaAvisoActivity (View view){

        Intent buscaAvisoActivity = new Intent(this, BuscaAvisoExtraordinarioActivity.class);
        startActivity(buscaAvisoActivity);
    }

    public void chamaBuscarAtividadesActivity (View view){

        Intent buscarAtividadesActivity = new Intent(this, BuscarAtividades.class);
        startActivity(buscarAtividadesActivity);
    }

    public void chamaCadastrarParticipantesActivity (View view){

        Intent cadastrarParticipantesActivity = new Intent(this, CadastraParticipanteActivity.class);
        startActivity(cadastrarParticipantesActivity);
    }



}
