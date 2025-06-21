import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
5. Typos - Проверить соответствие параграфа орфографии
Локатор: By.tagName(“p”)
 */

public class TyposTest {
    WebDriver driver;
    // Ожидаемый правильный текст, с которым будем сравнивать
    String CORRECT_TEXT = "Sometimes you'll see a typo, other times you won't.";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/typos");
    }

    @Test
    public void typosTest() {
        // Находим второй параграф на странице (индекс 1)
        WebElement paragraph = driver.findElements(By.tagName("p")).get(1);
        // Получаем текст из этого параграфа
        String actualText = paragraph.getText();

        // Сравниваем полученный текст с ожидаемым
        if (!actualText.equals(CORRECT_TEXT)) {
            // Если тексты не совпадают, выводим сообщение об ошибке
            System.out.println("Найдена опечатка! Ожидалось: '" + CORRECT_TEXT
                    + "', а получили: '" + actualText + "'");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
