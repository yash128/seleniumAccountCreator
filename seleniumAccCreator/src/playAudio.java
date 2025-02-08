import java.awt.*;

public class playAudio {
    static int a = 0;
    public static void main() {
       while (true){
           extras.waitFor(500);
           Toolkit.getDefaultToolkit().beep();
           a++;
           if (a==50) break;
       }
       a=0;
    }
}
