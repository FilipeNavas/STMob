package br.edu.ifsp.stmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.stmob.dao.UsuarioDAO;
import br.edu.ifsp.stmob.modelo.Usuario;

public class RecuperaSenhaActivity extends AppCompatActivity {

    private Usuario u;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);

        email = (EditText) findViewById(R.id.recSenhaEmail);
    }


    /*
     * I Passo: verificar se o email está cadastrado no banco
     * II Passo: selecionar senha do banco
     * III Passo: enviar e-mail com a senha para o usuário
     *
     */
    public void recuperarSenha(View v)
    {
        UsuarioDAO usuarioDao = null;
        usuarioDao = new UsuarioDAO(getApplicationContext());

        //Usuario participante = usuarioDao.getByEmail(email.getText().toString());
        Usuario participante = usuarioDao.getByEmail("joaodasilva@hotmail.comsad");

        System.out.println("Usuário: "+ participante.getUsuEmail());

        if(participante.getUsuEmail() != null)
        {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{participante.getUsuEmail()});
            email.putExtra(Intent.EXTRA_SUBJECT, "STMob :: Recuperação de senha");
            email.putExtra(Intent.EXTRA_TEXT, "Segue sua senha: "+participante.getUsuSenha());
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Escolha um cliente de e-mail:"));
        }
        else
        {
            exibirMensagem("E-mail não encontrado em nosso sistema!");
        }
    }


    // Exibe mensagem na tela
    private void exibirMensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}