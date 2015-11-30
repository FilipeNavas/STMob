package br.edu.ifsp.stmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Usuario;

public class AlteraInformacoesPessoais extends AppCompatActivity {

    private Usuario u;
    private UsuarioDAO dao;
    private EditText cadastraCod;
    private EditText cadastraNome;
    private EditText cadastraEmail;
    private EditText cadastraTelefone;
    private Spinner cadastraTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_informacoes_pessoais);

        cadastraCod = (EditText) findViewById(R.id.cadastraCod);
        cadastraNome = (EditText) findViewById(R.id.cadastraNome);
        cadastraEmail = (EditText) findViewById(R.id.cadastraEmail);
        cadastraTelefone = (EditText) findViewById(R.id.cadastraTelefone);
        cadastraTipo = (Spinner) findViewById(R.id.cadastraTipo);

        //u = usuarios.get(); pega usuário pelo código setado na sessão
        preecherDados(u);// Preenche formulário com os dados do usuário
    }


    public void salvar(View v)
    {
        u.setUsuNome(cadastraNome.getText().toString());
        u.setUsuTelefone(cadastraTelefone.getText().toString());

        dao.atualizar(u);
        exibirMensagem("Sua conta foi atualizada com sucesso!");
    }


    private void preecherDados(Usuario usuario)
    {
        cadastraNome.setText(usuario.getUsuNome());
        cadastraEmail.setText(usuario.getUsuEmail());
        cadastraTelefone.setText(usuario.getUsuTelefone());
        cadastraTipo.setSelection(usuario.getUsuTipo().equalsIgnoreCase("Estudante") ? 0 : 1);

    }

    private void exibirMensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
