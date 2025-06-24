import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
4. Inputs - Проверить на возможность ввести различные цифровые и
нецифровые значения, используя Keys.ARROW_UP И
Keys.ARROW_DOWN
Локатор: By.tagName(“input”)
 */

public class InputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/inputs");
    }

    @Test
    public void inputTest() {
        // Находим элемент input (поле ввода) на странице по его HTML-тегу <input>
        WebElement input = driver.findElement(By.tagName("input"));

        // 1. Ввод цифрового значения
        input.sendKeys("456");
        System.out.println("Введено число: " + input.getAttribute("value"));

        // Очистка поля
        input.clear();

        // 2. Попытка ввода букв
        input.sendKeys("MATRESHKA");
        System.out.println("После букв: " + input.getAttribute("value")); // Должно быть пусто

        // 3. Использование стрелок для изменения значения
        input.sendKeys(Keys.ARROW_UP);
        System.out.println("После стрелки ВВЕРХ: " + input.getAttribute("value")); // Должно быть 1

        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ARROW_DOWN);
        System.out.println("После двух стрелок ВНИЗ: " + input.getAttribute("value")); // Должно быть -1
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
