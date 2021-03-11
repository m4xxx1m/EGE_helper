package ru.maximivanov.ege_helper;

import android.graphics.Bitmap;

// класс работает с конкретными заданиями, из которых состоит тест
public class Task extends Subject {
    public byte taskNum;
    private String name;
    private String taskText;
    private Bitmap taskImage;
    private String answer;

    public Task(byte id, byte taskNum) {
        super(id);
        this.taskNum = taskNum;
    }

    public String getName() {
        return name;
    }

    public boolean hasImage() {
        // change!
        return false;
    }
}
