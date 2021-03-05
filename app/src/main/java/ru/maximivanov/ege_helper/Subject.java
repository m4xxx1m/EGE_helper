package ru.maximivanov.ege_helper;

public class Subject {
    protected byte id;
    protected String name;
    public byte taskAmount;
    protected Theory theory;

    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount,
                SubjectsList.getSubject(id).theory);
    }

    public Subject(byte id, String name, byte taskAmount, Theory theory) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
        this.theory = theory;
    }
}
