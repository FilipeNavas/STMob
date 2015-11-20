package br.edu.ifsp.stmob.modelo;

/**
 * Created by Filipe on 2015-11-20.
 *
 * Módulo Eventos
 *
 */
public class Feedback {

    private String feeDescricao;
    private int feeCod;

    //Devido ao relacionamento entre as classes. Ver diagramas de classe.
    private Usuario feeUsuario;

    public void enviarFeedback(String feeDescricao){}

    public String getFeeDescricao() {
        return feeDescricao;
    }

    public void setFeeDescricao(String feeDescricao) {
        this.feeDescricao = feeDescricao;
    }

    public Usuario getFeeUsuario() {
        return feeUsuario;
    }

    public void setFeeUsuario(Usuario feeUsuario) {
        this.feeUsuario = feeUsuario;
    }

    public int getFeeCod() { return feeCod;}

    public void setFeeCod(int feeCod) {this.feeCod = feeCod;}
}
