package br.edu.ifsp.stmob.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by cliente on 21/10/2015.
 *
 *  Módulo Inscrições
 *
 */
public class Atividade {
    private int atvCod;
    private int atvVagasLimite;
    private int getAtvVagasOcupadas;
    private String atvTitulo;
    private String atvDescricao;
    private Time Horario;
    private String atvLocal;
    private Date atvData;
    private List<Atividade> atvList;


    public int getAtvCod() {
        return atvCod;
    }
    public void setAtvCod(int atvCod) {
        this.atvCod = atvCod;
    }

    public int getAtvVagasLimite() {
        return atvVagasLimite;
    }
    public void setAtvVagasLimite(int atvVagasLimite) {
        this.atvVagasLimite = atvVagasLimite;
    }

    public int getGetAtvVagasOcupadas() {
        return getAtvVagasOcupadas;
    }
    public void setGetAtvVagasOcupadas(int getAtvVagasOcupadas) {
        this.getAtvVagasOcupadas = getAtvVagasOcupadas;
    }

    public String getAtvTitulo() {
        return atvTitulo;
    }
    public void setAtvTitulo(String atvTitulo) {
        this.atvTitulo = atvTitulo;
    }

    public String getAtvDescricao() {
        return atvDescricao;
    }
    public void setAtvDescricao(String atvDescricao) {
        this.atvDescricao = atvDescricao;
    }

    public Time getHorario() {
        return Horario;
    }
    public void setHorario(Time horario) {
        Horario = horario;
    }

    public String getAtvLocal() {
        return atvLocal;
    }
    public void setAtvLocal(String atvLocal) {
        this.atvLocal = atvLocal;
    }

    public Date getAtvData() {
        return atvData;
    }
    public void setAtvData(Date atvData) {
        this.atvData = atvData;
    }

    public List<Atividade> buscarListaAtividade(String atvNome, String atvPales, int atvConhec){
        return atvList;
    }

    public Atividade buscarListaAtividadeEspecifica(int AtvCod){
        Atividade a= new Atividade();
        return a;
    }
    public void exibirGrade(){}

    public void emiteEmailInf(){}
}
