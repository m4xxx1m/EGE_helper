package ru.maximivanov.ege_helper;

import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

public class Test {
    byte id;
    private ArrayList <Task> tasks;
    private int taskAmount; // количество тестовых заданий в предмете
    private int testScore = 0; // сколько баллов набрал пользователь
    private int taskNum; // при решении тестов, сосотоящих из одного задания

    public void setTaskAmount(int taskAmount) {
        this.taskAmount = taskAmount;
    }

    public Test(byte subId) {
        this.id = subId;
        taskAmount = SubjectsList.getSubject(subId).taskAmount;
        tasks = new ArrayList<>(taskAmount);
    }

    public Test(byte subId, int score, int taskAmount) {
        id = subId;
        this.taskAmount = taskAmount;
        testScore = score;
    }

    public int getTestScore() {
        return testScore;
    }

    public void incrementTestScore() {
        testScore++;
    }

    public int getTaskAmount() {
        return taskAmount;
    }

    public void commonTaskSet(FragmentManager fm) {
        for (byte i = 1; i <= taskAmount; ++i) {
            TaskFragment fragment = (TaskFragment) fm
                    .findFragmentByTag(String.valueOf(i));
            assert fragment != null;
            if (User.getSubject(id).tasksNames[i-1] != null) {
                fragment.set(i, i, User.getSubject(id).tasksNames[i-1],
                        String.valueOf(tasks.get(i-1).getTaskText()), tasks.get(i-1).getTaskImage());
            }
            else {
                fragment.set(i, i, User.getSubject(id).name,
                        String.valueOf(tasks.get(i-1).getTaskText()), tasks.get(i-1).getTaskImage());
            }
        }
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public void oneTaskSet(FragmentManager fm) {
        for (byte i = 1; i <= taskAmount; ++i) {
            TaskFragment fragment = (TaskFragment) fm
                    .findFragmentByTag(String.valueOf(i));
            assert fragment != null;
            if (User.getSubject(id).tasksNames[taskNum-1] != null) {
                fragment.set(i, taskNum, User.getSubject(id).tasksNames[taskNum-1],
                        String.valueOf(tasks.get(i-1).getTaskText()), tasks.get(i-1).getTaskImage());
            }
            else {
                fragment.set(i, taskNum, User.getSubject(id).name,
                        String.valueOf(tasks.get(i-1).getTaskText()), tasks.get(i-1).getTaskImage());
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void finish(TestActivity context) {
        // закончить выполнение теста
        User.userStatistic.addTest(this);
        Files.insertStatistic(id, taskAmount, testScore);
        Files.updateAnswerScore(id, SubjectsList.getSubject(id).tasksAnswersScore);
        Intent finishTestIntent = new Intent(context, TestFinishActivity.class);
        context.startActivity(finishTestIntent);
        context.finish();
    }
}
