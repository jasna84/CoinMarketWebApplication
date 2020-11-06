package main.java.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    public static Integer TIMEOUT_WEBDRIVER_WAIT = 10;

    public BasePage() {
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
        System.out.println("Page created: " + this.getClass().getName());
    }

    private static final Logger logger = LogManager.getLogger(BasePage.class.getSimpleName());

    public static DataReader data = new DataReader();

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)Driver.getInstance().getDriver()).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), 30);
        wait.until(pageLoadCondition);
    }

    public void waitForUrl(String url) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.urlToBe(url));
    }

    public boolean waitForElement(By by, int attempts) {

        boolean condition = false;
        int waitUntilNow = 0;
        while (!condition && waitUntilNow < attempts) {
            try {
                condition = (new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT))
                        .until(sizes -> Driver.getInstance().getDriver().findElements(by).size()!=0);
            } catch(TimeoutException e) {
                System.out.println("Element: "+by+" was not found within 10 sec. Trying again ...");
            }
            waitUntilNow = waitUntilNow + 1;
        }

        if(waitUntilNow == attempts) {
            System.out.println("Element was not found: "+by + " number of attempts: "+attempts+", each attempts lasted 10sec.");
        }

        return condition;
    }

    public void clickJS(By by) {
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].click();", element);
    }

    public void clickJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].click();", element);
    }

    public void click(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.click();
    }

    public void click(WebElement element) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendText(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public String getElementText(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        return element.getText();
    }

    public String getElementAttribute(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        return element.getAttribute(text);
    }

    public List<WebElement> getListOfElements(By by) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        return Driver.getInstance().getDriver().findElements(by);
    }

    public void waitForExceptionsToDisappear(By by) {
        (new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(Driver.getInstance().getDriver().findElement(by))));
    }

    public static void hardCodedWaiter(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enterSearchTerm(By by, String text) {
        new WebDriverWait(Driver.getInstance().getDriver(), TIMEOUT_WEBDRIVER_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = Driver.getInstance().getDriver().findElement(by);
        element.clear();
        element.sendKeys(text);
        hardCodedWaiter(2000);
        element.sendKeys(Keys.ENTER);
        hardCodedWaiter(2000);
    }

    public void scrollTo(WebElement webelement) {
        JavascriptExecutor js = (JavascriptExecutor) (Driver.getInstance().getDriver());
        js.executeScript("arguments[0].scrollIntoView();", webelement);
    }

    public boolean exist() {
        return false;
    }

    public String getWindowHandle() {
        return Driver.getInstance().getDriver().getWindowHandle();
    }

    public void switchToNewWindow() {
        for(String winHandle : Driver.getInstance().getDriver().getWindowHandles()){
            Driver.getInstance().getDriver().switchTo().window(winHandle);
        }
    }

    public String getUrl() {
        return Driver.getInstance().getDriver().getCurrentUrl();
    }

}
