package ru.maximivanov.ege_helper;

import android.content.Context;

import java.util.ArrayList;

// данный класс включает в себя все предметы, заранее инициализированные
public class SubjectsList {
    public static final byte subjectsAmount = 11;
    private static final ArrayList <Subject> subjects = new ArrayList<>(subjectsAmount);

    public static void setSubjects(Context context) {
        subjects.add(0, new Subject((byte) 0, context.getString(R.string.russian),
                (byte) 26, context));
        subjects.add(1, new Subject((byte) 1, context.getString(R.string.math_profile),
                (byte) 12, context));
        subjects.add(2, new Subject((byte) 2, context.getString(R.string.informatics),
                (byte) 14, context));
        subjects.add(3, new Subject((byte) 3, context.getString(R.string.physics),
                (byte) 26, context));
        subjects.add(4, new Subject((byte) 4, context.getString(R.string.chemistry),
                (byte) 29, context));
        subjects.add(5, new Subject((byte) 5, context.getString(R.string.biology),
                (byte) 21, context));
        subjects.add(6, new Subject((byte) 6, context.getString(R.string.social_studies),
                (byte) 20, context));
        subjects.add(7, new Subject((byte) 7, context.getString(R.string.literature),
                (byte) 7, context));
        subjects.add(8, new Subject((byte) 8, context.getString(R.string.geography),
                (byte) 27, context));
        subjects.add(9, new Subject((byte) 9, context.getString(R.string.history),
                (byte) 19, context));
        subjects.add(10, new Subject((byte) 10, context.getString(R.string.english),
                (byte) 29, context));
        for (int i = 0; i < 11; ++i) {
            subjects.get(i).setTheory();
        }
    }

    public static Subject getSubject(byte id) {
        return subjects.get(id);
    }
}
