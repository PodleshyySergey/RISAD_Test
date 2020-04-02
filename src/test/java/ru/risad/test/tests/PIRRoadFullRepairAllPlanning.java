package ru.risad.test.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.risad.test.model.*;

public class PIRRoadFullRepairAllPlanning extends TestBase {

//    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    ВНЕСЕНИЕ И РЕДАКТИРОВАНИЕ ТЕСТОВЫХ ДАННЫХ
//    ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    выбор года программы работ (!!! для проходжения теста создания программы работ (testCreateProgramWorkFKU)
//    необходимо указать год, в котором программа работ еще не создавалась)
    public String YearProgramWork = "2052";
    //     программа работ - Капремонт дорог ПИР
    String idProgramWork = "PIRRoadFullRepair";
    //    Учетные записи пользователей для входа в систему
    User userFKU = new User(
            "baykal01",
            "Orator16");
    User userFDA = new User(
            "fda",
            "vashkulat");
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
    Correction deleteCorr = new Correction(
            "",
            "28.03.2020",
            "333");
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

//    -------------------------------------------------------------------------------------------------------------
//    -------------------------------------------------------------------------------------------------------------

    @Test
    public void testCreateProgramWorkFKU() {
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);      //Нужно указывать год, в котором программа работ еще не была создана
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getProgramHelper().createProgramWork();
        app.getUserHelper().logout();
    }

    @Test(priority = 1)
    public void testCreateOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки,
//        и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);

        app.getUserHelper().logout();

    }

    @Test(priority = 2)
    public void testEditOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
//        addAndFillCorrection(correctionCreate);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки,
//        и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().openEditObject(app.getObjectHelper().nameObject);
        app.getObjectHelper().editTopFormOPRPIR(objectPWEdit1);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки, и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.getSubArticleHelper().editSubArticleFirst(article226PZ);
        app.getObjectHelper().editObjectWork(objectWorkEdit);
        app.getObjectHelper().saveEditOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();
    }

    @Test(priority = 3)
    public void testFormationPWbyFKU() {

        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getProgramHelper().pushFomationPW();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();

    }

    @Test(priority = 4)
    public void testCreateOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);

    }

    @Test(priority = 5)
    public void testEditOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().openEditObject(app.getObjectHelper().nameObject);
        app.getObjectHelper().editTopFormOPRPIR(objectPWEdit1);
        app.getObjectHelper().addAndFillCorrection(correctionEdit);
        app.getSubArticleHelper().editSubArticleFirst(article226PZ);
        app.getObjectHelper().editObjectWork(objectWorkEdit);
        app.getObjectHelper().saveEditOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();

    }

    @Test(priority = 6)
    public void testDeleteOPWinStateFormedByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().clickDeleteOPW(app.getObjectHelper().nameObject);
        app.getObjectHelper().fillDeleteCorrection(deleteCorr);
        app.getSessionHelper().refreshCurrentWindow();
//        Тут еще надо добавить проверку на отсутствие ОПр в гриде программы работ
        app.getUserHelper().logout();
    }

    @Test(priority = 7)
    public void testCreateOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFDA); //baykal01/Orator16
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
    }

    @Test(priority = 8)
    public void testEditOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().openEditObject(app.getObjectHelper().nameObject);
        app.getObjectHelper().editTopFormOPRPIR(objectPWEdit1);
        app.getObjectHelper().addAndFillCorrection(correctionEdit);
        app.getSubArticleHelper().editSubArticleFirst(article226PZ);
        app.getObjectHelper().editObjectWork(objectWorkEdit);
        app.getObjectHelper().saveEditOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();

    }

    @Test(priority = 9)
    public void testChangeStatusOPWinStateFormedByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Принято к утверждению"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Принят");

        //Перевод ОПР из статуса "Принято к утверждению" в статус "Не рассмотрено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Отклонено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Принято к утверждению"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Принят");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Отклонено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Не рассмотрено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();

    }

    @Test(priority = 10)
    public void testApprovalProgramWorkByFDA() {

        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getProgramHelper().pushApprovalPWbyFDA();

    }

    @Test(priority = 11)
    public void testCreateOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getProgramHelper().pushApprovalPWbyFDA();

        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();

    }

    @Test(priority = 12)
    public void testEditOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        //Редактирование ОПР
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().openEditObject(app.getObjectHelper().nameObject);
        app.getObjectHelper().editTopFormOPRPIR(objectPWEdit1);
        app.getObjectHelper().addAndFillCorrection(correctionEdit);
        app.getSubArticleHelper().editSubArticleFirst(article226PZ);
        app.getObjectHelper().editObjectWork(objectWorkEdit);
        app.getObjectHelper().saveEditOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getProgramHelper().pushApprovalPWbyFDA();

        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();


    }

    @Test(priority = 13)
    public void testCreateOPWinStateApproveByFDA() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFDA); //baykal01/Orator16
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);

        app.getUserHelper().logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getProgramHelper().pushApprovalPWbyFDA();

        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();
    }

    @Test(priority = 14)
    public void testEditOPWinStateApproveByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getUserHelper().logout();

        //Редактирование ОПР
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);
        app.getObjectHelper().openEditObject(app.getObjectHelper().nameObject);
        app.getObjectHelper().editTopFormOPRPIR(objectPWEdit1);
        app.getObjectHelper().addAndFillCorrection(correctionEdit);
        app.getSubArticleHelper().editSubArticleFirst(article226PZ);
        app.getObjectHelper().editObjectWork(objectWorkEdit);
        app.getObjectHelper().saveEditOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getUserHelper().logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getProgramHelper().pushApprovalPWbyFDA();

        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();

    }

    @Test(priority = 15)
    public void testChangeStatusOPWinStateApproveByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        app.getSessionHelper().gotoResource(baseURL);
        app.getUserHelper().login(userFKU);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        app.getObjectHelper().pushCreateOPRandSaveWindow();
        app.getObjectHelper().fillTopFormOPRPIR(objectPWCreate1);
        app.getObjectHelper().addAndFillCorrection(correctionCreate);
        app.getSubArticleHelper().fillSubArticleFirst(article226RPD);
        app.getObjectHelper().createObjectWork(objectWorkCreate);
        app.getObjectHelper().saveOPRandCloseWindow();
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().findNameObject(app.getObjectHelper().nameObject);

        app.getUserHelper().logout();


        app.getUserHelper().login(userFDA);
        app.getProgramHelper().choiceSection("Планирование");
        app.getProgramHelper().choiceYear(YearProgramWork);
        app.getProgramHelper().selectProgramWork(idProgramWork);

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Принято к утверждению"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Принят");

        //Перевод ОПР из статуса "Принято к утверждению" в статус "Не рассмотрено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Отклонено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Принято к утверждению"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Принят");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Отклонено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Не рассмотрено"
        app.getObjectHelper().rightClick(app.getObjectHelper().nameObject);
        app.getObjectHelper().selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.getSessionHelper().refreshCurrentWindow();
        app.getCheckHelper().checkStatusObject(app.getObjectHelper().nameObject, "Не рассмотрен");

        app.getUserHelper().logout();

    }

}
