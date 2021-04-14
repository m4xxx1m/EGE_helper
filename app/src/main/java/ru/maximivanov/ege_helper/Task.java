package ru.maximivanov.ege_helper;

import android.graphics.Bitmap;

// класс работает с конкретными заданиями, из которых состоит тест
public class Task extends Subject {
    public byte taskNum;
    private String taskText = null;
    private Bitmap taskImage = null;
    private String answer = null;
    private boolean hasImage = false;
    protected boolean isRight;
    protected String userAnswer = "";

    public String getAnswer() {
        return answer;
    }

    public Task(byte id, byte taskNum, boolean hasImage, Bitmap image, String taskText, String answer) {
        super(id);
        this.taskNum = taskNum;
        this.hasImage = hasImage;
        this.taskImage = image;
        //this.name = name;
        this.taskText = taskText;
        this.answer = answer;
    }

    public Bitmap getTaskImage() {
        return taskImage;
    }

    public Task(byte id, byte taskNum) {
        super(id);
        this.taskNum = taskNum;
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

    @Override
    public String toString() {
        return name + " - задание №" + taskNum;
    }
}
