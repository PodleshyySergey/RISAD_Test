package ru.risad.test.PIRRoadRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class K_EditOPWStateInFormFDA extends TestBase {

    @Test
    public void testEditOPWStateInFormFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadRepair"); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
        //      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR("ФКУ Упрдор «Енисей»", "Республика Тыва", "Устройство защитных слоев", "11", "22", "33", "44", "24", startYear);
        addAndFillCorrection("На вновь начинаемый объект", "09.02.2020", "123");
        fillSubArticleFirst("226", "Разработка проектной документации", "200");
        createObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "12", "111", "21", "222", "Ремонт 12.12.2012", "444", "1 категоря");
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

//        Редактирование ОПР под учетной записью с ролью "пользователь ФДА"
        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadRepair");
        openEditObject(nameObject);
        editTopFormOPRPIR("Устройство поверхностной обработки", "12", "32", "43", "45", "26", startYear);
        addAndFillCorrection("Перераспределение средств с", "09.02.2022", "1321");
        editSubArticleFirst("226","Прочие затраты", "333");
        editObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "21", "222", "32", "333", "Ремонт 12.12.2014", "555", "0 категоря");
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();

    }

}
