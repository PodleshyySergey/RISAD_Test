package ru.risad.test;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateObjectProgrammWorkinPIRRoadFullRepair extends TestBase {

    @Test
    public void testCreateOPWbyFKUinPIRRoadFullRepair() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear("2020");
        choiseProgramWork("PIRRoadFullRepair"); // "PIRRoadRepair" - еще одна программа работ, в которой сохраняются данные.
        createProgramWorkIfNotCreatedLater();
        createObjectProgramWork();
        findNameObject(nameObject);
        clickFormationOfPW();

        logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear("2020");
        choiseProgramWork("PIRRoadFullRepair");
        findNameObject(nameObject);
    }

}
