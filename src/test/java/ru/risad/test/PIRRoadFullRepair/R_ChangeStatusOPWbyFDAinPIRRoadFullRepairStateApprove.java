package ru.risad.test.PIRRoadFullRepair;

import org.testng.annotations.Test;
import ru.risad.test.TestBase;

public class R_ChangeStatusOPWbyFDAinPIRRoadFullRepairStateApprove extends TestBase {

    @Test
    public void testChangeStatusOPWbyFDAinPIRRoadFullRepairStateApprove() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        gotoResourse(baseURL);
        login("baykal01", "Orator16");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair"); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR("ФКУ Упрдор «Енисей»", "Республика Тыва", "Устройство защитных слоев", "11", "22", "33", "44", "24", startYear);
        addAndFillCorrection("На вновь начинаемый объект", "09.02.2020", "123");
        fillSubArticleFirst("226", "Разработка проектной документации", "200");
        createObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "12", "111", "21", "222", "Ремонт 12.12.2012", "444", "1 категоря");
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);

        logout();


        login("fda", "vashkulat");
        choiceSection("Планирование");
        choiceYear(startYear);
        choiseProgramWork("PIRRoadFullRepair");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Принято к утверждению"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Принято к утверждению");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Принят");

        //Перевод ОПР из статуса "Принято к утверждению" в статус "Не рассмотрено"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Не рассмотрено");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Отклонено");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Принято к утверждению"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Принято к утверждению");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Принят");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Отклонено");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Не рассмотрено"
        rightClick(nameObject);
        selectContextItem("Изменить статус объекта", "Не рассмотрено");
        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        logout();

    }

}
