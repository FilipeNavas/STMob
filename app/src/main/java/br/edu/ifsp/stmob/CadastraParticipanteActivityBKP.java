package br.edu.ifsp.stmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by Fernando on 03/11/2015.
 */
public class CadastraParticipanteActivityBKP extends AppCompatActivity {

    private Usuario u;
    private UsuarioDAO dao;
    private EditText cadastraNome;
    private EditText cadastraEmail;
    private EditText cadastraSenha;
    private EditText cadastraTelefone;
    private Spinner cadastraTipo;
    private String operacao;
    private String[] tipo = { "Estudante", "Professor" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_participante);

        cadastraNome = (EditText) findViewById(R.id.cadastraNome);
        cadastraEmail = (EditText) findViewById(R.id.cadastraEmail);
        cadastraSenha = (EditText) findViewById(R.id.cadastraSenha);
        cadastraTelefone = (EditText) findViewById(R.id.cadastraTelefone);
        cadastraTipo = (Spinner) findViewById(R.id.cadastraTipo);

        operacao = new String("Novo");
        dao = new UsuarioDAO(getApplicationContext());
    }

    public void salvar(View v)
    {
        if (operacao.equalsIgnoreCase("Novo")) {
            u = new Usuario();
        }

        u.setUsuNome(cadastraNome.getText().toString());
        u.setUsuEmail(cadastraEmail.getText().toString());
        u.setUsuSenha(cadastraSenha.getText().toString());
        u.setUsuTelefone(cadastraTelefone.getText().toString());
        u.setUsuTipo(tipo[cadastraTipo.getSelectedItemPosition()]
                .equalsIgnoreCase("Estudante") ? "Estudante" : "Professor");

        if (operacao.equalsIgnoreCase("Novo")) {
            dao.salvar(u);
            exibirMensagem("Pessoa cadastrada com sucesso!");
        }

        limparDados();
    }

    public void novo(View v)
    {
        operacao=new String("Novo");
        limparDados();
    }

    private void limparDados()
    {
        cadastraNome.setText("");
        cadastraEmail.setText("");
        cadastraSenha.setText("");
        cadastraTelefone.setText("");
        cadastraTipo.setSelection(0);
    }

    private void exibirMensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
