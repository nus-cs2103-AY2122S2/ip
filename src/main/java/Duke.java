import java.util.*;
import java.io.*;

public class Duke {

    public static final String hl = "------------------------------------------------------------------------";

    public static void greetings() {
        String logo = "";
        System.out.println(hl + "\nHi! I'm Duke\nWhat can I do for you?");
    }

    public static void chat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(hl);
            System.out.print("> ");
            String input = br.readLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(hl);
                break;
            } else {
                System.out.println(input);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        greetings();

        chat();
    }
}
