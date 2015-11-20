package br.edu.ifsp.stmob.modelo;

import android.content.Context;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import br.edu.ifsp.stmob.dao.AvisoExtraordinarioDAO;

/**
 * Created by Filipe on 2015-10-27.
 *
 * Módulo Participantes
 *
 */
public class AvisoExtraordinario {

    private int aviCod;
    private String aviTitulo;
    private Date aviData;
    private Time aviHorario;
    private String aviDescricao;

    //Campo devido ao relacionamento entre as classes
    Atividade aviAtividade;

    public void enviarAviso(AvisoExtraordinario avisoExtraordinario){}

    //UC06: Cadastra Aviso Extraordinário (Adm)
    //Responsável: Filipe Navas
    public void cadastrarAviso(Atividade atividade, String aviTitulo, Date aviData, Time aviHorario, String aviDescricao, Context context){

        //Instancia um objeto dao
        AvisoExtraordinarioDAO dao = new AvisoExtraordinarioDAO(context);

        //Cria um campo do tipo avisoExtraordinario
        AvisoExtraordinario avisoExtraordinario = new AvisoExtraordinario();

        avisoExtraordinario.setAviAtividade(atividade);
        avisoExtraordinario.setAviTitulo(aviTitulo);
        avisoExtraordinario.setAviData(aviData);
        avisoExtraordinario.setAviHorario(aviHorario);
        avisoExtraordinario.setAviDescricao(aviDescricao);

        //Salva no BD
        dao.salvar(avisoExtraordinario);

    }

    //UC07: Altera Aviso Extraordinário (Adm)
    //Responsável: Filipe Navas
    public void alterarAviso(Atividade atividade, String aviTitulo, Date aviData, Time aviHorario, String aviDescricao, int aviCod, Context context){

        //Instancia um objeto dao
        AvisoExtraordinarioDAO dao = new AvisoExtraordinarioDAO(context);

        //Cria um campo do tipo avisoExtraordinario
        AvisoExtraordinario avisoExtraordinario = new AvisoExtraordinario();

        avisoExtraordinario.setAviCod(aviCod);
        avisoExtraordinario.setAviAtividade(atividade);
        avisoExtraordinario.setAviTitulo(aviTitulo);
        avisoExtraordinario.setAviData(aviData);
        avisoExtraordinario.setAviHorario(aviHorario);
        avisoExtraordinario.setAviDescricao(aviDescricao);

        //Altera no BD
        dao.salvar(avisoExtraordinario);

    }

    public List<AvisoExtraordinario> buscarAvisos(Atividade atividade, Date aviData, Time aviHorario, Context context){

        //Instancia um objeto dao
        AvisoExtraordinarioDAO dao = new AvisoExtraordinarioDAO(context);

        //Cria uma variavel de avisoExtraordinario
        AvisoExtraordinario avisoExtraordinario = new AvisoExtraordinario();

        //Seta os campos
        avisoExtraordinario.setAviHorario(aviHorario);
        avisoExtraordinario.setAviData(aviData);
        avisoExtraordinario.setAviAtividade(atividade);

        //Faz a busca e retorna
        return dao.buscarAvisos(avisoExtraordinario);

    }


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

    public Atividade getAviAtividade() { return aviAtividade; }

    public void setAviAtividade(Atividade aviAtividade) { this.aviAtividade = aviAtividade; }
}
