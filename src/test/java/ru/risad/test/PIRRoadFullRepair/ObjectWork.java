package ru.risad.test.PIRRoadFullRepair;

public class ObjectWork {
    private final String discoveryFKU;
    private final String discoveryRegion;
    private final String selectRoadSection;
    private final String startRoadSectionKM;
    private final String startRoadSectionM;
    private final String endRoadSectionKM;
    private final String endRoadSectionM;
    private final String typeAndDateRepairRoad;
    private final String valueRoadTraffic;
    private final String categoryRoad;

    public ObjectWork(String discoveryFKU, String discoveryRegion, String selectRoadSection, String startRoadSectionKM, String startRoadSectionM, String endRoadSectionKM, String endRoadSectionM, String typeAndDateRepairRoad, String valueRoadTraffic, String categoryRoad) {
        this.discoveryFKU = discoveryFKU;
        this.discoveryRegion = discoveryRegion;
        this.selectRoadSection = selectRoadSection;
        this.startRoadSectionKM = startRoadSectionKM;
        this.startRoadSectionM = startRoadSectionM;
        this.endRoadSectionKM = endRoadSectionKM;
        this.endRoadSectionM = endRoadSectionM;
        this.typeAndDateRepairRoad = typeAndDateRepairRoad;
        this.valueRoadTraffic = valueRoadTraffic;
        this.categoryRoad = categoryRoad;
    }

    public String getDiscoveryFKU() {
        return discoveryFKU;
    }

    public String getDiscoveryRegion() {
        return discoveryRegion;
    }

    public String getSelectRoadSection() {
        return selectRoadSection;
    }

    public String getStartRoadSectionKM() {
        return startRoadSectionKM;
    }

    public String getStartRoadSectionM() {
        return startRoadSectionM;
    }

    public String getEndRoadSectionKM() {
        return endRoadSectionKM;
    }

    public String getEndRoadSectionM() {
        return endRoadSectionM;
    }

    public String getTypeAndDateRepairRoad() {
        return typeAndDateRepairRoad;
    }

    public String getValueRoadTraffic() {
        return valueRoadTraffic;
    }

    public String getCategoryRoad() {
        return categoryRoad;
    }
}
