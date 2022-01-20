import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = "";
        Boolean end = false;

        while(!end) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                end = true;
            } else {
                System.out.println(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
