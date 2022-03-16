package arthur;

import static arthur.commons.Messages.BYE_REPLY_TEMPLATE;
import static arthur.commons.Messages.INCORRECT_COMMAND_MESSAGE;
import static arthur.commons.Messages.INVALID_COMMAND_ERROR_MESSAGE;

import java.io.FileNotFoundException;
import java.io.IOException;

import arthur.exceptions.InvalidInstructionException;
import arthur.task.Task;
import arthur.timings.DateTime;

/**
 * Handles decoding of the user commands.
 */
public class Parser {
    private static final int MARKER_IDENTIFIER_FOR_STORAGE = 1;
    private static final int DELETE_IDENTIFIER_FOR_STORAGE = 2;
    private final String userInput;

    /**
     * Constructor for the parser object.
     *
     * @param inst String instruction to be followed.
     */
    public Parser(String inst) {
        this.userInput = inst;
    }

    /**
     * Uses the command string to determine appropriate action.
     *
     * @param taskList The Tasklist of tasks and operations.
     * @param storage  The object to access data file in storage.
     * @return A string output of the result of execution.
     */
    public String execute(TaskList taskList, Storage storage) throws InvalidInstructionException,
            IndexOutOfBoundsException, FileNotFoundException, IOException {
        // Helps to isolate the first word
        String[] inst = this.userInput.split(" ", 2);
        assert inst.length >= 1 : INVALID_COMMAND_ERROR_MESSAGE;
        String command = inst[0];
        // Task description in inst[1], if any
        int newTaskIndex = taskList.tasksSize();
        String result;
        switch (command) {
        case "list":
            result = taskList.listOut();
            break;
        case "mark":
        case "unmark":
            result = markerHandler(taskList, storage, inst[1]);
            break;
        case "reminder":
            result = DateTime.checkDate(taskList);
            break;
        case "todo":
            result = todoHandler(taskList, storage, inst[1], newTaskIndex);
            break;
        case "deadline":
            result = deadlineHandler(taskList, storage, inst[1], newTaskIndex);
            break;
        case "event":
            result = eventHandler(taskList, storage, inst[1], newTaskIndex);
            break;
        case "delete":
            result = deleteHandler(taskList, storage, inst[1]);
            break;
        case "find":
            result = taskList.find(inst[1]);
            break;
        case "bye":
            result = BYE_REPLY_TEMPLATE;
            break;
        default:
            throw new InvalidInstructionException(INCORRECT_COMMAND_MESSAGE);
        }
        return result;
    }

    private String markerHandler(TaskList taskList, Storage storage, String desc) throws IOException {
        String result;
        int taskIndex = Integer.parseInt(desc) - 1;
        Task task = taskList.getTask(taskIndex);
        result = taskList.marker(this.userInput);
        storage.editTasks(task, MARKER_IDENTIFIER_FOR_STORAGE);
        return result;
    }

    private String todoHandler(TaskList taskList, Storage storage, String desc, int lastTaskIndex) {
        String result;
        result = taskList.todo(desc);
        storage.addTasks(taskList.getTask(lastTaskIndex));
        return result;
    }

    private String deadlineHandler(TaskList taskList, Storage storage, String desc, int lastTaskIndex) {
        String result;
        result = taskList.deadline(desc);
        storage.addTasks(taskList.getTask(lastTaskIndex));
        return result;
    }

    private String eventHandler(TaskList taskList, Storage storage, String desc, int lastTaskIndex) {
        String result;
        result = taskList.event(desc);
        storage.addTasks(taskList.getTask(lastTaskIndex));
        return result;
    }

    private String deleteHandler(TaskList taskList, Storage storage, String desc) throws IOException {
        String result;
        int givenIndex = Integer.parseInt(desc);
        int taskIndex = givenIndex - 1;
        storage.editTasks(taskList.getTask(taskIndex), DELETE_IDENTIFIER_FOR_STORAGE);
        result = taskList.deleter(givenIndex);
        return result;
    }
}
