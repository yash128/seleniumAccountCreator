import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class extras {
    public HashMap<Integer,Character> special;
    public ArrayList<String> firstName;
    public ArrayList<String> lastName;
    public static final Random rand = new Random();
    public String dataRead;
    public ArrayList<String> userAgent;
    public extras() {
        userAgent = new ArrayList<>();
        firstName = new ArrayList<>();
        this.special = new HashMap<>();
        lastName = new ArrayList<>();
        userAgent();
        loadData();
        giveValueToSpecial();
    }

    // enter null for default url
    public boolean checkInternet(String url){
        if (url == null){
            url = "https://project.peakme.in";
        }
        try {
            URL u = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
            dataRead = reader.readLine();
            reader.close();
        }catch (IOException e){
            return false;
        }
        return true;
    }

    private void userAgent(){
        File f = new File("D:\\javaProject\\seleniumTestJava\\user-agents.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))){
            String str;
            while ((str = reader.readLine()) != null){
                userAgent.add(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeTo(String data,String path) {
        File f = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String copyClipboard(){
        Toolkit t = Toolkit.getDefaultToolkit();
        Clipboard clip = t.getSystemClipboard();
        String result = "";
        try {
            result = (String) clip.getData(DataFlavor.stringFlavor);
        }catch (IOException | UnsupportedFlavorException e){
            e.printStackTrace();
        }
        return result;
    }
    private void giveValueToSpecial() {
        special.put(1, '!');
        special.put(2, '@');
        special.put(3, '#');
        special.put(4, '$');
        special.put(5, '%');
        special.put(6, '^');
        special.put(7, '*');
        special.put(8, '(');
    }

    public String generatePass(String start, String last) {
        StringBuilder builder = new StringBuilder();
        int charsFromName = rand.nextInt(3) + 1;
        for (int i = 0; i < charsFromName; i++) {
            builder.append(start.charAt(i));
            builder.append(last.charAt(i));
        }
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(special.get(rand.nextInt(8) + 1));
        builder.append(rand.nextInt(999999));
        return String.valueOf(builder);
    }

    private void loadData() {
        try (BufferedReader caste = new BufferedReader(new FileReader("D:\\javaProject\\seleniumTestJava\\caste.txt"));
             BufferedReader nameFile = new BufferedReader(new FileReader("D:\\javaProject\\seleniumTestJava\\name.txt"))) {
            String n;
            while ((n = nameFile.readLine()) != null) {
                if (n.equals(",")) {
                    continue;
                }
                firstName.add(n);
            }
            while ((n = caste.readLine()) != null) {
                lastName.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void waitFor(int delay){
        try{
            TimeUnit.MILLISECONDS.sleep(delay);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class restartData{
    private Robot r;
    private int nameOfPhone;
    private Scanner scan;
    public restartData(Scanner scan){
        this.scan = scan;
        try {
            r = new Robot();
        } catch (AWTException awtException) {
            awtException.printStackTrace();
        }
    }
    public void getNamePhone(){
        System.out.print("press 1 if phone is Redmi and 2 if phone is LG K9 : ");
        nameOfPhone = scan.nextInt();
    }
    private void init(){
        r.mouseMove(707,842);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.delay(100);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    private void lgK9(){
        r.mouseMove(914,604);r.delay(300);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseMove(847,422);r.delay(1000);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);r.delay(4000);
        r.mouseMove(914,604);r.delay(2000);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);r.delay(300);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    private void redmi() {
        r.mouseMove(890,594);r.delay(400);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);r.delay(4000);
        r.mouseMove(894,448);r.delay(2000);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseMove(890,594);r.delay(600);r.mousePress(InputEvent.BUTTON1_DOWN_MASK);r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    public void doIt() {
        init();
        if (nameOfPhone == 1) {
            redmi();
        }
        if (nameOfPhone == 2){
            lgK9();
        }
        extras.waitFor(3000);
        init();
    }
}