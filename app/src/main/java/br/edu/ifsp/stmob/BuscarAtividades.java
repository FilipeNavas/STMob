package br.edu.ifsp.stmob;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BuscarAtividades extends Activity {

    private Spinner spAreaConhecimento;
    private String[] areaConhecimento = { "Ciencias Exatas e da Terra",
            "Ciencias Biologicas",
            "Engenharias",
            "Ciencias da Saude",
            "Ciencias Agrarias",
            "Ciencias Sociais Aplicadas",
            "Ciencias Humanas",
            "Linguistica Letras e Artes",
            "Outros" };


    private void preencherAreaConhecimento() {
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, areaConhecimento);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAreaConhecimento.setAdapter(adapter);
        } catch (Exception ex) {
            exibirMensagem("Selecione uma área do conhecimento " + ex.getMessage());
        }

    }

    private void exibirMensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_atividades);
        spAreaConhecimento = (Spinner)  findViewById(R.id.spAreaConhecimento);
        preencherAreaConhecimento();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buscar_atividades, menu);
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
}
