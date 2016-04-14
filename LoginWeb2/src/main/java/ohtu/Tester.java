package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println( driver.getPageSource() );

        driver.findElement(By.linkText("logout")).click();
        driver.findElement(By.linkText("login")).click();

        driver.findElement(By.name("username")).sendKeys("pekka");
        driver.findElement(By.name("password")).sendKeys("akkpe");
        driver.findElement(By.name("login")).submit();

        System.out.println("==");
        System.out.println( driver.getPageSource() );

        driver.get("http://localhost:8090");
        driver.findElement(By.linkText("login")).click();

        driver.findElement(By.name("username")).sendKeys("pelle");
        driver.findElement(By.name("password")).sendKeys("akkep");
        driver.findElement(By.name("login")).submit();

        System.out.println("==");
        System.out.println( driver.getPageSource() );

        driver.get("http://localhost:8090");
        driver.findElement(By.linkText("register new user")).click();

        driver.findElement(By.name("username")).sendKeys("peelo");
        driver.findElement(By.name("password")).sendKeys("salasana1");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasana1");

        driver.findElement(By.name("add")).submit();
        driver.findElement(By.linkText("continue to application mainpage")).click();
        driver.findElement(By.linkText("logout")).click();
    }
}
