package duke;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

/**
 * Represents the parser that parses inputs entered by the user.
 */
public class Parser {

    /** TaskList to be processed by parser. */
    protected TaskList tasklist;

    /**
     * Instantiates parser to process user inputs.
     *
     * @param list Task list retrieved from data.
     */
    public Parser(TaskList list) {
        this.tasklist = list;
    }

    /**
     * Returns a response by Mike based on user input.
     *
     * @param command User command input.
     * @return Response to user from Mike.
     * @throws DukeException If input command is invalid.
     */
    public String processCommand(String command) throws DukeException {
        String[] commandLine = command.split(" ");
        String prefixCommand = commandLine[0];

        if (prefixCommand.equals("list")) {
            return parseList();
        } else if (prefixCommand.equals("mark")) {
            return parseMark(commandLine);
        } else if (prefixCommand.equals("unmark")) {
            return parseUnmark(commandLine);
        } else if (prefixCommand.equals("todo")) {
            return parseTodo(command);
        } else if (prefixCommand.equals("deadline")) {
            return parseDeadline(command);
        } else if (prefixCommand.equals("event")) {
            return parseEvent(command);
        } else if (prefixCommand.equals("delete")) {
            return parseDelete(commandLine);
        } else if (prefixCommand.equals("tag")) {
            return parseTag(commandLine);
        } else if (prefixCommand.equals("find")) {
            return parseFind(commandLine);
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            throw new DukeException(UI.ERROR_NO_DESCRIPTION);
        } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            throw new DukeException(UI.ERROR_NO_NUMBER);
        } else {
            throw new DukeException(UI.ERROR_INVALID);
        }
    }

    /**
     * Returns list containing all tasks on the task list.
     *
     * @return List of tasks.
     * @throws DukeException If list is empty.
     */
    private String parseList() throws DukeException {
        return tasklist.printTaskList();
    }

    /**
     * Returns message that task has been marked as per command line.
     *
     * @param commandLine User input with mark prefix command in array representation.
     * @return Message that task has been marked.
     */
    private String parseMark(String[] commandLine) {
        int index = Integer.parseInt(commandLine[1]) - 1;
        assert index >= 0 : UI.ASSERTION_INDEX_ISSUE;
        return tasklist.mark(index);
    }

    /**
     * Returns message that task has been unmarked as per command line.
     *
     * @param commandLine User input with unmark prefix command in array representation.
     * @return Message that task has been unmarked.
     */
    private String parseUnmark(String[] commandLine) {
        int index = Integer.parseInt(commandLine[1]) - 1;
        assert index >= 0 : UI.ASSERTION_INDEX_ISSUE;
        return tasklist.unMark(index);
    }

    /**
     * Returns message that todo task has been added to the list as per user command.
     *
     * @param command User input with todo prefix command.
     * @return Message that todo task has been added to task list.
     */
    private String parseTodo(String command) {
        return tasklist.add(new Todo(command.substring(4)));
    }

    /**
     * Returns message that deadline task has been added to the list as per user command.
     *
     * @param command User input with deadline prefix command.
     * @return Message that deadline task has been added to task list.
     */
    private String parseDeadline(String command) {
        String[] commandSentence = command.substring(8).split("/by ");
        assert commandSentence.length == 2 : UI.ASSERTION_INVALID_COMMAND_FORMAT;
        return tasklist.add(new Deadline(commandSentence[0], commandSentence[1]));
    }

    /**
     * Returns message that event task has been added to the list as per user command.
     *
     * @param command User input with event prefix command.
     * @return Message that event task has been added to task list.
     */
    private String parseEvent(String command) {
        String[] commandSentence = command.substring(5).split("/at ");
        assert commandSentence.length == 2 : UI.ASSERTION_INVALID_COMMAND_FORMAT;
        return tasklist.add(new Event(commandSentence[0], commandSentence[1]));
    }

    /**
     * Returns message that task has been deleted from the task list as per user command.
     *
     * @param commandLine User input with delete prefix command in array representation.
     * @return Message that task has been deleted from task list.
     */
    private String parseDelete(String[] commandLine) {
        int index = Integer.parseInt(commandLine[1]) - 1;
        assert index >= 0 : UI.ASSERTION_INDEX_ISSUE;
        return tasklist.delete(index);
    }

    /**
     * Returns message that task has been tagged as per user command.
     *
     * @param commandLine User input with tag prefix in array representation.
     * @return Message that task has been tagged.
     */
    private String parseTag(String[] commandLine) {
        int index = Integer.parseInt(commandLine[1]) - 1;
        assert index >= 0 : UI.ASSERTION_INDEX_ISSUE;
        return tasklist.tag(index, commandLine[2]);
    }

    /**
     * Returns message of the search result.
     *
     * @param commandLine User input with find prefix command in array representation.
     * @return Message of list of tasks found.
     * @throws DukeException If no task is found.
     */
    private String parseFind(String[] commandLine) throws DukeException {
        return tasklist.find(commandLine[1]);
    }

}
