import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertFalse;

/*
7. * Hovers - Сделать цепочку из действий: наведение на профиль,
проверка имени, клик по ссылке, проверка, что нет 404 ошибки. Повторить
для каждого из профилей. Использовать класс Actions и
https://stackoverflow.com/questions/17293914/how-to-perform-mouseover-function
-in-selenium-webdriver-using-java
 */

public class HoversTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");
        softAssert = new SoftAssert();
    }

    @Test
    public void testProfiles() {
        Actions actions = new Actions(driver);

        // Для каждого из 3 профилей
        for (int i = 1; i <= 3; i++) {
            // 1. Наводим курсор на профиль
            WebElement profile = driver.findElement(By.xpath("(//div[@class='figure'])[" + i + "]"));
            actions.moveToElement(profile).perform();

            // 2. Проверяем имя
            String name = profile.findElement(By.tagName("h5")).getText();
            System.out.println("Profile " + i + " name: " + name);

            // 3. Кликаем ссылку
            profile.findElement(By.tagName("a")).click();

            // 4. Проверяем что нет 404 - теперь проверяем содержание страницы
            assertFalse(driver.getPageSource().contains("Not Found"),
                    "Страница содержит 'Not Found' - значит 404 ошибка");

            // Возвращаемся назад
            driver.navigate().back();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Метод quit() закрывает браузер и все связанные с ним окна и вкладки,
        // а также завершает сессию WebDriver, освобождая ресурсы
        driver.quit();
    }
}
