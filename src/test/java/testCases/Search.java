//package test.java.testCases;
//
//import main.java.pageObject.HomePage;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import test.java.BaseTest;
//
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertNull;
//
//public class Search extends BaseTest {
//
//    HomePage homePage = new HomePage();
//
//    @BeforeMethod
//    public void setUp()  {
//        setup();
//        homePage.navigateToHomePage();
//        homePage.exist();
//    }
//
//    @Test
//    public void searchExistingItem()
//    {
//
//        homePage.enterSearchQuery("Bit");
//        homePage.clickOnResult();
//
//        String resultName = homePage.getFoundCurrency();
//
//        assertEquals(resultName, "Bitcoin (BTC)");
//
//    }
//
//    @Test
//    public void searchNonexistentItem()
//    {
//
//        homePage.enterSearchQuery("Dinar");
//
//        assertNull(homePage.getSearchResults());
//
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        teardown();
//    }
//
//}
