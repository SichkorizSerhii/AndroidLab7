package edu.nuzp.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DrugDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "drugs.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DRUGS = "drugs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public DrugDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_DRUGS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL);";
        db.execSQL(createTableQuery);

        insertDrug(db, "Парацетамол");
        insertDrug(db, "Аспірин");
        insertDrug(db,"Ібупрофен");
        insertDrug(db,"Цитрамон");
        insertDrug(db,"Анальгін");
        insertDrug(db,"Нурофєн");
        insertDrug(db,"Ношпа");
        insertDrug(db,"Амізон");
        insertDrug(db,"Терафлю");
        insertDrug(db,"Німесил");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Cursor getDrugsCursor() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_DRUGS, null, null, null, null, null, null);
    }

    private long insertDrug(SQLiteDatabase db, String drugName) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, drugName);
        return db.insert(TABLE_DRUGS, null, values);
    }
}