package duke.ui;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.io.IOException;
import java.util.Arrays;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles input in main in Duke.java. Receives String from Scanner in main
 * Processes the input into 7 categories: Todo, Event, Deadline, list, mark, unmark, bye and throws error
 */
public class InputHandler {
    private Storage storage;

    /**
     * Constructs an InputHandler for Duke.java to handle inputs from user
     * @throws IOException If Storage class fails to initialise
     */
    public InputHandler() throws IOException {
        this.storage = new Storage();
    }


    /**
     * Handles input from Duke.java
     * @param input String input from main in Duke.java, from user input
     * @return boolean representing isChatEnded variable in main. If "bye" command is given, a true boolean is returned, else false is returned
     * @throws DukeException Invalid input types, or unrecognisable commands
     */
    public boolean handleInput(String input) throws DukeException, IOException {
        String[] splitInput = input.split(" ");
        String inputCommand = splitInput[0];
        Parser parser = new Parser();
        switch (inputCommand) {
        case "todo":
            //Confirms that input is in the format: todo [task]
            if (splitInput.length > 1) {
                Todo newTodo = (Todo) parser.parse(CommandType.TODO, splitInput);
                this.storage.writeData(newTodo);
                printAddTaskMessage(newTodo);
                return false;
            } else {
                throw new DukeException(":( OOPS!!! The description of a todo cannot be empty. Correct usage: todo [task]");
            }
        case "event":
            //Confirms that input is in the format: event [task] /at [date] [time(optional)]
            if (splitInput.length > 3) {
                Event newEvent = (Event) parser.parse(CommandType.EVENT, splitInput);
                this.storage.writeData(newEvent);
                printAddTaskMessage(newEvent);
                return false;

            } else {
                throw new DukeException(":( OOPS!!! The description of a event cannot be empty. Correct usage: event [task] /at [time]");
            }
        case "deadline":
            //Confirms that input is in the format: deadline [task] /by [date] [time(optional)]
            if (splitInput.length > 3) {
                Deadline newDeadline = (Deadline) parser.parse(CommandType.DEADLINE, splitInput);
                this.storage.writeData(newDeadline);
                printAddTaskMessage(newDeadline);
                return false;
            } else {
                throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty. Correct usage: deadline [task] /by [time]");
            }

        case "list":
            //Confirms that input command is simply "list"
            if (splitInput.length == 1) {
                parser.parse(CommandType.LIST, this.storage, splitInput);
                return false;
            } else {
                throw new DukeException("Wrong usage of list! Correct usage: list");
            }

        case "mark":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {

                parser.parse(CommandType.MARK, this.storage, splitInput);
                return false;

            } else {
                throw new DukeException("Wrong usage of mark! Correct usage: mark [index]");
            }

        case "unmark":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {
                parser.parse(CommandType.UNMARK, this.storage, splitInput);
                return false;
            } else {
                throw new DukeException("Wrong usage of unmark! Correct usage: unmark [index]");
            }

        case "delete":
            //Confirms that input is in the format mark [index]
            if (splitInput.length == 2) {
                parser.parse(CommandType.DELETE, this.storage, splitInput);
                return false;
            } else {
                throw new DukeException("Wrong usage of delete! Correct usage: delete [index]");
            }
        case "bye":
            return true;
        default:
            throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means! Possible commands: todo [task], event [task] /at [time],"
            + " deadline [task] /by [time], mark [index], unmark [index], delete [index], bye");
        }

    }

    /**
     * Prints out the task name that has been added as well as the number of tasks in the list
     * @param task The task that has been added
     */
    public void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + this.storage.taskListSize() + " tasks in the list." );
    }

    /**
     * Type of Command enum to be passed into Parser object
     */
    enum CommandType {
        TODO,
        EVENT,
        DEADLINE,
        LIST,
        MARK,
        UNMARK,
        DELETE
    }
}

