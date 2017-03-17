package com.example.geetika_luthra.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.geetika_luthra.mydiary.com.example.geetika_luthra.mydiary.vo.MyDiaryVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Geetika_Luthra on 17-03-2017.
 */

public class Diary_dbHelper extends SQLiteOpenHelper {

    public static final String KEY_TITLE = "title";
    public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_CREATED = "date";

    private static final String DATABASE_NAME = "database";
    private static final String DATABASE_TABLE = "diary";
    private static final int DATABASE_VERSION = 1;

    private static final String TAG = "DiaryDbAdapter";
    //private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    Diary_dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT NOT NULL,"
                + KEY_BODY + " TEXT NOT NULL," +  KEY_CREATED + " TEXT NOT NULL" +")";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public long createDiary(String title, String body) {
        SQLiteDatabase mDb = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_BODY, body);
        Calendar calendar = Calendar.getInstance();
//        //String date = calendar.get(Calendar.YEAR) + ""
//                + calendar.get(Calendar.MONTH) + ""
//                + calendar.get(Calendar.DAY_OF_MONTH) + "";
        calendar.add(Calendar.MONTH, 1);
        String date=calendar.get(Calendar.DAY_OF_MONTH)+ "-"
                + calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.YEAR) + "";

        initialValues.put(KEY_CREATED, date);
        calendar.add(Calendar.MONTH, 1);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public int getDiaryEntryCount() {
        int count=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    public boolean deleteDiary(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor getAllNotes() {

        return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,
                KEY_BODY, KEY_CREATED }, null, null, null, null, null);
    }

    public List<MyDiaryVO> getAllDiaryEntries() {
        List<MyDiaryVO> diaryList = new ArrayList<MyDiaryVO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyDiaryVO diary  = new MyDiaryVO();
                diary.settDate((cursor.getString(3)));
                diary.setTitle(cursor.getString(1));

                // Adding diary entry to list
                diaryList.add(diary);
            } while (cursor.moveToNext());
        }

        // return diary list
        return diaryList;
    }

    public Cursor getDiary(long rowId) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,
                                KEY_BODY, KEY_CREATED }, KEY_ROWID + "=" + rowId, null, null,
                        null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public boolean updateDiary(long rowId, String title, String body) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_BODY, body);
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + ""
                + calendar.get(Calendar.MONTH) + ""
                + calendar.get(Calendar.DAY_OF_MONTH) + ""
                + calendar.get(Calendar.HOUR_OF_DAY) + ""
                + calendar.get(Calendar.MINUTE) + "";
        args.put(KEY_CREATED, created);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
