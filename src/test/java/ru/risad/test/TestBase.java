package ru.risad.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    JavascriptExecutor js;
    String baseURL = "https://172.16.10.57:7443/";
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

    protected void choiseProgramWork(String nameProgramWork) {
        driver.findElement(By.id(nameProgramWork)).click();
    }

    protected void createObjectProgramWorkInDev() {

        pushCreateOPRandSaveWindow();
        fillFormOPRtop();
        fillDescriptionAdjustment();
        fillSubarticle();
        createObjectWork();
        saveOPRandCloseWindow();

    }

    private void saveOPRandCloseWindow() {
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button")).click();															        //Обращение к кнопке создания объекта программы работ.
        driver.close();
        driver.switchTo().window(vars.get("root").toString());
    }

    private void createObjectWork() {
        //Создание объекта работ
        driver.findElement(By.xpath("//span[contains(.,'Объекты работы')]")).click();																               //Переход на вкладку "Объекты работ"
        driver.findElement(By.id("btn-create-newobj")).click();																							           //Обращение к кнопке создания объекта работ
        driver.findElement(By.xpath("//div[contains(text(),'Наименование дороги')]/..//span[@class='k-widget k-dropdowntree k-dropdowntree-clearable']")).click(); //Разворачивание списка для поля "Наименование дороги"
        driver.findElement(By.xpath("//span[contains(.,'ФКУ Упрдор «Енисей»')]/..//span[@class='k-icon k-i-expand']")).click();							           //Разворачивание списка ФКУ
        driver.findElement(By.xpath("//span[contains(.,'Республика Тыва')]/..//span[@class='k-icon k-i-expand']")).click();							           //Выбор региона
        driver.findElement(By.xpath("//span[contains(.,'\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией')]")).click();																	           //Выбор участка дороги
        driver.findElement(By.id("tb-start")).click();																									           //Позиционирование на поле ввода "Начало (км)"
        driver.findElement(By.id("tb-start")).sendKeys("12");																				       //Ввод значения в поле "Начало (км)"
        driver.findElement(By.id("tb-startAdd")).click();																								        //Позиционирование на поле ввода "Начало (м)"
        driver.findElement(By.id("tb-startAdd")).sendKeys("111");																				//Ввод значения в поле "Начало (м)"
        driver.findElement(By.id("tb-finish")).click();																									        //Позиционирование на поле ввода "Конец (км)"
        driver.findElement(By.id("tb-finish")).sendKeys("21");																					//Ввод значения в поле "Конец (км)"
        driver.findElement(By.id("tb-finishAdd")).click();																								        //Позиционирование на поле ввода "Конец (м)"
        driver.findElement(By.id("tb-finishAdd")).sendKeys("222");																				//Ввод значения в поле "Конец (м)"
        driver.findElement(By.id("tb-fullRepairDateStr")).click();																						        //Позиционирование на поле "Последний год ремонта..."
        driver.findElement(By.id("tb-fullRepairDateStr")).sendKeys("Ремонт 12.12.2012");															//Ввод значения в поле "Последний год ремонта..."
        driver.findElement(By.id("tb-traffic")).clear();																								        //Очиска поля "Интенсивность движения"
        driver.findElement(By.id("tb-traffic")).sendKeys("444");																					//Ввод значения в поле "Интенсивность движения"
        driver.findElement(By.id("tb-categories")).click();																								        //Позиционирование на поле "Техническая категория"
        driver.findElement(By.id("tb-categories")).sendKeys("1 категоря");																		//Ввод значения в поле "Техническая категория"
        driver.findElement(By.xpath("//button[contains(.,'Да')]")).click();																				        //Обращение к кнопке сохранения объекта работ.
    }

    private void fillSubarticle() {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']")).click(); 			//Разворачивание списка подстатей
        driver.findElement(By.xpath("//li[contains(.,'226')]")).click();																						//Выбор подстатьи
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']")).click(); 			//Разворачивание списка в столбце "Вид работ"
        driver.findElement(By.xpath("//div[@class='k-animation-container'][4]//li[contains(text(),'Разработка проектной документации')]")).click();				//Выбор значения из списка "Разработка проектной документации"
//        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']")).clear();	//Очистка поля перед внесеним значения года
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']")).click();	//Позиционирование на поле ввода в столбце с годом ПР
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys("200");																									//Ввод значения в поле
    }

    private void fillDescriptionAdjustment() {
        //ВНЕСЕНИЕ ОПИСАНИЯ КОРРЕКТИРОВКИ

        driver.findElement(By.xpath("//div[contains(text(),'Описание корректировки')]/..//button")).click();								//Обращение к кнопке добавления описания корректировки
        driver.findElement(By.id("correction-description-dialog-date")).click();															//Позиционирование на датапикере в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).clear();															//Очиска поля датапикера в описании корректировки
        driver.findElement(By.id("correction-description-dialog-date")).sendKeys("09.02.2020");								//Ввод даты с клавиатуры в датапикер. !!!нужно предварительно очистить поле ввода датапикера!!!
        driver.findElement(By.id("correction-description-dialog-name")).click();															//Позиционирование на поле ввода "Номер документа".
        driver.findElement(By.id("correction-description-dialog-name")).sendKeys("123");										//Ввод значения в поле "Номер документа"
        driver.findElement(By.xpath("//div[3]/button")).click();																			//Обращение к кнопке "Ок" на форме добавления описания корректировки.
    }

    private void fillFormOPRtop() {
        driver.findElement(By.xpath("//div[contains(text(),'Регион')]/..//span[@class='k-dropdown-wrap k-state-default']")).click();    //Разворачивание списка "Регион"
        driver.findElement(By.xpath("//span[contains(text(),'ФКУ Упрдор «Енисей»')]/../span[@class='k-icon k-i-expand']")).click();     //Разворачивание ФКУ
        driver.findElement(By.xpath("//span[contains(.,'Республика Тыва')]")).click();												    //Выбор региона
        driver.findElement(By.xpath("//div[contains(text(),'Регион')]")).click();														//Доп.клик для скрытия выпадающего списка "Регион"
        driver.findElement(By.id("ta-name")).click();																					//Позиционирование на поле для ввода Наименования объекта
        driver.findElement(By.id("ta-name")).sendKeys("Test_001"+ System.currentTimeMillis());							//Ввод наименования объекта
        driver.findElement(By.xpath("//div[contains(text(),'Вид работ')]/..//span[@class='k-widget k-dropdown']")).click();				//Разворачивание списка "Вид работ"
        driver.findElement(By.xpath("//li[contains(.,'Устройство защитных слоев')]")).click();											//Выбор значения из списка "Устройство защитных слоев"
        driver.findElement(By.xpath("//div[contains(text(),'Протяженность ремонтируемого покрытия (км)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.id("tb-roadLength")).sendKeys("11");																				//Ввод значения в поле "Протяженность ремонтируемого покрытия (км)"
        driver.findElement(By.xpath("//div[contains(text(),'Площадь ремонтируемого покрытия (кв.м)')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.id("tb-coveringArea")).sendKeys("22");																			    //Ввод значения в поле "Площадь ремонтируемого покрытия (кв.м.)"
        driver.findElement(By.xpath("//div[contains(text(),'Стоимость проектно-изыскательских работ')]/..//input[@class='k-formatted-value k-input']")).click(); //Позиционирование на поле ввода "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.id("tb-psdCost")).sendKeys("33");																					//Ввод значения в поле "Стоимость проектно-изыскательских работ в соответствии с расчетами (тыс. руб)"
        driver.findElement(By.xpath("//div[contains(text(),'Объем работ')]/..//input[@class='k-formatted-value k-input']")).click();			                //Позиционирование на поле ввода "Объем работ (км)"
        driver.findElement(By.id("tb-jobAmount")).sendKeys("44");																				//Ввод значения в поле "Объем работ (км)"
        driver.findElement(By.id("tb-cmrDates")).click();               	                                                                                    //Позиционирование на поле "Сроки проведения СМР"
        driver.findElement(By.id("tb-cmrDates")).sendKeys("24");																		    		//Ввод значения в поле "Сроки проведения СМР"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).clear(); 	                                                                                                     //Очистка поля "Год окончания работ"
        driver.findElement(By.xpath("//div[contains(text(),'Год окончания работ')]/..//input[@class='k-formatted-value k-input']")).click(); 	                //Позиционирование на поле ввода "Год окончания работ"
        driver.findElement(By.id("tb-endYear")).sendKeys("2020");																				//Ввод значения в поле "Год окончания работ"
    }

    private void pushCreateOPRandSaveWindow() {
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.xpath("//div[@id='btns-edits']/button/span")).click(); 													//обращение к кнопке создания ОПР
        vars.put("win4227", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win4227").toString());
    }

    protected void choiceYear(String year) {
        driver.findElement(By.xpath("//input[@id='program_year']")).click();
        driver.findElement(By.xpath("//input[@id='program_year']")).clear();
        driver.findElement(By.xpath("//input[@id='program_year']")).click();
        driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(year);
    }

    protected void choiceSection(String section) {
        driver.findElement(By.xpath("//span[contains(text(), '"+ section + "')]")).click();
    }

    protected void gotoResourse(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    protected void login(String userName, String userPassword) {
        //Закрытие сообщения "Подключение не защищено". Нужно сделать через условие наличия элемента на странице
        driver.findElement(By.xpath("//button[@id='details-button']")).click();
        driver.findElement(By.xpath("//a[@id='proceed-link']")).click();

        driver.findElement(By.id("Username")).click();
        driver.findElement(By.id("Username")).sendKeys(userName);
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys(userPassword);
        driver.findElement(By.name("button")).click();
    }

    protected void createProgramWorkIfNotCreatedLater() {
        if (isElementPresent(By.xpath("//div[@id='dialog']/p[1]"))) {
            driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        }

    }

    public boolean isElementPresent(By locator) {
       return driver.findElements(locator).size() > 0;
    }
}
