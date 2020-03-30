package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.risad.test.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver driver;
    public String nameObject = "";                         //переменная для сохранения имени ОПР, по которому потом будет поиск ОПР в гриде программы работ
    protected Map<String, Object> vars;
    JavascriptExecutor js;

    public void selectProgramWork(String idProgramWork) {
        driver.findElement(By.id(idProgramWork)).click();
    }

//    public void createObjectProgramWork() {
//
//        pushCreateOPRandSaveWindow();
//        fillTopFormOPRPIR(new ObjectPWfillTop("ФКУ Упрдор «Енисей»", "Республика Тыва", "Устройство защитных слоев", "11", "22", "33", "44", "24", "2020"));
//        addAndFillCorrection(new Correction("На вновь начинаемый объект", "09.02.2020", "123"));
//        fillSubArticleFirst(new SubArticle("226", "Разработка проектной документации", "200"));
//        createObjectWork(new ObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "12", "111", "21", "222", "Ремонт 12.12.2012", "444", "1 категоря"));
//        saveOPRandCloseWindow();
//
//    }

    public void saveOPRandCloseWindow() {
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Создать')]")).click();	 //Обращение к кнопке создания объекта программы работ.
        driver.switchTo().window(vars.get("root").toString());
    }

    public void refreshCurrentWindow() {
        driver.navigate().refresh();
    }

    public void saveEditOPRandCloseWindow() {
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Обновить')]")).click();	//Обращение к кнопке создания объекта программы работ.
        driver.switchTo().window(vars.get("root").toString());
    }

    public void createObjectWork(ObjectWork objectWork) {
        //Создание объекта работ
        driver.findElement(By.xpath("//span[contains(.,'Объекты работы')]")).click();																               //Переход на вкладку "Объекты работ"
        driver.findElement(By.id("btn-create-newobj")).click();																							           //Обращение к кнопке создания объекта работ
        driver.findElement(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']")).click(); //Разворачивание списка для поля "Наименование дороги"
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getDiscoveryFKU() + "')]/..//span[@class='k-icon k-i-expand']")).click();							           //Разворачивание списка ФКУ
        driver.findElement(By.xpath("//span[contains(.," + objectWork.getDiscoveryRegion() + ")]/..//span[@class='k-icon k-i-expand']")).click();							           //Выбор региона
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getSelectRoadSection() + ")]")).click();																	           //Выбор участка дороги
        type(By.id("tb-start"),objectWork.getStartRoadSectionKM());
        type(By.id("tb-startAdd"),objectWork.getStartRoadSectionM());
        type(By.id("tb-finish"),objectWork.getEndRoadSectionKM());
        type(By.id("tb-finishAdd"),objectWork.getEndRoadSectionM());
        type(By.id("tb-fullRepairDateStr"),objectWork.getTypeAndDateRepairRoad());
        type(By.id("tb-traffic"),objectWork.getValueRoadTraffic());
        type(By.id("tb-categories"),objectWork.getCategoryRoad());
        driver.findElement(By.xpath("//button[contains(.,'Да')]")).click();																				        //Обращение к кнопке сохранения объекта работ.
    }

    private void type(By locator,String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).click();																									           //Позиционирование на поле ввода "Начало (км)"
        driver.findElement(locator).sendKeys(text);																				       //Ввод значения в поле "Начало (км)"
    }

    public void editObjectWork(ObjectWork objectWork) {
        //Создание объекта работ
        driver.findElement(By.xpath("//span[contains(.,'Объекты работы')]")).click();																               //Переход на вкладку "Объекты работ"
        driver.findElement(By.xpath("//a[@class='k-button k-button-icontext k-grid-edit']")).click();																							           //Обращение к кнопке редактирования объекта работ
        driver.findElement(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']")).click(); //Разворачивание списка для поля "Наименование дороги"
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getDiscoveryFKU() + "')]/..//span[@class='k-icon k-i-expand']")).click();							           //Разворачивание списка ФКУ
        driver.findElement(By.xpath("//span[contains(.," + objectWork.getDiscoveryRegion() + ")]/..//span[@class='k-icon k-i-expand']")).click();							           //Выбор региона
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getSelectRoadSection() + ")]")).click();																	           //Выбор участка дороги
        type(By.id("tb-start"),objectWork.getStartRoadSectionKM());
        type(By.id("tb-startAdd"),objectWork.getStartRoadSectionM());
        type(By.id("tb-finish"),objectWork.getEndRoadSectionKM());
        type(By.id("tb-finishAdd"),objectWork.getEndRoadSectionM());
        type(By.id("tb-fullRepairDateStr"),objectWork.getTypeAndDateRepairRoad());
        type(By.id("tb-traffic"),objectWork.getValueRoadTraffic());
        type(By.id("tb-categories"),objectWork.getCategoryRoad());
        driver.findElement(By.xpath("//button[contains(.,'Да')]")).click();																				        //Обращение к кнопке сохранения объекта работ.
    }

    //функция для добавления подстатьи без обращения к кнопке добавления (т.е. первая подстатья, грид которой сразу отображается на форме создания объекта программы работ)
    public void fillSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span/span")).click(); 			//Разворачивание списка подстатей
        driver.findElement(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]")).click();																						//Выбор подстатьи

        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']")).click(); 			//Разворачивание списка в столбце "Вид работ"
        driver.findElement(By.xpath("//div[@class='k-animation-container'][4]//li[contains(text(),'" + subArticle.getSelectTypeWork() + "')]")).click();				//Выбор значения из списка "Разработка проектной документации"
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']")).click();	//Позиционирование на поле ввода в столбце с годом ПР
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }

    public void editSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span")).click(); 			//Разворачивание списка подстатей
        driver.findElement(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]")).click();																						//Выбор подстатьи

        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']")).click(); 			//Разворачивание списка в столбце "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'" + subArticle.getSelectTypeWork() + "')]")).click();				//Выбор значения из списка "Разработка проектной документации"

        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']")).click();
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).clear();
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']")).click();	//Позиционирование на поле ввода в столбце с годом ПР
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }

    public void addAndFillCorrection(Correction correction) {
        //ВНЕСЕНИЕ ОПИСАНИЯ КОРРЕКТИРОВКИ
        driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();								//Обращение к кнопке добавления описания корректировки
        driver.findElement(By.xpath("//div[contains(text(),'Формулировка')]/..//span[@class='k-input']")).click();
        driver.findElement(By.xpath("//div[@id='correction-description-dialog-template-list']//li[contains(.,'" + correction.getNameCorrection() + "')]")).click();
        type(By.id("correction-description-dialog-date"),correction.getDateCorrection());
        type(By.id("correction-description-dialog-name"),correction.getNumberCorrection());
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();																			//Обращение к кнопке "Ок" на форме добавления описания корректировки.
    }

    public void fillTopFormOPRPIR(ObjectPWfillTop objectPWfillTop) {
        if (!objectPWfillTop.getSelectFKU().equals("")) {
            driver.findElement(By.xpath("//div[contains(text(),'Регион')]/..//span[@class='k-dropdown-wrap k-state-default']")).click();    //Разворачивание списка "Регион"
            driver.findElement(By.xpath("//span[contains(text(),'" + objectPWfillTop.getSelectFKU() + "')]/../span[@class='k-icon k-i-expand']")).click();     //Разворачивание ФКУ
            driver.findElement(By.xpath("//span[contains(.,'" + objectPWfillTop.getSelectRegion() + "')]")).click();                                                    //Выбор региона
        }
        driver.findElement(By.xpath("//div[contains(text(),'Регион')]")).click();	//Доп.клик для скрытия выпадающего списка "Регион"
        nameObject = "Test_" + System.currentTimeMillis();                          //Сохранение названия объекта в переменную для последующего проверки отображения в гриде программы работ
        type(By.id("ta-name"),nameObject);
        driver.findElement(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']")).click();				//Разворачивание списка "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'" + objectPWfillTop.getTypeOfWork() + "')]")).click();											//Выбор значения из списка "Устройство защитных слоев"
        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWfillTop.getLengthRepairCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWfillTop.getAreaRepairCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWfillTop.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();			                //Позиционирование на поле ввода "Объем работ (км)"
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWfillTop.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"
        driver.findElement(By.id("tb-cmrDates")).click();               	                                                                                    //Позиционирование на поле "Сроки проведения СМР"
        driver.findElement(By.id("tb-cmrDates")).sendKeys(objectPWfillTop.getDatesCMR());																		    		//Ввод значения в поле "Сроки проведения СМР"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWfillTop.getEndYearWorks());																				//Ввод значения в поле "Год окончания работ"
    }

    public void editTopFormOPRPIR(ObjectPWfillTop objectPWfillTop) {

        driver.findElement(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']")).click();				//Разворачивание списка "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'" + objectPWfillTop.getTypeOfWork() + "')]")).click();										        	//Выбор значения из списка "Устройство защитных слоев"

        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-roadLength")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWfillTop.getLengthRepairCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"

        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-coveringArea")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWfillTop.getAreaRepairCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"

        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-psdCost")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWfillTop.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"

        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-jobAmount")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();			                //Позиционирование на поле ввода "Объем работ (км)"
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWfillTop.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"

        type(By.id("tb-cmrDates"),objectPWfillTop.getDatesCMR());

        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWfillTop.getEndYearWorks());             																			//Ввод значения в поле "Год окончания работ"
    }

    public void pushCreateOPRandSaveWindow() {
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.xpath("//div[@id='btns-edits']/button/span")).click(); 													//обращение к кнопке создания ОПР
        vars.put("win4227", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win4227").toString());
    }

    public void choiceYear(String year) {

//        Попытка реализации через Actions (года, который есть в открыващемся датапикере, без перехода на другие года)
//        Actions actions = new Actions(driver)
//                .moveToElement(driver.findElement(By.xpath("//span[@aria-controls='program_year_dateview']")))
//                .click()
//                .moveToElement(driver.findElement(By.xpath("//div/table//td/a[contains(text(),'" + year + "')]")))
//                .click();
//        actions.build().perform();

//      Попытка реализации выбора года путем открытия и кликами по кнопкам, но срабатывает первая строчка (не открывается датапикер)
//        driver.findElement(By.xpath("//span[@aria-controls='program_year_dateview']")).click();
//        int intYear = Integer.parseInt(year);
//        int firstYear = Integer.parseInt(driver.findElement(By.xpath("//div/table//td/a[1]")).getText());
//        int lastYear = Integer.parseInt(driver.findElement(By.xpath("//div/table//tr[3]/td[4]/a")).getText());
//        while (intYear < firstYear) {
//            driver.findElement(By.xpath("//div[@class='k-header']/a[@aria-label='Previous]")).click();
//        }
//        while (intYear > lastYear) {
//            driver.findElement(By.xpath("//div[@class='k-header']/a[@aria-label='Next]")).click();
//        }
//        driver.findElement(By.xpath("//div/table//td/a[contais(text(),'" + year + "')]")).click();


//      Реализация с очисткой поля и ввода года "с клавиатуры" (иногда дает сбои, почему-то вводятся не все цифры из числа года)
//      После добавления цикла работает без сбоев
        while (!driver.findElement(By.xpath("//input[@id='program_year']")).getAttribute("value").equals(year)) {
            driver.findElement(By.xpath("//input[@id='program_year']")).click();
            driver.findElement(By.xpath("//input[@id='program_year']")).clear();
            driver.findElement(By.xpath("//input[@id='program_year']")).click();
            driver.findElement(By.xpath("//input[@id='program_year']")).click();
            driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(year);
        }
    }

    public void choiceSection(String section) {
        driver.findElement(By.xpath("//span[contains(text(), '"+ section + "')]")).click();
    }

    public void gotoResourse(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void login(User user) {
        //Закрытие сообщения "Подключение не защищено".
        if (isElementPresent(By.xpath("//button[@id='details-button']"))) {
            driver.findElement(By.xpath("//button[@id='details-button']")).click();
            driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
        }
        type(By.id("Username"),user.getLogin());
        type(By.id("Password"),user.getPassword());
        driver.findElement(By.name("button")).click();
    }

    public void createProgramWorkIfNotCreatedLater() {
        if (isElementPresent(By.xpath("//div[@id='dialog']/p[1]"))) {
            driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        }

    }

    public void createProgramWork() {
        driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
    }

    public boolean isElementPresent(By locator) {
       return driver.findElements(locator).size() > 0;
    }

    public void findNameObject(String nameObjectTemp) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]"));
    }

    public void checkStatusObject(String nameObjectTemp, final String status) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]/i[@title='" + status + "']"));
    }

    public void openEditObject(String nameObjectTemp) {
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]")).click();     //фокусировка на ОПР
        driver.findElement(By.xpath("//button[@ix='3']/span")).click();                              //обращение к кнопке редактирования ОПР
        vars.put("win4228", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win4228").toString());
    }

    public void logout() {
        driver.findElement(By.xpath("//div[@class='user-panel logout item']//input[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@class='PostLogoutRedirectUri']")).click();
    }

    public void pushFomationPW() {
        driver.findElement(By.xpath("//button[@title='Сформировать']")).click();
    }

    public void fillDeleteCorrection(Correction deleteCorr) {
        type(By.id("correction-description-dialog-date"),deleteCorr.getDateCorrection());
        type(By.id("correction-description-dialog-doc"),deleteCorr.getNumberCorrection());
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
    }

    public void clickDeleteOPW(String nameObjectTemp) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]")).click();             //фокусировка на ОПР
        driver.findElement(By.xpath("//button[@ix='4']/span")).click();                             //обращение к кнопке редактирования ОПР
    }

    public void rightClick(final String nameObiect) {
        WebElement element = driver.findElement(By.xpath("//td[contains(.,'" + nameObiect + "')]"));
        element.click();
        Actions action = new Actions(driver).contextClick(element);
        action.build().perform();
    }

    public void selectContextItem(final String menuItem, final String submenuItem) {
        Actions action = new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//li[contains(.,'" + menuItem + "')]/span")))
                .click()
                .moveToElement(driver.findElement(By.xpath("//li/span[contains(.,'" + submenuItem + "')]")))
                .click();
                action.build().perform();
    }

    public void pushApprovalPWbyFDA() {
        driver.findElement(By.xpath("//button[@title='Утвердить']/span")).click();
    }

    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    public void stop() {
        driver.quit();
    }
}
