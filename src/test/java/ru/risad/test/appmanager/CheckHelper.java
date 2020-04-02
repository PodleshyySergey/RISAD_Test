package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckHelper  extends HelperBase {

    public CheckHelper(WebDriver driver) {
        super(driver);
    }

    public void findNameObject(String nameObjectTemp) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]"));
    }

    public void checkStatusObject(String nameObjectTemp, final String status) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]/i[@title='" + status + "']"));
    }
}
