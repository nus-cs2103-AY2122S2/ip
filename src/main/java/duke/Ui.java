package duke;

import java.io.IOException;
import java.util.Scanner;
/**
 * Ui class that handles the display and obtaining of user input
 * of Duke application
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Ui {
    Parser parser;
    String line = "____________________________________________________________\n";
    Scanner scanner;

    /**
     * Constructor.
     * Initialises a scanner for user input and starts up application welcome message.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        System.out.println(createWelcomeMessage());
    }

    /** Returns a pre-made Welcome message to users  */
    public String createWelcomeMessage() {
        StringBuilder welcomeMessage = new StringBuilder();
        String welcome = "Welcome to Duke, your personal Task assistant!\n";
        welcomeMessage.append(line).append(welcome).append(line);
        return welcomeMessage.toString();
    }

    /** Takes in TaskList and Storage objects so that they can be updated with user's commands  */
    public void displayCommandMessage(TaskList tasks, Storage storage) throws DukeException, IOException {
        System.out.println(askForCommand());
        //parseUserInput(tasks, storage);
    }

    /** Returns a pre-made command message to users  */
    public String askForCommand() {
        StringBuilder commandMessage = new StringBuilder();
        String command = "What is your command: \n";
        commandMessage.append(command);
        return commandMessage.toString();
    }

    /** Takes in TaskList and Storage objects to parse the user input  */
    public String parseUserInput(TaskList tasks, Storage storage, String userInput) throws DukeException, IOException {
        parser = new Parser(tasks, storage);//Calls Parser to parse information of userInput
        try {
            return parser.parseInput(userInput);//and do the respective action from duke.TaskList
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }
}
