package ru.maximivanov.ege_helper;

import java.util.ArrayList;

public class User {
    private static ArrayList<Byte> userSubjectsId = new ArrayList<>();
    private Statistic userStatistic;
    public static boolean isInitialised = false;

    public static byte getSubjectsLen() {
        return (byte) userSubjectsId.size();
    }

    public static Subject getSubject(byte userSubId) {
        return SubjectsList.getSubject(userSubjectsId.get(userSubId));
    }

    public static void setUserSubjectsId(ArrayList<Byte> subjectsId) {
        userSubjectsId.addAll(subjectsId);
        isInitialised = true;
    }
}
