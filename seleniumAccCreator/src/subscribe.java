import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;

public class subscribe {
    private static String links[] = {"https://www.youtube.com/watch?v=sCBfivd4pLE","https://www.youtube.com/watch?v=YG5uQlSmX2w"};
    private static ArrayList<String> list = new ArrayList<>();
    public static void mainWithDriver(String[] args) {
        read();
        System.setProperty("webdriver.chrome.driver","G:\\drivers\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("user-agent=Mozilla/5.0 (Linux; Android 10; SM-A102U) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36");
        WebDriver driver = new ChromeDriver(opt);
        for (String str : list) {
            String data[] = str.split("~");
            driver.manage().window().maximize();
            driver.get("https://accounts.google.com");
            driver.findElement(By.cssSelector("#identifierId")).sendKeys(data[0]);
            driver.findElement(By.cssSelector(".VfPpkd-LgbsSe-OWXEXe-k8QpJ > span:nth-child(3)")).click();
            extras.waitFor(5000);
            driver.findElement(By.cssSelector("#password > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")).sendKeys(data[1]);
            driver.findElement(By.cssSelector("html.CMgTXc body#yDmH0d.nyoS7c.SoDlKd.EIlDfe div.H2SoFe.LZgQXe.TFhTPc div#initialView.RAYh1e.LZgQXe.qmmlRd div.xkfVF div.Aa1VU div#view_container.JhUD8d.SQNfcc.vLGJgb div.zWl5kd div.DRS7Fe.bxPAYd.k6Zj8d div.pwWryf.bxPAYd div.Wxwduf.Us7fWe.JhUD8d div.zQJV3 div.dG5hZc div.qhFLie div#passwordNext.FliLIb.DL0QTb div.VfPpkd-dgl2Hf-ppHlrf-sM5MNb button.VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.qIypjc.TrZEUc.lw1w4b div.VfPpkd-RLmnJb")).click();
            extras.waitFor(5000);
            driver.get("https://www.youtube.com/watch?v=sCBfivd4pLE");
            extras.waitFor(1000);
            driver.findElement(By.cssSelector("html body ytd-app div#content.style-scope.ytd-app ytd-page-manager#page-manager.style-scope.ytd-app ytd-watch-flexy.style-scope.ytd-page-manager.hide-skeleton div#columns.style-scope.ytd-watch-flexy div#primary.style-scope.ytd-watch-flexy div#primary-inner.style-scope.ytd-watch-flexy div#meta.style-scope.ytd-watch-flexy div#meta-contents.style-scope.ytd-watch-flexy ytd-video-secondary-info-renderer.style-scope.ytd-watch-flexy div#container.style-scope.ytd-video-secondary-info-renderer div#top-row.style-scope.ytd-video-secondary-info-renderer div#subscribe-button.style-scope.ytd-video-secondary-info-renderer ytd-subscribe-button-renderer.style-scope.ytd-video-secondary-info-renderer tp-yt-paper-button.style-scope.ytd-subscribe-button-renderer yt-formatted-string.style-scope.ytd-subscribe-button-renderer")).click();
            extras.waitFor(1000);driver.manage().deleteAllCookies();
        }
        driver.close();
        driver.quit();
    }

    public static void main(String[] args) { //with robot class
        read();
        try{
            Robot r = new Robot();
            for (String d : list) {
                String data[] = d.split("~");
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(KeyEvent.VK_SHIFT);
                r.keyPress(KeyEvent.VK_P);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_CONTROL);
                r.keyRelease(KeyEvent.VK_SHIFT);
                r.keyRelease(KeyEvent.VK_P);
                extras.waitFor(3000);
                pressKeys("accounts.google.com", r);
                r.keyPress(KeyEvent.VK_ENTER);
                r.keyRelease(KeyEvent.VK_ENTER);
                extras.waitFor(10000);
                pressKeys(data[0], r);
                mouseMoveClick(902, 606, r);
                extras.waitFor(10000);
                pressKeys(data[1],r);
                mouseMoveClick(910, 558, r);
                extras.waitFor(5000);
                mouseMoveClick(892,689,r);
                extras.waitFor(5000);
                for (String s : links) {
                    r.keyPress(KeyEvent.VK_CONTROL);
                    r.keyPress(KeyEvent.VK_T);
                    r.delay(100);
                    r.keyRelease(KeyEvent.VK_CONTROL);
                    r.keyRelease(KeyEvent.VK_T);
                    pressKeys(s, r);
                    r.delay(200);
                    r.keyPress(KeyEvent.VK_ENTER);
                    r.keyRelease(KeyEvent.VK_ENTER);
                    extras.waitFor(10000);
                    mouseMoveClick(946, 810, r);
                    r.delay(2000);
                }
                r.keyPress(KeyEvent.VK_ALT);
                r.keyPress(KeyEvent.VK_F4);
                r.delay(200);
                r.keyRelease(KeyEvent.VK_ALT);
                r.keyRelease(KeyEvent.VK_F4);
                Toolkit.getDefaultToolkit().beep();
                extras.waitFor(3000);
            }
        }catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void mouseMoveClick(int x,int y,Robot r){
        r.mouseMove(x,y);r.delay(100);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.delay(200);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    public static void pressKeys2(String str,Robot r){
        for (int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if (Character.isUpperCase(c))
                r.keyPress(KeyEvent.VK_SHIFT);
            r.keyPress(Character.toUpperCase(c));
            r.delay(100);
            r.keyRelease(Character.toUpperCase(c));
            if (Character.isUpperCase(c))
                r.keyRelease(KeyEvent.VK_SHIFT);
        }
    }

    public static void pressKeys(String str,Robot r){
        StringSelection sel = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(sel,sel);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.delay(100);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
    }
    public static void read() {
        File f = new File("password.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))){
            String str;
            while ((str=reader.readLine()) != null){
                list.add(str);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
