package ru.maximivanov.ege_helper;

public class Subject {
    protected final byte id;
    protected final String name;
    public final byte taskAmount;
    protected Theory theory;

    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount);
    }

    public Subject(byte id, String name, byte taskAmount) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
    }

    public void setTheory() { this.theory = new Theory(id); }
}
