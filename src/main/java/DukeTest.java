import java.util.Scanner;

public class DukeTest {
    private static String exitTrigger = "bye";

    public static void echo(String str) {
        System.out.println(str);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = scanner.nextLine();

        while (!(input.equals(exitTrigger))) {
            echo(input);
            input = scanner.nextLine();
        }

        exit();
        scanner.close();
    }
}
