package ru.risad.test;

import org.testng.annotations.Test;

public class E_CreateOPWinPIRRoadFullRepairStateInFormFKU extends TestBase {

    @Test
    public void testCreateOPWinPIRRoadFullRepairStateInFormFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair"); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR("ФКУ Упрдор «Енисей»", "Республика Тыва", "Устройство защитных слоев", "11", "22", "33", "44", "24", startYear);
        addAndFillCorrection("На вновь начинаемый объект", "09.02.2020", "123");
        fillSubArticleFirst("226", "Разработка проектной документации", "200");
        createObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "12", "111", "21", "222", "Ремонт 12.12.2012", "444", "1 категоря");
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);

        logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair");
        findNameObject(nameObject);

    }

}
