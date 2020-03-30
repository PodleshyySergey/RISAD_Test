package ru.risad.test.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.risad.test.appmanager.ApplicationManager;
import ru.risad.test.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestBase extends ApplicationManager {
    public String baseURL = "https://172.16.10.57:7443/";

    @BeforeMethod
    public void setUp() {
        init();
    }

    @AfterMethod
    public void tearDown() {
        stop();
    }


}
