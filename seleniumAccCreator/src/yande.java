import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import java.util.*;
public class yande {
    private static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        extras extra = new extras();
        FirefoxOptions profile = new FirefoxOptions();
        profile.addPreference("network.proxy.type",1);
        profile.addPreference("network.proxy.socks","127.0.0.1");
        profile.addPreference("network.proxy.socks_port",9150);
        WebDriver driver = new FirefoxDriver(profile);
        Scanner scan = new Scanner(System.in);
        Select s;
        driver.manage().window().maximize();
        try {
            while (true) {
                System.out.println("driver get");
                driver.get("https://mail.yandex.com");
                Thread.sleep(4000);
                driver.findElement(By.className("control")).click();
                Thread.sleep(4000);

                String firstName = extra.firstName.remove(rand.nextInt(extra.firstName.size()));
                String lastName = extra.lastName.remove(rand.nextInt(extra.lastName.size()));
                String pass = extra.generatePass(firstName, lastName);

                driver.findElement(By.id("firstname")).sendKeys(firstName);
                driver.findElement(By.id("lastname")).sendKeys(lastName);
                String mail = lastName + rand.nextInt(1000) + "." + firstName;
                driver.findElement(By.id("login")).sendKeys(mail);
                driver.findElement(By.id("password")).sendKeys(pass);
                driver.findElement(By.id("password_confirm")).sendKeys(pass);
                driver.findElement(By.className("Link_pseudo")).click();
                s = new Select(driver.findElement(By.className("Select2-Control")));
                String ques = s.getFirstSelectedOption().getText();
                String ans;
                if (rand.nextBoolean()) {
                    ans = firstName;
                } else {
                    ans = lastName;
                }
                driver.findElement(By.id("hint_answer")).sendKeys(ans);
                Thread.sleep(15000);
                JavascriptExecutor j = (JavascriptExecutor) driver;
                j.executeScript("document.getElementsByClassName('Button2-Text')[1].click()");
                Thread.sleep(2000);
//                driver.findElements(By.className("Button2-Text")).get(1).click();
                j.executeScript("document.getElementsByClassName('Button2-Text')[2].click()");
                System.out.println("buttons click");
                extra.writeTo(firstName + "," + lastName + "," + mail + "," + pass + "," + ques + "," + ans,"yand.txt");
                while (true){
                    if (driver.getCurrentUrl().contains("passport")){
                        Thread.sleep(2000);
                        System.out.println("in loop");
                    }else {
                        break;
                    }
                }
                System.out.println("at delete point : ");
                if (scan.nextLine().equals("q")) break;
                driver.manage().deleteAllCookies();
            }
        } finally {
            System.out.println("at error");
            driver.quit();
        }
    }
}
