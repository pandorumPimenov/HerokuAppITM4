import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/*
2. Checkboxes - проверить, что первый чекбокс unchecked, отметить
первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
checked, сделать unheck, проверить, что он unchecked
Локатор: By.cssSelector("[type=checkbox]”)
 */

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkboxesTest() {

        // 1. Получаем первый чекбокс
        WebElement checkbox1 = driver.findElements(By.cssSelector("[type=checkbox]")).get(0);

        // 2 Проверяем, что первый чекбокс не отмечен
        assertFalse("Первый чекбокс должен быть unchecked", checkbox1.isSelected());

        // 3. Отмечаем первый чекбокс
        checkbox1.click();
        assertTrue("Первый чекбокс должен быть checked после клика", checkbox1.isSelected());

        // 4. Получаем второй чекбокс
        WebElement checkbox2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1);

        // 5. Проверяем, что второй чекбокс отмечен
        assertTrue("Второй чекбокс должен быть checked", checkbox2.isSelected());

        // 6. Снимаем отметку со второго чекбокса
        checkbox2.click();
        assertFalse("Второй чекбокс должен быть unchecked после клика", checkbox2.isSelected());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
