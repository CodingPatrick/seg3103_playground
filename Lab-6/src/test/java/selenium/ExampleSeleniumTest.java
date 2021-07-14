package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

class ExampleSeleniumTest {

  static Process server;
  private WebDriver driver;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar");
    server = pb.start();
    Thread.sleep(10000);
  }

  @BeforeEach
  void setUp() {
    // Pick your browser
    // driver = new FirefoxDriver();
    // driver = new SafariDriver();
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.get("http://localhost:8080/");
    // wait to make sure Selenium is done loading the page
    WebDriverWait wait = new WebDriverWait(driver, 60);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
  }

  @AfterEach
  public void tearDown() {
    driver.close();
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    server.destroy();
  }

  @Test
  void test1() {
    WebElement element = driver.findElement(By.id("title"));
    String expected = "YAMAZONE BookStore";
    String actual = element.getText();
    assertEquals(expected, actual);
  }

  @Test
  public void test2() {
    WebElement welcome = driver.findElement(By.cssSelector("p"));
    String expected = "Welcome";
    String actual = welcome.getText();
    assertEquals(expected, getWords(actual)[0]);
    WebElement langSelector = driver.findElement(By.id("locales"));
    langSelector.click();
    WebElement frSelector = driver.findElement(By.cssSelector("option:nth-child(3)"));
    frSelector.click();
    welcome = driver.findElement(By.cssSelector("p"));
    expected = "Bienvenu";
    actual = welcome.getText();
    assertEquals(expected, getWords(actual)[0]);
  }

  private String[] getWords(String s) {
    return s.split("\\s+");
  }

  // NEW SELENIUM TEST FOR LAB 6 (ALSO WORKS FOR ASSIGNMENT 3)
  @Test
  public void F1PositiveTest() {
    // Ensuring that I login before doing any admin actions
    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("password");
    driver.findElement(By.id("loginBtn")).click();
    // Filling out the form
    driver.get("http://localhost:8080/admin");
    driver.findElement(By.id("addBook-category")).sendKeys("Fiction");
    driver.findElement(By.id("addBook-id")).sendKeys("h11023");
    driver.findElement(By.id("addBook-title")).sendKeys("Of Mice and Men");
    driver.findElement(By.id("addBook-authors")).sendKeys("John Steinbeck");
    driver.findElement(By.id("longDescription")).sendKeys("The book talks about two ranch workers during the great depression");
    driver.findElement(By.id("cost")).sendKeys("20.00");
    // submitting the form
    driver.findElement(By.name("addBook")).click();
    // ensuring that there is a feedback messages
    String feedbackMessage = driver.findElement(By.cssSelector("#feedback h2")).getText();
    assertEquals("Successfully added book", feedbackMessage);
    // ensuring that the books has actually been added
    driver.findElement(By.id("searchBtn")).click();
    String title = driver.findElement(By.id("title-h11023")).getText();
    assertEquals("Of Mice and Men", title);
  }
}

