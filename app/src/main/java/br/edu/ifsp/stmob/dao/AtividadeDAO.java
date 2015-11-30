package br.edu.ifsp.stmob.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.AreaConhecimento;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by Filipe on 2015-11-20.
 *
 * Created by Acer on 06/11/2015.
 */
public class AtividadeDAO extends DAO <Atividade> {

    private SQLiteDatabase database;
    private String buscaSql;

    public AtividadeDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Atividade","Vagas_Limite","Vagas_Ocupadas", "Titulo", "Descricao", "Horario", "Local", "Data", "Area_Conhecimento_Cod_Area_Conhecimento", "Palestrante_Cod_Palestrante"};
        tableName = "atividade";
        database = getWritableDatabase();

    }

    public Atividade buscarListaAtividadeEspecifica(Integer codAtividade) {
        Atividade atividade = null;

        Cursor cursor = executeSelect("Cod_atividade = ?", new String[]{String.valueOf(codAtividade)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            atividade = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }


        return atividade;
    }
    public List<Atividade> getByFiltro(String tit,int pls,int arc){
        List<Atividade> list = new ArrayList<Atividade>();
        List<String> filtro= new ArrayList<>();
        String where="";
        if(!tit.isEmpty()){
            where+="Titulo = ? ";
            filtro.add(tit);
        }
        if(pls!=0){
            if (!where.equals("")){
                where+=" and ";
            }
            where+=" Palestrante_Cod_Palestrante = ? ";
            filtro.add(Integer.toString(pls));
        }
        if(arc!=0){
            if (!where.equals("")){
                where+=" and ";
            }
            where+=" Area_Conhecimento_Cod_Area_Conhecimento = ? ";
            filtro.add(Integer.toString(arc));
        }
        Log.d("where", where);
        String[] whereArgs = new String[filtro.size()];
        whereArgs=filtro.toArray(whereArgs);


        Cursor cursor = executeSelect(where, whereArgs, null);


        if(cursor!=null && cursor.moveToFirst())
        {
            do{
                list.add(serializeByCursor(cursor));
            }while(cursor.moveToNext());


        }

        if(!cursor.isClosed())
        {
            cursor.close();
        }
        return list;
    }
    public List<Atividade> listAll() {
        List<Atividade> list = new ArrayList<Atividade>();
        Cursor cursor = executeSelect(null, null, "1");


        if(cursor!=null && cursor.moveToFirst())
        {
            do{
                list.add(serializeByCursor(cursor));
            }while(cursor.moveToNext());


        }

        if(!cursor.isClosed())
        {
            cursor.close();
        }
        return list;
    }


    private Atividade serializeByCursor(Cursor cursor)
    {
        Atividade atividade = new Atividade();
        atividade.setAtvCod(cursor.getInt(0));
        atividade.setAtvVagasLimite(cursor.getInt(1));
        atividade.setGetAtvVagasOcupadas(cursor.getInt(2));
        atividade.setAtvTitulo(cursor.getString(3));
        atividade.setAtvDescricao(cursor.getString(4));
        atividade.setAtvHorario(cursor.getString(5));
        atividade.setAtvLocal(cursor.getString(6));
        atividade.setAtvData(cursor.getString(7));

        //##AREACONHECIMENTO##
        //Recupera o Cod da chave estrangeira como um int
        int insAreaConhecimentoCod = cursor.getInt(8);

        //Cria um objeto
        AreaConhecimento atvAreaConhecimento = new AreaConhecimento();

        //Seta o codigo da chave estrangeira no objeto
        atvAreaConhecimento.setArcCod(insAreaConhecimentoCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final

        atividade.setAvtAreaConhecimento(atvAreaConhecimento);

        //##PALESTRANTE##
        //Recupera o Cod da chave estrangeira como um int
        int insPalestranteCod = cursor.getInt(9);

        //Cria um objeto
        Palestrante atvPalestrante = new Palestrante();

        //Seta o codigo da chave estrangeira no objeto
        atvPalestrante.setPltCod(insPalestranteCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        atividade.setAvtPalestrante(atvPalestrante);

        return atividade;

    }


    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }



}
