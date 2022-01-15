import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Duke {
    /** Stores the messages sent by the user. */
    private static List<String> messages = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello master I am Chi \nHow may I serve you today nyan~?");

        // Stores the text input of user
        String echo;
        // Request for user input
        echo = userInput.nextLine();
        // Check if bye has been said
        while (!echo.toLowerCase().equals("bye")) {
            if (echo.toLowerCase().equals("list")) {
                int i = 1;
                for (String s: messages) {
                    System.out.printf("%d. %s\n",i, s);
                    ++i;
                }
                echo = userInput.nextLine();
                continue;
            }
            // Echo message sent by user
            System.out.println(echo);
            // Store message
            messages.add(echo);
            echo = userInput.nextLine();
        }
        // Ending statement and close scanner
        System.out.println("Sayonara, see you next time nyan~");
        userInput.close();
    }
}
