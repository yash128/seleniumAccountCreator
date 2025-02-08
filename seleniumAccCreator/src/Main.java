import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        extras e = new extras();
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("general.useragent.override",e.userAgent.get(0));
        WebDriver obj = new FirefoxDriver();
        obj.manage().window().maximize();
        obj.get("https://accounts.google.com");
        Thread.sleep(5000);
        obj.findElement(By.className("NlWrkb")).click();
        Actions action = new Actions(obj);
        WebElement elem = obj.findElement(By.className("z80M1"));
        action.moveToElement(elem).click().perform();
        Thread.sleep(10000);
        WebElement firstName = obj.findElement(By.id("firstName"));
        WebElement last = obj.findElement(By.id("lastName"));
        WebElement user = obj.findElement(By.id("username"));
        WebElement pws = obj.findElement(By.name("Passwd"));
        WebElement cpsw = obj.findElement(By.name("ConfirmPasswd"));
        WebElement button = obj.findElements(By.className("VfPpkd-RLmnJb")).get(1);
        firstName.sendKeys("manmohan");
        last.sendKeys("singh");
        user.sendKeys("manmohan234029");
        pws.sendKeys("amrinderA@9810");
        cpsw.sendKeys("amrinderA@9810");
        button.click();
        Thread.sleep(5000);
        sel(obj.findElement(By.id("month")),8);
        sel(obj.findElement(By.id("gender")),1);

    }
    private static void sel(WebElement elem,int val){ // for month value 1-12, for gender 1-male, 2-female
        Select select = new Select(elem);
        select.selectByValue(String.valueOf(val));
    }
}
/*
    from selenium import webdriver
        import time
        driver = webdriver.Firefox(executable_path="G:\\drivers\\geckodriver.exe")
         driver.get("https://accounts.google.com")
         driver.implicitly_wait(10)
         driver.find_element_by_class_name("NlWrkb").click()
         driver.find_element_by_class_name("z80M1").click()
         time.sleep(10)
         firstName = driver.find_element_by_id("firstName")
         last = driver.find_element_by_id("lastName")
         user = driver.find_element_by_id("username")
         pws = driver.find_element_by_name("Passwd")
         cpsw = driver.find_element_by_name("ConfirmPasswd")
         button = driver.find_element_by_class_name("VfPpkd-RLmnJb")
         firstName.send_keys("bhavya")
         last.send_keys("76")
         user.send_keys("bhavyadon8798")
         pws.send_keys("bhaviraja")
         cpsw.send_keys("bhaviraja")
         button.click()
        driver.get("https://peakme.in")
        elem = driver.find_element_by_class_name("bind")
        elem.__setattr__("id","std")
*/
