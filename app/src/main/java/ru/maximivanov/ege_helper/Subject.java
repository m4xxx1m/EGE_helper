package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;

public class Subject {
    protected final byte id;
    protected final String name;
    public final byte taskAmount;
    protected Theory theory;
    protected int[] tasksAnswersScore;
    protected String []tasksNames;
    public static final int COMMON_TEST = 0;
    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount);
        tasksAnswersScore = new int[taskAmount];
        tasksNames = new String[taskAmount];
    }

    public void makeCommonTest(Context context) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra("subject", id);
        toTest.putExtra("isCommon", true);
        context.startActivity(toTest);
    }

    public void makeOneTaskTest(Context context, byte taskNum) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra("subject", id);
        toTest.putExtra("isCommon", false);
        toTest.putExtra("taskNum", taskNum);
        context.startActivity(toTest);
    }

    public Subject(byte id, String name, byte taskAmount) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
        tasksAnswersScore = new int[taskAmount];
    }

    public void setTheory() { this.theory = new Theory(id); }
}
