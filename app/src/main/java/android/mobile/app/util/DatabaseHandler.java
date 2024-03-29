package android.mobile.app.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "notificacoes";

	// Contacts table name
	private static final String TABLE_NOTIFI = "notificacao";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_DESC = "DESC";
	private static final String KEY_GRUPO = "GRUPO";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTIFI + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_DESC + " TEXT,"
				+ KEY_GRUPO + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFI);

		// Create tables again
		onCreate(db);
	}

	/**
	 * CRUD(Create, Read, Update, Delete)
	 */

	//
	void addNotificacao(BDnoti notification) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DESC, notification.getDesc()); // C
		values.put(KEY_GRUPO, notification.get_grupo()); // C

		//
		db.insert(TABLE_NOTIFI, null, values);
		db.close(); //
	}

	//
    BDnoti getDescricao(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NOTIFI, new String[] { KEY_ID,
                KEY_DESC, KEY_GRUPO}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

        BDnoti notification = new BDnoti(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));

		return notification;
	}
	

	public List<BDnoti> getAllNotification() {
		List<BDnoti> contactList = new ArrayList<BDnoti>();

		String selectQuery = "SELECT  * FROM " + TABLE_NOTIFI;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// adicionando a lista
		if (cursor.moveToFirst()) {
			do {
                BDnoti notificacao = new BDnoti();
				notificacao.setID(Integer.parseInt(cursor.getString(0)));
				notificacao.setDesc(cursor.getString(1));
				notificacao.set_grupo(cursor.getString(2));

				contactList.add(notificacao);
			} while (cursor.moveToNext());
		}


		return contactList;
	}


	public int updateNotificacao(BDnoti notification) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DESC, notification.getDesc());
		values.put(KEY_GRUPO, notification.get_grupo());


		return db.update(TABLE_NOTIFI, values, KEY_ID + " = ?",
				new String[] { String.valueOf(notification.getID()) });
	}


	public void deleteNotificacao(BDnoti notification) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NOTIFI, KEY_ID + " = ?",
				new String[] { String.valueOf(notification.getID()) });
		db.close();
	}



	public int getNotifiCont() {
		String countQuery = "SELECT  * FROM " + TABLE_NOTIFI;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();


		return cursor.getCount();
	}

}
