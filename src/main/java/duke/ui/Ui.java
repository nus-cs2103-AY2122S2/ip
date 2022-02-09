package duke.ui;

import duke.commands.Command;
import duke.parser.Parser;

import java.util.Scanner;

public class Ui {
    private final Scanner s = new Scanner(System.in);

    /** Say welcome to the user!  */
    public static String welcome() {
        return "Hello! I am xpz\nWhat can I do for you?\n";
    }

    /** Read in the user input and pass it to Parser to return a command.  */
    public Command read(String input) {
        return Parser.parse(input);
    }

    /** Returns the response and add dividing lines to it to make it more fancy.  */
    public String respond(String respond) {
        return respond;
    }

}
