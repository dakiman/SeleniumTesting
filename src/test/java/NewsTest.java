import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import utils.RandomUtils;

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
    void newsCanBeEdited() {
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

        WebElement editBtn = driver.findElement(By.xpath("//div[div/text() = '"+title_mk_value+"']/a/button[contains(@class, 'btn-success')]"));
        editBtn.click();
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));

        title_en = driver.findElement(By.name("title_en"));
        description_en = driver.findElement(By.name("description_en"));
        title_mk = driver.findElement(By.name("title_mk"));
        description_mk = driver.findElement(By.name("description_mk"));

        title_mk_value = RandomUtils.randomString(10);
        String description_mk_value = RandomUtils.randomString(10);
        String title_en_value = RandomUtils.randomString(10);
        String description_en_value = RandomUtils.randomString(10);

        title_en.sendKeys(title_en_value);
        description_en.sendKeys(description_en_value);
        title_mk.sendKeys(title_mk_value);
        description_mk.sendKeys(description_mk_value);
        driver.findElement(By.xpath("//button[contains(@class, 'btn-primary')]")).click();

        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));
        Assert.assertTrue(driver.getPageSource().contains(description_mk_value));
        Assert.assertTrue(driver.getPageSource().contains(title_en_value));
        Assert.assertTrue(driver.getPageSource().contains(description_en_value));
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

        //navigate to index page for news and assert that the item we created exists
        driver.get(baseUrl + "/admin/news");
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));
        //find and click the delete button of the element we created
        WebElement deleteBtn = driver.findElement(By.xpath("//div[div/text() = '"+ title_mk_value +"']/button[contains(@class, 'btn-delete')]"));
        deleteBtn.click();
        //wait for modal to appear and click confirm
        WebElement confirmBtn = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btn-confirm']")));
        confirmBtn.click();
        //check if container for item is visible
        WebElement box = driver.findElement(By.xpath("//div[div/div/text() = '"+title_mk_value+"']"));
        Assert.assertFalse(box.isDisplayed());
    }
}
