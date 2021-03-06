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
public class AlteraParticipanteActivity extends AppCompatActivity {

    private Usuario u;
    private UsuarioDAO dao;
    private EditText cadastraNome;
    private EditText cadastraEmail;
    private EditText cadastraSenha;
    private EditText cadastraTelefone;
    private Spinner cadastraTipo;
    private String[] tipo = { "Estudante", "Professor" };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_participante);

        cadastraNome = (EditText) findViewById(R.id.cadastraNome);
        cadastraEmail = (EditText) findViewById(R.id.cadastraEmail);
        cadastraSenha = (EditText) findViewById(R.id.cadastraSenha);
        cadastraTelefone = (EditText) findViewById(R.id.cadastraTelefone);
        cadastraTipo = (Spinner) findViewById(R.id.cadastraTipo);

       dao = new UsuarioDAO(getApplicationContext());
    }

    public void salvar(View v)
    {
        u.setUsuNome(cadastraNome.getText().toString());
        u.setUsuEmail(cadastraEmail.getText().toString());
        u.setUsuSenha(cadastraSenha.getText().toString());
        u.setUsuTelefone(cadastraTelefone.getText().toString());
        u.setUsuTipo(tipo[cadastraTipo.getSelectedItemPosition()]
                .equalsIgnoreCase("Estudante") ? "Estudante" : "Professor");

        dao.atualizar(u);
        exibirMensagem("Usuário atualizado com sucesso!");

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


    /*
    * Método que preenche dados do usuário no formulário de alteração
    */
    private void preecherDados(Usuario u) {
        cadastraNome.setText(u.getUsuNome());
        cadastraEmail.setText(u.getUsuEmail());
        cadastraTelefone.setText(u.getUsuTelefone());
        cadastraTipo.setSelection(u.getUsuTipo().equalsIgnoreCase("") ? 0 : 1);
    }
}