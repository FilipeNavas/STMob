package br.edu.ifsp.stmob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by Fernando on 03/11/2015.
 */
public class CadastraParticipanteActivity extends AppCompatActivity {

    private Usuario u;
    private UsuarioDAO dao;
    private List<Usuario> usuarios;
    private EditText cadastraCod;
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

       // cadastraCod = (EditText) findViewById(R.id.cadastraCod);
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

        //System.out.println("DADOS:" + u.getUsuNome() + " - " + u.getUsuEmail());
        if (operacao.equalsIgnoreCase("Novo"))
        {
            dao.salvar(u);
            exibirMensagem("Participante cadastrado com sucesso!");
        }
        else
        {
            dao.atualizar(u);
            exibirMensagem("Participante atualizado com sucesso!");
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
        //cadastraCod.setText("");
        cadastraNome.setText("");
        cadastraEmail.setText("");
        cadastraSenha.setText("");
        cadastraTelefone.setText("");
        cadastraTipo.setSelection(0);
    }


    private AdapterView.OnItemClickListener selecionarParticipante = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id)
        {
            operacao = new String("Atualizar");
            u = usuarios.get(pos);
            preecherDados(u);
            }
        };


    // Preenche dados do usuário no caso de Atualização
    private void preecherDados(Usuario usuario)
    {
        cadastraCod.setText(String.valueOf(usuario.getUsuCod()));
        cadastraNome.setText(usuario.getUsuNome());
        cadastraEmail.setText(usuario.getUsuEmail());
        cadastraTelefone.setText(usuario.getUsuTelefone());
        cadastraSenha.setText(usuario.getUsuSenha());
        cadastraTipo.setSelection(usuario.getUsuTipo().equalsIgnoreCase("Estudante") ? 0 : 1);
    }


    // Seta mensagens de retorno
    private void exibirMensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }


    public void voltarTelaInicial(View view)
    {
        Intent telaInicial = new Intent(this, MainActivity.class);
        startActivity(telaInicial);
    }

}
