import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class peakMeIn {
    private static final extras e = new extras();
    private static String preIp = "";
    private static int y;
    private final static dataInput inp = new dataInput();
    private static Thread dataThread = new Thread(inp);
    private static restartData data;
    private static final String ips = "D:\\javaProject\\seleniumTestJava\\ipsCon.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of impressions you want (in tens) : - > ");
        int x = scanner.nextInt();
        System.out.print("press 1 for imp, 2 for click and 3 for fast process");
        y = scanner.nextInt();
        data = new restartData(scanner);
        data.getNamePhone();
        e.writeTo("started at " + currentDate(),ips);
        dataThread.start();
        int z = 0;
        while (z<x){
            if (inp.getInput().indexOf('q') != -1) break;
            try {
                main();
            }catch(Exception d){
                StringWriter writer = new StringWriter();
                d.printStackTrace(new PrintWriter(writer));
                e.writeTo("main " + writer.toString() + d.getMessage(),ips);
            }finally{
                e.writeTo("system done at " + currentDate(),ips);
            }
            z++;
        }
        e.writeTo("process ended with approximately " + z*10 + "done", ips);
        System.out.println("approximated times done today " + z*10 + " with " + y + "as a system");
        playAudio.main();
    }
    private static String currentDate() {
        LocalDateTime date = LocalDateTime.now();
        return (date.getMonth()+" "+date.getDayOfMonth()+" "+date.getDayOfWeek()+" Time: "+date.getHour()+":"+date.getMinute());
    }
    public static void mouseLocation(){
        java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println(p.getX() + " " + p.getY());
    }
    private static void main(){
        Random rand = new Random();
        int n = 0;/*
        restartWifi wifi = null;
        try {
        wifi = new restartWifi();*/
        boolean clicked;
        boolean cond;
        while (n < 10) {
            WebDriver driver = null;
            FirefoxOptions option = new FirefoxOptions();
            String userAgent = e.userAgent.get(rand.nextInt(e.userAgent.size()));
            option.addPreference("general.useragent.override",userAgent);
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("user-agent="+userAgent);
            boolean cond2 = true;
            try {
                System.setProperty("webdriver.gecko.driver","G:\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver(option);
//                driver = new ChromeDriver(opt);

                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
                do {
                    n++;
                    if (cond2) driver.get("https://peakme.in/shome.php");
                    else driver.navigate().refresh();
                    String mainHandle = driver.getWindowHandle();
                    extras.waitFor(800);
                    driver.findElement(By.id("name")).sendKeys(e.firstName.get(rand.nextInt(e.firstName.size())));
                    extras.waitFor(800);
                    clicked = false;
                    if (y==2){
                        cond = rand.nextBoolean();
                    }else if (y==3){
                        cond = false;
                    } else{
                        cond = rand.nextInt(100) % 3 == 0;
                    }
                    if (cond) {
                        try {
                            Actions act = new Actions(driver);
                            WebElement elem = driver.findElement(By.cssSelector("iframe"));
                            act.moveToElement(elem).click().perform();
                            clicked = true;
                            extras.waitFor(9000);
                            click();//scroll down
//                            extras.waitFor(2000);
                            /*
                            Predicate pred = (Predicate<WebDriver>) webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
                            int count = 0;
                            while (!pred.test(driver)) {
                                extras.waitFor(1000);
                                if (count>10){
                                    break;
                                }
                                count++;
                            }
                            JavascriptExecutor secPage = (JavascriptExecutor) driver;
                            secPage.executeScript("");//check scroll available*/

//                            driver.close();
//                            driver.switchTo().window(mainHandle);
                        } catch (Exception a) {
                            System.out.println("new window error");
                            a.printStackTrace();
                            playAudio.main();
                        }
                    }
                    /*try {
                        wifi.start();
                    } catch (InterruptedException a) {
                        a.printStackTrace();
                    }
                    extras.waitFor(33000);*/
                    driver.manage().deleteAllCookies();
                    data.doIt();
                    int k =0;
                    while (true) {
//                        click();
                        if (e.checkInternet(null)) {
                            String currentIp = e.dataRead.split("<br>")[0].split(" ")[2];
                            if (currentIp.equals(preIp)){
                                while (!inp.getInput().equals("2")){
                                    System.out.println("same ip address found. Enter 2 if fixed.");
                                    playAudio.main();
                                    extras.waitFor(20000);
                                }
                                inp.setInput("");
                                if (dataThread.getState()==Thread.State.TERMINATED) {
                                    dataThread = new Thread(inp);
                                    dataThread.start();
                                }
                            }
                            e.writeTo(n + "->" + currentIp + " " + clicked + " " + userAgent, ips);
                            preIp = currentIp;
                            break;
                        }k++;extras.waitFor(2000);if (k==10) throw new Exception();
                    }
                    cond2=false;
                } while (rand.nextInt() % 2 == 0);
            }catch (Exception g){
                StringWriter sw = new StringWriter();
                g.printStackTrace(new PrintWriter(sw));
                g.printStackTrace();
                System.out.println(g.getMessage());
                e.writeTo("second -> stacktrace " + sw.toString() + " message => " + g.getMessage(),ips);
                while (!e.checkInternet(null)){
                    System.out.println("internet not connected");
                    playAudio.main();
                }
            } finally {
                if (driver != null)
                driver.quit();
            }
        }
        /*} catch (Exception f){
            e.writeTo("third  " + f.getMessage(),ips);
        } finally {
            if (wifi != null)
            wifi.end();
        }*/
    }
    private static void click() {
        Robot r = null;
        try {
            r = new Robot();
        }catch (AWTException ignored){}
        assert r != null;/*
        r.mouseMove(1381,845);
        r.delay(800);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.delay(800);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);*/
        int a = 0;
        while (a<3) {
            a++;
            r.keyPress(KeyEvent.VK_PAGE_DOWN);
            r.delay(3000);
            r.keyRelease(KeyEvent.VK_PAGE_DOWN);
        }
    }
    
    private static class restartWifi { //value of password is not set, please set it
        private final WebDriver driver = new ChromeDriver();
        private final JavascriptExecutor exec = (JavascriptExecutor) driver;

        public restartWifi() {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            driver.manage().window().maximize();
        }

        public void start() throws InterruptedException {
            driver.manage().deleteAllCookies();
            driver.get("http://192.168.18.1");
            exec.executeScript("document.querySelector(\"#txt_Username\").value = 'Epuser';document.querySelector(\"#txt_Password\").value = '?';document.querySelector(\"#loginbutton\").click()");
            TimeUnit.MILLISECONDS.sleep(1500);
            exec.executeScript("document.querySelector(\"#menuIframe\").contentWindow.document.getElementById(\"Restarttext\").click();");
            TimeUnit.MILLISECONDS.sleep(1000);
            exec.executeScript("document.querySelector(\"#menuIframe\").contentWindow.document.querySelector(\"#routermngtpageSrc\").contentWindow.document.querySelector(\"#btnReboot\").click();");
            TimeUnit.MILLISECONDS.sleep(500);
            driver.switchTo().alert().accept();
        }
        public void end(){
            driver.quit();
        }
    }
}
