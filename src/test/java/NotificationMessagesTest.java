import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/*
8. * Notification Messages - кликнуть на кнопку, дождаться появления
нотификации, проверить соответствие текста ожиданиям
 */

public class NotificationMessagesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    @Test
    public void notificationMessagesTest() {
        // 1. Находим и кликаем на кнопку
        WebElement button = driver.findElement(By.linkText("Click here"));
        button.click();

        // 2. Получаем текст уведомления
        WebElement notification = driver.findElement(By.id("flash"));
        String notificationText = notification.getText().trim();

        // 3. Проверяем, что текст уведомления соответствует одному из ожидаемых
        assertTrue(notificationText.contains("Action successful")
                        || notificationText.contains("Action unsuccesful, please try again"),
                "Текст уведомления не соответствует ожидаемому. Фактический текст: " + notificationText);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
