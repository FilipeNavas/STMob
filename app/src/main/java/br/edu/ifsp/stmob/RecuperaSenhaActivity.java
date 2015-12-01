package br.edu.ifsp.stmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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

        String emailUsuario = email.getText().toString();
        //System.out.println(emailUsuario);

        try
        {
            Usuario participante = usuarioDao.getByEmail(emailUsuario);

            System.out.println("Usuário: " + participante.getUsuEmail());

            if (participante.getUsuEmail() != null)
            {

                Intent emailSend = new Intent(Intent.ACTION_SEND);
                emailSend.putExtra(Intent.EXTRA_EMAIL, new String[]{participante.getUsuEmail()});
                emailSend.putExtra(Intent.EXTRA_SUBJECT, "STMob :: Recuperação de senha");
//                email.putExtra(Intent.EXTRA_TEXT, msg);
                emailSend.putExtra(
                        Intent.EXTRA_TEXT,
                        Html.fromHtml(new StringBuilder()
                                .append("Saudações, " + participante.getUsuNome() + "<br /><br />")
                                .append("Você solicitou que recuperássemos sua senha.<br /><br />")
                                .append("Seu usuário: " + participante.getUsuEmail() + "<br />")
                                .append("Sua senha: " + participante.getUsuSenha() + "<br /><br /><br />")
                        .append("---<br />Atenciosamente, <br />Equipe STMob")
                        .toString())
                );
                emailSend.setType("message/rfc822");
                startActivity(Intent.createChooser(emailSend, "Escolha um cliente de e-mail:"));

            }
            else
            {
                exibirMensagem("E-mail não encontrado em nosso sistema!");
            }
        } catch (Exception e)
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