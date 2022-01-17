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

        // boolean to indicate whether userInput is bye
        boolean isBye = false;

        String userInput = sc.nextLine();

        while (!isBye) {
            if (userInput.equals("bye")) {
                isBye = true;

                System.out.println(lines);
                System.out.println("    Bye. See you again next time! Have a nice day 😊!");
                System.out.println(lines);
            } else {
                System.out.println(lines);
                System.out.println("    " + userInput);
                System.out.println(lines);

                userInput = sc.nextLine();
            }
        }
    }
}
