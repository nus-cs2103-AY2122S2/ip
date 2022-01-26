import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void welcomePrompt() {
        String logo = "-----YALE-----";
        System.out.println("Allow me to introduce myself\n" + logo);
        System.out.println("The name's Yale." );
    }

    /**
     * Method to receive input from the scanner and
     * returns that input in a String format
     * @param scanner
     * @return Input
     */
    public String receiveInput(Scanner scanner) {
        System.out.println("\nEnter command below:");
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Method to check if user input
     * is equal to "bye"
     * @param input
     * @return
     */
    public boolean checkExit(String input) {
        return input.equals("bye");
    }
}
