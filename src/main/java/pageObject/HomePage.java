package main.java.pageObject;

import main.java.config.BasePage;
import main.java.config.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class HomePage extends BasePage {

    public void navigateToHomePage() {
        Driver.getInstance().getDriver().navigate().to((data.getProperties("homeUrl")));
        String currentPage = getUrl();
        String expectedPage = data.getProperties("homeUrl");
        Assert.assertEquals(currentPage, expectedPage);
        logger.info("Home page successfully navigated");
    }

    private static final Logger logger = LogManager.getLogger(HomePage.class.getSimpleName());

    By mainFilter = By.className("table-control-filter");
    By priceMin = By.cssSelector("input[data-qa-id='range-filter-input-min']");
    By priceMax = By.cssSelector("input[data-qa-id='range-filter-input-max']");
    By applyButton = By.cssSelector("button[data-qa-id=filter-dd-button-apply]");
    By menuButtons = By.cssSelector("button[data-qa-id=filter-dd-toggle]");
    By watchlistIconStar = By.className("icon-Star");
    By watchlistLink = By.linkText("Watchlist");
    By showRows100 = By.xpath("//*[@id='__next']/div/div[2]/div[1]/div/div[1]/div[2]/div/div[contains(text(), '100')]");
    By showRows50 = By.xpath("//*[@id='__next']/div/div[2]/div[1]/div/div[1]/div[2]/div/div[contains(text(), '50')]");
    By showRows20 = By.xpath("//*[@id='__next']/div/div[2]/div[1]/div/div[1]/div[2]/div/div[contains(text(), '20')]");
    By showRowsMain = By.xpath("//*[@id='__next']/div/div[2]/div[1]/div/div[1]/div[2]/div/div");
    By tableRows = By.xpath("//tbody/tr");


    public void clickOnMainFilter() {
        click(mainFilter);
    }

    public void clickOnPriceButton(){
        List<WebElement> filters = getListOfElements(menuButtons);
        for(WebElement filter : filters) {
            if(filter.getText().equals("Price")){
                filter.click();
            }
        }
    }

    public void sendMinPrice(){
        sendText(priceMin, "2000");
    }

    public void sendMaxPrice(){
        String placeholderText = getElementAttribute(priceMax, "placeholder");
        if(placeholderText != "$99,999") {
            sendText(priceMax, "99999");
        }
        click(applyButton);
    }

    public void addFiveItemsToWatchlist() {
        for(int i = 1; i < 6; i++){
            List<WebElement> icons = getListOfElements(watchlistIconStar);
            clickJS(icons.get(i));
            System.out.println("Check executed");
        }
        logger.info("Items added to the Watchlist");
    }

    public void goToWatchlistPage() {
        click(watchlistLink);
        String expectedPage = data.getProperties("watchlistUrl");
        waitForUrl(expectedPage);
        String currentPage = getUrl();
        Assert.assertEquals(currentPage, expectedPage);
        logger.info("Watchlist page successfully navigated");

    }

    public void clickOnSelect20Rows () {
        click(showRowsMain);
        clickJS(showRows20);
        logger.info("20 rows selected");
    }

    public void clickOnSelect50Rows () {

        click(showRowsMain);
        clickJS(showRows50);
        logger.info("50 rows selected");
    }

    public void clickOnSelect100Rows () {

        click(showRowsMain);
        clickJS(showRows100);
        logger.info("100 rows selected");
    }

    public List<WebElement> getTableRows() {
        hardCodedWaiter(3000);
        return getListOfElements(tableRows);
    }

}
