package br.edu.ifsp.stmob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.InscricaoDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Inscricao;
import br.edu.ifsp.stmob.modelo.Palestrante;
import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by cliente on 29/11/2015.
 */
public class DetalheAtividadeActivity extends Activity {


    private PalestranteDAO plsDao;
    private AreaConhecimentoDAO arcDao;
    private AtividadeDAO atvDao;
    private InscricaoDAO insDao;
    private Atividade atividade;
    private TextView atvTit;
    private TextView atvDscr;
    private TextView atvPls;
    private TextView atvLocal;
    private TextView atvData;
    private Button inscricao;
    private Button compartilhar;
    private Button presenca;
    private Button agenda;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_atividade);
        atvDao = new AtividadeDAO(getApplicationContext());
        plsDao = new PalestranteDAO(getApplicationContext());
        atvTit= (TextView) findViewById(R.id.atvNome);
        atvDscr= (TextView) findViewById(R.id.atvDescr);
        atvPls= (TextView) findViewById(R.id.atvPales);
        insDao = new InscricaoDAO(getApplicationContext());
        atvLocal= (TextView) findViewById(R.id.atvLocal);
        atvData= (TextView) findViewById(R.id.atvData);
        atividade= new Atividade();

        atvPls.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DetalhePalestranteActivity.class);
                it.putExtra("pls",Integer.toString(atividade.getAvtPalestrante().getPltCod()));

                startActivity(it);

            }
        });

        //botoes
        inscricao = (Button) findViewById(R.id.btnInscr);
        inscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Inscricao ins = new Inscricao();
                Usuario usu = new Usuario();
                usu.setUsuCod(1);
                ins.setInsAtividade(atividade);
                ins.setInsUsuario(usu);
                Inscricao isInscrit = insDao.isInscrito(atividade.getAtvCod(), usu.getUsuCod());

                if (isInscrit.getInsCod()!=0) {
                    insDao.deletar(isInscrit.getInsCod());
                    Toast.makeText(DetalheAtividadeActivity.this, "Inscricao cancelada", Toast.LENGTH_SHORT).show();
                }else{
                    insDao.inscricao(ins);
                    Toast.makeText(DetalheAtividadeActivity.this, "Inscrito com Sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        compartilhar = (Button) findViewById(R.id.btnShare);




        presenca = (Button) findViewById(R.id.btnPresenca);
        presenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                IntentIntegrator integrator = new IntentIntegrator(DetalheAtividadeActivity.this);
                integrator.addExtra("SCAN_WIDTH", 460);
                integrator.addExtra("SCAN_HEIGHT", 500);
                integrator.addExtra("SCAN_MODE", "QR_CODE_MODE");
                //customize the prompt message before scanning
                //integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
                integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
            }
        });


        agenda = (Button) findViewById(R.id.btnAgenda);

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


    //MÉTODO DE INTEGRAÇÃO DE QRCODE
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {

                if( result.getContents().toString().equals(String.valueOf( atividade.getAtvCod()))){
                    Toast toast = Toast.makeText(getApplicationContext(),  "PRESENÇA CONFIRMADA!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),  "SUA PRESENÇA NÃO FOI CONFIRMADA!", Toast.LENGTH_LONG);
                    toast.show();
                }

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.result_failed + result.toString(), Toast.LENGTH_LONG);
                toast.show();
                //showDialog(R.string.result_failed, Bundle.EMPTY);
            }
        }
    }


}
