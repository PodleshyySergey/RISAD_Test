package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.risad.test.model.User;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver driver) {
        super(driver);
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

    public void logout() {
        click(By.xpath("//div[@class='user-panel logout item']//input[@type='submit']"));
        click(By.xpath("//a[@class='PostLogoutRedirectUri']"));
    }
}
