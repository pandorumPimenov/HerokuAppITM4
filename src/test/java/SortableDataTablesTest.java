import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/*
6. * Sortable Data Tables - Проверить содержимое нескольких (3-5) ячеек
таблицы. Использовать xpath типа //table//tr[1]//td[1] - получение первой
ячейки из первого ряда первой таблицы и так далее
 */

public class SortableDataTablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void tableCellsContentTest() {
        // Проверка содержимого нескольких ячеек в первой таблице

        // 1. Получаем первую ячейку из первого ряда (фамилия)
        WebElement cell1 = driver.findElement(By.xpath("//table//tr[1]//td[1]"));
        assertEquals(cell1.getText(), "Smith", "Проверка ячейки (1,1)");

        // 2. Получаем вторую ячейку из первого ряда (имя)
        WebElement cell2 = driver.findElement(By.xpath("//table//tr[1]//td[2]"));
        assertEquals(cell2.getText(), "John", "Проверка ячейки (1,2)");

        // 3. Получаем третью ячейку из первого ряда (email)
        WebElement cell3 = driver.findElement(By.xpath("//table//tr[1]//td[3]"));
        assertEquals(cell3.getText(), "jsmith@gmail.com", "Проверка ячейки (1,3)");

        // 4. Получаем первую ячейку из второго ряда (фамилия)
        WebElement cell4 = driver.findElement(By.xpath("//table//tr[2]//td[1]"));
        assertEquals(cell4.getText(), "Bach", "Проверка ячейки (2,1)");

        // 5. Получаем четвертую ячейку из пятого ряда (web-сайт)
        WebElement cell5 = driver.findElement(By.xpath("//table//tr[4]//td[5]"));
        assertEquals(cell5.getText(), "http://www.timconway.com", "Проверка ячейки (4,5)");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
