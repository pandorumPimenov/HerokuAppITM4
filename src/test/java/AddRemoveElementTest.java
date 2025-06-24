import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/*
1. Add/Remove Elements - добавить 2 элемента, удалить элемент,
проверить количество элементов DELETE
Локаторы xpath:
a. By.xpath("//button[text()='Add Element']")
b. By.xpath("//button[text()='Delete']")
 */

public class AddRemoveElementTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        softAssert = new SoftAssert();
    }

    @Test
    public void addRemoveElementsTest() {

        // 1. Добавляем 2 элемента
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        addButton.click();

        // 2. Проверяем, что добавилось 2 элемента
        int initialCount = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(initialCount, 2);

        // 3. Удаляем первый элемент
        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        // 4. Проверяем, что остался 1 элемент
        int remainingCount = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(remainingCount, 1);

        // 5. Проверяем оставшуюся кнопку
        WebElement lastDeleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        softAssert.assertTrue(lastDeleteButton.isDisplayed());
        softAssert.assertTrue(lastDeleteButton.isEnabled());
        softAssert.assertEquals(lastDeleteButton.getText(), "Delete");

        softAssert.assertAll(); // Выполнение всех проверок и вывод результатов
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
