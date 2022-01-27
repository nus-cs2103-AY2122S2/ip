package user;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    public int handleMarkTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(5);  // "mark " is 5 letters
        int taskToMark;
        try {
            taskToMark = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToMark && taskToMark <= numTasks) {
            return taskToMark - 1;
        } else {
            throw new DukeException(String.format("Task %d does not exist!", taskToMark));
        }
    }

    public int handleUnmarkTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(7);  // "unmark " is 7 letters
        int taskToUnmark;
        try {
            taskToUnmark = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToUnmark && taskToUnmark <= numTasks) {
            return taskToUnmark - 1;
        } else {
            throw new DukeException(String.format("Task %d does not exist!", taskToUnmark));
        }
    }

    public int handleDeleteTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(7);  // "delete " is 7 letters
        int taskToDelete;
        try {
            taskToDelete = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToDelete && taskToDelete <= numTasks) {
            return taskToDelete - 1;
        } else {
            throw new DukeException("Task %d does not exist!");
        }
    }

    public Task handleTodo(String taskString) throws DukeException {
        if (taskString.length() <= 5) {
            throw new DukeException("Description cannot be empty!");
        } else {
            String taskName = taskString.substring(5);  // "todo " has 5 characters
            return new Todo(taskName);
        }
    }

    public Task handleDeadline(String taskString) throws DukeException {
        if (taskString.length() <= 9) {
            throw new DukeException("Description cannot be empty!");
        } else {
            int byIdx = taskString.indexOf("/by");
            if (byIdx <= 9) {  // either -1, or 0 to 9
                throw new DukeException("Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'");
            } else {
                String taskName = taskString.substring(9, byIdx-1);  // "deadline " has 9 characters
                try {
                    String taskDeadline = taskString.substring(byIdx + 4);  // "/by " has 4 characters
                    return new Deadline(taskName, taskDeadline);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException("Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'");
                }
            }
        }
    }

    public Task handleEvent(String taskString) throws DukeException {
        if (taskString.length() <= 6) {
            throw new DukeException("Description cannot be empty!");
        } else {
            int atIdx = taskString.indexOf("/at");
            if (atIdx <= 6) {  // either -1, or 0 to 6
                throw new DukeException("Format for events: 'event [some event] /at [dd/mm/yyyy-hh:mm]'");
            } else {
                String taskName = taskString.substring(6, atIdx-1);  // "event " has 9 characters
                try {
                    String taskTime = taskString.substring(atIdx + 4);  // "/at " has 4 characters
                    return new Event(taskName, taskTime);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException("Format for events: 'event [some event] /at [dd/mm/yyyy-hh:mm]'");
                }
            }
        }
    }

    public Task addTask(String taskString) throws DukeException {
        Task t;
        if (taskString.startsWith("todo ")) {
            t = handleTodo(taskString);
        } else if (taskString.startsWith("deadline ")) {
            t = handleDeadline(taskString);
        } else if (taskString.startsWith("event ")) {
            t = handleEvent(taskString);
        } else {
            throw new DukeException("I don't think I know what this is!");
        }
        return t;
    }
}