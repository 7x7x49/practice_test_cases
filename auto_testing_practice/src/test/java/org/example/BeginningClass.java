package org.example;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BeginningClass {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    //Количество мс для демонстрации экрана после завершения теста
    private static final long END_SCREEN_MS = 20000;

    protected static final String BASE_URL =
            "http://217.74.37.176/?route=account/register&language=ru-ru";

    @BeforeClass
    public static void setUpClass() {
        //Пути к драйверам
        Path edgePath = Paths.get("src", "test", "resources", "msedgedriver.exe");
        Path msEdgePath = Paths.get("src", "test", "resources", "geckodriver.exe");
        Path chromePath = Paths.get("src", "test", "resources", "chromedriver.exe");

        try {
            if (Files.exists(edgePath)) {
                System.setProperty("webdriver.edge.driver", edgePath.toAbsolutePath().toString());
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                driver = new EdgeDriver(options);
            } else if (Files.exists(msEdgePath)) {
                System.setProperty("webdriver.edge.driver", msEdgePath.toAbsolutePath().toString());
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                driver = new EdgeDriver(options);
            } else if (Files.exists(chromePath)) {
                System.setProperty("webdriver.chrome.driver", chromePath.toAbsolutePath().toString());
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            } else {
                throw new RuntimeException("Не найден ни один драйвер");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации драйвера, скорее всего, у Вас не установлен сам браузер. Ошибка: " + e.getMessage(), e);
        }
    }

    @Before
    public void openPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        sleep(500);
    }

    @After
    public void afterEachTest() {
        sleep(END_SCREEN_MS);
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) driver.quit();
    }

    protected static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
