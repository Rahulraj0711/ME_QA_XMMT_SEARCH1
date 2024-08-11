package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    // EdgeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt("webklipper-publisher-widget-container-notification-frame"));
        WebElement popup = driver.findElement(By.cssSelector("div.container a.close"));
        popup.click();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test Case: testCase01");

        // Use the driver.getCurrentUrl() to get the current url text
        String currentUrl = driver.getCurrentUrl();

        // Use the contains method to check "makemytrip" is present
        if (currentUrl.contains("makemytrip")) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        System.out.println("End Test Case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test Case: testCase02");

        // Click on the from element and send "Bangalore" as the value and select the
        // Bangalore from the dropdown
        WebElement fromCity = driver.findElement(By.xpath("//div/label[@for='fromCity']"));
        fromCity.click();
        Thread.sleep(3000);
        WebElement fromCityBox = driver.findElement(By.xpath("//div[@role='combobox']/input"));
        fromCityBox.sendKeys("blr");
        Thread.sleep(3000);
        WebElement selectFromCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectFromCity.click();

        // Click on the to element and send "Delhi" as the value and select the Delhi
        // from the dropdown
        WebElement toCity = driver.findElement(By.cssSelector("div.searchToCity"));
        toCity.click();
        WebElement toCityBox = driver.findElement(By.cssSelector("div.searchToCity input"));
        toCityBox.sendKeys("del");
        Thread.sleep(3000);
        WebElement selectToCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectToCity.click();

        // Click on the departure element and select valid date from the calendar
        // Thread.sleep(3000);
        // List<WebElement> days = driver.findElements(
        // By.cssSelector("div.DayPicker-Month:nth-of-type(2)
        // div.DayPicker-Day[aria-disabled='false']"));
        // WebElement date = days.get(28);
        // date.click();
        WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        dateElement.click();

        // Click on the search button
        WebElement searchButton = driver.findElement(By.cssSelector("a.widgetSearchBtn"));
        searchButton.click();

        // Use wait for all search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.overlay")));
        WebElement newPopup = driver.findElement(By.cssSelector("div.overlay span.overlayCrossIcon"));
        newPopup.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.listingRhs")));

        // Store all the price per adult for all flights in a list
        List<WebElement> flightsList = driver
                .findElements(By.cssSelector("div.listingCard div.priceSection div.blackText"));
        for (WebElement e : flightsList) {
            System.out.println("Flight Price: " + e.getText());
        }

        System.out.println("End Test Case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test Case: testCase03");

        // Click on trains menu
        WebElement trainsMenu = driver.findElement(By.cssSelector("li.menu_Trains"));
        trainsMenu.click();
        Thread.sleep(3000);

        // Click on the from element and send "YPR" as the value and select the
        // Bangalore from the dropdown
        WebElement fromCity = driver.findElement(By.xpath("//label[@for='fromCity']"));
        fromCity.click();
        Thread.sleep(3000);
        WebElement fromCityBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromCityBox.sendKeys("ypr");
        Thread.sleep(3000);
        WebElement selectFromCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectFromCity.click();

        // Click on the to element and send "NDLS" as the value and select the Delhi
        // from the dropdown
        // WebElement toCity = driver.findElement(By.xpath("//label[@for='toCity']"));
        // toCity.click();
        // Thread.sleep(3000);
        WebElement toCityBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toCityBox.sendKeys("ndls");
        Thread.sleep(3000);
        WebElement selectToCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectToCity.click();

        // Click on the departure element and select valid date from the calendar
        // WebElement dateElement = driver.findElement(By.cssSelector("div
        // label[for='travelDate']"));
        // dateElement.click();
        // List<WebElement> days = driver.findElements(
        // By.cssSelector("div.DayPicker-Month:nth-of-type(2)
        // div.DayPicker-Day[aria-disabled='false']"));
        // WebElement date = days.get(28);
        // date.click();
        WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        dateElement.click();

        // Click on Class dropdown element and select Third AC
        // WebElement classElement =
        // driver.findElement(By.xpath("//label[@for='travelClass']"));
        // classElement.click();
        WebElement thirdClass = driver.findElement(By.cssSelector("li[data-cy='3A']"));
        thirdClass.click();

        // Click on the search button
        WebElement searchButton = driver.findElement(By.cssSelector("a.widgetSearchBtn"));
        searchButton.click();

        // Use wait for all search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.train-list")));

        // Store the train price for 3AC
        List<WebElement> trainsList = driver
                .findElements(By.cssSelector("div.train-list div.ticket-price"));
        for (WebElement e : trainsList) {
            System.out.println("Train Price: " + e.getText());
        }

        System.out.println("End Test Case: testCase03");
    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test Case: testCase04");

        // Click on buses menu
        WebElement busesMenu = driver.findElement(By.cssSelector("li.menu_Buses"));
        busesMenu.click();
        Thread.sleep(3000);

        // Select Bangalore(search for bangl) as the departure location for buses
        WebElement fromCity = driver.findElement(By.xpath("//label[@for='fromCity']"));
        fromCity.click();
        Thread.sleep(3000);
        WebElement fromCityBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromCityBox.sendKeys("bangl");
        Thread.sleep(3000);
        WebElement selectFromCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectFromCity.click();

        // Select Ranchi(search for ran) as the arrival location for buses
        WebElement toCityBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toCityBox.sendKeys("ran");
        Thread.sleep(3000);
        WebElement selectToCity = driver.findElements(By.xpath("//ul[@role='listbox']/li")).get(0);
        selectToCity.click();

        // Select the correct date (29th of next Month) for buses
        // List<WebElement> days = driver.findElements(
        // By.cssSelector("div.DayPicker-Month:nth-of-type(2)
        // div.DayPicker-Day[aria-disabled='false']"));
        // WebElement date = days.get(28);
        // date.click();
        WebElement dateElement = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        dateElement.click();

        // Click on the search button
        WebElement searchButton = driver.findElement(By.cssSelector("button#search_button"));
        searchButton.click();

        // Verify that text displayed contains No buses found for 29th of next Month
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='error-title']")));
        String message = driver.findElement(By.xpath("//span[@class='error-title']")).getText();
        if (message.equals("No buses found for 29 Apr")) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }

        System.out.println("End Test Case: testCase04");
    }

}
