package michael;

import java.time.format.DateTimeParseException;

import michael.exceptions.AdditionalDescriptionException;
import michael.exceptions.InvalidCommandException;
import michael.exceptions.InvalidDescriptionException;

/**
 * Ui class handles the input
 *
 * @author Justin Ng Jie Ern
 */
public class Ui {

    /**
     * Constant String from Michael after every command to Michael.
     */
    private static final String FROM_MICHAEL = "From Michael: \n\t";

    /**
     * Storage Class to help with loading or saving or file.
     */
    private Storage storage;

    /**
     * TaskList Object is to help loaded and save.
     */
    private TaskList taskList;

    /**
     * Constructor to create a Ui Object.
     *
     * @param taskList Constructor to create a Ui Object.
     * @param storage Storage Class to help with loading or saving or file.
     */
    public Ui(TaskList taskList, Storage storage) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Evaluates if the Command inputted by User is valid.
     *
     * @param commandString The first String of the User input.
     * @return Boolean if the Command is a valid command.
     */
    private boolean isValidCommand(String commandString) {
        return (commandString.equals("bye") || commandString.equals("list") || commandString.equals("find")
                || commandString.equals("event") || commandString.equals("todo") || commandString.equals("deadline")
                || commandString.equals("delete") || commandString.equals("save") || commandString.equals("help")
                || commandString.equals("mark") || commandString.equals("unmark"));
    }

    /**
     * Evaluates if the Command inputted is a single worded command.
     *
     * @param commandString The first String of the User input.
     * @return Boolean if the command is a single worded command.
     */
    private boolean isValidSingleCommand(String commandString) {
        return (commandString.equals("bye") || commandString.equals("list") || commandString.equals("save")
                || commandString.equals("help"));
    }

    /**
     * Action to be completed if input is a single worded command.
     *
     * @param command The first String of the User input.
     * @return String of the reply that will be used as the text for the DialogBox
     */
    public String singleCommAction(String command) {
        String reply = "";
        if (command.equals("bye")) {
            reply = taskList.bye(storage);
        } else if (command.equals("save")) {
            reply = storage.save();
        } else if (command.equals("list")) {
            reply = taskList.list();
        } else if (command.equals("help")) {
            reply = taskList.help();
        }
        return reply;
    }

    /**
     * Action to be completed if input is a multi worded command.
     *
     * @param command The first String of the User input.
     * @return String of the reply that will be used as the text for the DialogBox
     */
    public String multiWordCommAction(String command, String input) {
        String reply = "";
        if (command.equals("mark") || command.equals("unmark")) {
            reply = taskList.taskCheck(input);
        } else if (command.equals("find")) {
            reply = taskList.find(input);
        } else if (command.equals("todo")) {
            reply = taskList.todo(input);
        } else if (command.equals("deadline")) {
            reply = taskList.deadline(input);
        } else if (command.equals("event")) {
            reply = taskList.event(input);
        } else if (command.equals("delete")) { //firstWord.equals("delete")
            reply = taskList.delete(input);
        }
        return reply;
    }

    /**
     * Action to be completed by any command.
     *
     * @param command The first String of the User input.
     * @param commandLen Number of arguments in the User input.
     * @param userInput String of the User input.
     * @return String of reply that will be in the Dialog Box.
     * @throws InvalidCommandException if Command inputted is not a valid command.
     * @throws AdditionalDescriptionException if there are too many arguments for a command.
     * @throws InvalidDescriptionException if there are too little arguments for a command.
     */
    public String commandAction(String command, int commandLen, String userInput)
            throws InvalidCommandException, AdditionalDescriptionException, InvalidDescriptionException {
        String reply = "";
        if (!isValidCommand(command)) {
            throw new InvalidCommandException();
        }

        if (isValidSingleCommand(command)) {
            if (commandLen > 1) {
                throw new AdditionalDescriptionException();
            }
            reply = singleCommAction(command);
        } else {
            if (commandLen < 2) {
                throw new InvalidDescriptionException();
            }
            reply = multiWordCommAction(command, userInput);
        }
        return reply;
    }

    /**
     * Method to run Michael.
     */
    public String run(String userInput) {
        String command = "";
        String reply = "";
        try {
            String[] commandArr = userInput.split(" ");
            int commandLen = commandArr.length;
            command = commandArr[0];
            reply = commandAction(command, commandLen, userInput);

        } catch (InvalidCommandException e) {
            System.out.println("'" + command + "' is an invalid command! Please try again!");
            reply = "'" + command + "' is an invalid command! Please try again!";
        } catch (AdditionalDescriptionException e) {
            System.out.println("There are too many arguments for the '" + command + "' command!");
            reply = "There are too many arguments for the '" + command + "' command!";
        } catch (InvalidDescriptionException e) {
            System.out.println("There cannot be an empty description of your task! Please try again! ");
            reply = "There cannot be an empty description of your task! Please try again! ";
        } catch (DateTimeParseException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
            reply = "An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.";
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("That is qan invalid task. Please try again!");
            reply = "That is an invalid task. Please try again!";
        } finally {
            System.out.println("__________________________________________");
        }
        return reply + "\n";
    }
}
