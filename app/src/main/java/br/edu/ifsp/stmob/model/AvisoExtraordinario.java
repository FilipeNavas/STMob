package br.edu.ifsp.stmob.model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Filipe on 2015-10-27.
 */
public class AvisoExtraordinario {

    int aviCod;
    String aviTitulo;
    Date aviData;
    Time aviHorario;
    String aviDescricao;

    public void enviarAviso(AvisoExtraordinario avisoExtraordinario){}

    //public void cadastrarAviso(Atividade atividade, String aviTitulo, Date aviData, Time aviHorario, String aviDescricao){}

    //public void alterarAviso(Atividade atividade, String aviTitulo, Date aviData, Time aviHorario, String aviDescricao){}

    public void excluirAviso(int aviCod){}

    public int getAviCod() {
        return aviCod;
    }

    public void setAviCod(int aviCod) {
        this.aviCod = aviCod;
    }

    public String getAviTitulo() {
        return aviTitulo;
    }

    public void setAviTitulo(String aviTitulo) {
        this.aviTitulo = aviTitulo;
    }

    public Date getAviData() {
        return aviData;
    }

    public void setAviData(Date aviData) {
        this.aviData = aviData;
    }

    public Time getAviHorario() {
        return aviHorario;
    }

    public void setAviHorario(Time aviHorario) {
        this.aviHorario = aviHorario;
    }

    public String getAviDescricao() {
        return aviDescricao;
    }

    public void setAviDescricao(String aviDescricao) {
        this.aviDescricao = aviDescricao;
    }
}
