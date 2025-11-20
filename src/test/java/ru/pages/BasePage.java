package ru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.title;

//Базовая страница (наследование)
public class BasePage {

    /*protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        PageFactory.initElements(driver, this);
    }*/

    public String getPageTitle() {
        //return driver.getTitle();
        return title();
    }
}