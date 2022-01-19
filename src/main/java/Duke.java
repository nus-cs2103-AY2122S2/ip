import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(lines);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
                break;
            }
            else {
                System.out.println(lines + "\n" + input + "\n" + lines);
            }
        }
    }
}
