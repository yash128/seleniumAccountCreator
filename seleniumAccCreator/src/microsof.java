import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;
import java.util.Scanner;

public class microsof {
    private static final Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        extras extra = new extras();
        WebDriver driver = new FirefoxDriver();
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        Select s;
        driver.manage().window().maximize();
        try {
//            while (true) {
            System.out.println("driver get");
//            driver.get("https://outlook.live.com/owa/?nlp=1&signup=1");
            driver.get("https://peakme.in");
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
            Thread.sleep(10000);
            driver.findElements(By.className("Button2-Text")).get(1).click();
            Thread.sleep(4000);
            driver.findElements(By.className("Button2-Text")).get(2).click();
            extra.writeTo(firstName + "," + lastName + "," + mail + "," + pass + "," + ques + "," + ans,"start.txt");
            while (true){
                if (driver.getCurrentUrl().contains("passport")){
                    Thread.sleep(2000);
                }else {break;}}
            System.out.println("at delete point : ");
            scan.nextLine();
            driver.manage().deleteAllCookies();
//            }
        } finally {
            System.out.println("at error");
            scan.nextLine();
            driver.quit();
        }
    }
}
