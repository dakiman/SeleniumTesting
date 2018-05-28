import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.RandomUtils;

import java.util.List;

class ItemsTest extends WebDriverConfig {

    @Test
    void itemShouldGetCreated() {
        //Navigate to the items creation page
        driver.get(baseUrl + "/admin/items/create");
        //Create the items title first so we can use it to confirm its existence
        String title_mk_value = RandomUtils.randomString(10);
        //get the input fields
        WebElement title_en = driver.findElement(By.name("title_en"));
        WebElement description_en = driver.findElement(By.name("description_en"));
        WebElement title_mk = driver.findElement(By.name("title_mk"));
        WebElement description_mk = driver.findElement(By.name("description_mk"));
        WebElement upload = driver.findElement(By.name("cat_img[]"));

        //Get the select dropdown and count the number of options. Randomize the selected index
        Select category_select = new Select(driver.findElement(By.name("category_id")));
        List<WebElement> select_list = category_select.getOptions();
        int category_index = RandomUtils.randomInt(1, select_list.size());

        //populate the form
        title_en.sendKeys(RandomUtils.randomString(10));
        description_en.sendKeys(RandomUtils.randomString(30));
        title_mk.sendKeys(title_mk_value);
        description_mk.sendKeys(RandomUtils.randomString(30));
        //using the randomized index here
        category_select.selectByIndex(category_index);
        upload.sendKeys(resourceDir + "J9chN5s.jpg");

        //submit the form
        title_en.submit();
        //assert success message is present on screen
        Assert.assertTrue(driver.getPageSource().contains("Успешно додадено."));

        //navigate to index page for categories
        driver.get(baseUrl + "/admin/items");
        //find the select dropdown and select the option we used before
        category_select = new Select(driver.findElement(By.id("selector")));
        category_select.selectByIndex(category_index);
        //assert that the item is present within the index of that category
        Assert.assertTrue(driver.getPageSource().contains(title_mk_value));
    }

}
