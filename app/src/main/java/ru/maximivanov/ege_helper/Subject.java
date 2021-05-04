package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;

public class Subject {
    protected final byte id;
    protected final String name;
    public final byte taskAmount;
    private Theory theory;
    protected Integer[] tasksAnswersScore;
    protected String[] tasksNames;
    protected static final String SUBJECT = "subject";
    protected static final String IS_COMMON = "isCommon";
    protected static final String TASK_NUM = "taskNum";

    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount, null);
    }

    public Theory getTheory() {
        return theory;
    }

    public void makeCommonTest(Context context) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra(SUBJECT, id);
        toTest.putExtra(IS_COMMON, true);
        context.startActivity(toTest);
    }

    public void setTasksAnswersScore(Integer[] scoreArr) {
        if (tasksAnswersScore.length == scoreArr.length) {
            tasksAnswersScore = scoreArr;
        }
    }

    public void makeOneTaskTest(Context context, byte taskNum) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra(SUBJECT, id);
        toTest.putExtra(IS_COMMON, false);
        toTest.putExtra(TASK_NUM, taskNum);
        context.startActivity(toTest);
    }

    public Subject(byte id, String name, byte taskAmount, Context context) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
        tasksAnswersScore = new Integer[taskAmount];
        theory = new Theory(this.id);
        if (context != null)
            setTasksNames(context);
    }

    public void setTheory() { this.theory = new Theory(id); }

    private void setTasksNames(Context context) {
        switch (id) {
            case 0:
                tasksNames = context.getResources().getStringArray(R.array.task_names_russian);
                break;
            case 1:
                tasksNames = context.getResources().getStringArray(R.array.task_names_maths);
                break;
            case 2:
                tasksNames = context.getResources().getStringArray(R.array.task_names_informatics);
                break;
            case 3:
                tasksNames = context.getResources().getStringArray(R.array.task_names_physics);
                break;
            case 4:
            case 6:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                tasksNames = new String[] { context.getString(R.string.coming_soon) };
                break;
        }
    }

    public void ansScoreInc(int i) {
        if (tasksAnswersScore[i] != null)
            tasksAnswersScore[i] = tasksAnswersScore[i] + 1;
        else
            tasksAnswersScore[i] = 1;
    }

    public void ansScoreDec(int i) {
        if (tasksAnswersScore[i] != null)
            tasksAnswersScore[i] = tasksAnswersScore[i] - 1;
        else
            tasksAnswersScore[i] = -1;
    }
}
