package com.aula.atividades.datatotable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

	private MyDbHelper dbhelper;
	private Context ourcontext;
	private SQLiteDatabase database;

	public SQLController(Context c) {
		ourcontext = c;
	}

	public SQLController open() throws SQLException {
		dbhelper = new MyDbHelper(ourcontext);
		database = dbhelper.getWritableDatabase();
		return this;

	}

	public void close() {
		dbhelper.close();
	}
	public void insertTipo(String novo_tipo) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(MyDbHelper.CAMPO_TIPO_DESC, novo_tipo);
		database.insert(MyDbHelper.TABLE_TIPO, null, cv);

	}

    public void insertEvento(String data,String local,String participantes) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.CAMPO_EVENTO_DATA_INI, data);
		cv.put(MyDbHelper.CAMPO_EVENTO_LOCAL,local);
		cv.put(MyDbHelper.CAMPO_EVENTO_PARTICIPANTES,participantes);
        database.insert(MyDbHelper.TABLE_EVENTO, null, cv);
    }
    public Cursor verEventos() {
        // TODO Auto-generated method stub
        String[] allColumns = new String[] { MyDbHelper.CAMPO_EVENTO_COD, MyDbHelper.CAMPO_EVENTO_DATA_INI};

        Cursor c = database.query(MyDbHelper.TABLE_EVENTO, allColumns, null, null, null,
                null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }

	public Cursor VerTipos() {
		// TODO Auto-generated method stub
		String[] allColumns = new String[] { MyDbHelper.CAMPO_TIPO_COD, MyDbHelper.CAMPO_TIPO_DESC};

		Cursor c = database.query(MyDbHelper.TABLE_TIPO, allColumns, null, null, null,
				null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public Cursor VerRepeticoes() {
		// TODO Auto-generated method stub
		String[] allColumns = new String[] { MyDbHelper.CAMPO_REPETICAO_COD, MyDbHelper.CAMPO_REPETICAO_DESC};

		Cursor c = database.query(MyDbHelper.TABLE_REPETICOES, allColumns, null, null, null,
				null, null);

		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	public void insert_ReplicaEvento(String fk,String data) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(MyDbHelper.CAMPO_COPIA_EVENTOS_FK, fk);
		cv.put(MyDbHelper.CAMPO_COPIA_EVENTOS_DATA, data);
		database.insert(MyDbHelper.TABLE_COPIA_EVENTOS, null, cv);
	}
	public Cursor getEvento(String cod) throws SQLException
	{
		Cursor mCursor =
				database.query(true, MyDbHelper.TABLE_EVENTO, new String[] {MyDbHelper.CAMPO_EVENTO_COD, MyDbHelper.CAMPO_EVENTO_DATA_INI,MyDbHelper.CAMPO_EVENTO_LOCAL}, MyDbHelper.CAMPO_EVENTO_COD + "=" + cod, null,
						null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}




}
