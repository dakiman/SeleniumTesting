import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class CarouselTest extends WebDriverConfig {

    @Test
    void carouselImageShouldGetAdded() {
        driver.get(baseUrl + "/admin/gallery/create");
        WebElement upload = driver.findElement(By.name("car_img"));
        upload.sendKeys(resourceDir + "J9chN5s.jpg");
        upload.submit();
        Assert.assertTrue(driver.getPageSource().contains("Сликата е успешно додадена!"));
    }

}
