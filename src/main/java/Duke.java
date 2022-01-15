import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);

        String input = "placeholder";
        while (!input.equals("bye")) {
            input = sc.nextLine(); //reads string.

           if (!input.equals("bye")) {
               System.out.println(input + "\n");
           } else {
               System.out.println("Bye. Hope to see you again soon!");
           }
        }
    }
}
