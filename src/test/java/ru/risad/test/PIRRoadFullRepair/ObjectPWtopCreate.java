package ru.risad.test.PIRRoadFullRepair;

public class ObjectPWtopCreate {
    private final String selectFKU;
    private final String selectRegion;
    private final String typeOfWork;
    private final String lengthRepairCovering;
    private final String areaRepairCovering;
    private final String coastPIR;
    private final String scopeWork;
    private final String datesCMR;
    private final String endYearWorks;

    public ObjectPWtopCreate(String selectFKU, String selectRegion, String typeOfWork, String lengthRepairCovering, String areaRepairCovering, String coastPIR, String scopeWork, String datesCMR, String endYearWorks) {
        this.selectFKU = selectFKU;
        this.selectRegion = selectRegion;
        this.typeOfWork = typeOfWork;
        this.lengthRepairCovering = lengthRepairCovering;
        this.areaRepairCovering = areaRepairCovering;
        this.coastPIR = coastPIR;
        this.scopeWork = scopeWork;
        this.datesCMR = datesCMR;
        this.endYearWorks = endYearWorks;
    }

    public String getSelectFKU() {
        return selectFKU;
    }

    public String getSelectRegion() {
        return selectRegion;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public String getLengthRepairCovering() {
        return lengthRepairCovering;
    }

    public String getAreaRepairCovering() {
        return areaRepairCovering;
    }

    public String getCoastPIR() {
        return coastPIR;
    }

    public String getScopeWork() {
        return scopeWork;
    }

    public String getDatesCMR() {
        return datesCMR;
    }

    public String getEndYearWorks() {
        return endYearWorks;
    }
}
