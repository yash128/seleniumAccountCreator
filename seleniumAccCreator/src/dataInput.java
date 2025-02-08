import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dataInput implements Runnable{
    public String getInput() {
        return input;
    }
    public boolean run = true;

    public synchronized void setInput(String input) {
        this.input = input;
        System.out.println("value input is : " + input);
    }
    private String input = "";
    private BufferedReader scan;
    private InputStreamReader reader;
    public dataInput(){
        reader = new InputStreamReader(System.in);
        scan = new BufferedReader(reader);
    }
    @Override
    public void run() {
        while (run){
            try {
                setInput(scan.readLine());
                if (input.equals("t")) {
                    run = false;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void close(){
        try {
            scan.close();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
