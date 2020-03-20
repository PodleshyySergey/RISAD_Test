package ru.risad.test;

public class Correction {
    private final String nameCorrection;
    private final String dateCorrection;
    private final String numberCorrection;

    public Correction(String nameCorrection, String dateCorrection, String numberCorrection) {
        this.nameCorrection = nameCorrection;
        this.dateCorrection = dateCorrection;
        this.numberCorrection = numberCorrection;
    }

    public String getNameCorrection() {
        return nameCorrection;
    }

    public String getDateCorrection() {
        return dateCorrection;
    }

    public String getNumberCorrection() {
        return numberCorrection;
    }
}
