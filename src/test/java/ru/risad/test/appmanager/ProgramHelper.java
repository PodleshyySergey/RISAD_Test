package ru.risad.test.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProgramHelper extends HelperBase  {

    public ProgramHelper(WebDriver driver) {
        super(driver);
    }

    public void pushApprovalPWbyFDA() {
        click(By.xpath("//button[@title='Утвердить']/span"));
    }

    public void selectProgramWork(String idProgramWork) {
        click(By.id(idProgramWork));
    }

    public void choiceYear(String year) {

//      Реализация с очисткой поля и ввода года "с клавиатуры" (иногда дает сбои, почему-то вводятся не все цифры из числа года)
//      После добавления цикла работает без сбоев
        while (!driver.findElement(By.xpath("//input[@id='program_year']")).getAttribute("value").equals(year)) {
            type(By.xpath("//input[@id='program_year']"),year);
        }
    }

    public void choiceSection(String section) {
        click(By.xpath("//span[contains(text(), '"+ section + "')]"));
    }

    public void createProgramWorkIfNotCreatedLater() {
        if (isElementPresent(By.xpath("//div[@id='dialog']/p[1]"))) {
            click(By.xpath("//button[contains(.,'Ok')]"));
        }

    }

    public void createProgramWork() {
        click(By.xpath("//button[contains(.,'Ok')]"));
    }

    public void pushFomationPW() {
        click(By.xpath("//button[@title='Сформировать']"));
    }
}
