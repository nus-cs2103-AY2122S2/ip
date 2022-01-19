import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                printFormat(notebook.instruction(input));
                input = io.readLine();
            }
            printFormat("Bye!" + "\n" + "Have a great day!");
        } catch (IOException e) {
            System.out.println("Sorry, there seems to be an Issue."
                    + "\n" + "Please restart and try again");
        }
    }
}
