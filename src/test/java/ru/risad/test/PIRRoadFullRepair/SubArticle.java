package ru.risad.test.PIRRoadFullRepair;

public class SubArticle {
    private final String numberArticle;
    private final String selectTypeWork;
    private final String costArticle;

    public SubArticle(String numberArticle, String selectTypeWork, String costArticle) {
        this.numberArticle = numberArticle;
        this.selectTypeWork = selectTypeWork;
        this.costArticle = costArticle;
    }

    public String getNumberArticle() {
        return numberArticle;
    }

    public String getSelectTypeWork() {
        return selectTypeWork;
    }

    public String getCostArticle() {
        return costArticle;
    }
}
