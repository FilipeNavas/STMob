package br.edu.ifsp.stmob.modelo;

/**
 * Created by Acer on 02/11/2015.
 *
 * Módulo Inscrições
 *
 */
public class Palestrante {

    private int pltCod;
    private String pltNome;
    private String pltApresentacao;
    private String pltLattes;

    public int getPltCod() {
        return pltCod;
    }

    public void setPltCod(int pltCod) {
        this.pltCod = pltCod;
    }

    public String getPltNome() {
        return pltNome;
    }

    public void setPltNome(String pltNome) {
        this.pltNome = pltNome;
    }

    public String getPltApresentacao() {
        return pltApresentacao;
    }

    public void setPltApresentacao(String pltApresentacao) {
        this.pltApresentacao = pltApresentacao;
    }

    public String getPltLattes() {
        return pltLattes;
    }

    public void setPltLattes(String pltLattes) {
        this.pltLattes = pltLattes;
    }
}
