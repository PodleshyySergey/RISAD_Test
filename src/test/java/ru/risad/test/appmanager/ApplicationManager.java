package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.risad.test.model.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private CheckHelper checkHelper;
    private SubArticleHelper subArticleHelper;
    private ObjectHelper objectHelper;
    private ProgramHelper programHelper;
    private UserHelper userHelper;
    private SessionHelper sessionHelper;
    public WebDriver driver;
    JavascriptExecutor js;


    public void init() {
        driver = new ChromeDriver();
        userHelper = new UserHelper(driver);
        sessionHelper = new SessionHelper(driver);
        programHelper = new ProgramHelper(driver);
        objectHelper = new ObjectHelper(driver);
        subArticleHelper = new SubArticleHelper(driver);
        checkHelper = new CheckHelper(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        js = (JavascriptExecutor) driver;
        objectHelper.vars = new HashMap<String, Object>();
    }

    public void stop() {
        driver.quit();
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ProgramHelper getProgramHelper() {
        return programHelper;
    }

    public ObjectHelper getObjectHelper() {
        return objectHelper;
    }

    public SubArticleHelper getSubArticleHelper() {
        return subArticleHelper;
    }

    public CheckHelper getCheckHelper() {
        return checkHelper;
    }
}
