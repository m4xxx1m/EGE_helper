package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

public class Test {
    byte id;
    private ArrayList <Task> tasks;
    private int taskAmount; // количество тестовых заданий в предмете
    private int testScore; // сколько баллов набрал пользователь
    private ArrayList<Integer> userAnswers;

    public Test(byte subId) {
        this.id = subId;
        taskAmount = SubjectsList.getSubject(subId).taskAmount;
        tasks = new ArrayList<>(taskAmount);
    }

    public void incrementTestScore() {
        testScore++;
    }

    public void set(FragmentManager fm) {
        for (byte i = 1; i <= taskAmount; ++i) {
            TaskFragment fragment = (TaskFragment) fm
                    .findFragmentByTag(String.valueOf(i));
            assert fragment != null;
            try {
                fragment.set(i, i, User.getSubject(id).tasksNames[i-1],
                        String.valueOf(tasks.get(i-1).getTaskText()));
            }
            catch (NullPointerException e) {
                fragment.set(i, i, User.getSubject(id).name,
                        String.valueOf(tasks.get(i-1).getTaskText()));
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
        Intent finishTestIntent = new Intent(context, TestFinishActivity.class);
        context.startActivity(finishTestIntent);
        context.finish();
    }
}
