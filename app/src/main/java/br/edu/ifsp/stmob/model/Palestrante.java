package br.edu.ifsp.stmob.model;

/**
 * Created by Acer on 02/11/2015.
 *
 * Módulo Inscrições
 *
 */
public class Palestrante {

    private String nomePalestrante;
    private String apresentacao;
    private String lattes;

    public String getNomePalestrante() {
        return nomePalestrante;
    }

    public void setNomePalestrante(String nomePalestrante) {
        this.nomePalestrante = nomePalestrante;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }
}
