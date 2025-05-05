import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {
    public static ArrayList<String> getSchedule(String url) {
        // Set the path to the ChromeDriver executable
        System.out.println("Setting up ChromeDriver path...");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LiamK\\Documents\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeOptions
        System.out.println("Configuring ChromeOptions...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration (sometimes needed for headless)
        options.addArguments("--window-size=1920x1080"); // Set a default window size (optional)

        // Initialize the WebDriver with ChromeOptions
        System.out.println("Initializing WebDriver...");
        WebDriver driver = new ChromeDriver(options);
        ArrayList<String> result = new ArrayList<>();

        try {
            // Navigate to the webpage
            System.out.println("Navigating to URL: " + url);
            driver.get(url);

            // Set up WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout after 10 seconds

            // Wait for the day elements to load
            System.out.println("Waiting for day elements to load...");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button.title")));
            List<WebElement> dayElements = driver.findElements(By.cssSelector("button.title"));
            System.out.println("Found " + dayElements.size() + " day elements.");

            // Wait for the date elements to load
            System.out.println("Waiting for date elements to load...");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".date")));
            List<WebElement> dateElements = driver.findElements(By.cssSelector(".date"));
            System.out.println("Found " + dateElements.size() + " date elements.");

            // Initialize a list to store the data
            List<String[]> scheduleData = new ArrayList<>();

            // Variable to track the last valid date for multi-day ranges
            String lastValidDate = "";

            // Iterate over the day elements
            int dateIndex = 0; // Index to track dateElements alignment
            for (WebElement dayElement : dayElements) {

                // Extract the Day schedule text
                String daySchedule = dayElement.getText();

                // Ensure the dateIndex does not exceed the size of dateElements
                if (dateIndex >= dateElements.size()) {
                    //System.out.println("No more date elements to process.");
                    break;
                }

                // Get the current date element
                WebElement dateElement = dateElements.get(dateIndex);

                // Handle multi-day elements
                if (dateElement.getDomAttribute("class").contains("multi-day")) {
                    //System.out.println("Multi-day element detected.");

                    // Extract only the first date
                    WebElement firstDate = dateElement.findElement(By.cssSelector(".event-list-date:first-of-type"));
                    String month = firstDate.findElement(By.cssSelector(".month")).getText();
                    String day = firstDate.findElement(By.cssSelector(".day")).getText();
                    lastValidDate = month + " " + day;

                    // Add the schedule and date to the list
                    scheduleData.add(new String[]{daySchedule, lastValidDate});

                    // Increment the dateIndex to skip the entire multi-day block
                    dateIndex++;
                } else {
                    //System.out.println("Single-day element detected.");

                    // Extract the single date as usual
                    String month = dateElement.findElement(By.cssSelector(".month")).getText();
                    String day = dateElement.findElement(By.cssSelector(".day")).getText();
                    lastValidDate = month + " " + day;

                    // Add the schedule and date to the list
                    scheduleData.add(new String[]{daySchedule, lastValidDate});

                    // Increment the date index
                    dateIndex++;
                }
            }

            // Filter and format the output
            //System.out.println("Formatting the output...");
            for (String[] data : scheduleData) {
                String daySchedule = data[0];
                String date = data[1];

                // Check if the schedule matches "Day # xxxxx" format
                if (daySchedule.matches("Day \\d+: .*")) {
                    // Format the output as "MONTH ## Day #"
                    String formattedOutput = String.format("%s:%s", date, daySchedule.split(":")[0].substring(4));
                    result.add(formattedOutput);
                }
            }

        } catch (Exception e) {
            //System.out.println("An error occurred:");
            e.printStackTrace();
        } finally {
            // Close the browser
            //System.out.println("Closing the WebDriver...");
            driver.quit();
        }

        //System.out.println("Returning result...");
        return result;
    }
}
