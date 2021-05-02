package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Theory {
    private String[] names;
    private String[] paths;
    private final int id;

    public Theory(byte id) {
        this.id = id;
    }

    public String getName(int num) {
        if (getSize() == 0)
            return "";
        return names[num];
    }

    public int getSize() {
        try {
            return names.length;
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public void browseToTheory(Context context, int num) {
        // функция переносит пользователя на сайт или видео с теорией
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(paths[num]));
        context.startActivity(intent);
    }

    public void initialize(Context context) {
        switch (id) {
            case 0:
                names = context.getResources().getStringArray(R.array.theory_names_russian);
                paths = context.getResources().getStringArray(R.array.theory_paths_russian);
                break;
            case 1:
                names = context.getResources().getStringArray(R.array.theory_names_maths);
                paths = context.getResources().getStringArray(R.array.theory_paths_maths);
                break;
            case 2:
                names = context.getResources().getStringArray(R.array.theory_names_informatics);
                paths = context.getResources().getStringArray(R.array.theory_paths_informatics);
                break;
            default:
                names = new String[0];
                paths = new String[0];
        }
    }
}
