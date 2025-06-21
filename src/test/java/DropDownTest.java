import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;

/*
3. Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
Локатор: By.id(“dropdown”)
 */

public class DropDownTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void dropdownTest() {
        // 1. Находим элемент dropdown
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        // 2. Проверяем наличие первого элемента (дефолтного)
        WebElement option1 = dropdownElement.findElement(By.xpath(".//option[1]"));
        assertEquals(option1.getText(), "Please select an option");

        // 3. Проверяем наличие Option 1
        WebElement option2 = dropdownElement.findElement(By.xpath(".//option[2]"));
        assertEquals(option2.getText(), "Option 1");

        // 4. Проверяем наличие Option 2
        WebElement option3 = dropdownElement.findElement(By.xpath(".//option[3]"));
        assertEquals(option3.getText(), "Option 2");

        // 5. Выбираем Option 1 и проверяем, что он выбран
        dropdown.selectByVisibleText("Option 1");
        assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");

        // 6. Выбираем Option 2 и проверяем, что он выбран
        dropdown.selectByVisibleText("Option 2");
        assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
