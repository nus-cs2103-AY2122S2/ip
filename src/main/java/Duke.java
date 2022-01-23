import java.util.Scanner;

public class Duke {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        System.out.println(WELCOME_MESSAGE);
        while (true) {
            String userInput = sc.nextLine();
            switch (userInput) {
            case "bye":
                isExit = true;
                System.out.println(GOODBYE_MESSAGE);
                break;
            default:
                System.out.println(userInput);
                break;
            }
            if (isExit) {
                break;
            }
        }
    }
}
