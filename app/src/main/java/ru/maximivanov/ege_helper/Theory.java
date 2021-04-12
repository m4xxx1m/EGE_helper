package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

public class Theory {
    private final ArrayList<String> names;
    private final ArrayList<String> paths;
    public Theory(byte id) {
        names = new ArrayList<>();
        paths = new ArrayList<>();
        switch (id) {
            case 0:
                names.add("Рекомендации по самостоятельной подготовке к ЕГЭ по русскому языку");
                paths.add("http://doc.fipi.ru/o-nas/novosti/metod-rekomendatsii-dlya-vypusknikov-po-sam-podgotovke-k-ekzamenam-2020/russkiy-yazyk-ege.pdf");
                names.add("Фонетика");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_1_Fonetika.pdf");
                break;
            case 1:
                names.add("Рекомендации по самостоятельной подготовке к ЕГЭ по математике");
                paths.add("http://doc.fipi.ru/o-nas/novosti/metod-rekomendatsii-dlya-vypusknikov-po-sam-podgotovke-k-ekzamenam-2020/matematika-profilnaya-ege.pdf");
                break;
            case 2:
                names.add("Рекомендации по самостоятельной подготовке к ЕГЭ по информатике и ИКТ");
                paths.add("http://doc.fipi.ru/o-nas/novosti/metod-rekomendatsii-dlya-vypusknikov-po-sam-podgotovke-k-ekzamenam-2020/informatika-ege.pdf");
                break;
        }
    }

    public String getName(int num) {
        if (getSize() == 0)
            return "";
        return names.get(num);
    }

    public int getSize() {
        return names.size();
    }

    public void browseToTheory(Context context, int num) {
        // функция переносит пользователя на сайт или видео с теорией
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(paths.get(num)));
        context.startActivity(intent);
    }
}
