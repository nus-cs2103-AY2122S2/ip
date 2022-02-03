package component;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.IncorrectInputException;
import exceptions.TaskException;
import exceptions.ToDosException;
import exceptions.WrongInputException;

/**
 * A class that belongs to the DukeComponent Package.
 * This class encapsulates the Parser logic from users in Duke.
 */
public class Parser {
    private final String input;

    /**
     * Constructor for Parser.
     * @param userInput  User input as a type String.
     * @throws TaskException Throws a TaskException when user input
     *                       is in an incorrect format.
     */
    public Parser(String userInput) throws TaskException {
        checkUserInput(userInput);
        this.input = userInput;
    }

    /**
     * Execute the command for tasks.
     * @param tasks Pass the TaskList into {@link component.Command} class for manipulation.
     */
    public String executeCommand(TaskList tasks) {
        Command c = new Command(input, tasks);
        return c.execute();
    }

    /**
     * Checks user input.
     * @param userInput User input in a String.
     * @throws TaskException Throws a TaskException if the user input is invalid.
     */
    private void checkUserInput(String userInput) throws TaskException {
        String[] splitInput = userInput.split(" ");
        if (splitInput.length == 0 || notCommand(splitInput[0])) {
            throw new IncorrectInputException();

        } else if (splitInput.length == 1 && !userInput.equals("list") && !userInput.equals("bye")) {
            String command = splitInput[0];
            switch (command) {
            case "todo":
                throw new ToDosException();
            case "deadline":
                throw new DeadlineException();
            default: //case "event":
                throw new EventException();
            }
        } else {
            String command = splitInput[0];
            if (command.equals("mark") || command.equals("unmark")) {
                try {
                    Integer.parseInt(splitInput[1]);
                } catch (NumberFormatException e) {
                    throw new WrongInputException();
                }
            }
        }
    }

    /**
     * Helper method for {@link #checkUserInput(String)}.
     * @param command Checks if user input is a command.
     * @return True if input is not a command and false otherwise.
     */
    private static boolean notCommand(String command) {
        return (!command.equals("bye") && !command.equals("list")
                && !command.equals("delete") && !command.equals("mark") && !command.equals("unmark")
                && !command.equals("todo") && !command.equals("deadline") && !command.equals("event")
                && !command.equals("find"));
    }

}
