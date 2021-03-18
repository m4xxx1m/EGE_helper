package ru.maximivanov.ege_helper;

import java.util.ArrayList;

// Данный класс хранит статистику пользователя
public class Statistic {
    private ArrayList<Test> testResults = new ArrayList<>(); // результаты прошедших тестов

    public int testResultsSize() {
        return testResults.size();
    }

    public void addTest(Test test) {
        testResults.add(test);
    }

    public Test getTest(int num) {
        return testResults.get(num);
    }

    public Test getLastTest() {
        return getTest(testResults.size()-1);
    }

    public Task getBadTask() {
        // метод возвращает задание, которое пока что плохо дается польлзователю
        int min = Integer.MAX_VALUE;
        Task badTask = null;
//        for (byte i = 0; i < User.getSubjectsLen(); ++i) {
//            Subject thisSub = User.getSubject(i);
//            for (byte wrongIndex = 0; wrongIndex < thisSub.tasksWrongAnswers.length; ++i) {
//                if (thisSub.tasksWrongAnswers[wrongIndex] < min) {
//                    min = thisSub.tasksWrongAnswers[wrongIndex];
//                    badTask = new Task(thisSub.id, wrongIndex);
//                }
//            }
//        }
        return badTask;
    }
}
