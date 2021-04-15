package ru.maximivanov.ege_helper;

import android.content.Context;

import java.util.ArrayList;

/*
    id предметов:
        0 - Русский язык
        1 - Математика профиль
        2 - Информатика
        3 - Физика
        4 - Химия
        5 - Биология
        6 - Обществознание
        7 - Литература
        8 - География
        9 - История
        10 - Английский язык
*/

// данный класс включает в себя все предметы, заранее инициализированные
public class SubjectsList {
    public static final byte subjectsAmount = 11;
    private static final ArrayList <Subject> subjects = new ArrayList<>(subjectsAmount);

    public static void setSubjects(Context context) {
        subjects.add(0, new Subject((byte) 0, context.getString(R.string.russian),
                (byte) 26));
        subjects.add(1, new Subject((byte) 1, context.getString(R.string.math_profile),
                (byte) 12));
        subjects.add(2, new Subject((byte) 2, context.getString(R.string.informatics),
                (byte) 27));
        subjects.add(3, new Subject((byte) 3, context.getString(R.string.physics),
                (byte) 26));
        subjects.add(4, new Subject((byte) 4, context.getString(R.string.chemistry),
                (byte) 29));
        subjects.add(5, new Subject((byte) 5, context.getString(R.string.biology),
                (byte) 21));
        subjects.add(6, new Subject((byte) 6, context.getString(R.string.social_studies),
                (byte) 20));
        subjects.add(7, new Subject((byte) 7, context.getString(R.string.literature),
                (byte) 7));
        subjects.add(8, new Subject((byte) 8, context.getString(R.string.geography),
                (byte) 27));
        subjects.add(9, new Subject((byte) 9, context.getString(R.string.history),
                (byte) 19));
        subjects.add(10, new Subject((byte) 10, context.getString(R.string.english),
                (byte) 29));
        for (int i = 0; i < 11; ++i) {
            subjects.get(i).setTheory();
        }
    }

    public static Subject getSubject(byte id) {
        return subjects.get(id);
    }
}
