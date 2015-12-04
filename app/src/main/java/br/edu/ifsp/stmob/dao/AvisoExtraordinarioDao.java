package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.AvisoExtraordinario;

/**
 * Created by Filipe on 2015-11-12.
 */
public class AvisoExtraordinarioDAO extends DAO<AvisoExtraordinario> {

    private SQLiteDatabase database;

    public AvisoExtraordinarioDAO(Context context) {
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

    //Busca o Avisos por atividade, ou data ou horario
    public List<AvisoExtraordinario> buscarAvisos(AvisoExtraordinario avisoExtraordinario) {

        List<AvisoExtraordinario> list = new ArrayList<AvisoExtraordinario>();

        //Cria o cursor
        Cursor cursor = null;

        //Se tiver a data e o horario, faz o select com eles
        if( String.valueOf(avisoExtraordinario.getAviHorario()) != null && String.valueOf(avisoExtraordinario.getAviData()) != null) {
            cursor =
                    executeSelect("Atividade_Cod_Atividade = ? AND (Data = ? AND Horario)",
                            new String[]{String.valueOf(avisoExtraordinario.getAviAtividade().getAtvCod()),
                                    String.valueOf(avisoExtraordinario.getAviData()),
                                    String.valueOf(avisoExtraordinario.getAviHorario())},
                            null);

            //Senão, faz somente com o cod da atividade
        }else{
            cursor =
                    executeSelect("Atividade_Cod_Atividade = ?",
                            new String[]{String.valueOf(avisoExtraordinario.getAviAtividade().getAtvCod())},
                            null);
        }


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
        //ContentValues values = serializeContentValues(avisoExtraordinario);

        //Serializa para ContentValues - nao pega o codigo
        ContentValues values = new ContentValues();

        values.put("Titulo", avisoExtraordinario.getAviTitulo());
        values.put("Data", String.valueOf(avisoExtraordinario.getAviData()));
        values.put("Descricao", avisoExtraordinario.getAviDescricao());
        values.put("Horario", String.valueOf(avisoExtraordinario.getAviHorario()));
        values.put("Atividade_Cod_Atividade", avisoExtraordinario.getAviAtividade().getAtvCod()); //Chave Estrangeira

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
        //avisoExtraordinario.setAviData(Date.valueOf(cursor.getString(2)));
        avisoExtraordinario.setAviData(null);
        avisoExtraordinario.setAviDescricao(cursor.getString(3));
        //avisoExtraordinario.setAviHorario(Time.valueOf(cursor.getString(4)));
        avisoExtraordinario.setAviHorario(null);

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
