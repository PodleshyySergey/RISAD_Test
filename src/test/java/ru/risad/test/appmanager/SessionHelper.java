package ru.risad.test.appmanager;

import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void refreshCurrentWindow() {
        driver.navigate().refresh();
    }

    public void gotoResource(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }
}
