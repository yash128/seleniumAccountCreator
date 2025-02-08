import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class pro {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        FirefoxOptions profile;
//        profile.addPreference("network.proxy.type",1);
//        profile.addPreference("network.proxy.socks","127.0.0.1");
//        profile.addPreference("network.proxy.socks_port",9150);
//        WebDriver driver = new FirefoxDriver(profile);
        Scanner scan = new Scanner(System.in);
        WebDriver driver = null;
        extras extra = new extras();
        restartData data = new restartData(scan);
        data.getNamePhone();
        System.out.println("enter number of accounts want : ");
        int count = scan.nextInt();
//        try{
//            while (!scan.nextLine().equals("q")){
//                driver.get("https://project.peakme.in");
//            }
//        }finally {
//            driver.quit();
//        }
        int num = 0;
        while (num<count) {
            try {
                num++;
                String userA = extra.userAgent.get(rand.nextInt(extra.userAgent.size()));
                profile = new FirefoxOptions();
                profile.addPreference("general.useragent.override", userA);
                driver = new FirefoxDriver(profile);
                System.out.println("driver get");
                driver.get("https://mail.tutanota.com/signup");
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
                driver.manage().window().maximize();

                Thread.sleep(10000);
                driver.findElement(By.cssSelector("div.button-min-height:nth-child(5) > button:nth-child(1)")).click();
                Thread.sleep(1000);
                driver.findElement(By.cssSelector("div.checkbox:nth-child(1) > div:nth-child(1) > input:nth-child(1)")).click();
                Thread.sleep(800);
                driver.findElement(By.cssSelector("div.checkbox:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).click();
                Thread.sleep(800);
                driver.findElement(By.cssSelector(".dialog-buttons > button:nth-child(2)")).click();
                WebElement user = driver.findElement(By.cssSelector(".right"));
                WebElement repass = driver.findElement(By.cssSelector("div.text-field:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
                WebElement pass = driver.findElement(By.cssSelector("input.input:nth-child(4)"));

                String firstName = extra.firstName.remove(rand.nextInt(extra.firstName.size())).toLowerCase();
                String lastName = extra.lastName.remove(rand.nextInt(extra.lastName.size())).toLowerCase();
                String pass1 = extra.generatePass(firstName, lastName);
                String mail = lastName + rand.nextInt(1000) + "." + firstName;

                Thread.sleep(2000);

                user.click();
                user.sendKeys(mail);
                pass.click();
                pass.sendKeys(pass1);
                repass.click();
                repass.sendKeys(pass1);

                Thread.sleep(8000);

                driver.findElement(By.cssSelector("div.checkbox:nth-child(4) > div:nth-child(1) > input:nth-child(1)")).click();
                driver.findElement(By.cssSelector(".max-width-m > div:nth-child(3) > div:nth-child(1) > input:nth-child(1)")).click();
                driver.findElement(By.cssSelector(".mt-l > button:nth-child(1)")).click();

                Thread.sleep(40000);

                driver.findElement(By.cssSelector("div.flex:nth-child(4) > button:nth-child(1)")).click();
                driver.findElement(By.cssSelector("div.flex-center:nth-child(4) > div:nth-child(1) > button:nth-child(1)")).click();

                extra.writeTo(firstName + "," + lastName + "," + mail + "," + pass1 + "," + extra.copyClipboard(), "D:\\javaProject\\seleniumTestJava\\tuta.txt");
                extra.writeTo(mail + "@tutanota.com","C:\\Users\\sanjiv\\Desktop\\reco.txt");
                data.doIt();

                int k = 0;
                while (!extra.checkInternet(null)){
                    if (k==10) throw new Exception();
                    k++;
                    Thread.sleep(2000);
                }
            }catch (Exception e){
                e.printStackTrace();
                playAudio.main();
            } finally {
                if (driver != null)
                    driver.quit();
            }
        }
    }
}
