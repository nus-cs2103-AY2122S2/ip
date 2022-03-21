package user;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.MoneyChange;
import tasks.Task;
import tasks.Todo;

/** A class that handles the parsing of a user's input. */
public class Parser {

    /**
     * Returns the task number of a task to be marked as completed.
     *
     * @param command The user's input to mark the task as completed.
     * @param numTasks The total number of tasks saved.
     * @return The task number of the task.
     * @throws DukeException If the task number provided is invalid.
     */
    public int handleMarkTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(5); // "mark " is 5 letters
        int taskToMark;
        try {
            taskToMark = Integer.parseInt(taskString);
        } catch (NumberFormatException err) {
            throw new DukeException(Task.INVALID_TASK_NUM_ERROR_STRING);
        }

        if (1 <= taskToMark && taskToMark <= numTasks) {
            return taskToMark - 1;
        } else {
            throw new DukeException(String.format(Task.TASK_NUM_DOES_NOT_EXIST_ERROR_STRING, taskToMark));
        }
    }

    /**
     * Returns the task number of a task to be marked as not completed.
     *
     * @param command The user's input to mark the task as not completed.
     * @param numTasks The total number of tasks saved.
     * @return The task number of the task.
     * @throws DukeException If the task number provided is invalid.
     */
    public int handleUnmarkTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(7); // "unmark " is 7 letters
        int taskToUnmark;
        try {
            taskToUnmark = Integer.parseInt(taskString);
        } catch (NumberFormatException err) {
            throw new DukeException(Task.INVALID_TASK_NUM_ERROR_STRING);
        }

        if (1 <= taskToUnmark && taskToUnmark <= numTasks) {
            return taskToUnmark - 1;
        } else {
            throw new DukeException(String.format(Task.TASK_NUM_DOES_NOT_EXIST_ERROR_STRING, taskToUnmark));
        }
    }

    /**
     * Returns the task number of a task to be deleted.
     *
     * @param command The user's input to mark the task as deleted.
     * @param numTasks The total number of tasks saved.
     * @return The task number of the task.
     * @throws DukeException If the task number provided is invalid.
     */
    public int handleDeleteTask(String command, int numTasks) throws DukeException {
        String taskString = command.substring(7); // "delete " is 7 letters
        int taskToDelete;
        try {
            taskToDelete = Integer.parseInt(taskString);
        } catch (NumberFormatException err) {
            throw new DukeException(Task.INVALID_TASK_NUM_ERROR_STRING);
        }

        if (1 <= taskToDelete && taskToDelete <= numTasks) {
            return taskToDelete - 1;
        } else {
            throw new DukeException(String.format(Task.TASK_NUM_DOES_NOT_EXIST_ERROR_STRING, taskToDelete));
        }
    }

    /**
     * Returns the keywords of the task string to be found.
     *
     * @param command The user's input of a task to find.
     * @return The keywords given by the user.
     */
    public String handleFindTask(String command) {
        return command.substring(5); // "find " is 7 letters
    }

    /**
     * Returns a Todo task given the user's input to create a Todo task.
     *
     * @param taskString The user's input.
     * @return The Todo task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task handleTodo(String taskString) throws DukeException {
        if (taskString.length() <= 5) { // length of 'todo '
            throw new DukeException(Task.BAD_DESCRIPTION_ERROR_STRING);
        } else {
            String taskName = taskString.substring(5); // "todo " has 5 characters
            return new Todo(taskName);
        }
    }

    /**
     * Returns a Deadline task given the user's input to create a Deadline task.
     *
     * @param taskString The user's input.
     * @return The Deadline task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task handleDeadline(String taskString) throws DukeException {
        if (taskString.length() <= 9) { // length of 'deadline '
            throw new DukeException(Task.BAD_DESCRIPTION_ERROR_STRING);
        } else {
            int byIdx = taskString.indexOf("/by");
            if (byIdx <= 9) { // either -1, or 0 to 9
                throw new DukeException(Deadline.WRONG_FORMAT_ERROR_STRING);
            } else {
                String taskName = taskString.substring(9, byIdx - 1); // "deadline " has 9 characters
                try {
                    String taskDeadline = taskString.substring(byIdx + 4); // "/by " has 4 characters
                    return new Deadline(taskName, taskDeadline);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(Deadline.WRONG_FORMAT_ERROR_STRING);
                }
            }
        }
    }

    /**
     * Returns an Event task given the user's input to create an Event task.
     *
     * @param taskString The user's input.
     * @return The Event task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task handleEvent(String taskString) throws DukeException {
        if (taskString.length() <= 6) { // length of 'event '
            throw new DukeException(Task.BAD_DESCRIPTION_ERROR_STRING);
        } else {
            int atIdx = taskString.indexOf("/at");
            if (atIdx <= 6) { // either -1, or 0 to 6
                throw new DukeException(Event.WRONG_FORMAT_ERROR_STRING);
            } else {
                String taskName = taskString.substring(6, atIdx - 1); // "event " has 6 characters
                try {
                    String taskTime = taskString.substring(atIdx + 4); // "/at " has 4 characters
                    return new Event(taskName, taskTime);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(Event.WRONG_FORMAT_ERROR_STRING);
                }
            }
        }
    }

    /**
     * Returns an Event task given the user's input to lend someone money.
     *
     * @param taskString The user's input.
     * @return The Event task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task handleLend(String taskString) throws DukeException {
        if (taskString.length() <= 5) { // length of 'lend '
            throw new DukeException(Task.BAD_DESCRIPTION_ERROR_STRING);
        } else {
            int toIdx = taskString.indexOf("/to");
            if (toIdx <= 5) { // either -1, or 0 to 5
                throw new DukeException(MoneyChange.WRONG_LEND_FORMAT_ERROR_STRING);
            } else {
                String amount = taskString.substring(5, toIdx - 1); // "lend " has 9 characters
                try {
                    float amountFloat = Float.parseFloat(amount);
                    String person = taskString.substring(toIdx + 4); // "/to " has 4 characters
                    return new MoneyChange(-amountFloat, person);
                } catch (NumberFormatException err) {
                    throw new DukeException(MoneyChange.INVALID_MONEY_AMOUNT);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(MoneyChange.WRONG_LEND_FORMAT_ERROR_STRING);
                }
            }
        }
    }

    /**
     * Returns an Event task given the user's input to borrow money.
     *
     * @param taskString The user's input.
     * @return The Event task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task handleBorrow(String taskString) throws DukeException {
        if (taskString.length() <= 7) { // length of 'borrow '
            throw new DukeException(Task.BAD_DESCRIPTION_ERROR_STRING);
        } else {
            int fromIdx = taskString.indexOf("/from");
            if (fromIdx <= 7) { // either -1, or 0 to 7
                throw new DukeException(MoneyChange.WRONG_BORROW_FORMAT_ERROR_STRING);
            } else {
                String amount = taskString.substring(7, fromIdx - 1); // "borrow " has 9 characters
                try {
                    float amountFloat = Float.parseFloat(amount);
                    String person = taskString.substring(fromIdx + 6); // "/from " has 6 characters
                    return new MoneyChange(amountFloat, person);
                } catch (NumberFormatException err) {
                    throw new DukeException(MoneyChange.INVALID_MONEY_AMOUNT);
                } catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(MoneyChange.WRONG_LEND_FORMAT_ERROR_STRING);
                }
            }
        }
    }

    /**
     * Returns the right Task given the user's input to create a new Task.
     *
     * @param taskString The user's input.
     * @return The right task.
     * @throws DukeException If the user's input is invalid.
     */
    public Task addTask(String taskString) throws DukeException {
        Task t;
        if (taskString.startsWith("todo ")) {
            t = handleTodo(taskString);
        } else if (taskString.startsWith("deadline ")) {
            t = handleDeadline(taskString);
        } else if (taskString.startsWith("event ")) {
            t = handleEvent(taskString);
        } else if (taskString.startsWith("lend ")) {
            t = handleLend(taskString);
        } else if (taskString.startsWith("borrow ")) {
            t = handleBorrow(taskString);
        } else {
            throw new DukeException(Task.UNKNOWN_INPUT_ERROR_STRING);
        }
        return t;
    }
}
