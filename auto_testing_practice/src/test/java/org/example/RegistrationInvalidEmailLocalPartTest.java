package org.example;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class RegistrationInvalidEmailLocalPartTest extends BeginningClass {

    //Определение полей
    private final By FIRST_NAME = By.cssSelector("input[name='firstname'], input[placeholder='Имя'], input[placeholder='First Name']");
    private final By LAST_NAME = By.cssSelector("input[name='lastname'], input[placeholder='Фамилия'], input[placeholder='Last Name']");
    private final By EMAIL = By.cssSelector("input[name='email'], input[placeholder='E-Mail'], input[type='email']");
    private final By PASSWORD = By.cssSelector("input[name='password'], input[placeholder='Пароль'], input[placeholder='Password']");
    private final By AGREE = By.name("agree");
    private final By CONTINUE_BTN = By.xpath(
            "//button[normalize-space()='Продолжить' or @id='button-register' or @type='submit'] | //input[@value='Продолжить' or @id='button-register' or @type='submit']"
    );

    //Ввод данных для проверки
    @Test
    public void shouldShowErrorOnInvalidEmailLocalPart() {
        scroll(driver.findElement(CONTINUE_BTN));
        type(FIRST_NAME, "Татьяна");
        type(LAST_NAME,  "Миндалева");
        type(EMAIL, "아몬드$@mail.ru"); //Некорректный E-MAIL
        type(PASSWORD, "X8n2Q7pR9tK4sJ6vM3w");
        acceptPrivacy(); //Соглашение с политикой конфиденциальности
        click(CONTINUE_BTN);  //Нажатие на кнопку "Продолжить"
    }

    //Взаимодействие с областью ввода текста
    protected void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        el.sendKeys(text);
    }

    public void scroll(WebElement element){

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Соглашение с политикой конфиденциальности (метод)
    private void acceptPrivacy() {
        try {
            WebElement cb = driver.findElement(AGREE);
            if (!cb.isSelected()) cb.click();
        } catch (NoSuchElementException e) {
            click(By.xpath("//label[contains(.,'Privacy Policy') or contains(.,'Политика конфиденциальности')]"));
        }
    }

    //Метод нажатия на кнопку "Продолжить"
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
