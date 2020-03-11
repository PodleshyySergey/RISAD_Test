package ru.risad.test;

// Generated by Selenium IDE
import org.testng.annotations.Test;

public class CreateObjectProgrammWorkinPIRRoadFullRepair extends TestBase {

    @Test
    public void atestCreateOPWbyFKUinPIRRoadFullRepair() {
        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear("2020");
        choiseProgramWork("PIRRoadFullRepair"); // "PIRRoadRepair" - еще одна программа работ, в которой сохраняются данные.
        createProgramWorkIfNotCreatedLater();
        createObjectProgramWork();
        findNameObject(nameObject);
        clickFormationOfPW();
    }

    @Test
    public void checkCreatedOPWbyFDAinPIRRoadFullRepair() {
        gotoResourse(baseURL);
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear("2020");
        choiseProgramWork("PIRRoadFullRepair");
        findNameObject(nameObject);
    }


}
