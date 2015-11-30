package br.edu.ifsp.stmob;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by cliente on 29/11/2015.
 */
public class DetalheAtividadeActivity extends Activity {

    private AtividadeDAO atvDao;
    private PalestranteDAO plsDao;
    private AreaConhecimentoDAO arcDao;
    private Atividade atividade;
    private TextView atvTit;
    private TextView atvDscr;
    private TextView atvPls;
    private TextView atvLocal;
    private TextView atvData;
    private Button inscricao;
    private Button compartilhar;
    private Button presenca;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_atividade);
        atvDao = new AtividadeDAO(getApplicationContext());
        plsDao = new PalestranteDAO(getApplicationContext());
        atvTit= (TextView) findViewById(R.id.atvNome);
        atvDscr= (TextView) findViewById(R.id.atvDescr);
        atvPls= (TextView) findViewById(R.id.atvPales);
        atvLocal= (TextView) findViewById(R.id.atvLocal);
        atvData= (TextView) findViewById(R.id.atvData);
        atividade= new Atividade();

        //botoes
        inscricao = (Button) findViewById(R.id.btnInscr);
        compartilhar = (Button) findViewById(R.id.btnShare);
        presenca = (Button) findViewById(R.id.btnPresenca);;

    }

    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Log.d("extra", extras.getString("atvCod") );
           if (!extras.getString("atvCod").isEmpty()) {
                atividade= atvDao.buscarListaAtividadeEspecifica(Integer.parseInt(extras.getString("atvCod")));
               preencherAtividade(atividade);
            }

        }
    }

    private void preencherAtividade(Atividade atv){
        Palestrante p =plsDao.getByID(atv.getAvtPalestrante().getPltCod());
        atvTit.setText(atv.getAtvTitulo());
        atvDscr.setText(atv.getAtvDescricao());
        atvPls.setText(p.getPltNome());
        atvLocal.setText(atv.getAtvLocal());
        atvData.setText(atv.getAtvData()+" - "+  atv.getAtvHorario().substring(0,2)+':'+atv.getAtvHorario().substring(2,4));

    }

}
