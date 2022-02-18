package duke.ui;

import duke.commands.Command;
import duke.commands.PrintCommand;
import duke.parser.Parser;


public class Ui {

    /** Read in the user input and pass it to Parser to return a command.  */
    public Command read(String input) {
        try {
            return Parser.parse(input);
        } catch (AssertionError e) {
            return new PrintCommand("Your command is too long!");
        }
    }

    /** Returns the response and add dividing lines to it to make it more fancy.  */
    public String respond(String respond) {
        return respond;
    }

}
