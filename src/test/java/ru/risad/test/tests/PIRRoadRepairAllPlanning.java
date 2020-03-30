package ru.risad.test.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.risad.test.model.*;

public class PIRRoadRepairAllPlanning extends TestBase {

    //    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    ВНЕСЕНИЕ И РЕДАКТИРОВАНИЕ ТЕСТОВЫХ ДАННЫХ
//    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    выбор года программы работ (!!! для проходжения теста создания программы работ (testCreateProgramWorkFKU) необходимо указать год, в котором программа работ еще не создавалась)
    public String YearProgramWork = "2055";
//         программа работ - Ремонт дорог ПИР
    String idProgramWork = "PIRRoadRepair";
    //    Учетные записи пользователей для входа в систему
    User userFKU = new User("baykal01", "Orator16");
    User userFDA = new User("fda", "vashkulat");
    //    Тестовые данные для создания и редактирования объекта программы работ
    ObjectPWfillTop objectPWCreate1 = new ObjectPWfillTop(
            "ФКУ Упрдор «Енисей»",
            "Республика Тыва",
            "Устройство защитных слоев",
            "11",
            "22",
            "33",
            "44",
            "24",
            YearProgramWork);
    ObjectPWfillTop objectPWEdit1 = new ObjectPWfillTop(
            "",
            "",
            "Устройство поверхностной обработки",
            "12",
            "32",
            "43",
            "45",
            "26",
            YearProgramWork);
    //    Тестовые данные для добавления и редактирования корректировки
    Correction correctionCreate = new Correction(
            "На вновь начинаемый объект",
            "09.02.2020",
            "123");
    Correction correctionEdit = new Correction(
            "Перераспределение средств с",
            "19.03.2022",
            "1321");
    //    Тестовые данные для постатей
    SubArticle article226RPD = new SubArticle(
            "226",
            "Разработка проектной документации",
            "200");
    SubArticle article226PZ = new SubArticle(
            "226",
            "Прочие затраты",
            "200");
    //    Тестовые данные для создания и редактирования объекта работ (вкладка "Объекты работ")
    ObjectWork objectWorkCreate = new ObjectWork(
            "ФКУ Упрдор «Енисей»",
            "'Республика Тыва'",
            "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'",
            "12",
            "111",
            "21",
            "222",
            "Ремонт 12.12.2012",
            "444",
            "1 категоря");
    ObjectWork objectWorkEdit = new ObjectWork(
            "ФКУ Упрдор «Енисей»",
            "'Республика Тыва'",
            "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'",
            "21",
            "222",
            "32",
            "333",
            "Ремонт 12.12.2014",
            "555",
            "0 категоря");

//    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void testCreateProgramWorkFKU() {
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);                                     //Нужно указывать год, в котором программа работ еще не была создана
        selectProgramWork(idProgramWork);
        createProgramWork();
        logout();
    }

    @Test(priority = 1)
    public void testCreateOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst. Почему-то нужно открыть и закрыть окно добавления корректировки, и тогда выпадающиий список с номерами подстатей будет открываться
        driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);

        logout();

    }

    @Test(priority = 2)
    public void testEditOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
//        addAndFillCorrection(correctionCreate);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst. Почему-то нужно открыть и закрыть окно добавления корректировки, и тогда выпадающиий список с номерами подстатей будет открываться
        driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        openEditObject(nameObject);
        editTopFormOPRPIR(objectPWEdit1);

        //        Костыль для того, чтобы срабатывала функция fillSubArticleFirst. Почему-то нужно открыть и закрыть окно добавления корректировки, и тогда выпадающиий список с номерами подстатей будет открываться
        driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        editSubArticleFirst(article226PZ);
        editObjectWork(objectWorkEdit);
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();
    }

    @Test(priority = 3)
    public void testFormationPWbyFKU() {

        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        pushFomationPW();
        refreshCurrentWindow();
        logout();

    }

    @Test(priority = 4)
    public void testCreateOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        findNameObject(nameObject);

    }

    @Test(priority = 5)
    public void testEditOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        openEditObject(nameObject);
        editTopFormOPRPIR(objectPWEdit1);
        addAndFillCorrection(correctionEdit);
        editSubArticleFirst(article226PZ);
        editObjectWork(objectWorkEdit);
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();

    }

    @Test(priority = 6)
    public void testDeleteOPWinStateFormedByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        clickDeleteOPW(nameObject);
        fillDeleteCorrection();
        refreshCurrentWindow();
//        Тут еще надо добавить проверку на отсутствие ОПр в гриде программы работ
        logout();
    }

    @Test(priority = 7)
    public void testCreateOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFDA); //baykal01/Orator16
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        findNameObject(nameObject);
    }

    @Test(priority = 8)
    public void testEditOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        openEditObject(nameObject);
        editTopFormOPRPIR(objectPWEdit1);
        addAndFillCorrection(correctionEdit);
        editSubArticleFirst(article226PZ);
        editObjectWork(objectWorkEdit);
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();

    }

    @Test(priority = 9)
    public void testChangeStatusOPWinStateFormedByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

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

    @Test(priority = 10)
    public void testApprovalProgramWorkByFDA() {

        gotoResourse(baseURL);
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        pushApprovalPWbyFDA();

    }

    @Test(priority = 11)
    public void testCreateOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

        findNameObject(nameObject);
        checkStatusObject(nameObject, "Не рассмотрен");

        pushApprovalPWbyFDA();

        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        logout();

    }

    @Test(priority = 12)
    public void testEditOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        //Редактирование ОПР
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        openEditObject(nameObject);
        editTopFormOPRPIR(objectPWEdit1);
        addAndFillCorrection(correctionEdit);
        editSubArticleFirst(article226PZ);
        editObjectWork(objectWorkEdit);
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

        findNameObject(nameObject);
        checkStatusObject(nameObject, "Не рассмотрен");

        pushApprovalPWbyFDA();

        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        logout();


    }

    @Test(priority = 13)
    public void testCreateOPWinStateApproveByFDA() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFDA); //baykal01/Orator16
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);

        logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

        findNameObject(nameObject);
        checkStatusObject(nameObject, "Не рассмотрен");

        pushApprovalPWbyFDA();

        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        logout();
    }

    @Test(priority = 14)
    public void testEditOPWinStateApproveByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        gotoResourse(baseURL);
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);
        logout();

        //Редактирование ОПР
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);
        openEditObject(nameObject);
        editTopFormOPRPIR(objectPWEdit1);
        addAndFillCorrection(correctionEdit);
        editSubArticleFirst(article226PZ);
        editObjectWork(objectWorkEdit);
        saveEditOPRandCloseWindow();
        refreshCurrentWindow();
        logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

        findNameObject(nameObject);
        checkStatusObject(nameObject, "Не рассмотрен");

        pushApprovalPWbyFDA();

        refreshCurrentWindow();
        checkStatusObject(nameObject, "Не рассмотрен");

        logout();

    }

    @Test(priority = 15)
    public void testChangeStatusOPWinStateApproveByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        gotoResourse(baseURL);
        login(userFKU);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(objectPWCreate1);
        addAndFillCorrection(correctionCreate);
        fillSubArticleFirst(article226RPD);
        createObjectWork(objectWorkCreate);
        saveOPRandCloseWindow();
        refreshCurrentWindow();
        findNameObject(nameObject);

        logout();


        login(userFDA);
        choiceSection("Планирование");
        choiceYear(YearProgramWork);
        selectProgramWork(idProgramWork);

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
