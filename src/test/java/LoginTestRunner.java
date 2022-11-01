import Pages.DashboardPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class LoginTestRunner extends Setup{
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test(priority = 1)
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin("Admin","admin123");
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "viewEmployeeList";
        Assert.assertTrue(urlActual.contains(urlExpected));

    }
    @Test(priority = 2)
    public void ifProfileImageExists(){
        boolean imageDisplayStatus = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(imageDisplayStatus);
    }
    @Test(priority = 3)
    public void selectEmployementStatus(){
       List <WebElement> dropdown = Collections.singletonList(driver.findElement(By.className("oxd-select-text-input")));
        dropdown.get(0).click();
        for(int i = 0;i<4; i++){
            dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
        }
            dropdown.get(0).sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[type=submit]")).click();

    }

    @Test(priority = 4)
    public void getEmployeeStatus(){
        Utils.scrollDown(driver);
        WebElement table =driver.findElement(By.className("oxd-table-body"));
        List <WebElement> allRows= Collections.singletonList(table.findElement((By.cssSelector("[role = row]"))));
        for (WebElement row : allRows){
           List <WebElement>cells=row.findElements(By.cssSelector("[role=cell]"));
           System.out.println(cells.get(5).getText());
            Assert.assertTrue(cells.get(5).getText().contains("Full-Time Probation"));

        }

    }

    @Test(priority = 5)
    public void doLogout(){dashboardPage = new DashboardPage(driver);
    dashboardPage.btnProfileImage.click();
    dashboardPage.linklogOut.click();
    }
}
