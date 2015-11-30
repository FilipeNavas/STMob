package br.edu.ifsp.stmob.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.stmob.modelo.AreaConhecimento;

/**
 * Created by Módulo Eventos
 */

public class AreaConhecimentoDAO extends DAO<AreaConhecimento> {

    private SQLiteDatabase database;

    public AreaConhecimentoDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Area_Conhecimento","Descricao"};
        tableName = "Area_Conhecimento";
        database = getWritableDatabase();
    }


    public AreaConhecimento getByID(Integer id) {
        AreaConhecimento areaConhecimento = null;

        Cursor cursor = executeSelect("Cod_Area_Conhecimento = ?", new String[]{String.valueOf(id)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            areaConhecimento = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }
        return areaConhecimento;
    }

    public AreaConhecimento getByDescricao(String descricao) {
        AreaConhecimento areaConhecimento = null;

        Cursor cursor = executeSelect("Descricao = ?", new String[]{String.valueOf(descricao)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            areaConhecimento = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }
        return areaConhecimento;
    }
    public List<AreaConhecimento> listAll() {
        List<AreaConhecimento> list = new ArrayList<AreaConhecimento>();
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


    private AreaConhecimento serializeByCursor(Cursor cursor)
    {
        AreaConhecimento areaConhecimento = new AreaConhecimento();
        areaConhecimento.setArcCod(cursor.getInt(0));
        areaConhecimento.setArcDescricao(cursor.getString(1));

        return areaConhecimento;
    }

    /*private ContentValues serializeContentValues(AreaConhecimento pessoa)
    {
        ContentValues values = new ContentValues();
        // values.put("id", pessoa.getId());
        values.put("titulo", pessoa.getTitulo());
        values.put("anoLancamento", pessoa.getAnoLancamento());
        values.put("genero", pessoa.getGenero());

        return values;
    }*/

    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }
}


