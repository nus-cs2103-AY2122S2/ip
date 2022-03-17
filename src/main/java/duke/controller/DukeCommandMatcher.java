package duke.controller;

import duke.command.DescriptionCommand;
import duke.command.DurationCommand;
import duke.command.TypeCommand;
import duke.command.TimeCommand;
import duke.exception.InvalidCommandException;
import duke.exception.DukeException;
import duke.exception.NullCommandException;
import duke.task.*;
import duke.ui.Ui;
import duke.utility.Storage;
import duke.utils.Constants;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;


/**
 * The class to match the command to Duke's response.
 */
public class DukeCommandMatcher {


    public static final String DURATION_SYMBOL = " ~ ";
    public TaskList taskList;
    public static final DateTimeFormatter TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("MMM d yyyy")
                    .optionalStart().appendPattern(" HH:mm")
                    .optionalEnd().toFormatter();

    /**
     * constructor for DukeCommandMatcher
     * @param db
     */
    public DukeCommandMatcher(Storage db) {
        this.taskList = TaskList.getCurrentList(db);
    }

    /**
     *
     * @param command user input
     * @return string for the task type
     * @throws DukeException when command is invalid
     */
    public String commandSwitcher(String command) throws DukeException {
        if (Objects.equals(command, "")) {
            throw new NullCommandException(command);
        }
        CommandParser parser = new CommandParser(command);
        TypeCommand result = parser.parse();
        switch (result.getType()) {
        case EXIT:
            return handleExit();
        case TODO:
            return handleTodo(result);
        case DEADLINE:
            return handleDeadline(result);
        case LIST:
            return handleList();
        case DONE:
            return handleDone(result);
        case EVENT:
            return handleEvent(result);
        case DELETE:
            return handleDelete(result);
        case FIND:
            return handleFind(result);
        case HELP:
            return handleHelp();
        default:
            break;
        }
        throw new InvalidCommandException(command);
    }

    /**
     * @return string for list of tasks
     * @throws DukeException
     */
    public String handleList() throws DukeException {
        return taskList.ListTasks();
    }

    /**
     * to handle the tasks to be done
     * @param command user input command
     * @return string of tasks that are done or not done yet
     * @throws DukeException
     */
    public String handleDone(TypeCommand command) throws DukeException {
        String idx = ((DescriptionCommand) command).getDescription();
        int targetTaskPos = Integer.parseInt(idx) - 1;
        return taskList.markTask(targetTaskPos);
    }

    /**
     * Return the string from the task added in the task list
     * @param task
     * @return string of the task
     */
    public String handleAdd(Task task) {
        return taskList.addTasks(task);
    }

    public String handleTodo(TypeCommand command) {
        String description = ((DescriptionCommand) command).getDescription();
        Todo todo = new Todo(description);
        assert (todo.getTaskType() == "T");
        return handleAdd(todo);
    }

    /**
     * Return the string of the deadline of the task added
     * @param command
     * @return string of the deadline
     */
    public String handleDeadline(TypeCommand command) {
        String description = ((DescriptionCommand) command).getDescription();
        String time = TIME_FORMATTER.format(((TimeCommand) command).getTime());
        Deadline deadline = new Deadline(description, time);
        assert (deadline.getTaskType() == "D");
        return handleAdd(deadline);
    }

    public String handleEvent(TypeCommand command) {
        String description = ((DurationCommand) command).getDescription();
        String startTime = TIME_FORMATTER.format(((DurationCommand) command).getStartTime());
        String endTime = TIME_FORMATTER.format(((DurationCommand) command).getEndTime());
        Event event = new Event(description, startTime + DURATION_SYMBOL + endTime);
        assert (event.getTaskType() == "E");
        return handleAdd(event);
    }

    public String handleDelete(TypeCommand command) throws DukeException {
        String index = ((DescriptionCommand) command).getDescription();
        int deleting = Integer.parseInt(index) - 1;
        assert (deleting > 0);
        return taskList.deleteTasks(deleting);
    }

    public String handleFind(TypeCommand command) throws DukeException {
        String key = ((DescriptionCommand) command).getDescription();
        return taskList.query(key);
    }

    public String handleHelp() {
        return Ui.printListCommands();
    }

    public String handleExit() {
        Ui.printBye();
        return Constants.EXIT;
    }

}