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

public class FramesTest {
    /*
    Frames https://the-internet.herokuapp.com/frames
    - Открыть iFrame
    - Проверить, что текст внутри параграфа равен "Your content goes here."
     */

    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com/frames";
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void iFrameContentTest() {
        // Кликнуть по ссылке "iFrame"
        driver.findElement(By.linkText("iFrame")).click();

        // Явное ожидания WebDriverWait с таймаутом 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Условие ожидания доступности фрейма
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("mce_0_ifr")));

        // Ожидаем, пока параграф загрузится (явное ожидание)
        WebElement paragraph = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tinymce p")));

        // Считываем текст параграфа
        String actualText = paragraph.getText();

        // Проверяем, что текст соответствует ожидаемому значению с помощью SoftAssert
        softAssert.assertEquals(actualText, "Your content goes here.",
                "Текст в iframe не совпадает");

        // Выход из iframe
        driver.switchTo().defaultContent();

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}