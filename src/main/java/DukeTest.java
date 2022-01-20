import java.util.ArrayList;
import java.util.Scanner;

public class DukeTest {
    private static String exitTrigger = "bye";
    private static ArrayList<String> store = new ArrayList<>();

    private static void echo(String str) {
        System.out.println(str);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addToStore(String str) {
        store.add(str);
        System.out.println("added: " + str);
    }

    private static void list() {
        for (int i = 1; i <= store.size(); i++) {
            System.out.println(i + ". " + store.get(i - 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = scanner.nextLine();

        while (!(input.equals(exitTrigger))) {
            switch (input) {
                case "list":
                    list();
                    break;
                default:
                    addToStore(input);
                    break;
            }

            input = scanner.nextLine();
        }

        exit();
        scanner.close();
    }
}
