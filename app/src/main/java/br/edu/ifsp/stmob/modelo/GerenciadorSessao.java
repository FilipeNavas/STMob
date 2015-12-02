package br.edu.ifsp.stmob.modelo;

/**
 * Created by Filipe on 2015-12-02.
 *
 * Classe responsável pelo gerenciamento da sessão do usuário no app.
 * Ela guarda as info do usuário no SharedPreferences. (eh um esquema chave/valor)
 * Permite inserir e recuperar dados da sessão.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import br.edu.ifsp.stmob.LoginActivity;

public class GerenciadorSessao {

    // Shared Preferences
    SharedPreferences pref;

    // Editor para a Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode (modo)
    int PRIVATE_MODE = 0;

    // Sharedpref file nome (nome do arquivo da Shared Preferences)
    private static final String PREF_NAME = "STMobSharedPref";

    // Chaves da Shared Preferences
    private static final String ESTA_LOGADO = "estaLogado";

    // Nome do usuário (variável pública para ser acessada de fora)
    public static final String CHAVE_NOME = "nome";

    // Email do usuário (variável pública para ser acessada de fora)
    public static final String CHAVE_EMAIL = "email";

    // Código do usuário (variável pública para ser acessada de fora)
    public static final String CHAVE_CODIGO = "codigo";

    // Tipo do usuário (variável pública para ser acessada de fora)
    public static final String CHAVE_TIPO = "tipo";

    // Construtor
    public GerenciadorSessao(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Cria a sessão do login
     * */
    public void criarSessaoLogin(String nome, String email, String codigo, String tipo){
        // Storing login value as TRUE
        editor.putBoolean(ESTA_LOGADO, true);

        // Storing nome in pref
        editor.putString(CHAVE_NOME, nome);

        // Storing email in pref
        editor.putString(CHAVE_EMAIL, email);

        // Storing codigo in pref
        editor.putString(CHAVE_CODIGO, codigo);

        // Storing tipo do usuario in pref
        editor.putString(CHAVE_TIPO, tipo);

        // commit changes
        editor.commit();
    }

    /**
     * Checa se o usuario esta logado.
     * Caso não vai redirecionar para a activity de login.
     **/
    public void checaLogin(){
        // Check login status
        if(!this.estaLogado()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    /**
     * Pega os dados guardados
     * Retorna um HashMap
     * */
    public HashMap<String, String> pegarDadosUsuario(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user nome
        user.put(CHAVE_NOME, pref.getString(CHAVE_NOME, null));

        // user email
        user.put(CHAVE_EMAIL, pref.getString(CHAVE_EMAIL, null));

        // user codigo
        user.put(CHAVE_CODIGO, pref.getString(CHAVE_CODIGO, null));

        // user tipo
        user.put(CHAVE_TIPO, pref.getString(CHAVE_TIPO, null));

        // return user
        return user;
    }

    /**
     * Limpa os dados da sessão
     * */
    public void limpaSessao(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Checa se está logado
     * **/
    // Get Login State
    public boolean estaLogado(){
        return pref.getBoolean(ESTA_LOGADO, false);
    }
}