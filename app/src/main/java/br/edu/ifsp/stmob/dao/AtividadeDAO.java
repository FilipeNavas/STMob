package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.AreaConhecimento;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by Filipe on 2015-11-20.
 */
public class AtividadeDAO extends DAO<Atividade> {

    private SQLiteDatabase database;

    public AtividadeDAO(Context context) {
        super(context);
        campos = new String[]{"Cod_Atividade","Vagas_Limite","Vagas_Ocupadas", "Titulo", "Descricao", "Horario", "Local", "Data", "Area_Conhecimento_Cod_Area_Conhecimento", "Palestrante_Cod_Palestrante"};
        tableName = "Atividade";
        database = getWritableDatabase();

    }


    public Atividade getByCodAtividade(Integer codigo) {
        Atividade atividade = new Atividade();

        Cursor cursor = executeSelect("Cod_Atividade = ?", new String[]{String.valueOf(codigo)}, null);

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

    public boolean salvar(Atividade atividade) {
        //ContentValues values = serializeContentValues(atividade);

        //Serializa para ContentValues - nao pega o codigo
        ContentValues values = new ContentValues();

        values.put("Vagas_Limite", atividade.getAtvVagasLimite());
        values.put("Vagas_Ocupadas", atividade.getGetAtvVagasOcupadas());
        values.put("Descricao", atividade.getAtvDescricao());
        values.put("Horario", String.valueOf(atividade.getAvtHorario()));
        values.put("Local", atividade.getAtvLocal());
        values.put("Data", String.valueOf(atividade.getAvtHorario()));
        values.put("Area_Conhecimento_Cod_Area_Conhecimento", atividade.getAvtAreaConhecimento().getArcCod()); //Chave Estrangeira
        values.put("Palestrante_Cod_Palestrante", atividade.getAvtPalestrante().getPltCod()); //Chave Estrangeira

        if(database.insert(tableName, null, values)>0)
            return true;
        else
            return false;
    }

    public boolean deletar(Integer codigo) {
        if(database.delete(tableName, "Cod_Atividade = ?", new String[]{String.valueOf(codigo)})>0)
            return true;
        else
            return false;
    }

    public boolean atualizar(Atividade atividade) {
        ContentValues values = serializeContentValues(atividade);
        if(database.update(tableName, values, "Cod_Atividade = ?", new String[]{String.valueOf(atividade.getAtvCod())})>0)
            return true;
        else
            return false;
    }


    private Atividade serializeByCursor(Cursor cursor)
    {
        Atividade atividade = new Atividade();
        atividade.setAtvCod(cursor.getInt(0));
        atividade.setAtvVagasLimite(cursor.getInt(1));
        atividade.setGetAtvVagasOcupadas(cursor.getInt(2));
        atividade.setAtvTitulo(cursor.getString(3));
        atividade.setAtvDescricao(cursor.getString(4));
        atividade.setAvtHorario(Time.valueOf(cursor.getString(5)));
        atividade.setAtvLocal(cursor.getString(6));
        atividade.setAtvData(Date.valueOf(cursor.getString(7)));
        atividade.setAtvCod(cursor.getInt(8));
        atividade.setAtvCod(cursor.getInt(9));

        //##AREACONHECIMENTO##
        //Recupera o Cod da chave estrangeira como um int
        int insAreaConhecimentoCod = cursor.getInt(10);

        //Cria um objeto
        AreaConhecimento atvAreaConhecimento = new AreaConhecimento();

        //Seta o codigo da chave estrangeira no objeto
        atvAreaConhecimento.setArcCod(insAreaConhecimentoCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        atividade.setAvtAreaConhecimento(atvAreaConhecimento);

        //##PALESTRANTE##
        //Recupera o Cod da chave estrangeira como um int
        int insPalestranteCod = cursor.getInt(11);

        //Cria um objeto
        Palestrante atvPalestrante = new Palestrante();

        //Seta o codigo da chave estrangeira no objeto
        atvPalestrante.setPltCod(insPalestranteCod);

        //Finalmente seta o objeto (chave estrangeira) no objeto final
        atividade.setAvtPalestrante(atvPalestrante);

        return atividade;

    }

    private ContentValues serializeContentValues(Atividade atividade)
    {
        ContentValues values = new ContentValues();
        values.put("Cod_Atividade", atividade.getAtvCod());
        values.put("Vagas_Limite", atividade.getAtvVagasLimite());
        values.put("Vagas_Ocupadas", atividade.getGetAtvVagasOcupadas());
        values.put("Descricao", atividade.getAtvDescricao());
        values.put("Horario", String.valueOf(atividade.getAvtHorario()));
        values.put("Local", atividade.getAtvLocal());
        values.put("Data", String.valueOf(atividade.getAvtHorario()));
        values.put("Area_Conhecimento_Cod_Area_Conhecimento", atividade.getAvtAreaConhecimento().getArcCod()); //Chave Estrangeira
        values.put("Palestrante_Cod_Palestrante", atividade.getAvtPalestrante().getPltCod()); //Chave Estrangeira

        return values;
    }

    private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
    {

        return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

    }

}