package br.edu.ifsp.stmob.model;

/**
 * Created by Filipe on 2015-10-26.
 *
 *   Módulo Participantes
 *
 */
public class Usuario {

    int usuCod;
    String usuNome;
    String usuEmail;
    String usuSenha;
    String usuTipo;
    String usuTelefone;

    public void cadastrarUsuario(Usuario usuario){}

    public void logar(String usuEmail, String usuSenha){}

    public void alterarInf(String usuNome, String usuTelefone){}

    public void recuperarSenha(String usuEmail){}

    public int getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(int usuCod) {
        this.usuCod = usuCod;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public String getUsuTipo() {
        return usuTipo;
    }

    public void setUsuTipo(String usuTipo) {
        this.usuTipo = usuTipo;
    }

    public String getUsuTelefone() {
        return usuTelefone;
    }

    public void setUsuTelefone(String usuTelefone) {
        this.usuTelefone = usuTelefone;
    }
}


