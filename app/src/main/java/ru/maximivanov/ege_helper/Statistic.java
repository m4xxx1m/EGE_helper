package ru.maximivanov.ege_helper;

import java.util.ArrayList;

// Данный класс хранит статистику пользователя
public class Statistic {
    private final ArrayList<Test> testResults = new ArrayList<>(); // результаты прошедших тестов

    public int testResultsSize() {
        return testResults.size();
    }

    public void addTest(Test test) {
        testResults.add(test);
    }

    public Test getTest(int num) {
        if (testResults.size() < num + 1)
            return null;
        return testResults.get(num);
    }

    public Test getLastTest() {
        if (testResults.isEmpty())
            return null;
        return getTest(testResults.size()-1);
    }

    public ArrayList<Task> getBadTask() {
        // метод возвращает задания, которые пока что плохо даются польлзователю
        if (this.getLastTest() == null) {
            return null;
        }
        int min = Integer.MAX_VALUE;
        ArrayList<Task> badTasks = new ArrayList<>();
        for (byte i = 0; i < User.getSubjectsLen(); ++i) {
            Subject sub = User.getSubject(i);
            for (int score : sub.tasksAnswersScore) {
                if (score < min) {
                    min = score;
                }
            }
        }
        for (byte i = 0; i < User.getSubjectsLen(); ++i) {
            Subject sub = User.getSubject(i);
            for (byte j = 0; j < sub.tasksAnswersScore.length; ++j) {
                if (sub.tasksAnswersScore[j] == min) {
                    badTasks.add(new Task(sub.id, (byte) (j + 1)));
                }
            }
        }
        return badTasks;
    }
}
