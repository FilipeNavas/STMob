package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Inscricao;
import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by Filipe on 2015-11-20.
 */
public class InscricaoDAO extends DAO<Inscricao> {

    private SQLiteDatabase database;

    public InscricaoDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Inscricao", "Status_Presenca", "Atividade_Cod_Atividade", "Usuario_Cod_Usuario"};
        tableName = "Inscricao";
        database = getWritableDatabase();

    }


    public Inscricao getByCodInscricao(Integer codigo) {
        Inscricao inscricao = new Inscricao();

        Cursor cursor = executeSelect("Cod_Inscricao = ?", new String[]{String.valueOf(codigo)}, null);

        if (cursor != null && cursor.moveToFirst()) {
            inscricao = serializeByCursor(cursor);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }


        return inscricao;
    }

    public List<Inscricao> listAll() {
        List<Inscricao> list = new ArrayList<Inscricao>();
        Cursor cursor = executeSelect(null, null, "1");


        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(serializeByCursor(cursor));
            } while (cursor.moveToNext());


        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return list;


    }

    public boolean salvar(Inscricao inscricao) {
        //ContentValues values = serializeContentValues(inscricao);

        //Serializa para ContentValues - nao pega o codigo
        ContentValues values = new ContentValues();

        values.put("Descricao", inscricao.isInsStatusPresenca());
        values.put("Atividade_Cod_Atividade", inscricao.getInsAtividade().getAtvCod()); //Chave Estrangeira
        values.put("Usuario_Cod_Usuario", inscricao.getInsUsuario().getUsuCod()); //Chave Estrangeira

        if (database.insert(tableName, null, values) > 0)
            return true;
        else
            return false;
    }

    public boolean deletar(Integer codigo) {
        if (database.delete(tableName, "Cod_Inscricao = ?", new String[]{String.valueOf(codigo)}) > 0)
            return true;
        else
            return false;
    }

    public boolean atualizar(Inscricao inscricao) {
        ContentValues values = serializeContentValues(inscricao);
        if (database.update(tableName, values, "Cod_Inscricao = ?", new String[]{String.valueOf(inscricao.getInsCod())}) > 0)
            return true;
        else
            return false;
    }


    private Inscricao serializeByCursor(Cursor cursor) {
        Inscricao inscricao = new Inscricao();
        inscricao.setInsCod(cursor.getInt(0));
        inscricao.setInsStatusPresenca(Boolean.parseBoolean(cursor.getString(1)));

        //##ATIVIDADE##
        //Recupera o Cod da chave estrangeira como um int
        int insAtividadeCod = cursor.getInt(2);

        //Cria um objeto
        Atividade insAtividade = new Atividade();

        //Seta o codigo da chave estrangeira no objeto
        insAtividade.setAtvCod(insAtividadeCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        inscricao.setInsAtividade(insAtividade);

        //##USUARIO##
        //Recupera o Cod da chave estrangeira como um int
        int insUsuarioCod = cursor.getInt(3);

        //Cria um objeto
        Usuario insUsuario = new Usuario();

        //Seta o codigo da chave estrangeira no objeto
        insUsuario.setUsuCod(insUsuarioCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        inscricao.setInsUsuario(insUsuario);

        return inscricao;

    }

    private ContentValues serializeContentValues(Inscricao inscricao) {
        ContentValues values = new ContentValues();
        values.put("Cod_Inscricao", inscricao.getInsCod());
        values.put("Descricao", inscricao.isInsStatusPresenca());
        values.put("Atividade_Cod_Atividade", inscricao.getInsAtividade().getAtvCod()); //Chave Estrangeira
        values.put("Usuario_Cod_Usuario", inscricao.getInsUsuario().getUsuCod()); //Chave Estrangeira

        return values;
    }

    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy) {

        return database.query(tableName, campos, selection, selectionArgs, null, null, orderBy);

    }

}