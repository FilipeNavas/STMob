package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.Feedback;
import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by Filipe on 2015-11-20.
 */
public class FeedbackDAO extends DAO<Feedback> {

    private SQLiteDatabase database;

    public FeedbackDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Feedback","Descricao","Usuario_Cod_Usuario"};
        tableName = "Feedback";
        database = getWritableDatabase();

    }


    public Feedback getByCodFeedback(Integer codigo) {
        Feedback feedback = new Feedback();

        Cursor cursor = executeSelect("Cod_Feedback = ?", new String[]{String.valueOf(codigo)}, null);

        if(cursor!=null && cursor.moveToFirst())
        {
            feedback = serializeByCursor(cursor);
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }


        return feedback;
    }

    public List<Feedback> listAll() {
        List<Feedback> list = new ArrayList<Feedback>();
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

    public boolean salvar(Feedback feedback) {
        //ContentValues values = serializeContentValues(feedback);

        //Serializa para ContentValues - nao pega o codigo
        ContentValues values = new ContentValues();

        values.put("Descricao", feedback.getFeeDescricao());
        values.put("Usuario_Cod_Usuario", feedback.getFeeUsuario().getUsuCod()); //Chave Estrangeira

        if(database.insert(tableName, null, values)>0)
            return true;
        else
            return false;
    }

    public boolean deletar(Integer codigo) {
        if(database.delete(tableName, "Cod_Feedback = ?", new String[]{String.valueOf(codigo)})>0)
            return true;
        else
            return false;
    }

    public boolean atualizar(Feedback feedback) {
        ContentValues values = serializeContentValues(feedback);
        if(database.update(tableName, values, "Cod_Feedback = ?", new String[]{String.valueOf(feedback.getFeeCod())})>0)
            return true;
        else
            return false;
    }


    private Feedback serializeByCursor(Cursor cursor)
    {
        Feedback feedback = new Feedback();
        feedback.setFeeCod(cursor.getInt(0));
        feedback.setFeeDescricao(cursor.getString(1));

        //Recupera o Cod da chave estrangeira como um int
        int feeUsuarioCod = cursor.getInt(2);

        //Cria um objeto
        Usuario feeUsuario = new Usuario();

        //Seta o codigo da chave estrangeira no objeto
        feeUsuario.setUsuCod(feeUsuarioCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        feedback.setFeeUsuario(feeUsuario);

        return feedback;

    }

    private ContentValues serializeContentValues(Feedback feedback)
    {
        ContentValues values = new ContentValues();
        values.put("Cod_Feedback", feedback.getFeeCod());
        values.put("Descricao", feedback.getFeeDescricao());
        values.put("Usuario_Cod_Usuario", feedback.getFeeUsuario().getUsuCod()); //Chave Estrangeira

        return values;
    }

    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }

}