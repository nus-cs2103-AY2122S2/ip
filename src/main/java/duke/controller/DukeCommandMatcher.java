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

    public DukeCommandMatcher(Storage db) {
        this.taskList = TaskList.getCurrentList(db);
    }

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

    private String handleList() throws DukeException {
        return taskList.ListTasks();
    }

    private String handleDone(TypeCommand command) throws DukeException {
        String idxStr = ((DescriptionCommand) command).getDescription();
        int targetTaskPos = Integer.parseInt(idxStr) - 1;
        return taskList.markTask(targetTaskPos);
    }

    private String handleExit() {
        Ui.printBye();
        return Constants.EXIT;
    }

    private String handleAdd(Task task) {
        return taskList.addTasks(task);
    }

    private String handleTodo(TypeCommand command) {
        String content = ((DescriptionCommand) command).getDescription();
        Todo todo = new Todo(content);
        return handleAdd(todo);
    }

    private String handleDeadline(TypeCommand command) {
        String content = ((DescriptionCommand) command).getDescription();
        String time = TIME_FORMATTER.format(((TimeCommand) command).getTime());
        Deadline deadline = new Deadline(content, time);
        System.out.println("deadline time: " + deadline.getTask());
        return handleAdd(deadline);
    }

    private String handleEvent(TypeCommand command) {
        String content = ((DurationCommand) command).getDescription();
        String startTime = TIME_FORMATTER.format(((DurationCommand) command).getStartTime());
        String endTime = TIME_FORMATTER.format(((DurationCommand) command).getEndTime());
        Event event = new Event(content, startTime + DURATION_SYMBOL + endTime);
        return handleAdd(event);
    }

    private String handleDelete(TypeCommand command) throws DukeException {
        String index = ((DescriptionCommand) command).getDescription();
        int taskDeleting = Integer.parseInt(index) - 1;
        return taskList.deleteTasks(taskDeleting);
    }

    private String handleFind(TypeCommand command) throws DukeException {
        String key = ((DescriptionCommand) command).getDescription();
        return taskList.query(key);
    }

    private String handleHelp() {
        return Ui.printListCommands();
    }

}