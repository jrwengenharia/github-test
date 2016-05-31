package com.aula.atividades.datatotable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

	// TABLE INFORMATTION

	public static final String TABLE_TIPO = "tipo_compromissos";
	public static final String CAMPO_TIPO_DESC = "tipo_desc";
	public static final String CAMPO_TIPO_COD = "tipo_codigo";

    public static final String TABLE_EVENTO = "evento";
    public static final String CAMPO_EVENTO_COD = "evento_cod";
    public static final String CAMPO_EVENTO_LOCAL = "evento_local";
    public static final String CAMPO_EVENTO_TIPO_COMPROMISSO = "tipo_compromisso";
    public static final String CAMPO_EVENTO_PARTICIPANTES = "evento_participantes";
    public static final String CAMPO_EVENTO_DATA_INI = "evento_data";
	public static final String CAMPO_EVENTO_BOOL_REPETICAO = "status_repeticao";

	public static final String TABLE_COPIA_EVENTOS = "eventos_replica";
	public static final String CAMPO_COPIA_EVENTOS_COD = "eventos_cod";
	public static final String CAMPO_COPIA_EVENTOS_FK = "evento_fk";
	public static final String CAMPO_COPIA_EVENTOS_DATA = "eventos_data";


	public static final String TABLE_REPETICOES = "tipo_repeticoes";
	public static final String CAMPO_REPETICAO_COD = "repeticao_cod";
	public static final String CAMPO_REPETICAO_DESC = "repeticao_desc";



	// DATABASE INFORMATION
	static final String DB_NAME = "agenda";
	static final int DB_VERSION = 1;

	// TABLE CREATION STATEMENT

    private static final String CREATE_TABLE_TIPOS="create table "+TABLE_TIPO +"("
            + CAMPO_TIPO_COD +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CAMPO_TIPO_DESC+" TEXT NOT NULL);";

	private static final String CREATE_TABLE_REPETICOES="create table "+TABLE_REPETICOES +"("
			+ CAMPO_REPETICAO_COD +" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ CAMPO_REPETICAO_DESC+" TEXT NOT NULL);";

	private static final String INSERTS_REPETICOES="INSERT INTO "+TABLE_REPETICOES+"("+CAMPO_REPETICAO_COD+","+CAMPO_REPETICAO_DESC+") VALUES (1,'Diariamente'),(2,'Semanalmente'),(3,'Mensalmente'),(4,'Anualmente');";

    public static final String CREATE_TABLE_EVENTO=" create table "+TABLE_EVENTO+"("
            +CAMPO_EVENTO_COD+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CAMPO_EVENTO_DATA_INI+" TIMESTAMP not null,"
            +CAMPO_EVENTO_LOCAL+" TEXT,"
            +CAMPO_EVENTO_TIPO_COMPROMISSO+" INTEGER NOT NULL,"
            +CAMPO_EVENTO_PARTICIPANTES+" TEXT,"
			+CAMPO_EVENTO_BOOL_REPETICAO+" INTEGER NOT NULL);";

	public static final String CREATE_TABLE_EVENTOS_COPIA=" create table "+TABLE_COPIA_EVENTOS+"("
			+CAMPO_COPIA_EVENTOS_COD+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+CAMPO_COPIA_EVENTOS_FK+" INTEGER NOT NULL,"
			+CAMPO_COPIA_EVENTOS_DATA+" TIMESTAMP not null,);";

    public static final String FOREIGNKEYS = "PRAGMA foreign_keys = true;" +
            "ALTER TABLE "+TABLE_EVENTO+" ADD CONSTRAINT FK_TIPO_COMPROMISSO " +
			"FOREIGN KEY ("+CAMPO_EVENTO_TIPO_COMPROMISSO+") REFERENCES "+TABLE_TIPO+" ("+CAMPO_TIPO_COD+");" +
			"ALTER TABLE "+TABLE_COPIA_EVENTOS+" ADD CONSTRAINT FK_ID_EVENTO " +
			"FOREIGN KEY ("+CAMPO_COPIA_EVENTOS_FK+" REFERENCES "+TABLE_EVENTO+" ("+CAMPO_EVENTO_COD+")";

	public MyDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE_TIPOS);
        db.execSQL(CREATE_TABLE_EVENTO);
		db.execSQL(CREATE_TABLE_EVENTOS_COPIA);
		db.execSQL(CREATE_TABLE_REPETICOES);
        db.execSQL(FOREIGNKEYS);
        db.execSQL(INSERTS_REPETICOES);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPETICOES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTO);
		onCreate(db);

	}

}