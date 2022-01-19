import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // Echo
        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
