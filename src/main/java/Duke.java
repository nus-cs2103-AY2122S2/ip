import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("========================");

        while(true) {
            System.out.print("Me   : ");
            String message = sc.nextLine();
            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.print("Duke : " + message + "\n");
        }
        sc.close();
    }
}
