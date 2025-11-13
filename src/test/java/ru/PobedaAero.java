package ru;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static junit.framework.TestCase.assertEquals;

public class PobedaAero {

    static WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\_openjdk\\demo\\SeleniumWD\\SeleniumWD\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        /*Указаны неявные ожидания вида pageLoadTimeout() и implicitlyWait() перед выполнением тестового скрипта для успешной отработки пунктов 1 и 2.*/
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(130));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

        wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        driver.manage().window().maximize();
    }

    @Test
    public void testPobedaAero() {

        //Открыть сайт google.com и ввести в строку поиска «Сайт компании Победа»
        driver.get("https://www.google.com/");

        WebElement searchBox = driver.findElement(By.xpath("//textarea[@name='q']"));
        searchBox.sendKeys("Сайт компании Победа");
        searchBox.submit();

        //Дождаться прогрузки страницы с результатами поиска, после чего кликнуть на первую ссылку (https://www.pobeda.aero/)
        WebElement firstLink = driver.findElement(By.cssSelector("div#search cite"));

        //Кликнем на родительский элемент ссылки
        firstLink.findElement(By.xpath("./..")).click();

        /*Дождаться прогрузки страницы АК «Победа», после чего дождаться появления картинки
        с текстом «Полетели в Калининград» и проверить, что текст на странице действительно совпадает с текстом «Полетели в Калининград!»*/
        waitForKaliningrad();

        /*Кликнуть на переключатель языка, выбрать английский язык и убедиться,
        что на главной странице отображаются тексты "Ticket search", "Online check-in", "Manage my booking"*/
        switchToEnglishAndVerifyTexts();
    }

    private void waitForKaliningrad() {
        /*кастомное ожидание текста «Полетели в Калининград» и есть проверка, что картинка действительно отобразилась верная – для пункта 3*/
        long startTime = System.currentTimeMillis();
        long timeout = 10000; // 10 секунд

        driver.findElement(By.xpath("//button[@data-idx='9']")).click();

        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                //Ищем элемент с текстом "Полетели в Калининград"
                WebElement element = driver.findElement(By.xpath("//div[contains(text(), \"Полетели в Калининград!\")]"));

                if (element.isDisplayed()) {
                    String actualText = element.getText();
                    assertEquals("Текст должен совпадать с 'Полетели в Калининград!'",
                            "Полетели в Калининград!", actualText);
                    System.out.println("Картинка с текстом 'Полетели в Калининград!' успешно отобразилась)");
                    return;
                }
            } catch (Exception e) {
                //если не найден, продолжаем ждать
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        throw new AssertionError("Текст 'Полетели в Калининград!' не отобразился в течение 10 секунд");
    }
    private void switchToEnglishAndVerifyTexts() {

        //Кликнуть на переключатель языка, выбрать английский язык
        driver.findElement(By.xpath("//button[@class='dp-etauof-root-root']")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'dp-8gxax4-root-root')][position()=2]")).click();

        //убедиться, что на главной странице отображаются тексты "Ticket search", "Online check-in", "Manage my booking"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[text()='Ticket search' and position()=2]"), "Ticket search"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[text()='Online check-in' and position()=2]"), "Online check-in"));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[text()='Manage my booking' and position()=2]"), "Manage my booking"));

        System.out.println("На главной странице отображаются тексты Ticket search, Online check-in, Manage my booking");
    }

    @AfterEach
    public void tearDown() {
        if (driver !=null) {driver.quit();}
    }
}