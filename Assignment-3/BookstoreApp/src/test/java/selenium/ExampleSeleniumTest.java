package selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import io.github.bonigarcia.wdm.WebDriverManager;

/* WE USED CHROME DRIVER FOR OUT CODE */
/* TO USE ANOTHER DRIVER, PLEASE UNCOMMENT THE IMPORT BELOW AND THE CODE
   FOUND IN THE setUp() FUNCTION */
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(OrderAnnotation.class)
class ExampleSeleniumTest {

    static Process server;
    private WebDriver driver;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar");
        server = pb.start();
        Thread.sleep(5000);
    }

    @BeforeEach
    void setUp() {
        // PICK YOUR BROWSER (WE ARE USING CHROME)

        // WebDriverManager.firefoxdriver().setup();
        // driver = new FirefoxDriver();

        // WebDriverManager.safaridriver().setup();
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
    @Order(1)
    void test1() {
        WebElement element = driver.findElement(By.id("title"));
        String expected = "YAMAZONE BookStore";
        String actual = element.getText();
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
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
    @Order(3)
    public void F1PositiveTest() { // Ensuring that I login before doing any admin actions
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
        driver.findElement(By.id("cost")).sendKeys("25.00"); // submitting the form
        driver.findElement(By.name("addBook")).click(); // ensuring that there is a feedback messages
        String feedbackMessage = driver.findElement(By.cssSelector("#feedback h2")).getText();
        assertEquals("Successfully added book", feedbackMessage);
        // ensuring that the book has actually been added to the catalog
        driver.findElement(By.id("searchBtn")).click();
        String title = driver.findElement(By.id("title-h110232")).getText();
        assertEquals("Of Mice and Men volume (2)", title);
        driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();
    }

    @Test
    @Order(4)
    public void F1NegativeTest() { // Ensuring that I login before doing any admin actions
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
        driver.findElement(By.id("cost")).sendKeys(""); // submitting the form
        driver.findElement(By.name("addBook")).click(); // ensuring that there is a feedback messages
        String feedbackMessage = driver.findElement(By.cssSelector("#feedback h2")).getText();
        assertEquals("Validation errors", feedbackMessage); // ensuring that the book has NOT been added
        driver.findElement(By.id("searchBtn")).click(); // checking if a book has been added with an empty id
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("title-")).getText();
        });
        driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();

    }

    @Test
    @Order(5)
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
    @Order(6)
    public void F2NegativeTest() {
        WebElement category = driver.findElement(By.id("search"));
        category.sendKeys("20.00");
        driver.findElement(By.id("searchBtn")).click();
        String searchMessage = driver.findElement(By.cssSelector(".content h1")).getText();
        assertEquals("Sorry we do not have any item matching category '20.00' at this moment", searchMessage);
    }

    @Test
    @Order(7)
    public void F3PositiveTest() {
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click();
        String text = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals(text, "Core Servlets and JavaServer Pages 2nd Edition (Volume 1)");
    }

    @Test
    @Order(8)
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
    @Order(9)
    public void F4PositiveTest() { // added a book to book order
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click();
        String id = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(1)"))
                .getText();
        assertEquals("hall001", id);
        String description = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)", description);
        String cost = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(3)"))
                .getText();
        assertEquals("$39.95", cost);
        String number = driver.findElement(By.id("hall001")).getAttribute("value");
        assertEquals("1", number);
        String Total = driver.findElement(By.id("tothall001")).getText();
        assertEquals("$39.95", Total);
    }

    @Test
    @Order(10)
    public void F4NegativeTest() {
        driver.get("http://localhost:8080/orderPage"); // goes to an empty book order
        // attempting to get information for the first element of the book order in an
        // empty book order getting an id that cannot be reached
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(1)"))
                    .getText();
        }); // getting a description in an empty book order
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                    .getText();
        }); // getting a cost
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(3)"))
                    .getText();
        });
    }

    @Test
    @Order(11)
    public void F5PositiveTest() { // added a book to book order
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click(); // setting the number of copies to 5
        WebElement input = driver.findElement(By.id("hall001"));
        input.clear();
        input.sendKeys("5"); // clicking on the update button
        driver.findElement(By.cssSelector(".updatebt")).click(); // checking if the total price has updated
        driver.navigate().refresh(); // refresh the page to view the changes
        String Total = driver.findElement(By.id("tothall001")).getText();
        assertEquals("$199.75", Total);
    }

    @Test
    @Order(12)
    public void F5NegativeTest() { // added a book to book order
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click(); // setting the number of copies to 5
        WebElement input = driver.findElement(By.id("hall001"));
        input.clear();
        input.sendKeys("0"); // clicking on the update button
        driver.findElement(By.cssSelector(".updatebt")).click(); // checking if the book is still there
        driver.navigate().refresh(); // refresh the page to view the changes
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("hall001")); // try to find this specific element of the book that was set to 0
        });
    }

    @Test
    @Order(13)
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
    @Order(14)
    public void F6NegativeTest() {
        driver.findElement(By.id("searchBtn")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.findElement(By.cssSelector("body > div > div.content > form > button")).click();
        String shipping = driver.findElement(By.id("order_shipping")).getText();
        assertEquals("$0.00", shipping); // checking if the taxes are correct and
        String taxes = driver.findElement(By.id("order_taxes")).getText();
        assertEquals("$0.00", taxes); // checking if the total cost is correct
        String totalCost = driver.findElement(By.id("order_total")).getText();
        assertEquals("$0.00", totalCost);
    }

    @Test
    @Order(15)
    public void F7PositiveTest() throws Exception { // Ensuring that I login before doing any admin actions
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
        driver.findElement(By.id("longDescription"))
                .sendKeys("The book talks about two ranch workers during the great depression");
        driver.findElement(By.id("cost")).sendKeys("25.00"); // submitting the form
        driver.findElement(By.name("addBook")).click(); // get a list of all books
        driver.findElement(By.id("searchBtn")).click(); // delete the book with id removeMe
        driver.findElement(By.id("del-removeMe")).click();
        Thread.sleep(1000);
        driver.navigate().refresh(); // ensuring that the book has been removed from the books list
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("title-removeMe"));
        });
        driver.get("http://localhost:8080/admin");
        driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();
    }

    @Test
    @Order(16)
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
        // these tests ensure that they are there after a book was removed in
        // F7PositiveTest
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
    @Order(17)
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
    @Order(18)
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

    @Test
    @Order(19)
    public void UseCaseTest1_1() {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        String so = driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]"))
                .getAttribute("value");
        assertEquals("Sign out", so);
    }

    @Test
    @Order(20)
    public void UseCaseTest1_2() {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("falseTest");
        password.sendKeys("false");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/login?error");
        WebElement userName2 = driver.findElement(By.id("loginId"));
        WebElement password2 = driver.findElement(By.id("loginPasswd"));
        userName2.sendKeys("admin");
        password2.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        String so = driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]"))
                .getAttribute("value");
        assertEquals("Sign out", so);
    }

    @Test
    @Order(21)
    public void UseCaseTest2_1() {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]")).click();
        driver.get("http://localhost:8080/login?logout");
        String lo = driver.findElement(By.cssSelector("body > div > div.content > div")).getText();
        assertEquals("You have been logged out", lo);
    }

    @Test
    @Order(22)
    public void UseCaseTest3_1() {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        WebElement category = driver.findElement(By.id("addBook-category"));
        WebElement bookid = driver.findElement(By.id("addBook-id"));
        WebElement title = driver.findElement(By.id("addBook-title"));
        WebElement author = driver.findElement(By.id("addBook-authors"));
        WebElement description = driver.findElement(By.id("longDescription"));
        WebElement cost = driver.findElement(By.id("cost"));
        category.sendKeys("Fiction");
        bookid.sendKeys("h11023");
        title.sendKeys("Of Mice and Men");
        author.sendKeys("John Steinbeck");
        description.sendKeys("The book talks about two ranch workers during the great depression.");
        cost.sendKeys("20.00");
        driver.findElement(By.cssSelector("#addBook-form > button")).click();
        // checking the valid book
        String done = driver.findElement(By.cssSelector("#feedback > h2")).getText();
        assertEquals("Successfully added book", done);
    }

    @Test
    @Order(23)
    public void UseCaseTest3_2() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        driver.findElement(By.cssSelector("#addBook-form > button")).click();
        String check1 = driver.findElement(By.cssSelector("#feedback > h2")).getText();
        assertEquals("Validation errors", check1);
        WebElement category = driver.findElement(By.id("addBook-category"));
        WebElement bookid = driver.findElement(By.id("addBook-id"));
        WebElement title = driver.findElement(By.id("addBook-title"));
        WebElement author = driver.findElement(By.id("addBook-authors"));
        WebElement description = driver.findElement(By.id("longDescription"));
        WebElement cost = driver.findElement(By.id("cost"));
        category.sendKeys("Non-fiction");
        bookid.sendKeys("h12321");
        title.sendKeys("To Kill a Mockingbird");
        author.sendKeys("Harper Lee");
        description.sendKeys("The book talks about a lawyer fighting against racism.");
        cost.sendKeys("30.00");
        driver.findElement(By.cssSelector("#addBook-form > button")).click();
        // checking the valid book
        Thread.sleep(1000);

        String check2 = driver.findElement(By.cssSelector("#feedback > h2")).getText();
        assertEquals("Successfully added book", check2);
    }

    @Test
    @Order(24)
    public void UseCaseTest3_3() throws InterruptedException {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        WebElement category = driver.findElement(By.id("addBook-category"));
        WebElement bookid = driver.findElement(By.id("addBook-id"));
        WebElement title = driver.findElement(By.id("addBook-title"));
        WebElement author = driver.findElement(By.id("addBook-authors"));
        WebElement description = driver.findElement(By.id("longDescription"));
        WebElement cost = driver.findElement(By.id("cost"));
        category.sendKeys("Fiction");
        bookid.sendKeys("hall001");
        title.sendKeys("The Great Gatsby");
        author.sendKeys("F. Scott Fitzgerald");
        description.sendKeys("The book was made into a movie.");
        cost.sendKeys("20.00");
        driver.findElement(By.cssSelector("#addBook-form > button")).click();
        String check1 = driver.findElement(By.cssSelector("#feedback > h2")).getText();
        assertEquals("Book with same id already exist", check1);
        bookid.clear();
        bookid.sendKeys("h2501");
        driver.findElement(By.cssSelector("#addBook-form > button")).click();
        Thread.sleep(1000);
        String check2 = driver.findElement(By.cssSelector("#feedback h2")).getText();
        assertEquals("Successfully added book", check2);
    }

    @Test
    @Order(25)
    public void UseCaseTest4_1() {
        driver.get("http://localhost:8080");
        WebElement category = driver.findElement(By.id("search"));
        category.sendKeys("kids");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        String list = driver.findElement(By.cssSelector("body > div > div.content > h1")).getText();
        assertEquals("We currently have the following items in category 'kids'", list);
    }

    @Test
    @Order(26)
    public void UseCaseTest4_2() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        String list = driver.findElement(By.cssSelector("body > div > div.content > h1")).getText();
        assertEquals("We currently have the following items in category ''", list);
    }

    @Test
    @Order(27)
    public void UseCaseTest4_3() {
        driver.get("http://localhost:8080");
        WebElement category = driver.findElement(By.id("search"));
        category.sendKeys("blah");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        String list = driver.findElement(By.cssSelector("body > div > div.content > h1")).getText();
        assertEquals("Sorry we do not have any item matching category 'blah' at this moment", list);
    }

    @Test
    @Order(28)
    public void UseCaseTest5_1() throws Exception {
        driver.get("http://localhost:8080/login");
        WebElement userName = driver.findElement(By.id("loginId"));
        WebElement password = driver.findElement(By.id("loginPasswd"));
        userName.sendKeys("admin");
        password.sendKeys("password");
        driver.findElement(By.id("loginBtn")).click();
        driver.get("http://localhost:8080/admin");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/admin/catalog");
        driver.findElement(By.id("del-rowling001")).click();
        Thread.sleep(1000);
        driver.get("http://localhost:8080/admin/catalog");
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.id("del-rowling001")).getText();
        });
        String so = driver.findElement(By.cssSelector("body > div > div.menu > form:nth-child(3) > input[type=submit]"))
                .getAttribute("value");
        assertEquals("Sign out", so);
    }

    @Test
    @Order(29)
    public void UseCaseTest6_1() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-alexander001")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(
                        By.cssSelector("body > div > div.content > table > tbody > tr:nth-child(1) > td:nth-child(2)"))
                .getText();
        assertEquals("The Prydain Series", bookFound);
        String quantity = driver.findElement(By.id("alexander001")).getAttribute("value");
        assertEquals("1", quantity);
    }

    @Test
    @Order(30)
    public void UseCaseTest6_2() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-lewis001")).click();
        driver.findElement(By.id("order-lewis001")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("The Chronicles of Narnia", bookFound);
        String quantity = driver.findElement(By.id("lewis001")).getAttribute("value");
        assertEquals("2", quantity);
    }

    @Test
    @Order(31)
    public void UseCaseTest7_1() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-lewis001")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("The Chronicles of Narnia", bookFound);
    }

    @Test
    @Order(32)
    public void UseCaseTest8_1() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-hall002")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("Core Web Programming, 2nd Edition", bookFound);
        String quantityBefore = driver.findElement(By.id("hall002")).getAttribute("value");
        assertEquals("1", quantityBefore);
        String totalCostBefore = driver.findElement(By.id("tothall002")).getText();
        assertEquals("$49.99", totalCostBefore);
        WebElement number = driver.findElement(By.id("hall002"));
        number.clear();
        number.sendKeys("4");
        driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(4) > button"))
                .click();
        driver.get("http://localhost:8080/orderPage");
        String quantityAfter = driver.findElement(By.id("hall002")).getAttribute("value");
        assertEquals("4", quantityAfter);
        String totalCostAfter = driver.findElement(By.id("tothall002")).getText();
        assertEquals("$199.96", totalCostAfter);
    }

    @Test
    @Order(33)
    public void UseCaseTest8_2() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)", bookFound);
        String quantityBefore = driver.findElement(By.id("hall001")).getAttribute("value");
        assertEquals("1", quantityBefore);
        String totalCostBefore = driver.findElement(By.id("tothall001")).getText();
        assertEquals("$39.95", totalCostBefore);
        WebElement number = driver.findElement(By.id("hall001"));
        number.clear();
        number.sendKeys("-4");
        driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(4) > button"))
                .click();
        String quantityAfter = driver.findElement(By.id("hall001")).getAttribute("value");
        assertEquals("-4", quantityAfter);
        String totalCostAfter = driver.findElement(By.id("tothall001")).getText();
        assertEquals("$0.00", totalCostAfter);
        driver.navigate().refresh();
        assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> {
            driver.findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                    .getText();
        });
    }

    @Test
    @Order(34)
    public void UseCaseTest9_1() {
        driver.get("http://localhost:8080");
        driver.findElement(By.id("searchBtn")).click();
        driver.get("http://localhost:8080/catalog");
        driver.findElement(By.id("order-hall001")).click();
        driver.findElement(By.id("cartLink")).click();
        driver.get("http://localhost:8080/orderPage");
        String bookFound = driver
                .findElement(By.cssSelector("body > div > div.content > table > tbody > tr > td:nth-child(2)"))
                .getText();
        assertEquals("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)", bookFound);
        String quantityBefore = driver.findElement(By.id("hall001")).getAttribute("value");
        assertEquals("1", quantityBefore);
        String totalCostBefore = driver.findElement(By.id("tothall001")).getText();
        assertEquals("$39.95", totalCostBefore);
        driver.findElement(By.cssSelector("body > div > div.content > form > button")).click();
        driver.get("http://localhost:8080/checkout");
        String received = driver
                .findElement(By.cssSelector(
                        "#checkoutTable > tbody > tr > td:nth-child(2) > p:nth-child(1) > span:nth-child(1)"))
                .getText();
        assertEquals("We have received your order on", received);
    }

    @Test
    @Order(35)
    public void UseCaseTest10_1() {
        driver.get("http://localhost:8080");
        String titleBefore = driver.findElement(By.id("title")).getText();
        assertEquals("YAMAZONE BookStore", titleBefore);
        WebElement language = driver.findElement(By.id("locales"));
        language.click();
        WebElement french = driver.findElement(By.cssSelector("#locales > option:nth-child(3"));
        french.click();
        String titleAfter = driver.findElement(By.id("title")).getText();
        assertEquals("Librairie Y'AMAZONE", titleAfter);
    }
}
