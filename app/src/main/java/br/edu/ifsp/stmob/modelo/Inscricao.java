package br.edu.ifsp.stmob.modelo;

/**
 * Created by cliente on 25/10/2015.
 *
 * Módulo Inscrições
 *
 */
public class Inscricao {

    private boolean insStatusPresenca;
    private int insCod;

    //Devido ao relacionamento entre Usuario e Inscricao, tem esse campo
    private Usuario insUsuario;

    private Atividade insAtividade;

    public boolean limiteVagas(int atvCod){
        return false;
    }

    public boolean conflitoHorario(int atvCod){
        return false;
    }

    public void lerQrCode(){}

    public void ativaCamera(){}

    public boolean isInsStatusPresenca() {
        return insStatusPresenca;
    }

    public void setInsStatusPresenca(boolean insStatusPresenca) {
        this.insStatusPresenca = insStatusPresenca;
    }

    public int getInsCod() {
        return insCod;
    }

    public void setInsCod(int insCod) {
        this.insCod = insCod;
    }

    public Usuario getInsUsuario() {
        return insUsuario;
    }

    public void setInsUsuario(Usuario insUsuario) {
        this.insUsuario = insUsuario;
    }

    public Atividade getInsAtividade() {
        return insAtividade;
    }

    public void setInsAtividade(Atividade insAtividade) {
        this.insAtividade = insAtividade;
    }
}
