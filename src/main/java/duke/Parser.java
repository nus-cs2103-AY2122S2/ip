package duke;

import java.io.IOException;
import java.util.Arrays;

/**
 * Handles the raw input from user to create the corresponding objects
 */
public class Parser {

    private final String ADD_SUCCESS = "Got it! I've added:\n\t";
    private final String MARK_SUCCESS = "Congrats on completing:\n\t";
    private final String UNMARK_SUCCESS = "Congrats on not completing:\n\t";
    private final String DELETE_SUCCESS = "Removed this task:\n\t";


    /**
     * Gets the description from the raw input given by user
     *
     * @param inputArr raw input with command word, description and date time
     * @return String of description of input only
     */
    private final String getDescription(String[] inputArr) {
        return inputArr[1].split("/")[0]; // split input from slash
    }

    /**
     * Gets date and time from the raw input given by the user
     *
     * @param inputArr raw input with command word, description and date time
     * @return DateTime of input only
     */
    private final DateTime getDateTime(String[] inputArr) {
        String[] dateTimeArr = inputArr[1].split("/")[1].split("[- ]"); // [String, yyyy, mm, dd, HHMM]
        return new DateTime(Arrays.copyOfRange(dateTimeArr, 1, dateTimeArr.length));
        // will reduce dateTimeArr to [yyyy, mm, dd, HHMM]
    }

    /**
     * Adds given task into tasklist
     *
     * @param task Task to be added
     * @param tasklist Tasklist to be store the new task
     */
    private void addToTaskList(Task task, TaskList tasklist) {
        tasklist.addTask(task);
    }

    /**
     * Makes sense of the input given by the user
     *
     * @param input Raw input given by user
     * @param tasklist Tasklist where tasks are to be updated
     * @param storage Holds the backend storage of the file
     * @return String of the result of the task executed
     * @throws DukeException Incorrect use/format of Duke commands
     * @throws IOException Missing file/null error
     */
    public String parse(String input, TaskList tasklist, Storage storage) throws DukeException, IOException {
        String[] inputArr = input.trim().split(" ", 2); // split first word from body
        switch(inputArr[0]) {
        case "todo":
            if (inputArr.length == 1) {
                throw new DukeException("todo simi?");
            }
            ToDos newToDo = new ToDos(getDescription(inputArr));
            addToTaskList(newToDo, tasklist);
            return ADD_SUCCESS + newToDo;

        case "event":
            if (inputArr.length == 1) {
                throw new DukeException("event description?");
            }
            Events newEvent = new Events(getDescription(inputArr), getDateTime(inputArr));
            addToTaskList(newEvent, tasklist);
            return ADD_SUCCESS + newEvent;

        case "deadline":
            if (inputArr.length == 1) {
                throw new DukeException("deadline when end?");
            }
            Deadlines newDeadline = new Deadlines(getDescription(inputArr), getDateTime(inputArr));
            addToTaskList(newDeadline, tasklist);
            return ADD_SUCCESS + newDeadline;

        case "list":
            return tasklist.list();

        case "bye":
            storage.saveAllTasks(tasklist);
            return "Sayonara~";

        case "mark":
            Task markedTask = tasklist.mark(Integer.parseInt(inputArr[1]) - 1);
            return MARK_SUCCESS + markedTask;

        case "unmark":
            Task unmarkedTask = tasklist.unmark(Integer.parseInt(inputArr[1]) - 1);
            return UNMARK_SUCCESS + unmarkedTask;

        case "delete":
            Task deletedTask = tasklist.delete(Integer.parseInt(inputArr[1]) - 1);
            return DELETE_SUCCESS + deletedTask;
        default:
            throw new DukeException(":( OOPS!!!! I'm sorry, but I don't know what that means!");
        }


    }
}
