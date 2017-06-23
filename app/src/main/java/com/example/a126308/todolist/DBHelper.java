package com.example.a126308.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.description;
import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by 126308 on 8/6/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lists.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_LIST = "lists";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_TODOLIST = "toDOList";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_LIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT," + COLUMN_TODOLIST + " TEXT" + ")";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");


//        for (int i = 0; i < 4; i++) {
//            ContentValues values = new ContentValues();
//            values.put(COLUMN_TITLE, + i);
//            values.put(COLUMN_TODOLIST, + i);
//            db.insert(TABLE_LIST, null, values);
//        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);

    }

    public long insertList(String title, String list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_TODOLIST, list);
        long result = db.insert(TABLE_LIST, null, values);
        db.close();

        Log.d("SQL Insert" ," " + result); //id returned, shouldn’t be -1
        return result;
    }

    public int deleteList(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_LIST, condition, args);
        db.close();

        return result;
    }

    public ArrayList<MyList> getAllList() {
        ArrayList<MyList> lists = new ArrayList<MyList>();


        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_TITLE + "," + COLUMN_TODOLIST + " FROM " + TABLE_LIST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String list = cursor.getString(2);

                MyList data = new MyList(id, title,list);
                lists.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lists;
    }
}
