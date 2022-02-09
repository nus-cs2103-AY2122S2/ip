package duke;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the manner in which the program is parsed
 * and expected to behave when user inputs are entered.
 */
public class Parser {

    protected TaskList tasklist;
    public Parser(TaskList list) {
        tasklist = list;
    }

    /**
     * Processes the TaskList according to the input
     * entered by the user.
     *
     */
    public String processCommand(String cmd) throws DukeException {
        String[] c = cmd.split(" ");
        if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
            throw new DukeException(UI.gdes);
        } else if (cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete")) {
            throw new DukeException(UI.gnum);
        } else if (c[0].equals("list")) {
            return tasklist.printTaskList();
        } else if (c[0].equals("mark")) {
            int no = Integer.parseInt(c[1]);
            assert no > 0 : "number should be more than 0.";
            return tasklist.mark(no - 1);
        } else if (c[0].equals("unmark")) {
            int no = Integer.parseInt(c[1]);
            assert no > 0 : "number should be more than 0.";
            return tasklist.unMark(no - 1);
        } else if (c[0].equals("todo")) {
            return tasklist.add(new Todo(cmd.substring(4)));
        } else if (c[0].equals("deadline")) {
            String[] x = cmd.substring(8).split("/by ");
            assert x.length == 2 : "More or less than 2 elements detected";
            return tasklist.add(new Deadline(x[0], x[1]));
        } else if (c[0].equals("event")) {
            String[] x = cmd.substring(5).split("/at ");
            assert x.length == 2 : "More or less than 2 elements detected";
            return tasklist.add(new Event(x[0], x[1]));
        } else if (c[0].equals("delete")) {
            int no = Integer.parseInt(c[1]);
            assert no > 0 : "number should be more than 0.";
            return tasklist.delete(no - 1);
        } else if (c[0].equals("find")) {
            return tasklist.find(c[1]);
        } else {
            throw new DukeException(UI.invalid);
        }
    }
}
