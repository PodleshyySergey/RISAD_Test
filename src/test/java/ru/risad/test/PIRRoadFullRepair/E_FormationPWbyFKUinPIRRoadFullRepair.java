package ru.risad.test.PIRRoadFullRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class E_FormationPWbyFKUinPIRRoadFullRepair extends TestBase {

    @Test
    public void testFormationPWbyFKUinPIRRoadFullRepair() {

        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair");
        pushFomationPW();
        refreshCurrentWindow();
        logout();

    }

}
