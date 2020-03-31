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
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);      //Нужно указывать год, в котором программа работ еще не была создана
        app.selectProgramWork(idProgramWork);
        app.createProgramWork();
        app.logout();
    }

    @Test(priority = 1)
    public void testCreateOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки,
//        и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);

        app.logout();

    }

    @Test(priority = 2)
    public void testEditOPWinStateDevelopByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
//        addAndFillCorrection(correctionCreate);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки,
//        и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.openEditObject(app.nameObject);
        app.editTopFormOPRPIR(objectPWEdit1);

//        Костыль для того, чтобы срабатывала функция fillSubArticleFirst.
//        Почему-то нужно открыть и закрыть окно добавления корректировки, и тогда выпадающиий список с номерами подстатей будет открываться
        app.driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();
        app.driver.findElement(By.xpath("//button[contains(text(),'Ok')]/../button[contains(text(),'Отмена')]")).click();

        app.editSubArticleFirst(article226PZ);
        app.editObjectWork(objectWorkEdit);
        app.saveEditOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.logout();
    }

    @Test(priority = 3)
    public void testFormationPWbyFKU() {

        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.pushFomationPW();
        app.refreshCurrentWindow();
        app.logout();

    }

    @Test(priority = 4)
    public void testCreateOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.findNameObject(app.nameObject);

    }

    @Test(priority = 5)
    public void testEditOPWinStateFormedByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.openEditObject(app.nameObject);
        app.editTopFormOPRPIR(objectPWEdit1);
        app.addAndFillCorrection(correctionEdit);
        app.editSubArticleFirst(article226PZ);
        app.editObjectWork(objectWorkEdit);
        app.saveEditOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.logout();

    }

    @Test(priority = 6)
    public void testDeleteOPWinStateFormedByFKU() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.clickDeleteOPW(app.nameObject);
        app.fillDeleteCorrection(deleteCorr);
        app.refreshCurrentWindow();
//        Тут еще надо добавить проверку на отсутствие ОПр в гриде программы работ
        app.logout();
    }

    @Test(priority = 7)
    public void testCreateOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFDA); //baykal01/Orator16
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.findNameObject(app.nameObject);
    }

    @Test(priority = 8)
    public void testEditOPWinStateFormedByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.openEditObject(app.nameObject);
        app.editTopFormOPRPIR(objectPWEdit1);
        app.addAndFillCorrection(correctionEdit);
        app.editSubArticleFirst(article226PZ);
        app.editObjectWork(objectWorkEdit);
        app.saveEditOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.logout();

    }

    @Test(priority = 9)
    public void testChangeStatusOPWinStateFormedByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Принято к утверждению"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Принят");

        //Перевод ОПР из статуса "Принято к утверждению" в статус "Не рассмотрено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Отклонено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Принято к утверждению"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Принят");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Отклонено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Не рассмотрено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();

    }

    @Test(priority = 10)
    public void testApprovalProgramWorkByFDA() {

        app.gotoResourse(baseURL);
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.pushApprovalPWbyFDA();

    }

    @Test(priority = 11)
    public void testCreateOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        app.findNameObject(app.nameObject);
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.pushApprovalPWbyFDA();

        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();

    }

    @Test(priority = 12)
    public void testEditOPWinStateApproveByFKU() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        //Редактирование ОПР
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.openEditObject(app.nameObject);
        app.editTopFormOPRPIR(objectPWEdit1);
        app.addAndFillCorrection(correctionEdit);
        app.editSubArticleFirst(article226PZ);
        app.editObjectWork(objectWorkEdit);
        app.saveEditOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        app.findNameObject(app.nameObject);
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.pushApprovalPWbyFDA();

        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();


    }

    @Test(priority = 13)
    public void testCreateOPWinStateApproveByFDA() {
        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFDA); //baykal01/Orator16
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        //Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);

        app.logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        app.findNameObject(app.nameObject);
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.pushApprovalPWbyFDA();

        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();
    }

    @Test(priority = 14)
    public void testEditOPWinStateApproveByFDA() {

        //Создание ОПР под учетной записью с ролью "пользователь ФКУ"
        app.gotoResourse(baseURL);
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.
//      Создание объекта программы работ
        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);
        app.logout();

        //Редактирование ОПР
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);
        app.openEditObject(app.nameObject);
        app.editTopFormOPRPIR(objectPWEdit1);
        app.addAndFillCorrection(correctionEdit);
        app.editSubArticleFirst(article226PZ);
        app.editObjectWork(objectWorkEdit);
        app.saveEditOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.logout();


        //Проверка созданного ОПР под учетной записью с ролью "пользователь ФДА"
        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        app.findNameObject(app.nameObject);
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.pushApprovalPWbyFDA();

        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();

    }

    @Test(priority = 15)
    public void testChangeStatusOPWinStateApproveByFDA() {

        //Предварительное создание объекта программы работ под учеткой ФКУ (чтобы ОПР был со статусом "Не рассмотрен")
        app.gotoResourse(baseURL);
        app.login(userFKU);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork); // "PIRRoadFullRepair" - еще одна программа работ, в которой сохраняются данные.

        app.pushCreateOPRandSaveWindow();
        app.fillTopFormOPRPIR(objectPWCreate1);
        app.addAndFillCorrection(correctionCreate);
        app.fillSubArticleFirst(article226RPD);
        app.createObjectWork(objectWorkCreate);
        app.saveOPRandCloseWindow();
        app.refreshCurrentWindow();
        app.findNameObject(app.nameObject);

        app.logout();


        app.login(userFDA);
        app.choiceSection("Планирование");
        app.choiceYear(YearProgramWork);
        app.selectProgramWork(idProgramWork);

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Принято к утверждению"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Принят");

        //Перевод ОПР из статуса "Принято к утверждению" в статус "Не рассмотрено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Отклонено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Принято к утверждению"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Принято к утверждению");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Принят");

        //Перевод ОПР из статуса "Не рассмотрено" в статус "Отклонено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Отклонено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Отклонен");

        //Перевод ОПР из статуса "Отклонено" в статус "Не рассмотрено"
        app.rightClick(app.nameObject);
        app.selectContextItem("Изменить статус объекта", "Не рассмотрено");
        app.refreshCurrentWindow();
        app.checkStatusObject(app.nameObject, "Не рассмотрен");

        app.logout();

    }

}
