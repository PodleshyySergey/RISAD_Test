package ru.risad.test;

public class ObjectPWtopEdit {
    private final String typeOfWork;
    private final String lengthRepiarCovering;
    private final String areaRepiarCovering;
    private final String coastPIR;
    private final String scopeWork;
    private final String datesCMR;
    private final String endYearWorks;

    public ObjectPWtopEdit(String typeOfWork, String lengthRepiarCovering, String areaRepiarCovering, String coastPIR, String scopeWork, String datesCMR, String endYearWorks) {
        this.typeOfWork = typeOfWork;
        this.lengthRepiarCovering = lengthRepiarCovering;
        this.areaRepiarCovering = areaRepiarCovering;
        this.coastPIR = coastPIR;
        this.scopeWork = scopeWork;
        this.datesCMR = datesCMR;
        this.endYearWorks = endYearWorks;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public String getLengthRepiarCovering() {
        return lengthRepiarCovering;
    }

    public String getAreaRepiarCovering() {
        return areaRepiarCovering;
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
