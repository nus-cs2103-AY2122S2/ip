import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);

        ArrayList<String> list = new ArrayList<String>();
        String input = "";

        while (!input.equals("bye")) {

            input = br.readLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
