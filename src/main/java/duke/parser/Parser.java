package duke.parser;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.exceptions.InvalidTypeException;
import duke.exceptions.MissingNameException;
import duke.exceptions.MissingEventDateException;
import duke.exceptions.MissingDeadlineDateException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {

    private String userInput;

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";
    private static final String FIND = "find";

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Processes user commands.
     *
     * @return a String of output message
     */
    public String processInput() {
        switch (userInput.split(" ")[0]) {
            case BYE:
                return new ByeCommand().toString();
            case LIST:
                return new ListCommand().execute();
            case MARK:
                return new MarkCommand(userInput).execute();
            case UNMARK:
                return new UnmarkCommand(userInput).execute();
            case DELETE:
                return new DeleteCommand(userInput).execute();
            case FIND:
                return new FindCommand(userInput).execute();
            default:
                String taskCreationStatusMessage = handleTaskCreation(userInput);
                return taskCreationStatusMessage;
        }
    }

    /**
     * Handles the task creation process.
     * @param userInput
     * @return a String containing the task creation status message
     */
    public String handleTaskCreation(String userInput) {
        try {
            String newTaskMessage = Parser.createNewTask(userInput);
            System.out.println(newTaskMessage);
            return newTaskMessage;
        } catch (InvalidTypeException e) {
            String errorMsg = InvalidTypeException.printErrorMessage(userInput);
            System.out.println(errorMsg);
            return errorMsg;
        } catch (MissingNameException e) {
            String errorMsg = MissingNameException.printErrorMessage(userInput);
            System.out.println(errorMsg);
            return errorMsg;
        } catch (MissingEventDateException e) {
            String errorMsg = MissingEventDateException.printErrorMessage(userInput);
            System.out.println(errorMsg);
            return errorMsg;
        } catch (MissingDeadlineDateException e ) {
            String errorMsg = MissingDeadlineDateException.printErrorMessage(userInput);
            System.out.println(errorMsg);
            return errorMsg;
        }
    }

    /**
     * Creates a task based on the input of the users.
     *
     * @param input a String command
     * @return a String respond based on the command input
     * @throws InvalidTypeException if the command does not start with a valid type
     * @throws MissingNameException if the name of the item is missing
     * @throws MissingEventDateException if the date for events type is missing or in wrong format
     * @throws MissingDeadlineDateException if the date for deadline type is missing or in wrong format
     */
    public static String createNewTask(String input) throws InvalidTypeException, MissingNameException, MissingEventDateException, MissingDeadlineDateException{
        String[] splitString = input.split("/", 2);
        String[] instruction = splitString[0].split(" ", 2);
        Task currentTask = null;

        if(Task.isInvalidWithMissingName(input)) {
            throw new MissingNameException("Missing description");
        }

        switch(instruction[0]) {
            case TODO:
                currentTask = new ToDo(input.substring(4));
                break;
            case EVENT:
                if(Event.isInvalidWithMissingEventDate(input)) {
                    throw new MissingEventDateException("Missing date");
                }
                try {
                    currentTask = new Event(splitString[0].substring(5),
                            DateTimeParser.parseDate(splitString[1].substring(3)));
                } catch (DateTimeParseException e) {
                    throw new MissingEventDateException("Wrong format of date");
                }
                break;
            case DEADLINE:
                if(Deadline.isInvalidWithMissingEventDate(input)) {
                    throw new MissingDeadlineDateException("Missing date");
                }
                try {
                    currentTask = new Deadline(splitString[0].substring(8),
                            DateTimeParser.parseDate(splitString[1].substring(3)));
                } catch (DateTimeParseException e) {
                    throw new MissingEventDateException("Wrong format of date");
                }
                break;
            default:
                throw new InvalidTypeException("Invalid type");
        }

        updateFile();

        String output = Ui.createLine()
                + "       Got it! I have added this following task :D \n"
                + "       " + currentTask + "\n"
                + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                + Ui.createLine();

        return output;
    }


    public static void updateFile() {
        Storage storage = new Storage("data/duke.txt");
        storage.writeToPath(Task.printTaskList());
    }

}
