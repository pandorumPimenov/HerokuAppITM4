import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class FileUploadTest {
    /*
    File Upload https://the-internet.herokuapp.com/upload
    - загрузить файл
    - проверить, что имя файла на странице совпадает с именем
    загруженного файла
     */
    WebDriver driver;
    String URL = "https://the-internet.herokuapp.com/upload";
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void fileUploadTest() {
        // Создаем объект File и загружаем его
        File file = new File("src/test/resources/TestPictureFile.PNG");
        driver.findElement(By.cssSelector("[type = file]")).sendKeys(file.getAbsolutePath());

        // Отправляем форму
        driver.findElement(By.id("file-submit")).click();

        // Ожидаем появления элемента, который содержит имя загруженного файла
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String uploadedFileName = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files"))).getText();

        // Проверяем, что имя загруженного файла соответствует ожидаемому
        assertEquals(uploadedFileName, "TestPictureFile.PNG", "File name doesn't match");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
