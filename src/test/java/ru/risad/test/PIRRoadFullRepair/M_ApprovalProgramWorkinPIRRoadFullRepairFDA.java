package ru.risad.test.PIRRoadFullRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

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
