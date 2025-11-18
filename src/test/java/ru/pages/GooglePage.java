package ru.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Google
public class GooglePage {

    protected WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(css = "div#search cite")
    private WebElement firstSearchResult;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openGoogle() {
        driver.get("https://www.google.com/");
    }

    public void searchFor(String searchText) {
        searchInput.sendKeys(searchText);
        searchInput.submit();
    }

    public void clickFirstSearchResult() {
        firstSearchResult.findElement(By.xpath("./..")).click(); //жмём
    }
}