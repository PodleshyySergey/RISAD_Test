package ru.risad.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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

        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.xpath("//div[@id='btns-edits']/button")).click();
        vars.put("win3524", waitForWindow(2000));
        vars.put("root", driver.getWindowHandle());
        driver.switchTo().window(vars.get("win3524").toString());
        driver.findElement(By.xpath("//div[@id='programobjectobject']/div/div/div[2]/span/span/span[3]/span")).click();
        driver.findElement(By.cssSelector(".k-i-expand")).click();
        driver.findElement(By.xpath("//div[5]/div/div[1]/ul/li/ul/li[3]/div/span")).click();
        choiseProgramWork("ta-name");
        driver.findElement(By.id("ta-name")).sendKeys("Тест 003");
        driver.findElement(By.xpath("//div[@id='programobjectobject']/div/div[5]/div[2]/span/span/span[2]/span")).click();
        driver.findElement(By.xpath("//li[contains(.,'Устройство поверхностной обработки')]")).click();
        driver.findElement(By.xpath("//*[@id=\"programobjectobject\"]/div/div[6]/div[2]/span/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"tb-roadLength\"]")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id=\"programobjectobject\"]/div/div[7]/div[2]/span/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"tb-coveringArea\"]")).sendKeys("11");
        driver.findElement(By.xpath("//*[@id=\"programobjectobject\"]/div/div[8]/div[2]/span/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"tb-psdCost\"]")).sendKeys("12");
        driver.findElement(By.xpath("//*[@id=\"programobjectobject\"]/div/div[9]/div[2]/span/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"tb-jobAmount\"]")).sendKeys("13");
        driver.findElement(By.id("tb-cmrDates")).click();
        driver.findElement(By.id("tb-cmrDates")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"articles-grid\"]/div[3]/table/tbody/tr/td[2]/span/span/span[2]")).click();
        driver.findElement(By.xpath("//div[9]/div/div[2]/ul/li")).click();
        driver.findElement(By.xpath("//td[3]/span/span/span[2]/span")).click();
        driver.findElement(By.xpath("//div[10]/div/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"articles-grid\"]/div[3]/table/tbody/tr/td[4]/p/span[1]/span/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"articles-grid\"]/div[3]/table/tbody/tr/td[4]/p/span[1]/span/input[2]")).sendKeys("100");
        driver.findElement(By.xpath("//div[@id='programobjecttabs']/div/div/ul/li[2]/span[2]")).click();
        driver.findElement(By.xpath("//div[@id='programobjectnestedobject']/div/button")).click();
        driver.findElement(By.xpath("//div[@id='nestededitform']/div/div/div[2]/span/span/span[3]/span")).click();
        driver.findElement(By.xpath("//div[8]/div/div/ul/li/div/span")).click();
        driver.findElement(By.xpath("//div[8]/div/div/ul/li/ul/li[3]/div/span")).click();
        driver.findElement(By.xpath("//span[contains(.,'\"Енисей\" Красноярск - Абакан - Кызыл - Чадан - Хандагайты - граница с Монголией')]")).click();
        driver.findElement(By.id("tb-start")).click();
        driver.findElement(By.id("tb-start")).sendKeys("1");
        driver.findElement(By.id("tb-startAdd")).click();
        driver.findElement(By.id("tb-startAdd")).sendKeys("111");
        driver.findElement(By.id("tb-finish")).click();
        driver.findElement(By.id("tb-finish")).sendKeys("2");
        driver.findElement(By.id("tb-finishAdd")).click();
        driver.findElement(By.id("tb-finishAdd")).sendKeys("222");
        driver.findElement(By.id("tb-fullRepairDateStr")).click();
        driver.findElement(By.id("tb-fullRepairDateStr")).sendKeys("2010");
        driver.findElement(By.id("tb-traffic")).click();
        driver.findElement(By.id("tb-traffic")).sendKeys("444");
        driver.findElement(By.id("tb-categories")).click();
        driver.findElement(By.id("tb-categories")).sendKeys("1");
        driver.findElement(By.xpath("//div[2]/div/div/div/div[18]/div/button")).click();
        driver.findElement(By.xpath("//div[@id='programobjectcontainer']/div/button")).click();
        driver.switchTo().window(vars.get("root").toString());
    }

    protected void choiceYear(String year) {
        driver.findElement(By.xpath("//input[@id='program_year']")).click();
        driver.findElement(By.xpath("//input[@id='program_year']")).clear();
//        driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(Keys.BACK_SPACE);
//        driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(Keys.BACK_SPACE);
//        driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@id='program_year']")).click();
        driver.findElement(By.xpath("//input[@id='program_year']")).sendKeys(year);
    }

    protected void choiceSection() {
        driver.findElement(By.xpath("//li[@id='menu-item-planning-pl']/span")).click();
    }

    protected void gotoResourse(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    protected void login(String userName, String userPassword) {
        driver.findElement(By.xpath("//button[@id='details-button']")).click();
        driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
        driver.findElement(By.id("Username")).click();
        driver.findElement(By.id("Username")).sendKeys(userName);
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys(userPassword);
        driver.findElement(By.name("button")).click();
    }

    protected void createProgramWorkInSelectedYear() {
        if (isElementPresent(By.xpath("//div[@id='dialog']"))) {
            driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        }

    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
           return false;
        }
    }
}
