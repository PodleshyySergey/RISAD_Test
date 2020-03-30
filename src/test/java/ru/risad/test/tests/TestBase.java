package ru.risad.test.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.risad.test.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    JavascriptExecutor js;
    public String baseURL = "https://172.16.10.57:7443/";
    public String nameObject = "";                         //переменная для сохранения имени ОПР, по которому потом будет поиск ОПР в гриде программы работ
    private Map<String, Object> vars;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
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


    public void selectProgramWork(String idProgramWork) {
        driver.findElement(By.id(idProgramWork)).click();
    }

    public void createObjectProgramWork() {

        pushCreateOPRandSaveWindow();
        fillTopFormOPRPIR(new ObjectPWtopCreate("ФКУ Упрдор «Енисей»", "Республика Тыва", "Устройство защитных слоев", "11", "22", "33", "44", "24", "2020"));
        addAndFillCorrection(new Correction("На вновь начинаемый объект", "09.02.2020", "123"));
        fillSubArticleFirst(new SubArticle("226", "Разработка проектной документации", "200"));
        createObjectWork(new ObjectWork("ФКУ Упрдор «Енисей»", "'Республика Тыва'", "\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией'", "12", "111", "21", "222", "Ремонт 12.12.2012", "444", "1 категоря"));
        saveOPRandCloseWindow();

    }

    public void saveOPRandCloseWindow() {
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Создать')]")).click();															        //Обращение к кнопке создания объекта программы работ.
//        driver.close();
        driver.switchTo().window(vars.get("root").toString());
    }

    public void refreshCurrentWindow() {
        driver.navigate().refresh();
    }

    public void saveEditOPRandCloseWindow() {
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button[contains(.,'Обновить')]")).click();															        //Обращение к кнопке создания объекта программы работ.
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
        driver.findElement(By.id("tb-start")).click();																									           //Позиционирование на поле ввода "Начало (км)"
        driver.findElement(By.id("tb-start")).sendKeys(objectWork.getStartRoadSectionKM());																				       //Ввод значения в поле "Начало (км)"
        driver.findElement(By.id("tb-startAdd")).click();																								        //Позиционирование на поле ввода "Начало (м)"
        driver.findElement(By.id("tb-startAdd")).sendKeys(objectWork.getStartRoadSectionM());																				//Ввод значения в поле "Начало (м)"
        driver.findElement(By.id("tb-finish")).click();																									        //Позиционирование на поле ввода "Конец (км)"
        driver.findElement(By.id("tb-finish")).sendKeys(objectWork.getEndRoadSectionKM());																					//Ввод значения в поле "Конец (км)"
        driver.findElement(By.id("tb-finishAdd")).click();																								        //Позиционирование на поле ввода "Конец (м)"
        driver.findElement(By.id("tb-finishAdd")).sendKeys(objectWork.getEndRoadSectionM());																				//Ввод значения в поле "Конец (м)"
        driver.findElement(By.id("tb-fullRepairDateStr")).click();																						        //Позиционирование на поле "Последний год ремонта..."
        driver.findElement(By.id("tb-fullRepairDateStr")).sendKeys(objectWork.getTypeAndDateRepairRoad());															//Ввод значения в поле "Последний год ремонта..."
        driver.findElement(By.id("tb-traffic")).clear();																								        //Очиска поля "Интенсивность движения"
        driver.findElement(By.id("tb-traffic")).sendKeys(objectWork.getValueRoadTraffic());																					//Ввод значения в поле "Интенсивность движения"
        driver.findElement(By.id("tb-categories")).click();																								        //Позиционирование на поле "Техническая категория"
        driver.findElement(By.id("tb-categories")).sendKeys(objectWork.getCategoryRoad());																		//Ввод значения в поле "Техническая категория"
        driver.findElement(By.xpath("//button[contains(.,'Да')]")).click();																				        //Обращение к кнопке сохранения объекта работ.
    }

    public void editObjectWork(ObjectWork objectWork) {
        //Создание объекта работ
        driver.findElement(By.xpath("//span[contains(.,'Объекты работы')]")).click();																               //Переход на вкладку "Объекты работ"
        driver.findElement(By.xpath("//a[@class='k-button k-button-icontext k-grid-edit']")).click();																							           //Обращение к кнопке редактирования объекта работ
        driver.findElement(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']")).click(); //Разворачивание списка для поля "Наименование дороги"
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getDiscoveryFKU() + "')]/..//span[@class='k-icon k-i-expand']")).click();							           //Разворачивание списка ФКУ
        driver.findElement(By.xpath("//span[contains(.," + objectWork.getDiscoveryRegion() + ")]/..//span[@class='k-icon k-i-expand']")).click();							           //Выбор региона
        driver.findElement(By.xpath("//span[contains(.,'" + objectWork.getSelectRoadSection() + ")]")).click();																	           //Выбор участка дороги
        driver.findElement(By.id("tb-start")).clear();
        driver.findElement(By.id("tb-start")).click();																									           //Позиционирование на поле ввода "Начало (км)"
        driver.findElement(By.id("tb-start")).sendKeys(objectWork.getStartRoadSectionKM());																				       //Ввод значения в поле "Начало (км)"
        driver.findElement(By.id("tb-startAdd")).clear();
        driver.findElement(By.id("tb-startAdd")).click();																								        //Позиционирование на поле ввода "Начало (м)"
        driver.findElement(By.id("tb-startAdd")).sendKeys(objectWork.getStartRoadSectionM());																				//Ввод значения в поле "Начало (м)"
        driver.findElement(By.id("tb-finish")).clear();
        driver.findElement(By.id("tb-finish")).click();																									        //Позиционирование на поле ввода "Конец (км)"
        driver.findElement(By.id("tb-finish")).sendKeys(objectWork.getEndRoadSectionKM());																					//Ввод значения в поле "Конец (км)"
        driver.findElement(By.id("tb-finishAdd")).clear();
        driver.findElement(By.id("tb-finishAdd")).click();																								        //Позиционирование на поле ввода "Конец (м)"
        driver.findElement(By.id("tb-finishAdd")).sendKeys(objectWork.getEndRoadSectionM());																				//Ввод значения в поле "Конец (м)"
        driver.findElement(By.id("tb-fullRepairDateStr")).clear();
        driver.findElement(By.id("tb-fullRepairDateStr")).click();																						        //Позиционирование на поле "Последний год ремонта..."
        driver.findElement(By.id("tb-fullRepairDateStr")).sendKeys(objectWork.getTypeAndDateRepairRoad());															//Ввод значения в поле "Последний год ремонта..."
        driver.findElement(By.id("tb-traffic")).clear();
        driver.findElement(By.id("tb-traffic")).clear();																								        //Очиска поля "Интенсивность движения"
        driver.findElement(By.id("tb-traffic")).sendKeys(objectWork.getValueRoadTraffic());																					//Ввод значения в поле "Интенсивность движения"
        driver.findElement(By.id("tb-categories")).clear();
        driver.findElement(By.id("tb-categories")).click();																								        //Позиционирование на поле "Техническая категория"
        driver.findElement(By.id("tb-categories")).sendKeys(objectWork.getCategoryRoad());																		//Ввод значения в поле "Техническая категория"
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

        driver.findElement(By.id("correction-description-dialog-date")).click();															//Позиционирование на датапикере в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).clear();															//Очиска поля датапикера в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).sendKeys(correction.getDateCorrection());								//Ввод даты с клавиатуры в датапикер. !!!нужно предварительно очистить поле ввода датапикера!!!
        driver.findElement(By.id("correction-description-dialog-name")).click();															//Позиционирование на поле ввода "Номер документа".
        driver.findElement(By.id("correction-description-dialog-name")).sendKeys(correction.getNumberCorrection());										//Ввод значения в поле "Номер документа"
        driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();																			//Обращение к кнопке "Ок" на форме добавления описания корректировки.
    }

    public void fillTopFormOPRPIR(ObjectPWtopCreate objectPWtopCreate) {
        driver.findElement(By.xpath("//div[contains(text(),'Регион')]/..//span[@class='k-dropdown-wrap k-state-default']")).click();    //Разворачивание списка "Регион"
        driver.findElement(By.xpath("//span[contains(text(),'" + objectPWtopCreate.getSelectFKU() + "')]/../span[@class='k-icon k-i-expand']")).click();     //Разворачивание ФКУ
        driver.findElement(By.xpath("//span[contains(.,'" + objectPWtopCreate.getSelectRegion() + "')]")).click();												    //Выбор региона
        driver.findElement(By.xpath("//div[contains(text(),'Регион')]")).click();														//Доп.клик для скрытия выпадающего списка "Регион"
        nameObject = "Test_" + System.currentTimeMillis();                                                                              //Сохранение названия объекта в переменную для последующего проверки отображения в гриде программы работ
        driver.findElement(By.id("ta-name")).click();																					//Позиционирование на поле для ввода Наименования объекта
        driver.findElement(By.id("ta-name")).sendKeys(nameObject);							                            //Ввод наименования объекта
        driver.findElement(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']")).click();				//Разворачивание списка "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'" + objectPWtopCreate.getTypeOfWork() + "')]")).click();											//Выбор значения из списка "Устройство защитных слоев"
        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWtopCreate.getLengthRepairCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWtopCreate.getAreaRepairCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWtopCreate.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();			                //Позиционирование на поле ввода "Объем работ (км)"
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWtopCreate.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"
        driver.findElement(By.id("tb-cmrDates")).click();               	                                                                                    //Позиционирование на поле "Сроки проведения СМР"
        driver.findElement(By.id("tb-cmrDates")).sendKeys(objectPWtopCreate.getDatesCMR());																		    		//Ввод значения в поле "Сроки проведения СМР"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWtopCreate.getEndYearWorks());																				//Ввод значения в поле "Год окончания работ"
    }

    public void editTopFormOPRPIR(ObjectPWtopEdit objectPWtopEdit) {

        driver.findElement(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']")).click();				//Разворачивание списка "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'" + objectPWtopEdit.getTypeOfWork() + "')]")).click();										        	//Выбор значения из списка "Устройство защитных слоев"

        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-roadLength")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.id("tb-roadLength")).sendKeys(objectPWtopEdit.getLengthRepiarCovering());																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"

        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-coveringArea")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.id("tb-coveringArea")).sendKeys(objectPWtopEdit.getAreaRepiarCovering());																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"

        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-psdCost")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.id("tb-psdCost")).sendKeys(objectPWtopEdit.getCoastPIR());																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"

        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();
        driver.findElement(By.id("tb-jobAmount")).clear();
        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();			                //Позиционирование на поле ввода "Объем работ (км)"
        driver.findElement(By.id("tb-jobAmount")).sendKeys(objectPWtopEdit.getScopeWork());																				//Ввод значения в поле "Объем работ (км)"

        driver.findElement(By.id("tb-cmrDates")).clear();
        driver.findElement(By.id("tb-cmrDates")).click();               	                                                                                    //Позиционирование на поле "Сроки проведения СМР"
        driver.findElement(By.id("tb-cmrDates")).sendKeys(objectPWtopEdit.getDatesCMR());																		    		//Ввод значения в поле "Сроки проведения СМР"

        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).sendKeys(objectPWtopEdit.getEndYearWorks());             																			//Ввод значения в поле "Год окончания работ"
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
        driver.findElement(By.id("Username")).click();
        driver.findElement(By.id("Username")).sendKeys(user.getLogin());
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys(user.getPassword());
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

//    public void clickFormationOfPW() {
//        driver.findElement(By.xpath("//button[@title='Сформировать']/span")).click();
//        driver.navigate().refresh();
//    }

    public void logout() {
        driver.findElement(By.xpath("//div[@class='user-panel logout item']//input[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@class='PostLogoutRedirectUri']")).click();
    }

    public void pushFomationPW() {
        driver.findElement(By.xpath("//button[@title='Сформировать']")).click();
    }

    public void fillDeleteCorrection() {
        driver.findElement(By.id("correction-description-dialog-date")).click();										//Позиционирование на датапикере в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).clear();										//Очиска поля датапикера в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).sendKeys("12.03.2020");			//Ввод даты с клавиатуры в датапикер.
        driver.findElement(By.id("correction-description-dialog-doc")).click();											//Позиционирование на поле ввода "Номер документа".
        driver.findElement(By.id("correction-description-dialog-doc")).sendKeys("123");					//Ввод значения в поле "Номер документа"
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
}
