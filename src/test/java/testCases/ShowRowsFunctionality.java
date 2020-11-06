package test.java.testCases;

import main.java.pageObject.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import test.java.BaseTest;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ShowRowsFunctionality extends BaseTest {

    HomePage homePage = new HomePage();

    @BeforeTest
    public void setUp()  {
        setupDriver();
        homePage.navigateToHomePage();

    }

    @Test(priority = 1)
    public void validateShow100RowsFiltering()
    {
        homePage.clickOnSelect100Rows();

        List<WebElement> results = homePage.getTableRows();
        int resultsSize = results.size() - 1;

        assertEquals(resultsSize, 100);
    }

    @Test(priority = 2)
    public void validateShow50RowsFiltering()
    {
        homePage.clickOnSelect50Rows();

        List<WebElement> results = homePage.getTableRows();
        int resultsSize = results.size() - 1;

        assertEquals(resultsSize, 50);
    }

    @Test(priority = 3)
    public void validateShow20RowsFiltering()
    {
        homePage.clickOnSelect20Rows();

        List<WebElement> results = homePage.getTableRows();
        int resultsSize = results.size() - 1;

        assertEquals(resultsSize, 20);
    }

    @AfterTest
    public void tearDown() {
        teardown();
    }

}
