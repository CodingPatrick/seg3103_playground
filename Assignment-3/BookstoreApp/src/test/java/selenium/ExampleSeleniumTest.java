package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(OrderAnnotation.class)
class ExampleSeleniumTest {

  static Process server;
  private WebDriver driver;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar");
    server = pb.start();
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
    driver.findElement(By.id("addBook-id")).sendKeys("h110232");
    driver.findElement(By.id("addBook-title")).sendKeys("Of Mice and Men volume (2)");
    driver.findElement(By.id("addBook-authors")).sendKeys("John Steinbeck");
    driver.findElement(By.id("longDescription"))
        .sendKeys("The book talks about two ranch workers during the great depression");
    driver.findElement(By.id("cost")).sendKeys("25.00");

    // submitting the form
    driver.findElement(By.name("addBook")).click();

    // ensuring that there is a feedback messages
    String feedbackMessage = driver.findElement(By.cssSelector("#feedback h2")).getText();
    assertEquals("Successfully added book", feedbackMessage);

    // ensuring that the book has actually been added to the catalog
    driver.findElement(By.id("searchBtn")).click();
    String title = driver.findElement(By.id("title-h110232")).getText();

    assertEquals("Of Mice and Men volume (2)", title);

    driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();

  }

  @Test
 
  public void F1NegativeTest() {

    // Ensuring that I login before doing any admin actions
    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("password");
    driver.findElement(By.id("loginBtn")).click();

    // Filling out the form with empty values
    driver.get("http://localhost:8080/admin");
    driver.findElement(By.id("addBook-category")).sendKeys("");
    driver.findElement(By.id("addBook-id")).sendKeys("");
    driver.findElement(By.id("addBook-title")).sendKeys("");
    driver.findElement(By.id("addBook-authors")).sendKeys("");
    driver.findElement(By.id("longDescription")).sendKeys("");
    driver.findElement(By.id("cost")).sendKeys("");

    // submitting the form
    driver.findElement(By.name("addBook")).click();

    // ensuring that there is a feedback messages
    String feedbackMessage = driver.findElement(By.cssSelector("#feedback h2")).getText();
    assertEquals("Validation errors", feedbackMessage);

    // ensuring that the book has NOT been added
    driver.findElement(By.id("searchBtn")).click();

    // checking if a book has been added with an empty id
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
       driver.findElement(By.id("title-")).getText();
    });

    driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();


  }

  @Test
 
  public void F2PositiveTest() {
    WebElement category = driver.findElement(By.id("search"));
    category.sendKeys("kids");

    driver.findElement(By.id("searchBtn")).click();

    String searchMessage = driver.findElement(By.cssSelector(".content h1")).getText();
    assertEquals("We currently have the following items in category 'kids'", searchMessage);

    // at this state of the system there should be 3 books in place

    String Narnia = driver.findElement(By.id("title-lewis001")).getText();
    String Prydian = driver.findElement(By.id("title-alexander001")).getText();
    String HarryPotter = driver.findElement(By.id("title-rowling001")).getText();

    assertEquals(Narnia, "The Chronicles of Narnia");
    assertEquals(Prydian, "The Prydain Series");
    assertEquals(HarryPotter, "The Harry Potter Series");
  }

  @Test

  public void F2NegativeTest() {
    WebElement category = driver.findElement(By.id("search"));
    category.sendKeys("20.00");

    driver.findElement(By.id("searchBtn")).click();

    String searchMessage = driver.findElement(By.cssSelector(".content h1")).getText();
    assertEquals("Sorry we do not have any item matching category '20.00' at this moment", searchMessage);

  }

  @Test
 
  public void F3PositiveTest() {

    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-hall001")).click();

    driver.findElement(By.id("cartLink")).click();

    String text = driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
        .getText();
    assertEquals(text, "Core Servlets and JavaServer Pages 2nd Edition (Volume 1)");
  }

  @Test

  public void F3NegativeTest() {

    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-lewis001")).click();

    driver.findElement(By.id("cartLink")).click();

    driver.get("http://localhost:8080/orderPage");
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
      driver.findElement(By.id("hall002")); // attempt to find a book that does not exist as it was not added
    });

  }

  @Test
  
  public void F4PositiveTest() {

    // added a book to book order
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-hall001")).click();
    driver.findElement(By.id("cartLink")).click();

    String id = driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(1)"))
        .getText();
    assertEquals("hall001", id);

    String description = driver
        .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)")).getText();
    assertEquals("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)", description);

    String cost = driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(3)"))
        .getText();
    assertEquals("$39.95", cost);

    String number = driver.findElement(By.id("hall001")).getAttribute("value");
    assertEquals("1", number);

    String Total = driver.findElement(By.id("tothall001")).getText();
    assertEquals("$39.95", Total);

  }

  @Test
  
  public void F4NegativeTest() {

    driver.get("http://localhost:8080/orderPage"); // goes to an empty book order

    // attempting to get information for the first element of the book order in an
    // empty book order

    // getting an id that cannot be reached
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
    driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(1)"))
          .getText();
    });

    // getting a description in an empty book order
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
      driver
          .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)")).getText();
    });

    // getting a cost
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
    driver
          .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(3)")).getText();
    });

  }
  
  @Test
  public void F5PositiveTest() {

    // added a book to book order
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-hall001")).click();
    driver.findElement(By.id("cartLink")).click();
    // setting the number of copies to 5
    WebElement input = driver.findElement(By.id("hall001"));
    input.clear();
    input.sendKeys("5");
    // clicking on the update button
    driver.findElement(By.cssSelector(".updatebt")).click();
    // checking if the total price has updated

    driver.navigate().refresh(); // refresh the page to view the changes

    String Total = driver.findElement(By.id("tothall001")).getText();
    assertEquals("$199.75", Total);
  }

  @Test
  
  public void F5NegativeTest() {

    // added a book to book order
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-hall001")).click();
    driver.findElement(By.id("cartLink")).click();
    // setting the number of copies to 5

    WebElement input = driver.findElement(By.id("hall001"));
    input.clear();
    input.sendKeys("0");

    // clicking on the update button
    driver.findElement(By.cssSelector(".updatebt")).click();
    // checking if the book is still there
    driver.navigate().refresh(); // refresh the page to view the changes

    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
      driver.findElement(By.id("hall001")); // try to find this specific element of the book that was set to 0
    });

  }

  @Test
  
  public void F6PositiveTest() {

    // added a book to book order
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("order-hall001")).click();
    driver.findElement(By.id("cartLink")).click();

    driver.findElement(By.cssSelector("body > div > div.content > form > button")).click();

    // checking if the date is correct
    String orderDate = driver.findElement(By.cssSelector("#order_date b")).getText();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    assertEquals(formatter.format(new Date()), orderDate);

    // checking if the shipping cost is correct
    String shipping = driver.findElement(By.id("order_shipping")).getText();
    assertEquals("$12.00", shipping);
    // checking if the taxes are correct and
    String taxes = driver.findElement(By.id("order_taxes")).getText();
    assertEquals("$5.19", taxes);

    // checking if the total cost is correct
    String totalCost = driver.findElement(By.id("order_total")).getText();
    assertEquals("$57.14", totalCost);

  }

  @Test

  public void F6NegativeTest() {

    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.id("cartLink")).click();
    driver.findElement(By.cssSelector("body > div > div.content > form > button")).click();

    String shipping = driver.findElement(By.id("order_shipping")).getText();
    assertEquals("$0.00", shipping);
    // checking if the taxes are correct and
    String taxes = driver.findElement(By.id("order_taxes")).getText();
    assertEquals("$0.00", taxes);

    // checking if the total cost is correct
    String totalCost = driver.findElement(By.id("order_total")).getText();
    assertEquals("$0.00", totalCost);
  }

  
  @Test

  public void F7PositiveTest() {

    // Ensuring that I login before doing any admin actions
    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("password");
    driver.findElement(By.id("loginBtn")).click();

    // adding a book to remove, this is done to ensure that tests are isolated
    driver.get("http://localhost:8080/admin");
    driver.findElement(By.id("addBook-category")).sendKeys("Fiction");
    driver.findElement(By.id("addBook-id")).sendKeys("removeMe");
    driver.findElement(By.id("addBook-title")).sendKeys("Of Mice and Men volume (4)");
    driver.findElement(By.id("addBook-authors")).sendKeys("John Steinbeck");
    driver.findElement(By.id("longDescription")).sendKeys("The book talks about two ranch workers during the great depression");
    driver.findElement(By.id("cost")).sendKeys("25.00");
    // submitting the form
    driver.findElement(By.name("addBook")).click();
    // get a list of all books
    driver.findElement(By.id("searchBtn")).click();
    // delete the book with id removeMe
    driver.findElement(By.cssSelector("#del-removeMe")).click();

    driver.navigate().refresh();
    // ensuring that the book has been removed from the books list
    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
      driver.findElement(By.id("title-removeMe"));
    });

    driver.get("http://localhost:8080/admin");
    driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();


  }

  @Test
 
  public void F7NegativeTest() {

    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("password");
    driver.findElement(By.id("loginBtn")).click();

    // get a list of all books
    driver.findElement(By.id("searchBtn")).click();

    // at the current state of the system there are 5 books, 
    //these tests ensure that they are there after a book was removed in F7PositiveTest


    String bookTitle[] = new String[5];

    bookTitle[0] = driver.findElement(By.id("title-hall001")).getText();
    assertEquals(bookTitle[0], "Core Servlets and JavaServer Pages 2nd Edition (Volume 1)");

    bookTitle[1] = driver.findElement(By.id("title-hall002")).getText();
    assertEquals(bookTitle[1], "Core Web Programming, 2nd Edition");

    bookTitle[2] = driver.findElement(By.id("title-lewis001")).getText();
    assertEquals(bookTitle[2], "The Chronicles of Narnia");

    bookTitle[3] = driver.findElement(By.id("title-alexander001")).getText();
    assertEquals(bookTitle[3], "The Prydain Series");

    bookTitle[4] = driver.findElement(By.id("title-rowling001")).getText();
    assertEquals(bookTitle[4], "The Harry Potter Series");

    driver.get("http://localhost:8080/admin");
    driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();
  }

  
  @Test

  public void F8PositiveTest() {

    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("password");
    driver.findElement(By.id("loginBtn")).click();

    driver.get("http://localhost:8080/admin");

    driver.findElement(By.id("searchBtn")).click();

    String delete = driver.findElement(By.cssSelector("#del-rowling001")).getText();
    assertEquals("Delete", delete);

    driver.get("http://localhost:8080/admin");
    driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();

  }

  @Test
 
  public void F8NegativeTest() {

    driver.get("http://localhost:8080/login");
    WebElement userName = driver.findElement(By.id("loginId"));
    WebElement password = driver.findElement(By.id("loginPasswd"));
    userName.sendKeys("admin");
    password.sendKeys("passwordWrong");
    driver.findElement(By.id("loginBtn")).click();

    
    String error = driver.findElement(By.cssSelector("body > div > div.content > div")).getText();

    assertEquals("Invalid username and/or password", error);

    assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
      driver.findElement(By.id("del-hall001")).getText();
    });
    

    

  }

}
