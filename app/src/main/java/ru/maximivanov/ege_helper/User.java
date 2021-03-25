package ru.maximivanov.ege_helper;

import java.util.ArrayList;

// класс содержит информацию о пользователе
public class User {
    private static ArrayList<Byte> userSubjectsId;
    public static Statistic userStatistic;
    protected static boolean isInitialized = false;

    public static byte getSubjectsLen() {
        return (byte) userSubjectsId.size();
    }

    public static Subject getSubject(byte userSubId) {
        return SubjectsList.getSubject(userSubjectsId.get(userSubId));
    }

    public static void initializeSubjectArray() {
        userSubjectsId = new ArrayList<>();
    }

    public static void setUserSubjectsId(ArrayList<Byte> subjectsId) {
        userSubjectsId.addAll(subjectsId);
    }
}