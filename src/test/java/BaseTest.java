package test.java;

import main.java.config.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class.getSimpleName());

    public void setupDriver() {
        Driver.getInstance().setDriver();
        logger.info("Driver set successfully");
    }

    public void teardown() {
        Driver.getInstance().closeDriver();
        logger.info("Driver closed successfully");
    }

}

