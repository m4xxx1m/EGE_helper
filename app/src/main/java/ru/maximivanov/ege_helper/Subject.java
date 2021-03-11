package ru.maximivanov.ege_helper;

public class Subject {
    protected final byte id;
    protected final String name;
    public final byte taskAmount;
    protected Theory theory;
    protected int[] tasksWrongAnswers;

    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount);
        tasksWrongAnswers = new int[taskAmount];
    }

    public Test makeTest() {
        return null;
    }

    public Subject(byte id, String name, byte taskAmount) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
        tasksWrongAnswers = new int[taskAmount];
    }

    public void setTheory() { this.theory = new Theory(id); }
}
