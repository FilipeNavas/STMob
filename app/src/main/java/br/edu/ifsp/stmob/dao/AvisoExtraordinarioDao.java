package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.model.Atividade;
import br.edu.ifsp.stmob.model.AvisoExtraordinario;

/**
 * Created by Filipe on 2015-11-12.
 */
public class AvisoExtraordinarioDao extends DAO<AvisoExtraordinario> {

    private SQLiteDatabase database;



    public AvisoExtraordinarioDao(Context context) {
        super(context);
        campos = new String[]{"Cod_Aviso","Titulo","Data","Descricao","Horario","Atividade_Cod_Atividade"};
        tableName = "Aviso_Extraordinario";
        database = getWritableDatabase();

    }


    public AvisoExtraordinario getByTitulo(String titulo) {
        AvisoExtraordinario avisoExtraordinario = null;

        Cursor cursor = executeSelect("Titulo = ?", new String[]{titulo}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            avisoExtraordinario = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }


        return avisoExtraordinario;
    }

    public AvisoExtraordinario getByCodAviso(Integer codAviso) {
        AvisoExtraordinario avisoExtraordinario = null;

        Cursor cursor = executeSelect("Cod_Aviso = ?", new String[]{String.valueOf(codAviso)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            avisoExtraordinario = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }


        return avisoExtraordinario;
    }

    public List<AvisoExtraordinario> listAll() {
        List<AvisoExtraordinario> list = new ArrayList<AvisoExtraordinario>();
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

    public boolean salvar(AvisoExtraordinario avisoExtraordinario) {
        ContentValues values = serializeContentValues(avisoExtraordinario);
        if(database.insert(tableName, null, values)>0)
            return true;
        else
            return false;
    }

    public boolean deletar(Integer codAviso) {
        if(database.delete(tableName, "Cod_Aviso = ?", new String[]{String.valueOf(codAviso)})>0)
            return true;
        else
            return false;
    }

    public boolean atualizar(AvisoExtraordinario avisoExtraordinario) {
        ContentValues values = serializeContentValues(avisoExtraordinario);
        if(database.update(tableName, values, "Cod_Aviso = ?", new String[]{String.valueOf(avisoExtraordinario.getAviCod())})>0)
            return true;
        else
            return false;
    }


    private AvisoExtraordinario serializeByCursor(Cursor cursor)
    {
        AvisoExtraordinario avisoExtraordinario = new AvisoExtraordinario();
        avisoExtraordinario.setAviCod(cursor.getInt(0));
        avisoExtraordinario.setAviTitulo(cursor.getString(1));
        avisoExtraordinario.setAviData(Date.valueOf(cursor.getString(2)));
        avisoExtraordinario.setAviDescricao(cursor.getString(3));
        avisoExtraordinario.setAviHorario(Time.valueOf(cursor.getString(4)));

        //Recupera o Cod da chave estrangeira como um int
        int aviAtividadeCod = cursor.getInt(5);

        //Cria um objeto de Atividade
        Atividade aviAtividade = new Atividade();

        //Seta o codigo da atividade recuperado no banco no objeto recém criado
        aviAtividade.setAtvCod(aviAtividadeCod);

        //Finalmente seta o objeto Atividade no objeto AvisoExtraordinario
        avisoExtraordinario.setAviAtividade(aviAtividade);

        return avisoExtraordinario;

    }

    private ContentValues serializeContentValues(AvisoExtraordinario avisoExtraordinario)
    {
        ContentValues values = new ContentValues();
        values.put("Cod_Aviso", avisoExtraordinario.getAviCod());
        values.put("Titulo", avisoExtraordinario.getAviTitulo());
        values.put("Data", String.valueOf(avisoExtraordinario.getAviData()));
        values.put("Descricao", avisoExtraordinario.getAviDescricao());
        values.put("Horario", String.valueOf(avisoExtraordinario.getAviHorario()));
        values.put("Atividade_Cod_Atividade", avisoExtraordinario.getAviAtividade().getAtvCod()); //Chave Estrangeira

        return values;
    }

    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }

}
