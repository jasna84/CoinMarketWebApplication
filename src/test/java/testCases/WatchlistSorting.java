package test.java.testCases;

import main.java.pageObject.HomePage;
import main.java.pageObject.WatchlistPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.BaseTest;

public class WatchlistSorting extends BaseTest {

    HomePage homePage = new HomePage();

    @BeforeClass
    public void setUp() {
        setupDriver();
        homePage.navigateToHomePage();
    }

    @Test
    public void priceSortingIsValid() {



    }

    @Test
    public void hoursSortingIsValid() {



    }

    @Test
    public void daysSortingIsValid() {



    }

    @Test
    public void marketCapSortingIsValid() {



    }

    @Test
    public void volumeSortingIsValid() {



    }

    @Test
    public void circulatingSupplySortingIsValid() {



    }

    @AfterClass
    public void tearDown(){
        teardown();
    }

}
