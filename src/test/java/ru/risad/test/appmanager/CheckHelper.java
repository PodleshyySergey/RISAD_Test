package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.risad.test.model.ObjectPWfillTop;
import ru.risad.test.model.ObjectWork;
import ru.risad.test.model.SubArticle;

public class CheckHelper  extends HelperBase {

    public CheckHelper(WebDriver driver) {
        super(driver);
    }

    public void findNameObject(String nameObject) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObject + "')]"));
    }

    public void checkStatusObject(String nameObjectTemp, final String status) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObjectTemp + "')]/i[@title='" + status + "']"));
    }

    public void checkParamsObject(String nameObject, ObjectPWfillTop objectPWfillTop, SubArticle subArticle, ObjectWork objectWork, String yearProgramWork) {
        driver.findElement(By.xpath("//td[contains(.,'" + nameObject + "')]"));
        String uid = driver.findElement(By.xpath("//td[contains(.,'" + nameObject + "')]/..")).getAttribute("data-uid");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[1]")).getText(), objectWork.getTypeAndDateRepairRoad());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[2]")).getText(), objectWork.getValueRoadTraffic() + ",000");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[3]")).getText(), objectWork.getCategoryRoad());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[4]")).getText(), objectPWfillTop.getTypeOfWork());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[5]")).getText(), yearProgramWork + " - " + yearProgramWork);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[6]")).getText(), subArticle.getCostArticle());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[7]")).getText(), objectPWfillTop.getScopeWork());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[8]")).getText(), objectPWfillTop.getScopeWork());
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']//tr[@data-uid='" + uid + "']/td[9]")).getText(), subArticle.getCostArticle());




    }

}
