package test.java.testCases;

import main.java.pageObject.HomePage;
import main.java.pageObject.WatchlistPage;
import org.testng.Assert;
import org.testng.annotations.*;
import test.java.BaseTest;

public class Watchlist extends BaseTest {

    HomePage homePage = new HomePage();
    WatchlistPage watchlistPage = new WatchlistPage();


    @BeforeClass
    public void setUp() {
        setupDriver();
        homePage.navigateToHomePage();
    }

    @Test(priority = 1)
    public void addToWatchlistFromHomePage() {

        homePage.clickOnMainFilter();
        homePage.clickOnPriceButton();
        homePage.sendMinPrice();
        homePage.sendMaxPrice();
        homePage.addFiveItemsToWatchlist();
        homePage.goToWatchlistPage();

        int itemNumber = watchlistPage.countTableRows();
        Assert.assertEquals(itemNumber, 5);

    }

    @Test(priority = 2, dependsOnMethods = {"addToWatchlistFromHomePage"})
    public void removeFromWatchlist() {

        watchlistPage.removeItemsFromWatchlist();

        boolean watchlistEmpty = watchlistPage.notificationExists();
        Assert.assertTrue(watchlistEmpty);

        boolean buildWatchlistButton = watchlistPage.buildWatchlistButtonExists();
        Assert.assertTrue(buildWatchlistButton);

    }

    @Test(priority = 3, dependsOnMethods = {"removeFromWatchlist"})
    public void buildWatchlist() {

        watchlistPage.buildWatchlist();
        homePage.clickOnMainFilter();
        homePage.clickOnPriceButton();
        homePage.sendMinPrice();
        homePage.sendMaxPrice();
        homePage.addFiveItemsToWatchlist();
        homePage.goToWatchlistPage();

        int itemNumber = watchlistPage.countTableRows();
        Assert.assertEquals(itemNumber, 5);

    }







    @AfterClass
    public void tearDown(){
        teardown();
    }
}
