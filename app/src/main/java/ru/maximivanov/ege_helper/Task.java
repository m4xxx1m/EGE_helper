package ru.maximivanov.ege_helper;

import android.graphics.Bitmap;

// класс работает с конкретными заданиями, из которых состоит тест
public class Task extends Subject {
    public byte taskNum;
    private final String name;
    private final String taskText;
    private Bitmap taskImage;
    private final String answer;
    private final boolean hasImage;
    protected boolean isRight;
    protected String userAnswer = "";

    public String getAnswer() {
        return answer;
    }

    public Task(byte id, byte taskNum, boolean hasImage, String name, String taskText, String answer) {
        super(id);
        this.taskNum = taskNum;
        this.hasImage = hasImage;
        this.name = name;
        this.taskText = taskText;
        this.answer = answer;
    }

    public String getTaskText() {
        return taskText;
    }

    public String getName() {
        return name;
    }

    public boolean hasImage() {
        // change!
        return hasImage;
    }
}
