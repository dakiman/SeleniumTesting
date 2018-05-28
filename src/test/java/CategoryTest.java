import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.RandomUtils;

class CategoryTest extends WebDriverConfig {

    @Test
    void categoryShouldGetCreated() {
        //Navigate to the categories creation page
        driver.get(baseUrl + "/admin/categories/create");
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

        //navigate to index page for categories and assert that the item we have created exists
        driver.get(baseUrl + "/admin/categories");
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));
    }

}
