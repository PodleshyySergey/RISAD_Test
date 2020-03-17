package ru.risad.test;

import org.testng.annotations.Test;

public class M_ApprovalProgramWorkinPIRRoadFullRepairFDA extends TestBase {

    @Test
    public void ApprovalProgramWorkinPIRRoadFullRepairFDA() {

        gotoResourse(baseURL);
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair");
        pushApprovalPWbyFDA();

    }

}
