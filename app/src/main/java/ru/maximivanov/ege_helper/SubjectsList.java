package ru.maximivanov.ege_helper;

import java.util.ArrayList;

public class SubjectsList {
    private static ArrayList <Subject> subjects;
    private static ArrayList <Integer> theoryFragmentsId;

    private SubjectsList() {

    }

    public static Subject getSubject(byte id) {
        return subjects.get(id);
    }

    public static int getTheoryFragmentsId(byte subId) {
        return theoryFragmentsId.get(subId);
    }
}
