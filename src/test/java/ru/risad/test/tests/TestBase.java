package ru.risad.test.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.risad.test.appmanager.ApplicationManager;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager();
    public String baseURL = "https://172.16.10.57:7443/";

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }


    public ApplicationManager getApp() {
        return app;
    }
}
