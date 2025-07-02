import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class ContextMenuTest {
    /*
    Context Menu https://the-internet.herokuapp.com/context_menu
    правый клик по элементу
    - валидация текста на алерте
    - закрытие алерта
     */
    WebDriver driver;
    WebDriverWait wait;
    SoftAssert softAssert;
    String URL = "https://the-internet.herokuapp.com/context_menu";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
    }

    @Test
    public void contextMenuAlertTest() {
        // Находим элемент для контекстного меню
        WebElement elementHotspot = driver.findElement(By.id("hot-spot"));

        // Создаем объект Actions
        Actions actions = new Actions(driver);
        // Выполняем правый клик по элементу
        actions.contextClick(elementHotspot).perform();

        // Валидация текста на алерте
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            softAssert.assertEquals(alertText, "You selected a context menu",
                    "Текст алерта не соответствует ожидаемому");

            // Закрытие алерта
            alert.accept();

        } catch (TimeoutException e) {
            softAssert.fail("Alert не появился в течение ожидаемого времени");
        }

        // Проверяем все утверждения
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
