package duke.handler;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;

/**
 * Enum class used to handle the different commands used in the application.
 */
public enum Handlers {
    Bye("bye"),
    Deadline("deadline"),
    Delete("delete"),
    Event("event"),
    Find("find"),
    Mark("mark"),
    List("list"),
    Todo("todo"),
    Unmark("unmark");

    // Only date is provided in input array
    private static final int DATE = 3;
    // Both date and time is provided in input array
    private static final int DATEANDTIME = 4;

    public final String label;

    /**
     * Enum constructor to reference the exact commands used.
     *
     * @param label Exact commands used in the application.
     */
    Handlers(String label) {
        this.label = label;
    }

    /**
     * Handles and distributes all commands to the respective handlers.
     *
     * @param list Tasklist that contains all tasks.
     * @param input Text that the user has entered.
     */
    public static String commandHandler(Tasklist list, String input) {
        String[] inputArr = input.split(" ");
        String cmd = inputArr[0];
        try {
            if (cmd.equals(Bye.label)) {
                return Handlers.byeHandler(list);
            } else if (cmd.equals(List.label)) {
                return Handlers.listHandler(list);
            } else if (cmd.equals(Deadline.label)) {
                return Handlers.deadlineHandler(list, input, cmd);
            } else if (cmd.equals(Event.label)) {
                return Handlers.eventHandler(list, input, cmd);
            } else if (cmd.equals(Todo.label)) {
                return Handlers.todoHandler(list, input);
            } else if (cmd.equals(Mark.label)) {
                return Handlers.markHandler(list, input);
            } else if (cmd.equals(Unmark.label)) {
                return Handlers.unmarkHandler(list, input);
            } else if (cmd.equals(Find.label)) {
                return Handlers.findHandler(list, input);
            } else if (cmd.equals(Delete.label)) {
                return Handlers.deleteHandler(list, input);
            } else {
                throw new IllegalArgumentException("Sorry, this is not a recognized command.\n");
            }
        } catch (IllegalArgumentException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'bye'. Ends the program.
     *
     * @param list Tasklist that contains all tasks.
     */
    public static String byeHandler(Tasklist list) {
        FileHandler.writeToFile(list);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the command 'deadline'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     * @param cmd String representing the command being used.
     */
    public static String deadlineHandler(Tasklist list, String input, String cmd) {
        try {
            int index = input.indexOf("/by");
            String[] time = DukeException.isTaskValid(index, input, cmd, list);
            Deadline task;
            LocalDate date = LocalDate.of(Integer.parseInt(time[2]), Integer.parseInt(time[1]),
                    Integer.parseInt(time[0]));
            if (time.length == DATE) {
                task = new Deadline(false, input.substring(9, index - 1), date, "");
            } else if (time.length == DATEANDTIME) {
                task = new Deadline(false, input.substring(9, index - 1), date, time[3]);
            } else {
                throw new DukeException("Time provided in incorrect format.\n");
            }
            list.addTask(task);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder("Deadline Added: " + task.toString() + "\n");
            str.append("There are now ").append(list.getTotalTasks()).append(" tasks in the list.\n");
            return str.toString();
        } catch (DukeException | DateTimeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'delete'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static String deleteHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            Task t = list.delete(index);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder(t.toString() + "\n");
            str.append("There are now ").append(list.getTotalTasks()).append(" tasks in the list.\n");
            return str.toString();
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'event'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     * @param cmd String representing the command being used.
     */
    public static String eventHandler(Tasklist list, String input, String cmd) {
        try {
            int index = input.indexOf("/at");
            String[] time = DukeException.isTaskValid(index, input, cmd, list);
            Event task;
            LocalDate date = LocalDate.of(Integer.parseInt(time[2]), Integer.parseInt(time[1]),
                    Integer.parseInt(time[0]));
            if (time.length == DATE) {
                task = new Event(false, input.substring(6, index - 1), date, "");
            } else if (time.length == DATEANDTIME) {
                task = new Event(false, input.substring(6, index - 1), date, time[3]);
            } else {
                throw new DukeException("Time provided in incorrect format.");
            }
            list.addTask(task);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder("Event Added: " + task.toString() + "\n");
            str.append("There are now ").append(list.getTotalTasks()).append(" tasks in the list.\n");
            return str.toString();
        } catch (DukeException | DateTimeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'find'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static String findHandler(Tasklist list, String input) {
        try {
            String searchPhrase = DukeException.isWordValid(input);
            LinkedList<Task> filtered = new LinkedList<>();
            for (int i = 0; i < list.getTotalTasks(); i++) {
                if (list.getTask(i).getTaskName().contains(searchPhrase)) {
                    filtered.add(list.getTask(i));
                }
            }
            StringBuilder str = new StringBuilder();
            if (filtered.size() == 0) {
                str.append("Sorry, but we could not find any tasks containing the search term.\n");
                return str.toString();
            } else {
                str.append("Here are the matching tasks!\n").append(Tasklist.taskFormatter(filtered));
                return str.toString();
            }
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'mark'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static String markHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            Task t = list.mark(index);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder("Nice! I've marked this task as done!" + "\n");
            str.append(t.toString()).append("\n");
            return str.toString();
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'list'.
     *
     * @param list Tasklist that contains all tasks.
     */
    public static String listHandler(Tasklist list) {
        return list.toString();
    }

    /**
     * Handles the command 'todo'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static String todoHandler(Tasklist list, String input) {
        try {
            DukeException.isTaskValid(input, list);
            Todo task = new Todo(false, input.substring(5));
            list.addTask(task);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder("Todo Added: " + task.toString() + "\n");
            str.append("There are now ").append(list.getTotalTasks()).append(" tasks in the list.\n");
            return str.toString();
        } catch (DukeException err) {
            return err.getMessage();
        }
    }

    /**
     * Handles the command 'unmark'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static String unmarkHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            Task t = list.unmark(index);
            FileHandler.writeToFile(list);
            StringBuilder str = new StringBuilder("Okay! I've marked this as undone!" + "\n");
            str.append(t.toString()).append("\n");
            return str.toString();
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}
