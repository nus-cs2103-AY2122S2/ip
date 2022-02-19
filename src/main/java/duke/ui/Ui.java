package duke.ui;

import java.util.Scanner;


/**
 * Class encompassing all the interactions of the bot with the user.
 */
public class Ui  {
    private final Scanner sc;

    /**
     * Constructor for the Ui class, creates a new scanner which allows it to take input form a user.
     */
    public Ui(String... args) {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the string for greeting the user.
     *
     * @return String containing the greeting by Duke.
     */
    public String greet() {
        return encloseWithin("Hello! I'm duke.Duke\nWhat can I do for you?\n");
    }

    /**
     * Returns the string for saying goodbye to the user.
     * @return String containing the goodbye message.
     */
    public String sayGoodbye() {
        String goodbye = encloseWithin("Bye. Hope to see you again soon!\n");
        this.sc.close();
        return goodbye;

    }

    /**
     * Function to format output of the bot within 2 lines.
     *
     * @param str output to the user
     */
    public void say(String str) {
        if (!str.equals("null")) {
            String say = encloseWithin(str);
            System.out.println(say);
        }
    }

    /**
     * Receives input from user.
     *
     * @return the inputted texts from the user
     */
    public String askForInput() {
        return this.sc.nextLine();
    }

    private String encloseWithin(String str) {
        String header = "____________________________________________________________";
        StringBuilder string = new StringBuilder();
        string.append(header).append("\n").append(str).append(header).append("\n");
        return string.toString();
    }

}
