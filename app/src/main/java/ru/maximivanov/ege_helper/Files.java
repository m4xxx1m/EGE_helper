package ru.maximivanov.ege_helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

// класс для работы с файлами
public class Files {
    private static String refKey;
    private static Context context;

    private static final String DATABASE_NAME = "ru.maximivanov.ege_helper.db";
    private static final int DATABASE_VERSION = 1;

    private static final String STATISTIC_TABLE_NAME = "statistic";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SUBJECT = "subject";
    private static final String COLUMN_TASKS_AMOUNT = "tasksAmount";
    private static final String COLUMN_TASKS_SCORE = "testsScore";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_SUBJECT = 1;
    private static final int NUM_COLUMN_TASKS_AMOUNT = 2;
    private static final int NUM_COLUMN_TASKS_SCORE = 3;

    private static final String SUBJECT_TABLE_NAME = "subjects";

    private static final String SUB_COL_ID = "id";
    private static final String SUB_COL_SUB = "SubjectID";
    private static final String SUB_COL_ANSWERS_SCORE = "AnswersScore";

    private static final int NUM_SUB_COL_ID = 0;
    private static final int NUM_SUB_COL_SUB = 1;
    private static final int NUM_SUB_COL_ANSWERS_SCORE = 2;

    private static SQLiteDatabase mDataBase;

    private static final HashMap<Byte, Byte> subIds = new HashMap<>();

    public static void initialize(Context context) {
        Files.context = context;
        refKey = context.getString(R.string.preference_file_key);
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public static void insertStatistic(int subject, int taskAmount, int testsScore) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_TASKS_AMOUNT, taskAmount);
        cv.put(COLUMN_TASKS_SCORE, testsScore);
        mDataBase.insert(STATISTIC_TABLE_NAME, null, cv);
    }

    public static void insertSubjects(ArrayList<Byte> chosenSubjects) {
        byte i = 1;
        for (Byte sub : chosenSubjects) {
            ContentValues cv = new ContentValues();
            cv.put(SUB_COL_SUB, (int)sub);
            cv.put(SUB_COL_ANSWERS_SCORE, "!");
            mDataBase.insert(SUBJECT_TABLE_NAME, null, cv);
            subIds.put(sub, i);
            i++;
        }
    }

    public static void updateAnswerScore(byte subID, Integer[] scoreArr) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < scoreArr.length; ++i) {
            if (i != 0)
                str.append(" ");
            if (scoreArr[i] == null) {
                str.append("null");
            }
            else {
                str.append(scoreArr[i].toString());
            }
        }
        ContentValues cv = new ContentValues();
        cv.put(SUB_COL_SUB, (int) subID);
        cv.put(SUB_COL_ANSWERS_SCORE, str.toString());
        Log.d("debug_database", String.valueOf(
                mDataBase.update(SUBJECT_TABLE_NAME, cv, SUB_COL_ID + " = ?",
                        new String[] { String.valueOf(subIds.get(subID)) })));
    }

    public static void selectSubjects() {
        Cursor mCursor = mDataBase.query(SUBJECT_TABLE_NAME, null, null, null, null, null, null);
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            ArrayList<Byte> chosenSubject = new ArrayList<>();
            byte i = 1;
            do {
                byte subId = (byte) mCursor.getInt(NUM_SUB_COL_SUB);
                String tasksAnswersScore = mCursor.getString(NUM_SUB_COL_ANSWERS_SCORE);
                transformToIntAnswersScore(tasksAnswersScore, subId);
                chosenSubject.add(subId);
                subIds.put(subId, i);
                i++;
            } while (mCursor.moveToNext());
            User.setUserSubjectsId(chosenSubject);
        }
    }

    private static void transformToIntAnswersScore(String str, byte subID) {
        if (str != null && !str.equals("!")) {
            String[] strArr = str.split(" ");
            Integer[] scoreArr = new Integer[strArr.length];
            for (int i = 0; i < strArr.length; ++i) {
                if (strArr[i].equals("null")) {
                    scoreArr[i] = null;
                }
                else {
                    scoreArr[i] = Integer.parseInt(strArr[i]);
                }
            }
            SubjectsList.getSubject(subID).setTasksAnswersScore(scoreArr);
        }
    }

    public static void selectStatistic() {
        Cursor mCursor = mDataBase.query(STATISTIC_TABLE_NAME, null, null, null, null, null, null);
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

    public static void writeInt(String key, int fileContents) {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, fileContents);
        editor.apply();
    }

    public static void read() {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);
        User.userStatistic = new Statistic();
        User.initializeSubjectArray();

        User.isInitialized = sharedPref.getInt(keys[0], 0) != 0;
        if (!User.isInitialized) {
            return;
        }
        selectSubjects();
        selectStatistic();
    }

    public static final String[] keys = {"User.isInitialized", "chosenSubjects.size", "chosenSubject"};

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query_statistic = "CREATE TABLE " + STATISTIC_TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SUBJECT + " INTEGER, " +
                    COLUMN_TASKS_AMOUNT + " INTEGER, " +
                    COLUMN_TASKS_SCORE + " INTEGER); ";
            db.execSQL(query_statistic);

            String query_subjects = "CREATE TABLE " + SUBJECT_TABLE_NAME + " (" +
                    SUB_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SUB_COL_SUB + " INTEGER, " +
                    SUB_COL_ANSWERS_SCORE + " TEXT); ";
            db.execSQL(query_subjects);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + STATISTIC_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE_NAME);
            onCreate(db);
        }
    }
}