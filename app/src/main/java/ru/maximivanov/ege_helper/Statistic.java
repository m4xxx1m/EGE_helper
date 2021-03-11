package ru.maximivanov.ege_helper;

import java.util.ArrayList;

// Данный класс хранит статистику пользователя
public class Statistic {
    private ArrayList<Test> testResults = new ArrayList<>(); // результаты прошедших тестов

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
