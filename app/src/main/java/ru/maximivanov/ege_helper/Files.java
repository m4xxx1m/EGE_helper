package ru.maximivanov.ege_helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Comparator;

// класс для работы с файлами
// работа с файлами реализована в виде словаря, с использованием SharedReferences
public class Files {
    private static String refKey;
    private static Context context;

    private static final String DATABASE_NAME = "ru.maximivanov.ege_helper.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "statistic";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_TASKS_AMOUNT = "tasksAmount";
    private static final String COLUMN_TASKS_SCORE = "testsScore";
    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_SUBJECT = 1;
    private static final int NUM_COLUMN_TASKS_AMOUNT = 3;
    private static final int NUM_COLUMN_TASKS_SCORE = 2;

    private static SQLiteDatabase mDataBase;

    public static void initialize(Context context) {
        Files.context = context;
        refKey = context.getString(R.string.preference_file_key);
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public static void insertStatistic(int subject, int taskAmount, int testsScore) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_TASKS_AMOUNT, taskAmount);
        cv.put(COLUMN_TASKS_SCORE, testsScore);
        mDataBase.insert(TABLE_NAME, null, cv);
    }

    public static void selectStatistic() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Test> tests = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                int subId = mCursor.getInt(NUM_COLUMN_SUBJECT);
                int tasksAmount = mCursor.getInt(NUM_COLUMN_TASKS_AMOUNT);
                int testsScore = mCursor.getInt(NUM_COLUMN_TASKS_SCORE);
                Test test = new Test((byte) subId, testsScore, tasksAmount);
                User.userStatistic.addTest(test);
            } while (mCursor.moveToNext());
        }
    }

    public static void delete() {

    }

    public static void writeInt(String key, int fileContents) {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, fileContents);
        editor.apply();
    }

    public static void read() {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);

        User.isInitialized = sharedPref.getInt(keys[0], 0) != 0;

        int chosenSubjectSize = sharedPref.getInt(keys[1], 0);

        ArrayList<Byte> chosenSubject = new ArrayList<>();
        while (chosenSubjectSize > 0) {
            chosenSubject.add((byte) sharedPref.getInt(keys[2] + chosenSubjectSize, -1));
            chosenSubjectSize--;
        }
        chosenSubject.sort(Comparator.naturalOrder());
        User.initializeSubjectArray();
        User.setUserSubjectsId(chosenSubject);

//        int testsAmount = sharedPref.getInt(keys[3], 0);
        User.userStatistic = new Statistic();
//        for (int i = 0; i < testsAmount; ++i) {
//            Test test = new Test((byte) sharedPref.getInt(keys[4] + i, -1),
//                    sharedPref.getInt(keys[5] + i, 0), 100);
//            User.userStatistic.addTest(test);
//        }
        selectStatistic();
    }

    public static final String[] keys = {"User.isInitialized", "chosenSubjects.size", "chosenSubject",
        "testsAmount", "testSubId", "testsScore"};

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SUBJECT + " INTEGER, " +
                    COLUMN_TASKS_AMOUNT + " INTEGER, " +
                    COLUMN_TASKS_SCORE + " INTEGER); ";
            db.execSQL(query);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}