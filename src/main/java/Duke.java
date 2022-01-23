import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final ArrayList<String> TO_DO_LIST = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        System.out.println(WELCOME_MESSAGE);
        while (!isExit) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    isExit = true;
                    System.out.println(GOODBYE_MESSAGE);
                    break;
                case "list":
                    for (int i = 0; i < TO_DO_LIST.size(); i++) {
                        System.out.printf("%d. %s%n", i + 1, TO_DO_LIST.get(i));
                    }
                    break;
                default:
                    TO_DO_LIST.add(userInput);
                    System.out.printf("added: %s%n", userInput);
                    break;
            }
        }
    }
}
