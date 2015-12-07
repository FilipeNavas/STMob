package br.edu.ifsp.stmob.modelo;

import java.util.List;

/**
 * Created by cliente on 21/10/2015.
 */
public class Atividade {
    private int atvCod;
    private int atvVagasLimite;
    private int getAtvVagasOcupadas;
    private String atvTitulo;
    private String atvDescricao;
    private String atvHorario;
    private String atvLocal;
    private String atvData;
    private List<Atividade> atvList;
    //Campos devidos aos relacionamentos entre as classes
    private Palestrante avtPalestrante;
    private AreaConhecimento avtAreaConhecimento;


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

    public String getAtvHorario() {
        return atvHorario;
    }
    public void setAtvHorario(String atvHorario) {
        this.atvHorario = atvHorario;
    }

    public String getAtvLocal() {
        return atvLocal;
    }
    public void setAtvLocal(String atvLocal) {
        this.atvLocal = atvLocal;
    }

    public String getAtvData() {
        return atvData;
    }
    public void setAtvData(String atvData) {
        this.atvData = atvData;
    }

    public List<Atividade> buscarListaAtividade(String atvNome, String atvPales, int atvConhec){
        return atvList;
    }

    public Palestrante getAvtPalestrante() {
        return avtPalestrante;
    }

    public void setAvtPalestrante(Palestrante avtPalestrante) {
        this.avtPalestrante = avtPalestrante;
    }

    public AreaConhecimento getAvtAreaConhecimento() {
        return avtAreaConhecimento;
    }

    public void setAvtAreaConhecimento(AreaConhecimento avtAreaConhecimento) {
        this.avtAreaConhecimento = avtAreaConhecimento;
    }

    public Atividade buscarListaAtividadeEspecifica(int AtvCod){
        Atividade a= new Atividade();
        return a;
    }

    @Override
    public String toString() {
        return this.getAtvTitulo();
    }

    public void exibirGrade(){}

    public void emiteEmailInf(){}
}
