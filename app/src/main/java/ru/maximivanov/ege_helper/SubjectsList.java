package ru.maximivanov.ege_helper;

import android.content.Context;

import java.util.ArrayList;

// данный класс включает в себя все предметы, заранее инициализированные
public class SubjectsList {
    private static byte subjectsAmount;
    private static final ArrayList <Subject> subjects = new ArrayList<>(subjectsAmount);

    public static byte getSubjectsAmount() {
        return subjectsAmount;
    }

    public static void setSubjects(Context context) {
        subjectsAmount = (byte) context.getResources().getInteger(R.integer.subjects_amount);
        String[] names = context.getResources().getStringArray(R.array.subject_names);
        int[] tasksAmount = context.getResources().getIntArray(R.array.subjects_task_amount);
        for (int i = 0; i < subjectsAmount; ++i) {
            subjects.add(i, new Subject((byte) i, names[i], (byte) tasksAmount[i], context));
            subjects.get(i).setTheory();
        }
    }

    public static Subject getSubject(byte id) {
        return subjects.get(id);
    }
}
