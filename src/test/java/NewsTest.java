import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import utils.RandomUtils;

import java.util.concurrent.TimeUnit;

class NewsTest extends WebDriverConfig {

    @Test
    void newsShouldGetCreated() {
        //Navigate to the news creation page
        driver.get(baseUrl + "/admin/news/create");
        //Create the items title first so we can use it to confirm its existence
        String title_mk_value = RandomUtils.randomString(10);

        //get the input fields
        WebElement title_en = driver.findElement(By.name("title_en"));
        WebElement description_en = driver.findElement(By.name("description_en"));
        WebElement title_mk = driver.findElement(By.name("title_mk"));
        WebElement description_mk = driver.findElement(By.name("description_mk"));
        WebElement upload = driver.findElement(By.name("cat_img"));

        //populate the form
        title_en.sendKeys(RandomUtils.randomString(10));
        description_en.sendKeys(RandomUtils.randomString(30));
        title_mk.sendKeys(title_mk_value);
        description_mk.sendKeys(RandomUtils.randomString(30));
        upload.sendKeys(resourceDir + "J9chN5s.jpg");

        //submit the form
        title_en.submit();
        //assert success message is present on screen
        Assert.assertTrue(driver.getPageSource().contains("Успешно додадено."));

        //navigate to index page for news and assert that the news we have created exists
        driver.get(baseUrl + "/admin/news");
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));
    }

    @Test
    void newsShouldGetDeleted() {
        //Navigate to the news creation page
        driver.get(baseUrl + "/admin/news/create");
        //Create the items title first so we can use it to confirm its existence
        String title_mk_value = RandomUtils.randomString(10);

        //get the input fields
        WebElement title_en = driver.findElement(By.name("title_en"));
        WebElement description_en = driver.findElement(By.name("description_en"));
        WebElement title_mk = driver.findElement(By.name("title_mk"));
        WebElement description_mk = driver.findElement(By.name("description_mk"));
        WebElement upload = driver.findElement(By.name("cat_img"));

        //populate the form
        title_en.sendKeys(RandomUtils.randomString(10));
        description_en.sendKeys(RandomUtils.randomString(30));
        title_mk.sendKeys(title_mk_value);
        description_mk.sendKeys(RandomUtils.randomString(30));
        upload.sendKeys(resourceDir + "J9chN5s.jpg");

        //submit the form
        title_en.submit();
        //assert success message is present on screen
        Assert.assertTrue(driver.getPageSource().contains("Успешно додадено."));

        //navigate to index page for news and assert that the news we have created exists
        driver.get(baseUrl + "/admin/news");
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement deleteBtn = driver.findElement(By.xpath("//button[@class='btn btn-delete btn-danger btn-block m-b-1']"));
        deleteBtn.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement confirmBtn = driver.findElement(By.className("mark-as-resolved-btn"));
        confirmBtn.click();
        Assert.assertTrue(!deleteBtn.isDisplayed());
    }

}
