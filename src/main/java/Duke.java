import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initialize a scanner object
        Scanner userInput = new Scanner(System.in);
        System.out.println("Hello I am Chi \nHow may I serve you today?");

        // Stores the text input of user
        String echo;
        // Request for user input
        echo = userInput.nextLine();
        // Check if bye has been said
        while (!echo.toLowerCase().equals("bye")) {
            // If not, output their text and request for new message
            System.out.println(echo);
            echo = userInput.nextLine();
        }
        // Ending statement and close scanner
        System.out.println("Bye, see you next time nyan~");
        userInput.close();
    }
}
