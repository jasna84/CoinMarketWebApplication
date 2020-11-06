package main.java.pageObject;

import main.java.config.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WatchlistPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(WatchlistPage.class.getSimpleName());

    By tableRows = By.xpath("//tbody/tr");
    By watchlistIconStarFilled = By.className("icon-Star-Filled");
    By emptyWatchlistNotification = By.xpath("//h3[contains(text(), 'Your watchlist is empty')]");
    By buildWatchlistButton = By.linkText("Build my Watchlist");

    public int countTableRows() {
        hardCodedWaiter(2000);
        List<WebElement> rows = getListOfElements(tableRows);
        return rows.size();
    }

    public void removeItemsFromWatchlist() {
        List<WebElement> icons = getListOfElements(watchlistIconStarFilled);
        for(WebElement icon : icons){
            clickJS(icon);
            System.out.println("Uncheck executed");
        }
        logger.info("Items removed from the Watchlist");
    }

    public boolean notificationExists() {
       return waitForElement(emptyWatchlistNotification, 3);
    }

    public boolean buildWatchlistButtonExists() {
        return waitForElement(buildWatchlistButton, 3);
    }

    public void buildWatchlist() {
        clickJS(buildWatchlistButton);
        waitForUrl(data.getProperties("homeUrl"));
        logger.info("Switched to home page from the watchlist button");
    }

}
