package lucifer;

import java.io.IOException;
import java.util.Scanner;

import lucifer.commands.ParseCommands;
import lucifer.exceptions.EmptyInputException;
import lucifer.exceptions.InvalidException;
import lucifer.storage.Storage;
import lucifer.tasklist.TaskList;
import lucifer.ui.Ui;


/**
 * Main class which runs the Lucifer chatbot
 * @author Yu Meng
 */
public class Lucifer {
    /**
    * Lucifer.Storage which saves or load tasks on hand.
    **/
    private final Storage storage;
    /**
     * Lucifer.TaskList which stores the list of user tasks.
     **/
    private final TaskList tasks;
    /**
     * Ui that represents the interaction messages that user will have with Lucifer.Lucifer chatbot
     **/
    private final ParseCommands parser;

    /**
       * Constructor for Lucifer chatbot.
       */
    public Lucifer() throws IOException {
        storage = new Storage(System.getProperty("user.dir"));
        tasks = new TaskList(storage.loadList());
        parser = new ParseCommands(tasks);
    }
    /**
     * Starts the Lucifer chatbot.
     */
    private void run() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String command = sc.nextLine();
                if (command.isBlank()) {
                    throw new EmptyInputException();
                }
                if (command.equals("bye")) {
                    storage.saveFileList(tasks.getTaskList());
                    break;
                } else {
                    parser.parseCommand(command);
                }
            } catch (EmptyInputException | IOException | InvalidException e) {
                e.getMessage();
            }
        }
    }
    /**
     * Initializes and runs the program.
     *
     * @param args The input arguments.
     */
    public static void main(String[] args) throws IOException {
        new Lucifer().run();
    }
    /**
     * Retrieves the user input and parses the command given by user.
     */
    public String getResponse(String input) {
        String output;
        try {
            if (input.isBlank()) {
                throw new EmptyInputException();
            } else if (input.equals("bye")) {
                output = Ui.farewell() + "\nYour desires have been etched into my memories my love.";
                storage.saveFileList(tasks.getTaskList());
            } else {
                output = parser.parseCommand(input);
            }

        } catch (EmptyInputException | IOException | InvalidException e) {
            output = e.getMessage();
        }
        return output;
    }
}

