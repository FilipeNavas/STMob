package br.edu.ifsp.stmob.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.edu.ifsp.stmob.R;

/**
 * Created by Filipe on 2015-11-12.
 */
public class DAO<T extends Object> extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stmob_db";
    private static final int DATABASE_VERSION = 7;

    public static final String TABLE_AVISO_EXTRAORDINARIO = "Aviso_Extraordinario";
    public static final String TABLE_ATIVIDADE = "Atividade";
    public static final String TABLE_USUARIO = "Usuario";
    public static final String TABLE_AREA_CONHECIMENTO = "Area_Conhecimento";
    public static final String TABLE_PALESTRANTE = "Palestrante";
    public static final String TABLE_FEEDBACK = "Feedback";
    public static final String TABLE_INSCRICAO = "Inscricao";

    protected Context context;
    protected String[] campos;
    protected String tableName;

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE Usuario ( "
            + " Cod_Usuario INTEGER PRIMARY KEY,"
            + " Nome VARCHAR(200),"
            + " Email VARCHAR(200),"
            + " Senha VARCHAR(45),"
            + " Tipo VARCHAR(45),"
            + " Telefone VARCHAR(45)"
            + ");";

    private static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE Feedback ( "
            + " Cod_Feedback INTEGER PRIMARY KEY,"
            + " Descricao varchar(1200),"
            + " Usuario_Cod_Usuario INTEGER,"
            + " FOREIGN KEY(Usuario_Cod_Usuario) REFERENCES Usuario(Cod_Usuario)"
            + ");";

    private static final String CREATE_TABLE_PALESTRANTE = "CREATE TABLE Palestrante ( "
            + " Cod_Palestrante INTEGER PRIMARY KEY NOT NULL,"
            + " Nome_Palestrante varchar(200),"
            + " Apresentacao varchar(200),"
            + " Lattes varchar(200))";

    private static final String CREATE_TABLE_AREA_CONHECIMENTO = "CREATE TABLE Area_Conhecimento ( "
            + " Cod_Area_Conhecimento INTEGER PRIMARY KEY NOT NULL,"
            + " Descricao varchar(200))";

    private static final String CREATE_TABLE_ATIVIDADE = "CREATE TABLE Atividade ( "
            + " Cod_Atividade INTEGER PRIMARY KEY NOT NULL,"
            + " Vagas_Limite INTEGER,"
            + " Vagas_Ocupadas INTEGER,"
            + " Titulo VARCHAR(200),"
            + " Descricao VARCHAR(600),"
            + " Horario TIME,"
            + " Local VARCHAR(200),"
            + " Data DATE,"
            + " Area_Conhecimento_Cod_Area_Conhecimento INTEGER,"
            + " Palestrante_Cod_Palestrante INTEGER,"
            + " FOREIGN KEY(Area_Conhecimento_Cod_Area_Conhecimento) REFERENCES Area_Conhecimento(Cod_Area_Conhecimento),"
            + " FOREIGN KEY(Palestrante_Cod_Palestrante) REFERENCES Palestrante(Cod_Palestrante)"
            + ");";

    private static final String CREATE_TABLE_AVISO_EXTRAORDINARIO = "CREATE TABLE Aviso_Extraordinario ( "
            + " Cod_Aviso INTEGER PRIMARY KEY NOT NULL,"
            + " Titulo VARCHAR(200),"
            + " Data DATE,"
            + " Descricao VARCHAR(600),"
            + " Horario TIME,"
            + " Atividade_Cod_Atividade INTEGER,"
            + " FOREIGN KEY(Atividade_Cod_Atividade) REFERENCES Atividade(Cod_Atividade)"
            + ");";

    private static final String CREATE_TABLE_INSCRICAO = "CREATE TABLE Inscricao ( "
            + " Cod_Inscricao INTEGER PRIMARY KEY NOT NULL,"
            + " Status_Presenca BOOLEAN,"
            + " Atividade_Cod_Atividade INTEGER,"
            + " Usuario_Cod_Usuario INTEGER,"
            + " FOREIGN KEY(Atividade_Cod_Atividade) REFERENCES Atividadde(Cod_Atividade),"
            + " FOREIGN KEY(Usuario_Cod_Usuario) REFERENCES Usuario(Cod_Usuario)"
            + ");";

    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        //Cria as tabelas
        database.execSQL(CREATE_TABLE_USUARIO);
        database.execSQL(CREATE_TABLE_FEEDBACK);
        database.execSQL(CREATE_TABLE_PALESTRANTE);
        database.execSQL(CREATE_TABLE_AREA_CONHECIMENTO);
        database.execSQL(CREATE_TABLE_ATIVIDADE);
        database.execSQL(CREATE_TABLE_AVISO_EXTRAORDINARIO);
        database.execSQL(CREATE_TABLE_INSCRICAO);

        //Busca nos resources os inserts
        String[] areaConhecimentos;
        areaConhecimentos = context.getResources().getStringArray(R.array.insertAreaConhecimentoBD);

        //Executa os inserts
        for(String ac:areaConhecimentos)
        {
            database.execSQL(ac);
        }

        String[] palestrantes;
        palestrantes = context.getResources().getStringArray(R.array.insertPalestrantesBD);

        //Executa os inserts
        for(String pl:palestrantes)
        {
            database.execSQL(pl);
        }

        String[] atividades;
        atividades = context.getResources().getStringArray(R.array.insertAtividadesBD);
        for(String at:atividades)
        {
            database.execSQL(at);
        }

        String[] usuarios;
        usuarios = context.getResources().getStringArray(R.array.insertUsuariosAdminBD);
        for(String at:usuarios)
        {
            database.execSQL(at);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_INSCRICAO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_AVISO_EXTRAORDINARIO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_ATIVIDADE);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_AREA_CONHECIMENTO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PALESTRANTE);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USUARIO);

        onCreate(db);
    }

    protected void closeDatabase(SQLiteDatabase db)
    {
        if(db.isOpen())
            db.close();
    }
}
