package ru.risad.test.PIRRoadRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class E_FormationPWbyFKU extends TestBase {

    @Test
    public void testFormationPWbyFKU() {

        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadRepair");
        pushFomationPW();
        refreshCurrentWindow();
        logout();

    }

}
