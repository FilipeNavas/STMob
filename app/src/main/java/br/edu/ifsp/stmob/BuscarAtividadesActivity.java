package br.edu.ifsp.stmob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.AreaConhecimento;
import br.edu.ifsp.stmob.modelo.Palestrante;

public class BuscarAtividadesActivity extends Activity {

    private List<AreaConhecimento> areaConhecimento;
    private List<Palestrante> palestrante;
    private Spinner spPalestrante;
    private Spinner spAreaConhecimento;
    private EditText edNomeAtividade;
    private PalestranteDAO plsDao;
    private AreaConhecimentoDAO arcDao;
    private Button buscar;
    private Button gradeAtividadesCompleta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_atividades);
        edNomeAtividade=(EditText) findViewById(R.id.nomeAtividade);
        spPalestrante = (Spinner)  findViewById(R.id.spPalestrante);
        spAreaConhecimento = (Spinner)  findViewById(R.id.spAreaConhecimento);
        plsDao = new PalestranteDAO(getApplicationContext());
        arcDao = new AreaConhecimentoDAO(getApplicationContext());
        preencherPalestrante();
        preencherAreaConhecimento();


        //botao de Busca
        buscar = (Button) findViewById(R.id.btnEnviar);

        buscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent it = new Intent(getApplicationContext(), BuscaAtividadeResultadoActivity.class);
                it.putExtra("atv",edNomeAtividade.getText().toString());
                it.putExtra("pls",spPalestrante.getSelectedItem().toString());
                it.putExtra("arc",spAreaConhecimento.getSelectedItem().toString());
                startActivity(it);
            }
        });

        //botão de exibição de grades completa.
        gradeAtividadesCompleta = (Button) findViewById(R.id.btnGradeAtividadesCompleta);

        gradeAtividadesCompleta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent it = new Intent(getApplicationContext(), BuscaAtividadeResultadoActivity.class);
                it.putExtra("atv","");
                it.putExtra("pls", "Selecione");
                it.putExtra("arc", "Selecione");
                startActivity(it);
            }
        });

    }
    private void preencherPalestrante() {
        List <String> palestrantes= new ArrayList<>();
        palestrante=plsDao.listAll();

        palestrantes.add("Selecione");
        for(Palestrante pl:palestrante)
            palestrantes.add(pl.getPltNome());

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, palestrantes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPalestrante.setAdapter(adapter);
        } catch (Exception ex) {
            exibirMensagem("Erro: " + ex.getMessage());
        }

    }

    private void preencherAreaConhecimento() {
        areaConhecimento=arcDao.listAll();
        List <String> areaConhecimentos= new ArrayList<>();
        areaConhecimentos.add("Selecione");
        for(AreaConhecimento ac:areaConhecimento)
           areaConhecimentos.add(ac.getArcDescricao());

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, areaConhecimentos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAreaConhecimento.setAdapter(adapter);
        } catch (Exception ex) {
            exibirMensagem("Erro: " + ex.getMessage());
        }

    }
    private void exibirMensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


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
