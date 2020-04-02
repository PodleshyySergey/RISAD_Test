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
        click(By.id(idProgramWork));
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
        click(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Создать')]"));	 //Обращение к кнопке создания объекта программы работ.
        driver.switchTo().window(vars.get("root").toString());
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void refreshCurrentWindow() {
        driver.navigate().refresh();
    }

    public void saveEditOPRandCloseWindow() {
        click(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Обновить')]"));
        driver.switchTo().window(vars.get("root").toString());
    }

    public void createObjectWork(ObjectWork objectWork) {
        //Создание объекта работ
        click(By.xpath("//span[contains(.,'Объекты работы')]"));
        click(By.id("btn-create-newobj"));
        click(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']"));
        click(By.xpath("//span[contains(.,'" + objectWork.getDiscoveryFKU() + "')]/..//span[@class='k-icon k-i-expand']"));
        click(By.xpath("//span[contains(.," + objectWork.getDiscoveryRegion() + ")]/..//span[@class='k-icon k-i-expand']"));
        click(By.xpath("//span[contains(.,'" + objectWork.getSelectRoadSection() + ")]"));
        type(By.id("tb-start"),objectWork.getStartRoadSectionKM());
        type(By.id("tb-startAdd"),objectWork.getStartRoadSectionM());
        type(By.id("tb-finish"),objectWork.getEndRoadSectionKM());
        type(By.id("tb-finishAdd"),objectWork.getEndRoadSectionM());
        type(By.id("tb-fullRepairDateStr"),objectWork.getTypeAndDateRepairRoad());
        type(By.id("tb-traffic"),objectWork.getValueRoadTraffic());
        type(By.id("tb-categories"),objectWork.getCategoryRoad());
        click(By.xpath("//button[contains(.,'Да')]"));
    }

    public void type(By locator,String text) {
        driver.findElement(locator).clear();
        click(locator);
        driver.findElement(locator).sendKeys(text);																				       //Ввод значения в поле "Начало (км)"
    }

    public void editObjectWork(ObjectWork objectWork) {
        //Создание объекта работ
        click(By.xpath("//span[contains(.,'Объекты работы')]"));
        click(By.xpath("//a[@class='k-button k-button-icontext k-grid-edit']"));
        click(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']"));
        click(By.xpath("//span[contains(.,'" + objectWork.getDiscoveryFKU() + "')]/..//span[@class='k-icon k-i-expand']"));
        click(By.xpath("//span[contains(.," + objectWork.getDiscoveryRegion() + ")]/..//span[@class='k-icon k-i-expand']"));
        click(By.xpath("//span[contains(.,'" + objectWork.getSelectRoadSection() + ")]"));
        type(By.id("tb-start"),objectWork.getStartRoadSectionKM());
        type(By.id("tb-startAdd"),objectWork.getStartRoadSectionM());
        type(By.id("tb-finish"),objectWork.getEndRoadSectionKM());
        type(By.id("tb-finishAdd"),objectWork.getEndRoadSectionM());
        type(By.id("tb-fullRepairDateStr"),objectWork.getTypeAndDateRepairRoad());
        type(By.id("tb-traffic"),objectWork.getValueRoadTraffic());
        type(By.id("tb-categories"),objectWork.getCategoryRoad());
        click(By.xpath("//button[contains(.,'Да')]"));
    }

    //функция для добавления подстатьи без обращения к кнопке добавления (т.е. первая подстатья, грид которой сразу отображается на форме создания объекта программы работ)
    public void fillSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span/span"));
        click(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']"));
        click(By.xpath("//div[@class='k-animation-container'][4]//li[contains(text(),'" + subArticle.getSelectTypeWork() + "')]"));
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }

    public void editSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span"));
        click(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']"));
        click(By.xpath("//li[contains(.,'" + subArticle.getSelectTypeWork() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).clear();
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }

    public void addAndFillCorrection(Correction correction) {
        //ВНЕСЕНИЕ ОПИСАНИЯ КОРРЕКТИРОВКИ
        click(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button"));
        click(By.xpath("//div[contains(text(),'Формулировка')]/..//span[@class='k-input']"));
        click(By.xpath("//div[@id='correction-description-dialog-template-list']//li[contains(.,'" + correction.getNameCorrection() + "')]"));
        type(By.id("correction-description-dialog-date"),correction.getDateCorrection());
        type(By.id("correction-description-dialog-name"),correction.getNumberCorrection());
        click(By.xpath("//button[contains(text(),'Ok')]"));
    }

    public void fillTopFormOPRPIR(ObjectPWfillTop objectPWfillTop) {
        if (!objectPWfillTop.getSelectFKU().equals("")) {
            click(By.xpath("//div[contains(text(),'Регион')]/..//span[@class='k-dropdown-wrap k-state-default']"));
            click(By.xpath("//span[contains(text(),'" + objectPWfillTop.getSelectFKU() + "')]/../span[@class='k-icon k-i-expand']"));
            click(By.xpath("//span[contains(.,'" + objectPWfillTop.getSelectRegion() + "')]"));
        }
        click(By.xpath("//div[contains(text(),'Регион')]"));
        nameObject = "Test_" + System.currentTimeMillis();                          //Сохранение названия объекта в переменную для последующего проверки отображения в гриде программы работ
        type(By.id("ta-name"),nameObject);
        click(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']"));
        click(By.xpath("//li[contains(.,'" + objectPWfillTop.getTypeOfWork() + "')]"));
        click(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWfillTop.getLengthRepairCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"
        click(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWfillTop.getAreaRepairCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"
        click(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWfillTop.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        click(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWfillTop.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"
        click(By.id("tb-cmrDates"));
        driver.findElement(By.id("tb-cmrDates")).sendKeys(objectPWfillTop.getDatesCMR());																		    		//Ввод значения в поле "Сроки проведения СМР"
        click(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        click(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWfillTop.getEndYearWorks());																				//Ввод значения в поле "Год окончания работ"
    }

    public void editTopFormOPRPIR(ObjectPWfillTop objectPWfillTop) {

        click(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']"));
        click(By.xpath("//li[contains(.,'" + objectPWfillTop.getTypeOfWork() + "')]"));

        click(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-roadLength")).clear();
        click(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWfillTop.getLengthRepairCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"

        click(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-coveringArea")).clear();
        click(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWfillTop.getAreaRepairCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"

        click(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-psdCost")).clear();
        click(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWfillTop.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"

        click(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-jobAmount")).clear();
        click(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWfillTop.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"

        type(By.id("tb-cmrDates"),objectPWfillTop.getDatesCMR());

        click(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        click(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']"));
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWfillTop.getEndYearWorks());             																			//Ввод значения в поле "Год окончания работ"
    }

    public void pushCreateOPRandSaveWindow() {
        vars.put("window_handles", driver.getWindowHandles());
        click(By.xpath("//div[@id='btns-edits']/button/span"));
        vars.put("win4227", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win4227").toString());
    }

    public void choiceYear(String year) {
        
//      Реализация с очисткой поля и ввода года "с клавиатуры" (иногда дает сбои, почему-то вводятся не все цифры из числа года)
//      После добавления цикла работает без сбоев
        while (!driver.findElement(By.xpath("//input[@id='program_year']")).getAttribute("value").equals(year)) {
            type(By.xpath("//input[@id='program_year']"),year);
        }
    }

    public void choiceSection(String section) {
        click(By.xpath("//span[contains(text(), '"+ section + "')]"));
    }

    public void gotoResourse(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void login(User user) {
        //Закрытие сообщения "Подключение не защищено".
        if (isElementPresent(By.xpath("//button[@id='details-button']"))) {
            click(By.xpath("//button[@id='details-button']"));
            click(By.xpath("//a[@id='proceed-link']"));
        }
        type(By.id("Username"),user.getLogin());
        type(By.id("Password"),user.getPassword());
        click(By.name("button"));
    }

    public void createProgramWorkIfNotCreatedLater() {
        if (isElementPresent(By.xpath("//div[@id='dialog']/p[1]"))) {
            click(By.xpath("//button[contains(.,'Ok')]"));
        }

    }

    public void createProgramWork() {
        click(By.xpath("//button[contains(.,'Ok')]"));
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
        click(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]"));
        click(By.xpath("//button[@ix='3']/span"));
        vars.put("win4228", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win4228").toString());
    }

    public void logout() {
        click(By.xpath("//div[@class='user-panel logout item']//input[@type='submit']"));
        click(By.xpath("//a[@class='PostLogoutRedirectUri']"));
    }

    public void pushFomationPW() {
        click(By.xpath("//button[@title='Сформировать']"));
    }

    public void fillDeleteCorrection(Correction deleteCorr) {
        type(By.id("correction-description-dialog-date"),deleteCorr.getDateCorrection());
        type(By.id("correction-description-dialog-doc"),deleteCorr.getNumberCorrection());
        click(By.xpath("//button[contains(text(),'Ok')]"));
    }

    public void clickDeleteOPW(String nameObjectTemp) {
        click(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]"));
        click(By.xpath("//button[@ix='4']/span"));
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
        click(By.xpath("//button[@title='Утвердить']/span"));
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
