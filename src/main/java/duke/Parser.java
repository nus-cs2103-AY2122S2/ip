package duke;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

/**
 * Represents the manner in which the program is parsed
 * and expected to behave when user inputs are entered.
 */
public class Parser {

    /**
     * TaskList to be processed.
     */
    protected TaskList tasklist;

    /**
     * Creates Parser to process User inputs.
     * @param list
     */
    public Parser(TaskList list) {
        this.tasklist = list;
    }

    /**
     * Processes the TaskList according to the input
     * entered by the user.
     *
     */
    public String processCommand(String command) throws DukeException {
        String[] commandLine = command.split(" ");
        String prefixCommand = commandLine[0];

        if (prefixCommand.equals("list")) {
            return tasklist.printTaskList();
        } else if (prefixCommand.equals("mark")) {
            int index = Integer.parseInt(commandLine[1]) - 1;
            assert index >= 0 : "index should be more than or equal to 0.";
            return tasklist.mark(index);
        } else if (prefixCommand.equals("unmark")) {
            int index = Integer.parseInt(commandLine[1]) - 1;
            assert index >= 0 : "index should be more than or equal to 0.";
            return tasklist.unMark(index);
        } else if (prefixCommand.equals("todo")) {
            return tasklist.add(new Todo(command.substring(4)));
        } else if (prefixCommand.equals("deadline")) {
            String[] x = command.substring(8).split("/by ");
            assert x.length == 2 : "More or less than 2 elements detected";
            return tasklist.add(new Deadline(x[0], x[1]));
        } else if (prefixCommand.equals("event")) {
            String[] x = command.substring(5).split("/at ");
            assert x.length == 2 : "More or less than 2 elements detected";
            return tasklist.add(new Event(x[0], x[1]));
        } else if (commandLine.equals("delete")) {
            int index = Integer.parseInt(commandLine[1]) - 1;
            assert index >= 0 : "index should be more than or equal to 0.";
            return tasklist.delete(index);
        } else if (prefixCommand.equals("find")) {
            return tasklist.find(commandLine[1]);
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            throw new DukeException(UI.ERROR_NO_DESCRIPTION);
        } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            throw new DukeException(UI.ERROR_NO_NUMBER);
        } else {
            throw new DukeException(UI.ERROR_INVALID);
        }
    }
}
