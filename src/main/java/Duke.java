import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // greeting message
        String greetings = "    Hi there! 👋 I'm Duke\n"
                            + "    What can I do for you?";

        // divider
        String lines = "    ---------------------------------";

        System.out.println(lines);
        System.out.println(greetings);
        System.out.println(lines);
        // reading user input
        Scanner sc = new Scanner(System.in);

        // INITIALIZING VARIABLES
        /**
         * whether the user's input is bye
         */
        boolean isBye = false;

        /**
         * current number of to do tasks
         */
        int count = 0;

        /**
         * Array container for user's to do tasks
         */
        String[] todoList = new String[100];

        String userInput = sc.nextLine();

        while (!isBye) {
            if (userInput.equals("bye")) {
                isBye = true;

                System.out.println(lines);
                System.out.println("    Bye. See you again next time! Have a nice day 😊!");
                System.out.println(lines);
            } else {
                // storing input task in todoList

                switch (userInput) {
                    case "list":
                        System.out.println(lines);
                        for (int i = 0; i < count; i++) {
                            String display = String.format("    %d. %s", i + 1, todoList[i]);
                            System.out.println(display);
                        }
                        System.out.println(lines);
                        break;

                    default:
                        // storing input task
                        todoList[count] = userInput;
                        // displaying input task
                        System.out.println(lines);
                        System.out.println("    added: " + userInput);
                        System.out.println(lines);
                        break;
                }
                count++;
                userInput = sc.nextLine();
            }
        }
    }
}
