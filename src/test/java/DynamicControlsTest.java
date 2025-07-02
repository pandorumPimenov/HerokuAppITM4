import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class DynamicControlsTest {
    /*
    Dynamic Controls https://the-internet.herokuapp.com/dynamic_controls
    - нажать на кнопку Remove около чекбокса
    - дождаться надписи "It's gone"
    - проверить, что чекбокса нет
    - найти инпут
    - проверить, что он disabled
    - нажать на кнопку Enable
    - дождаться надписи "It's enabled!"
    - проверить, что инпут enabled
     */

    WebDriver driver;
    WebDriverWait wait;
    String URL = "https://the-internet.herokuapp.com/dynamic_controls";
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void removeCheckboxTest() {
        // Находим и кликаем кнопку Remove
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));
        removeButton.click();

        // Дождаться надписи "It's gone"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's gone!"));

        // Проверяем, что чекбокса нет
        softAssert.assertTrue(driver.findElements(By.id("checkbox")).isEmpty(),
                "Чекбокс не исчез после нажатия Remove");

        // Находим элемент - поле "input"
        WebElement input = driver.findElement(By.xpath("//input[@type='text']"));

        // Проверяем, что инпут disabled
        softAssert.assertFalse(input.isEnabled(),
                "Инпут не должен быть доступен");

        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        enableButton.click();

        // Ждем появления надписи "It's enabled!"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's enabled!"));

        // Проверяем, что инпут стал enabled
        softAssert.assertTrue(input.isEnabled(),
                "Инпут не стал доступен после нажатия Enable");

        // В конце теста обязательно вызываем assertAll()
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
