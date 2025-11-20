package ru.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Google
public class GooglePage {

    private SelenideElement searchInput = $("input[name='q']");
    private SelenideElement firstSearchResult = $("div#search cite");

    /*protected WebDriver driver;

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
    }*/

    public void openGoogle() {
        open("https://www.google.com/");
    }

    public void searchFor(String searchText) {
        /*searchInput.sendKeys(searchText);
        searchInput.submit();*/
        searchInput.shouldBe(visible).setValue(searchText).pressEnter();
    }

    public void clickFirstSearchResult() {
        /*firstSearchResult.findElement(By.xpath("./..")).click(); //жмём*/
        firstSearchResult.shouldBe(visible).parent().click();
    }
}