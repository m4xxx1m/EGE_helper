package ru.maximivanov.ege_helper;

public class Theory extends Subject {
    int fragmentLayoutId;

    public Theory(byte id) {
        super(id);
        fragmentLayoutId = SubjectsList.getTheoryFragmentsId(id);
    }

    public void showTheory() {

    }
}
