import java.util.Scanner;  // Import the Scanner class

public class Duke {

    public static void main(String[] args) {
        String line = "__________________________________________________________\n";
        String welcomeMessage = "Ahoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
        String welcomeQuestion = "What can I do for you?\n";
        String help = "A 'bye' command will exit the bot, yarr.\n";
        System.out.println(line + welcomeMessage + welcomeQuestion + help + line);

        Scanner sc= new Scanner(System.in);
        System.out.println("Your wish is my command. Your command: ");
        String command = ""; // Read user input
        String bye = "bye";
        while (!command.equalsIgnoreCase(bye)) { // Checks if user wants to exit or not
            command = sc.next();
            System.out.println("Aye, Aye. Your command: " + command + "\n" + line);  // Output user input
            if (!command.equalsIgnoreCase(bye)) {
                System.out.println("Yarr your wish is my command. Your command: ");
            } else {
                System.out.println("Fair winds adventurer, till we meet next time yarr. Bye for now.\n" + line);
            }
        }
    }
}
