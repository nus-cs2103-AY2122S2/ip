import java.util.*;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello there, I'm Duke! Let's chat!");

        // Start scanner
        FastIO sc = new FastIO();

        ArrayList<String> lst = new ArrayList<String>();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                lst.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + ". " + lst.get(i));
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! It was nice having you!");
        sc.close();
    }
}
