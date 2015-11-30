package br.edu.ifsp.stmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.modelo.AreaConhecimento;

public class ListaParticipanteFiltroActivity extends AppCompatActivity {

    private AreaConhecimentoDAO dao;
    private Spinner spAreaConhecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_participante_filtro);

        dao = new AreaConhecimentoDAO(getApplicationContext());
        preencherAreaConhecimento();
    }
    private void  preencherAreaConhecimento(){
        List<AreaConhecimento> areaConhecimento= dao.listAll();
        try {
            ArrayAdapter<AreaConhecimento> adapter = new ArrayAdapter<AreaConhecimento>(this,
                    android.R.layout.simple_list_item_1,areaConhecimento);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAreaConhecimento.setAdapter(adapter);
        } catch (Exception ex) {
            //exibirMensagem("Erro: " + ex.getMessage());
        }

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
    public int filtrar(String Nome,String Email){
              return 0;
    }
}
