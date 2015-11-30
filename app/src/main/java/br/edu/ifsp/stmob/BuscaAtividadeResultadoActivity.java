package br.edu.ifsp.stmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

import br.edu.ifsp.stmob.Adapter.AtividadeListAdapter;
import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Palestrante;


public class BuscaAtividadeResultadoActivity extends AppCompatActivity {

    private AtividadeDAO atvDao;
    private PalestranteDAO plsDao;
    private AreaConhecimentoDAO arcDao;
    private TextView titulo;
    private ListView lvAtividades;
    private List<Atividade> atividades;
    private String filTit=null;
    private int filPls=0;
    private int filArc=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_atividade_resultado);
        atvDao = new AtividadeDAO(getApplicationContext());
        plsDao= new PalestranteDAO (getApplicationContext());
        arcDao = new AreaConhecimentoDAO(getApplicationContext());
        titulo= (TextView) findViewById(R.id.txtBusca);
        lvAtividades = (ListView) findViewById(R.id.listView);
        lvAtividades.setOnItemClickListener(selecionarAtividade);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.getString("atv") != null) {
                filTit=extras.getString("atv");
            }
            if (!extras.getString("pls").equals("Selecione")){
                Palestrante teste =plsDao.getByNome(extras.getString("pls"));
                filPls=teste.getPltCod();
            }
            if (!extras.getString("arc").equals("Selecione")){
                filArc=arcDao.getByDescricao(extras.getString("arc")).getArcCod();
            }
        }
        atualizarLista();

    }
    private void atualizarLista() {
        Log.d("where", "[" + filTit + "]-" + filPls + '-' + filArc);
        if((!filTit.isEmpty()) || (filPls!=0) || (filArc!=0) ){
            atividades=atvDao.getByFiltro(filTit,filPls,filArc);
        }else {
            atividades = atvDao.listAll();
        }

        if (atividades != null) {
            if (atividades.size() > 0) {
                AtividadeListAdapter atv = new AtividadeListAdapter(
                        getApplicationContext(), atividades);
                lvAtividades.setAdapter(atv);
            }else {
                titulo.setText("Nenhum Resultado Encontrado");
                titulo.setTextSize(20);
            }

        }

    }
    private OnItemClickListener selecionarAtividade = new OnItemClickListener() {

        public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
            Intent it = new Intent(getApplicationContext(), DetalheAtividadeActivity.class);

            it.putExtra("atvCod", Integer.toString(atividades.get(pos).getAtvCod()));

            startActivity(it);

        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_busca_atividade_resultado, menu);
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
