package br.edu.ifsp.stmob.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by Módulo Eventos
 */

public class PalestranteDAO extends DAO<Palestrante> {

    private SQLiteDatabase database;

    public PalestranteDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Palestrante","Nome_Palestrante","Apresentacao","Lattes"};
        tableName = "Palestrante";
        database = getWritableDatabase();
    }

    public Palestrante getByID(Integer id) {
        Palestrante palestrante = null;

        Cursor cursor = executeSelect("Cod_Palestrante = ?", new String[]{String.valueOf(id)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            palestrante = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }
        return palestrante;
    }

    public List<Palestrante> listAll() {
        List<Palestrante> list = new ArrayList<Palestrante>();
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


    private Palestrante serializeByCursor(Cursor cursor)
    {
        Palestrante palestrante = new Palestrante();
        palestrante.setPltCod(cursor.getInt(0));
        palestrante.setPltNome(cursor.getString(1));
        palestrante.setPltApresentacao(cursor.getString(2));
        palestrante.setPltLattes(cursor.getString(3));

        return palestrante;
    }



    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }
}


