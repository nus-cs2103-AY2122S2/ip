package component;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.IncorrectInputException;
import exceptions.TaskException;
import exceptions.ToDosException;
import exceptions.WrongInputException;

/**
 * A class that belongs to the component package.
 * This class encapsulates the Parser logic from users in Nexus.
 */
public class Parser {
    private final String input;

    /**
     * Constructs Parser.
     * @param userInput  User input as a type String.
     * @throws TaskException Throws a TaskException when user input
     *                       is in an incorrect format.
     */
    public Parser(String userInput) throws TaskException {
        checkUserInput(userInput);
        this.input = userInput;
    }

    /**
     * Executes the command for tasks.
     * @param tasks Pass the TaskList into {@link component.Command} class for manipulation.
     */
    public String executeCommand(TaskList tasks) {
        Command c = new Command(input, tasks);
        return c.runCommand();
    }

    /**
     * Checks user input.
     * @param userInput User input in a String.
     * @throws exceptions.TaskException Throws a TaskException if the user input is invalid.
     */
    private void checkUserInput(String userInput) throws TaskException {
        String[] wordsSplitByEmptySpace = userInput.split(" ");
        String firstWord = wordsSplitByEmptySpace[0];
        if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete")) {
            checkForWrongInputException(wordsSplitByEmptySpace);
        }

        if (notCommand(firstWord)) {
            throw new IncorrectInputException();
        }

        if (incorrectLength(wordsSplitByEmptySpace)) {
            checkForTaskException(wordsSplitByEmptySpace);
        }
    }

    /**
     * Checks if user input is of incorrect length.
     * @param wordsSplitByEmptySpace String array representing the user input split by empty space.
     * @return Boolean value representing if user input has an incorrect length.
     */
    private boolean incorrectLength(String[] wordsSplitByEmptySpace) {
        String firstWord = wordsSplitByEmptySpace[0];
        return wordsSplitByEmptySpace.length == 1
                && !firstWord.equals("list") && !firstWord.equals("bye") && !firstWord.equals("help");
    }

    /**
     * Checks if user input has the wrong format.
     * @param wordsSplitByEmptySpace String array representing the user input split by empty space.
     * @throws WrongInputException Throws {@link exceptions.WrongInputException} if there is wrong input format.
     */
    private void checkForWrongInputException(String[] wordsSplitByEmptySpace) throws WrongInputException {
        try {
            Integer.parseInt(wordsSplitByEmptySpace[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException();
        }
    }

    /**
     * Checks if user input violates any task commands' format.
     * @param wordsSplitByEmptySpace String array representing the user input split by empty space.
     * @throws ToDosException Throws {@link exceptions.ToDosException} if ToDos command has the wrong format.
     * @throws DeadlineException Throws {@link  exceptions.DeadlineException} if Deadline command has the wrong format.
     * @throws EventException Throws {@link  exceptions.EventException} if Event command has the wrong format.
     */
    private void checkForTaskException(String[] wordsSplitByEmptySpace) throws ToDosException,
            DeadlineException, EventException {
        String command = wordsSplitByEmptySpace[0];
        switch (command) {
        case "todo":
            throw new ToDosException();
        case "deadline":
            throw new DeadlineException();
        default: //case "event":
            throw new EventException();
        }
    }

    /**
     * Helps to check if {@link #checkUserInput(String)} is incorrect.
     * @param command Checks if user input is a command.
     * @return True if input is not a command and false otherwise.
     */
    private static boolean notCommand(String command) {
        return (!command.equals("bye") && !command.equals("list")
                && !command.equals("delete") && !command.equals("mark")
                && !command.equals("unmark") && !command.equals("todo")
                && !command.equals("deadline") && !command.equals("event")
                && !command.equals("find") && !command.equals("help"));
    }

}
