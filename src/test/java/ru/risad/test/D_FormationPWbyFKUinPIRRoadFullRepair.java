package ru.risad.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class D_FormationPWbyFKUinPIRRoadFullRepair extends TestBase {

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
