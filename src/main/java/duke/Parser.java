package duke;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the manner in which the program is parsed
 * and expected to behave when user inputs are entered.
 */
public class Parser {

    TaskList tasklist;

    public Parser(TaskList list) {
        tasklist = list;
    }

    /**
     * Processes the TaskList according to the input
     * entered by the user.
     *
     * @throws IOException If unexpected input is entered.
     */
    public String processData(String cmd) {
        String[] c = cmd.split(" ");
        if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
            return new DukeException(UI.gdes).getMessage();
        } else if (cmd.equals("mark") || cmd.equals("unmark") || cmd.equals("delete")) {
            return new DukeException(UI.gnum).getMessage();
        } else if (c[0].equals("list")) {
            return tasklist.printTaskList();
        } else if (c[0].equals("mark")) {
            int no = Integer.parseInt(c[1]) - 1;
            return tasklist.mark(no);
        } else if (c[0].equals("unmark")) {
            int no = Integer.parseInt(c[1]) - 1;
            return tasklist.unMark(no);
        } else if (c[0].equals("todo")) {
            return tasklist.add(new Todo(cmd.substring(4)));
        } else if (c[0].equals("deadline")) {
            String[] x = cmd.substring(8).split("/by ");
            return tasklist.add(new Deadline(x[0], formatDate(x[1])));
        } else if (c[0].equals("event")) {
            String[] x = cmd.substring(5).split("/at ");
            return tasklist.add(new Event(x[0], x[1]));
        } else if (c[0].equals("delete")) {
            int no = Integer.parseInt(c[1]) - 1;
            return tasklist.delete(no);
        } else if (c[0].equals("find")) {
            return tasklist.find(c[1]);
        } else {
            return new DukeException(UI.invalid).getMessage();
        }
    }

    /**
     * Returns the formatted Date to display from the deadline input.
     *
     * @param input the input Date as per "yyyy-MM-dd HH:mm"
     * @return the formatted Date as per "MMM-dd-yyyy HH:mm a"
     */
    private String formatDate(String input) {
        DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
        return LocalDateTime.parse(input, formatIn).format(formatOut);
    }
}
