import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MenuScraper {
    private static final String URL_FILE = "src/URL.txt";

    public static ArrayList<String> getMenu(String currentMonth) {
        System.out.println("Reading URL from file...");
        String url = readUrlFromFile();
        if (url == null) {
            System.out.println("No URL found in file. Exiting...");
            return new ArrayList<>();
        }

        System.out.println("Setting up ChromeDriver path...");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LiamK\\Documents\\chromedriver-win64\\chromedriver.exe");

        System.out.println("Configuring ChromeOptions...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");

        System.out.println("Initializing WebDriver...");
        WebDriver driver = new ChromeDriver(options);
        ArrayList<String> menu = new ArrayList<>();

        try {
            System.out.println("Navigating to URL: " + url);
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Wait for the displayed month to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".month-switcher span:nth-of-type(1)")));
            WebElement monthElement = driver.findElement(By.cssSelector(".month-switcher span:nth-of-type(1)"));
            wait.until(d -> !monthElement.getText().trim().isEmpty());
            String displayedMonth = monthElement.getText().trim();

            System.out.println("Current month: " + currentMonth);
            System.out.println("Displayed month on website: " + displayedMonth);

            // If the displayed month doesn't match the current month, navigate to next month
            if (!displayedMonth.equalsIgnoreCase(currentMonth)) {
                System.out.println("Displayed month does not match current month. Attempting to navigate to next month...");

                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-backdrop")));
                    System.out.println("Modal backdrop is not visible.");
                } catch(Exception e) {
                    System.out.println("Modal backdrop still visible or not found.");
                }

                boolean clicked = false;
                int attempts = 0;
                while (!clicked && attempts < 3) {
                    try {
                        WebElement nextMonthButton = wait.until(
                            ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Next Month']"))
                        );
                        try {
                            nextMonthButton.click();
                        } catch(Exception clickEx) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextMonthButton);
                        }
                        System.out.println("Clicked on the Next Month button.");
                        Thread.sleep(2000);
                        clicked = true;
                    } catch (Exception e) {
                        System.out.println("Attempt " + (attempts + 1) + " failed to click Next Month button.");
                        attempts++;
                        Thread.sleep(1000);
                    }
                }
                
                if (!clicked) {
                    System.out.println("Failed to navigate to the next month after multiple attempts.");
                } else {
                    // Update the stored URL
                    String newUrl = driver.getCurrentUrl();
                    writeUrlToFile(newUrl);
                    System.out.println("New URL stored: " + newUrl);
                }
            }

            System.out.println("Waiting for calendar day elements to load...");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td.sc-iwsKbI")));

            List<WebElement> dayElements = driver.findElements(By.cssSelector("td.sc-iwsKbI"));
            System.out.println("Found " + dayElements.size() + " day elements.");

            for (WebElement dayElement : dayElements) {
                try {
                    WebElement dateElement = dayElement.findElement(By.cssSelector("span.notranslate"));
                    if (dateElement != null) {
                        String date = dateElement.getDomAttribute("aria-label").replace(".", "").trim();
                        date = date.substring(date.indexOf(" ") + 1).toUpperCase();

                        List<String> items = new ArrayList<>();

                        List<WebElement> menuItems = dayElement.findElements(By.cssSelector("div[aria-label]"));
                        for (WebElement menuItem : menuItems) {
                            String item = menuItem.getDomAttribute("aria-label");
                            if (item != null && !item.isEmpty() && !item.equals("--Served With--")) {
                                items.add(item);
                            }
                        }

                        date = date.replace(" ", "L "); // Clarifies that this is a Lunch menu Item to avoid overlap in storage file

                        if (!items.isEmpty()) {
                            String formattedMenu = String.format("%s: %s", date, String.join(", ", items));
                            menu.add(formattedMenu);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        } finally {
            System.out.println("Closing the WebDriver...");
            driver.quit();
        }

        return menu;
    }

    private static String readUrlFromFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(URL_FILE))).trim();
        } catch (IOException e) {
            System.out.println("Error reading URL file: " + e.getMessage());
            return null;
        }
    }

    private static void writeUrlToFile(String url) {
        try {
            Files.write(Paths.get(URL_FILE), url.getBytes());
            System.out.println("Successfully wrote new URL to file.");
        } catch (IOException e) {
            System.out.println("Error writing URL file: " + e.getMessage());
        }
    }
}
