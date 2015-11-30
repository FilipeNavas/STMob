package br.edu.ifsp.stmob;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.dao.AvisoExtraordinarioDAO;
import br.edu.ifsp.stmob.modelo.AvisoExtraordinario;

public class CadastraAvisoExtraordinarioActivity extends AppCompatActivity {

    public AvisoExtraordinario aviso;
    private List<AvisoExtraordinario> avisosExtraordinarios;
    private AvisoExtraordinarioDAO dao;
    private EditText codAviso;
    private EditText titulo;
    private EditText txtDescricao;
    private EditText txtDataHora;
    private Spinner atividade;
    private ListView lvAvisos;
    private String operacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_aviso_extraordinario);

    }


}
