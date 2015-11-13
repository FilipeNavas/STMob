package br.edu.ifsp.stmob.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Filipe on 2015-11-12.
 */
public class DAO<T extends Object> extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stmob_db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_AVISO_EXTRAORDINARIO = "Aviso_Extraordinario";
    public static final String TABLE_ATIVIDADE = "Atividade";
    public static final String TABLE_USUARIO = "Usuario";

    protected Context context;
    protected String[] campos;
    protected String tableName;

    private static final String CREATE_TABLE_AVISO_EXTRAORDINARIO = "CREATE TABLE Aviso_Extraordinario ( "
            + " Cod_Aviso INT primary key autoincrement NOT NULL,"
            + " Titulo VARCHAR(200),"
            + " Data DATE,"
            + " Descricao VARCHAR(600)"
            + " Horario TIME"
            + " Atividade_Cod_Atividade INT"
            + " FOREIGN KEY(Atividade_Cod_Atividade) REFERENCES Atividade(Cod_Atividade)"
            + ");";

    private static final String CREATE_TABLE_ATIVIDADE = "CREATE TABLE Atividade ( "
            + " Cod_Atividade INT primary key autoincrement NOT NULL,"
            + " Vagas_Limite INT,"
            + " Vagas_Ocupadas INT,"
            + " Titulo VARCHAR(200)"
            + " Descricao VARCHAR(600)"
            + " Horario TIME"
            + " Local VARCHAR(200)"
            + " Data DATE"
            + ");";

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE Usuario ( "
            + " Cod_Usuario INT primary key autoincrement NOT NULL,"
            + " Nome VARCHAR(200),"
            + " Email VARCHAR(200),"
            + " Senha VARCHAR(45)"
            + " Tipo VARCHAR(45)"
            + " Telefone VARCHAR(45)"
            + ");";

    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_ATIVIDADE);
        database.execSQL(CREATE_TABLE_AVISO_EXTRAORDINARIO);
        database.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ATIVIDADE);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_AVISO_EXTRAORDINARIO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USUARIO);

        onCreate(db);
    }

    protected void closeDatabase(SQLiteDatabase db)
    {
        if(db.isOpen())
            db.close();
    }
}
