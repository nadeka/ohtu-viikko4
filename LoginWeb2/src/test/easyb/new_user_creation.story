import ohtu.*
import ohtu.services.*
import ohtu.data_access.*
import ohtu.domain.*
import ohtu.io.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description """A new user account can be created
              if a proper unused username 
              and a proper password are given"""

scenario "creation successful with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
 
    when 'a valid username and password are entered', {
        driver.findElement(By.name("username")).sendKeys("peelo");
        driver.findElement(By.name("password")).sendKeys("salasan1");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasan1");

        driver.findElement(By.name("add")).submit();
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
        driver.getPageSource().contains("wrong username or password").shouldBe false
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
 
    when 'a valid username and password are entered', {
        driver.findElement(By.name("username")).sendKeys("apina");
        driver.findElement(By.name("password")).sendKeys("salasan1");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasan1");

        driver.findElement(By.name("add")).submit();
    }

    and 'command logout is selected', {
        driver.findElement(By.linkText("continue to application mainpage")).click();
        driver.findElement(By.linkText("logout")).click();
    }

    and 'command login is selected', {
        driver.findElement(By.linkText("login")).click();
    }

    and 'new credentials are given', {
         driver.findElement(By.name("username")).sendKeys("apina");
         driver.findElement(By.name("password")).sendKeys("salasan1");
         driver.findElement(By.name("login")).submit();
    }

    then 'new credentials allow logging in to system', {
       driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a valid username and too short password are entered', {
        driver.findElement(By.name("username")).sendKeys("12345");
        driver.findElement(By.name("password")).sendKeys("salasan");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasan");

        driver.findElement(By.name("add")).submit();
    }

    then 'new user is not be registered to system', {
        driver.getPageSource().contains("length greater or equal to 8").shouldBe true
    }
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a valid username and password consisting of letters are entered', {
        driver.findElement(By.name("username")).sendKeys("54321");
        driver.findElement(By.name("password")).sendKeys("salasana");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasana");

        driver.findElement(By.name("add")).submit();
    }

    then 'new user is not be registered to system', {
        driver.getPageSource().contains("must contain one character that is not a letter").shouldBe true
    }
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a too short username and valid password are entered', {
        driver.findElement(By.name("username")).sendKeys("apin");
        driver.findElement(By.name("password")).sendKeys("salasan1");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasan1");

        driver.findElement(By.name("add")).submit();
    }

    then 'new user is not be registered to system', {
        driver.getPageSource().contains("length 5-10").shouldBe true
    }

}

scenario "creation fails with already taken username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a already taken username and valid password are entered', {
        driver.findElement(By.name("username")).sendKeys("peelo");
        driver.findElement(By.name("password")).sendKeys("salasan1");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("salasan1");

        driver.findElement(By.name("add")).submit();
    }

    then 'new user is not be registered to system', {
        driver.getPageSource().contains("username or password invalid").shouldBe true
    }
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        driver.findElement(By.linkText("register new user")).click();
    }

    when 'a invalid username/password are entered', {
        driver.findElement(By.name("username")).sendKeys("jaajaa");
        driver.findElement(By.name("password")).sendKeys("jaa");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("jaa");

        driver.findElement(By.name("add")).submit();
    }

    and 'command login is selected', {
        driver.get("http://localhost:8090");
        driver.findElement(By.linkText("login")).click();
    }

    and 'same invalid credentials are given', {
        driver.findElement(By.name("username")).sendKeys("jaajaa");
        driver.findElement(By.name("password")).sendKeys("jaa");
        driver.findElement(By.name("login")).submit();
    }

    then 'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("wrong username or password").shouldBe true
    }
}