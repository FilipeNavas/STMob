package br.edu.ifsp.stmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.AvisoExtraordinarioDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.AvisoExtraordinario;

public class CadastraAvisoExtraordinarioActivity extends AppCompatActivity {

    public AvisoExtraordinario aviso;
    private Atividade atividade;
    private List<AvisoExtraordinario> avisosExtraordinarios;
    private List<Atividade> atividades;
    private AvisoExtraordinarioDAO avisoDAO;
    private AtividadeDAO atividadeDAO;
    private EditText codAviso;
    private EditText txtTitulo;
    private EditText txtDescricao;
    private EditText txtDataHora;
    private Spinner spinnerAtividades;
    private ListView lvAvisos;
    private String operacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_aviso_extraordinario);

        txtTitulo = (EditText) findViewById(R.id.titulo);
        txtDescricao = (EditText) findViewById(R.id.descricao);
        spinnerAtividades = (Spinner) findViewById(R.id.spinnerAtividades);

        avisoDAO = new AvisoExtraordinarioDAO(getApplicationContext());
        atividadeDAO = new AtividadeDAO(getApplicationContext());

        preencherAtividade();

    }

    public void salvar(View v){

        aviso = new AvisoExtraordinario();

        aviso.setAviTitulo(txtTitulo.getText().toString());
        aviso.setAviDescricao(txtDescricao.getText().toString());
        aviso.setAviData(null);
        aviso.setAviHorario(null);
        aviso.setAviAtividade(atividade = (Atividade) ((Spinner) findViewById(R.id.spinnerAtividades)).getSelectedItem());

        avisoDAO.salvar(aviso);
        exibirMensagem("Aviso cadastrado com sucesso!");
        exibirAvisos();

        limparDados();

    }

    public void preencherAtividade(){

        atividades = atividadeDAO.listAll();

        ArrayAdapter<Atividade> atividadeAdapter = new ArrayAdapter<Atividade>(this, android.R.layout.simple_spinner_item, atividades);
        spinnerAtividades.setAdapter(atividadeAdapter);

    }

    public void exibirAvisos() {
        for (AvisoExtraordinario a : avisosExtraordinarios = avisoDAO.listAll()) {
            System.out.println("Titulo: " + a.getAviTitulo() + " - Descrição: " + a.getAviDescricao() + " - Data: " + a.getAviData()
                    + " - Horário: " + a.getAviHorario() + " - Atividade: " + a.getAviAtividade().getAtvCod());
        }
    }

    private void limparDados(){

        txtTitulo.setText("");
        txtDescricao.setText("");

    }

    public void chamaListaAvisos (View view){

        Intent listaAvisosActivity = new Intent(this, ListaAvisoExtraordinarioActivity.class);
        startActivity(listaAvisosActivity);

    }

    // Seta mensagens de retorno
    private void exibirMensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


}
