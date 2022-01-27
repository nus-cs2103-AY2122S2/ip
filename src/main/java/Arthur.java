import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;

public class Arthur {

    /**
     * Adds design above and below the given text.
     * @param text The string to be placed between the design
     */
    public static void printFormat(String text) {
        String design = "_";
        System.out.println(design.repeat(50) + "\n"
                + text + "\n" + design.repeat(50));
    }

    public static void main(String[] args) {
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Notebook notebook = new Notebook();     // An object to store list of tasks

        // Greetings
        String logo = " / \\   _ |_ |_  | |  _  \n"
                + "/---\\ |  |_ | | |_| |  ";
        printFormat("Greetings from\n" + logo
                + "\n" + "How may I assist you today?");

        try {
            input = io.readLine();
            // keeps running till exit input 'bye'
            while (!input.equals("bye")) {
                input= input.toLowerCase();
                if (input.equals("hello") || input.equals("hi")) {  // Hello inputs
                    printFormat("Hello there!");
                } else {
                    try {
                        printFormat(notebook.instruction(input));
                    } catch (DateTimeParseException e) {
                        printFormat("Please enter the date/time in format: yyyy-mm-dd hh:mm \n"
                                + "You can also enter time or date only");
                    }
                }
                input = io.readLine();
            }
            printFormat("Bye!" + "\n" + "Have a great day!");
        } catch (IOException e) {
            printFormat("Sorry, there seems to be an Issue."
                    + "\n" + "Please restart and try again");
        }
    }
}
