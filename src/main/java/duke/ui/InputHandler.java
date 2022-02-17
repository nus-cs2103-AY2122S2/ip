package duke.ui;

import duke.storage.Storage;

import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;

import java.io.IOException;

/**
 * Handles input from user
 * Processes the input into 7 categories: Todo, Event, Deadline, list, mark, unmark, bye
 */
public class InputHandler {
    private Storage storage;
    private Parser parser;

    //Error messages
    final String unableToSnoozeErrorMessage = "Wrong usage of snooze! Correct usage: snooze [name]"
            + " [time](required if deadline/event";
    final String unableToFindErrorMessage = "Uh oh! It seems like you did not specify what to find";
    final String unableToDeleteErrorMessage = "Wrong usage of delete! Correct usage: delete [index]";
    final String unableToUnmarkErrorMessage = "Wrong usage of unmark! Correct usage: unmark [index]";
    final String unableToMarkErrorMessage = "Wrong usage of mark! Correct usage: mark [index]";
    final String unableToListErrorMessage = "Wrong usage of list! Correct usage: list";

    /**
     * Constructs an InputHandler and loads data into Storage object
     *
     * @throws IOException If Storage class fails to initialise
     */
    public InputHandler() throws IOException {
        this.storage = new Storage();
        this.parser = new Parser();
    }


    /**
     * Handles input from Duke.java. Breaks up the String input into proper parts and parses them.
     *
     * @param input String input from user input.
     * @return String output from Duke as response to user.
     * @throws DukeException For invalid input types, or unrecognisable commands.
     */
    public String handleInput(String input) throws DukeException, IOException {
        String endMessage = "Bye. Hope to see you again soon!";

        String[] splitInput = input.split(" ");
        String inputCommand = splitInput[0];

        switch (inputCommand) {
        case "todo":
            return taskCaseHandler(CommandType.TODO, splitInput );
        case "event":
            return taskCaseHandler(CommandType.EVENT, splitInput);
        case "deadline":
            return taskCaseHandler(CommandType.DEADLINE, splitInput);
        case "list":
            //Confirms that input command is simply "list"
            if (splitInput.length == 1) {
                return parser.parse(CommandType.LIST, this.storage, splitInput);
            } else {
                throw new DukeException(unableToListErrorMessage);
            }
        case "mark":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {
                return this.parser.parse(CommandType.MARK, this.storage, splitInput);
            } else {
                throw new DukeException(unableToMarkErrorMessage);
            }
        case "unmark":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {
                return this.parser.parse(CommandType.UNMARK, this.storage, splitInput);
            } else {
                throw new DukeException(unableToUnmarkErrorMessage);
            }
        case "delete":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {
                return this.parser.parse(CommandType.DELETE, this.storage, splitInput);
            } else {
                throw new DukeException(unableToDeleteErrorMessage);
            }
        case "find":
            if (splitInput.length > 1) {
                return this.parser.parse(CommandType.FIND, this.storage, splitInput);
            } else {
                throw new DukeException(unableToFindErrorMessage);
            }
        case "snooze":
            String splitInputBySlash[] = input.split(" /t ");
            if (splitInputBySlash.length > 1 && splitInput.length > 5) {
                return parser.parse(CommandType.SNOOZE, this.storage, splitInputBySlash);
            } else {
                throw new DukeException(unableToSnoozeErrorMessage);
            }
        case "bye":
            return endMessage;
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: " +
                    "todo [task], event [task] /at [time]," +
                    " deadline [task] /by [time], mark [index], unmark [index], delete [index], bye");
        }

    }

    /**
     * Handles Todo, Event and Deadline CommandTypes. Writes the task to storage and returns Duke's reply.
     *
     * @param typeOfTask Type of Task, Todo, Event or Deadline.
     * @param splitInput String array of user input, split by spaces.
     * @return String format of Duke's reply.
     * @throws DukeException If CommandType passed in is wrong.
     * @throws IOException If there is an issue writing to Storage.
     */
    public String taskCaseHandler(CommandType typeOfTask, String[] splitInput) throws DukeException, IOException {
        String emptyDescription = ":( OOPS!!! The description of a todo cannot be empty. Correct usage: ";
        String todoFormat = "todo [name]";
        String eventFormat = "event [name] /at [date] [time(optional)]";
        String deadlineFormat = "deadline [name] /by /by [date] [time(optional)]";
        switch(typeOfTask) {
        case TODO:
            //Confirms that input is in the format: todo [task]
            if (splitInput.length > 1) {
                Todo newTodo = (Todo) parser.parse(CommandType.TODO, splitInput);
                this.storage.writeData(newTodo);
                return addTaskMessage(newTodo);
            } else {
                throw new DukeException(emptyDescription + todoFormat);
            }
        case DEADLINE:
            //Confirms that input is in the format: deadline [task] /by [date] [time(optional)]
            if (splitInput.length > 3) {
                Deadline newDeadline = (Deadline) parser.parse(CommandType.DEADLINE, splitInput);
                this.storage.writeData(newDeadline);
                return addTaskMessage(newDeadline);
            } else {
                throw new DukeException(emptyDescription + deadlineFormat);
            }
        case EVENT:
            //Confirms that input is in the format: event [task] /at [date] [time(optional)]
            if (splitInput.length > 3) {
                Event newEvent = (Event) parser.parse(CommandType.EVENT, splitInput);
                this.storage.writeData(newEvent);
                return addTaskMessage(newEvent);

            } else {
                throw new DukeException(emptyDescription + eventFormat);
            }
        default:
            throw new DukeException("Incorrect format. Should never reach this stage");
        }
    }

    /**
     * Prints out the task name that has been added as well as the number of tasks in the list.
     *
     * @param task The task that has been added
     */
    public String addTaskMessage(Task task) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + this.storage.taskListSize() +
                " tasks in the list." ;
    }

    /**
     * Types of commands accepted by Duke
     */
    enum CommandType {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        SNOOZE
    }
}

