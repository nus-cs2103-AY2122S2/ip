package DukeComponent;

import Exceptions.TaskException;
import Exceptions.EventException;
import Exceptions.ToDosException;
import Exceptions.IncorrectInputException;
import Exceptions.DeadlineException;
import Exceptions.WrongInputException;

/**
 * A class that belongs to the DukeComponent Package.
 * This class encapsulates the Parser logic from users in Duke.
 */
public class Parser {
    String userInput;

    /**
     * Constructor for Parser.
     * @param userInput  User input as a type String.
     * @throws TaskException Throws a TaskException when user input
     *                       is in an incorrect format.
     */
    public Parser(String userInput) throws TaskException {
        checkUserInput(userInput);
        this.userInput = userInput;
    }

    /**
     * Execute the command for tasks.
     * @param tasks Pass the TaskList into {@link DukeComponent.Command} class for manipulation.
     */
    public void executeCommand(TaskList tasks) {
        Command c = new Command(userInput, tasks);
        c.execute();
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

        } else if (splitInput.length == 1) {
            String command = splitInput[0];
            switch (command) {
            case "todo":
                throw new ToDosException();
            case "deadline":
                throw new DeadlineException();
            case "event":
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
     * @param userInput Checks if user input is a command.
     * @return True if input is not a command and false otherwise.
     */

    private static boolean notCommand(String userInput) {
        return (!userInput.equals("bye") && !userInput.equals("list")
                && !userInput.equals("delete")  && !userInput.equals("mark") && !userInput.equals("unmark")
                && !userInput.equals("todo") && !userInput.equals("deadline") && !userInput.equals("event"));
    }


}
