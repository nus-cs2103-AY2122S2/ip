import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String s = input.next();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            System.out.println(s);
        }

    }
}
