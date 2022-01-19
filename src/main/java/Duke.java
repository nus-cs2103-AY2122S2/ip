import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ron\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        String input = sc.nextLine();
        String bye = "bye";
        while (!input.equals(bye)) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
