package ru;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pikabu {

    static WebDriver driver;

    @BeforeEach
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\_openjdk\\demo\\SeleniumWD\\SeleniumWD\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void authorizPikabuWebSite() {

        //Перейти на сайт «https://pikabu.ru/».
        driver.get("https://pikabu.ru/");
        //Убедиться, что текущий URL соответствует ожидаемому
        Assert.assertEquals("https://pikabu.ru/", driver.getCurrentUrl());
        //Убедиться, что заголовок сайта: «Горячее – самые интересные и обсуждаемые посты | Пикабу».
        Assert.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", driver.getTitle());

        //Кликнуть на кнопку «Войти».
        driver.findElement(By.cssSelector("button.header-right-menu__login-button")).click();

        //Ждем появления модального окна
        try {
            Thread.sleep(2000); //Лучше использовать WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Убедиться, что отображается модальное окно «Авторизация»
        WebElement authModal = driver.findElement(By.cssSelector("div.auth-modal"));
        Assert.assertTrue(authModal.isDisplayed());

        //Убедиться, что отображаются поля «Логин» и «Пароль»
        WebElement loginField = driver.findElement(By.xpath("//div[contains(@class, 'auth-modal')]//input[@placeholder='Логин']"));
        WebElement passwordField = driver.findElement(By.xpath("//div[contains(@class, 'auth-modal')]//input[@placeholder='Пароль']"));
        Assert.assertTrue(loginField.isDisplayed());
        Assert.assertTrue(passwordField.isDisplayed());

        //Ввести в поля данные в формате логин/пароль – Qwerty/Qwerty
        loginField.sendKeys("Qwerty");
        passwordField.sendKeys("Qwerty");

        //Нажать кнопку «Войти»
        driver.findElement(By.xpath("//div[contains(@class, 'auth-modal')]//span[text()=\"Войти\"]/parent::button")).click();

        //Ждем появления сообщения об ошибке
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Убедиться, что появилось сообщение об ошибке, и его текст: «Ошибка. Вы ввели неверные данные авторизации».
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'auth-modal')]//span[contains(text(), \"Ошибка. Вы ввели неверные данные авторизации\")]"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals("Ошибка. Вы ввели неверные данные авторизации", errorMessage.getText());
    }

        @AfterEach
        public void tearDown() {
            if (driver !=null) {driver.quit();}
    }
}