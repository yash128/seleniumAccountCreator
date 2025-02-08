import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.*;
import java.util.Scanner;

public class Trial {
    public static void main(String[] args) throws Exception {
        dataInput a = new dataInput();
        Thread b = new Thread(a);
        System.out.println("sd");
        b.interrupt();
        return;
    }
}
class a implements Runnable {
    public BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
    public boolean b=true;
    @Override
    public void run() {
        while (b) {
            try {
                System.out.println("enter ");
                String a = s.readLine();
                System.out.println("inp is  " + a);
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
