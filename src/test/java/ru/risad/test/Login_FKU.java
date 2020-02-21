package ru.risad.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Login_FKU {
//    WebDriver driver;

//    @BeforeTest
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }

    @Test
    public void loginFKU(WebDriver driver) {

        driver.get("https://172.16.10.57:7443/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[@id='details-button']")).click();
        driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
        driver.findElement(By.id("Username")).click();
        driver.findElement(By.id("Username")).sendKeys("baykal01");
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys("Orator16");
        driver.findElement(By.name("button")).click();
    }
}
