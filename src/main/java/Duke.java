import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        ArrayList<String> items = new ArrayList<>();

        System.out.println("Hello!!! I'm jBot\nWhat can I do for you? :3");
        s = br.readLine();

        while (true) {
            // "bye" to end the program
            if (s.equals("bye")) {
                System.out.println("Bye!! See you again soon!!");
                break;
            }
            // if user requests to list the items
            else if (s.equals("list")){
                int t = 1;
                for(String item : items) {
                    System.out.println(t + ". " + item);
                    t++;
                }
            }
            // user add items to list
            else {
                System.out.println("     added: " + s);
                items.add(s);
            }
            s = br.readLine();
        }
    }
}

