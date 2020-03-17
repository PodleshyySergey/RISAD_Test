package ru.risad.test.PIRRoadFullRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class A_CreateProgramWorkFKUinPIRRoadFullRepair extends TestBase {

    @Test
    public void testCreateProgramWorkFKU() {
        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear(startYear);                                     //Нужно указывать год, в котором программа работ еще не была создана
        choiseProgramWork("PIRRoadFullRepair");      // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
        createProgramWork();
        logout();
    }
}
