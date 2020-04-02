package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.risad.test.model.SubArticle;

public class SubArticleHelper extends HelperBase {

    public SubArticleHelper(WebDriver driver) {
        super(driver);
    }

    //функция для добавления подстатьи без обращения к кнопке добавления (т.е. первая подстатья, грид которой сразу отображается на форме создания объекта программы работ)
    public void fillSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span/span"));
        click(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']"));
        click(By.xpath("//div[@class='k-animation-container'][4]//li[contains(text(),'" + subArticle.getSelectTypeWork() + "')]"));
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }

    public void editSubArticleFirst(SubArticle subArticle) {
        //ДОБАВЛЕНИЕ ПОДСТАТЕЙ В ОБЪЕКТ ПРОГРАММЫ РАБОТ
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownArticle']/span"));
        click(By.xpath("//li[contains(.,'" + subArticle.getNumberArticle() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']/span[@class='k-widget k-dropdown dropDownJobType']"));
        click(By.xpath("//li[contains(.,'" + subArticle.getSelectTypeWork() + "')]"));

        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).clear();
        click(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='k-formatted-value textBoxValueYear k-input']"));
        driver.findElement(By.xpath("//tbody[@role='rowgroup']//td[@role='gridcell']//input[@class='textBoxValueYear k-input']")).sendKeys(subArticle.getCostArticle());																									//Ввод значения в поле
    }
}
