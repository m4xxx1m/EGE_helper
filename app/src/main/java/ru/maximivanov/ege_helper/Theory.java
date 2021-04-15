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

                names.add("Орфоэпический словник");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_Orfoep_slovnik.pdf");

                names.add("Лексика и фразеология");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_2_Leksika.pdf");

                names.add("Словарик паронимов");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Slovarik_paronimov.pdf");

                names.add("Грамматика. Морфология");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_3_grammatika.pdf");

                names.add("Грамматика. Синтаксис");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_4_sintaksis.pdf");

                names.add("Орфография");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Rus_5_orfografia.pdf");

                names.add("Видеоконсультация по подготовке к ЕГЭ по русскому языку");
                paths.add("https://www.youtube.com/watch?v=MnL1na2h8aI");
                break;
            case 1:
                names.add("Рекомендации по самостоятельной подготовке к ЕГЭ по математике");
                paths.add("http://doc.fipi.ru/o-nas/novosti/metod-rekomendatsii-dlya-vypusknikov-po-sam-podgotovke-k-ekzamenam-2020/matematika-profilnaya-ege.pdf");

                names.add("Числа, уравнения, неравенства, функции");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Mat_baz_1_Chisla.pdf");

                names.add("Практико-ориентированные задачи");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Mat_baz_2_praktik.pdf");

                names.add("Алгебра и арифметика");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Mat_baz_3_algebra.pdf");

                names.add("Вероятность");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Mat_baz_3_algebra.pdf");

                names.add("Рациональные, иррациональные, показательные, логарифмические и тригонометрические выражения");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Mat_prof_1.pdf");

                names.add("Текстовые задания");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Matem_pr_2_tekst.pdf");

                names.add("Уравнения");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Matem_pr_3%20uravnenia.pdf");

                names.add("Видеоконсультация по подготовке к ЕГЭ по математике");
                paths.add("https://www.youtube.com/watch?v=MGAO7XOz9hs");
                break;
            case 2:
                names.add("Рекомендации по самостоятельной подготовке к ЕГЭ по информатике и ИКТ");
                paths.add("http://doc.fipi.ru/o-nas/novosti/metod-rekomendatsii-dlya-vypusknikov-po-sam-podgotovke-k-ekzamenam-2020/informatika-ege.pdf");

                names.add("Информация и информационные процессы. Кодирование информации. Системы счисления");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Inf_1_informatia.pdf");

                names.add("Логические основы компьютера");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Inf_2_osnovy.pdf");

                names.add("Теория игр, игровые стратегии");
                paths.add("http://doc.fipi.ru/navigator-podgotovki/navigator-ege/Inf_3_Teoria_igr.pdf");

                names.add("Видеоконсультация по подготовке к ЕГЭ по информатике и ИКТ");
                paths.add("https://www.youtube.com/watch?v=9qXxaNSf3Og");
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
