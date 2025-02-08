import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

//button with cssSelector #u_w_f_f4 and way of fetching code is not accurately managed in mailRead.
//they both might return error
public class mailRead {
    private static final ArrayList<String[]> data = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        read();
        extras extra = new extras();
        WebDriver driver = new ChromeDriver();
        driver.get("https://facebook.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[5]/a")).click();
        String[] arr = data.get(0);
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div[1]/div[1]/div/input")).sendKeys(arr[0]);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div[1]/div[2]/div/div[1]/input")).sendKeys(arr[1]);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[2]/div/div[1]/input")).sendKeys(arr[2]+"@tutanota.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[3]/div/div/div[1]/input")).sendKeys(arr[2]+"@tutanota.com");
        String facePass = extra.generatePass(arr[0],arr[1]);
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[4]/div/div[1]/input")).sendKeys(facePass);

        Select sel = new Select(driver.findElement(By.cssSelector("#day")));
        int day = extras.rand.nextInt(12)+1;
        sel.selectByValue(String.valueOf(day));
        Thread.sleep(2000);
        Select sel1 = new Select(driver.findElement(By.cssSelector("#month")));
        int month = extras.rand.nextInt(30)+1;
        sel1.selectByValue(String.valueOf(month));
        Thread.sleep(2000);
        Select sel2 = new Select(driver.findElement(By.cssSelector("#year")));
        int year = extras.rand.nextInt(31)+1971;
        sel2.selectByValue(String.valueOf(year));

//        driver.findElements(By.cssSelector("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[7]/span/span[1]/input")).get(9).click();//female
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[2]/div/div/div[1]/form/div[1]/div[7]/span/span[2]/input")).click();//male

        driver.findElement(By.cssSelector("#reg_form_box > div._1lch > button:nth-child(1)")).click();

        extra.writeTo(arr[0]+","+arr[1]+","+arr[2]+"@tutanota.com"+","+facePass+","+day+","+month+","+year,"face.txt");

        /*WebDriver webDriver = new FirefoxDriver();
        webDriver.get("mail.tutanota.com");
        WebElement user = webDriver.findElement(By.cssSelector("#login-view > div.flex-grow.flex-center.scroll > div > div:nth-child(1) > form > div:nth-child(1) > div > div > div > div > div > input"));
        user.click();user.sendKeys(arr[2]);
        WebElement pass = webDriver.findElement(By.cssSelector("#login-view > div.flex-grow.flex-center.scroll > div > div:nth-child(1) > form > div:nth-child(2) > div > div > div > div > div > input"));
        pass.click();pass.sendKeys(arr[3]);
        webDriver.findElement(By.cssSelector("#login-view > div.flex-grow.flex-center.scroll > div > div:nth-child(1) > form > div:nth-child(4) > button")).click();
        Thread.sleep(10000);
        String code = webDriver.findElement(By.cssSelector("#mail > div > div.view-columns.backface_fix.flex-grow.rel > div.view-column.overflow-x-hidden.fill-absolute.backface_fix.list-border-right.hide-outline > div > div > div > ul > li:nth-child(1) > div > div.flex-grow.min-width-0 > div.bottom.flex-space-between > div")).getText();
        String[] c = code.split(" ");
        String co = "";
        for (String cs : c){
            if (cs.contains("FB-")){
                co = cs.substring(3);
                break;
            }
        }

*/
//        driver.findElement(By.cssSelector("#code_in_cliff")).sendKeys(co);
//        driver.findElement(By.cssSelector("#u_w_f_f4")).click();
        Thread.sleep(5000);
//        webDriver.quit();
//        driver.quit();
    }
    private static void read(){
        File f = new File("tuta.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))){
            reader.readLine();
            reader.readLine();
            reader.readLine();
            String str;
            while ((str = reader.readLine()) != null){
                data.add(str.split(","));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
