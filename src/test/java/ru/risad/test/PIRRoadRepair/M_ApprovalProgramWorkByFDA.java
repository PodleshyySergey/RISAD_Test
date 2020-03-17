package ru.risad.test.PIRRoadRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class M_ApprovalProgramWorkByFDA extends TestBase {

    @Test
    public void ApprovalProgramWorkByFDA() {

        gotoResourse(baseURL);
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadRepair");
        pushApprovalPWbyFDA();

    }

}
