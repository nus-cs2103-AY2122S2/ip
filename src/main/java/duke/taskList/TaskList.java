package duke.taskList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidTaskNumberException;
import duke.exception.DukeWrongInputFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the list of tasks that the user has created.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Returns a new TaskList Object with an empty java.util.ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a formatted String listing out the duke.task.Task that the list currently contains.
     *
     * @return List of Tasks as a String.
     */
    public String printTasks() {
        StringBuilder output = new StringBuilder("\n\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\t").append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns the current size of the tasks ArrayList.
     *
     * @return size of current list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Executes appropriate action specified by the duke.command.Command given by the user.
     *
     * @param c The duke.command.Command given by user.
     * @param task Additional arguments the user supplied such as description.
     * @param showMessage A boolean to indicate if a feedback message should be generated.
     * @return Feedback String corresponding to the duke.command.Command.
     * @throws DukeWrongInputFormatException If there's a mistake with the format of the command.
     */
    public String addTask(Command c, String task, boolean canShowMessage) throws DukeException {
        Task t;
        switch (c) {
        case TODO:
            t = new ToDo(task.trim());
            break;
        case DEADLINE:
            String[] descriptionAndDate = task.split(" /by ", 2);
            if (descriptionAndDate.length <= 1) {
                throw new DukeWrongInputFormatException("Missing deadline by date.");
            }
            t = new Deadline(descriptionAndDate[0].trim(), descriptionAndDate[1].trim());
            break;
        case EVENT:
            String[] descriptionAndTime = task.split(" /at ", 2);
            if (descriptionAndTime.length <= 1) {
                throw new DukeWrongInputFormatException("Missing event at date.");
            }
            t = new Event(descriptionAndTime[0].trim(), descriptionAndTime[1].trim());
            break;
        default:
            t = null;
            break;
        }
        this.tasks.add(t);
        if(canShowMessage) {
            return "\n\tGot it. I've added this task:\n\t\t" + t + "\n\t"
                    + "Now you have " + tasks.size() + " in the list.\n" ;
        } else {
            return null;
        }
    }

    /**
     * Sets the done boolean variable of the duke.task.Task at index in to true.
     *
     * @param in Index of task to be marked as done, given as a String.
     * @param showMessage A boolean to indicate if a feedback message should be generated.
     * @return Feedback String.
     * @throws DukeWrongInputFormatException If there's a mistake with the format of the command or if the index number does not exist.
     */
    public String markAsDone(String in, boolean canShowMessage) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                this.tasks.get(index-1).setDone(true);
                if (canShowMessage) {
                    return "\n\tNice! I've marked this task as done:\n\t\t"
                            + this.tasks.get(index-1).toString() + "\n";
                } else {
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number "
                    + "after typing mark: mark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: mark <taskNum>");
        }
    }

    /**
     * Sets the done boolean variable of the duke.task.Task at index in to false.
     *
     * @param in Index of task to be marked as not done, given as a String.
     * @return Feedback String.
     * @throws DukeWrongInputFormatException If there's a mistake with the format of the command or if the index number does not exist.
     */
    public String unmarkDone(String in) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                this.tasks.get(index-1).setDone(false);
                return "\n\tOK, I've marked this task as not done yet:\n\t\t"
                        + this.tasks.get(index-1).toString() + "\n";
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number "
                    + "after typing unmark: unmark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: unmark <taskNum>");
        }
    }

    /**
     * Deletes the duke.task.Task at index in.
     *
     * @param in Index of task to be deleted, given as a String.
     * @return Feedback String.
     * @throws DukeWrongInputFormatException If there's a mistake with the format of the command or if the index number does not exist.
     */
    public String deleteTask(String in) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                Task deleted = tasks.remove(index - 1);
                return "\n\tNoted. I've removed this task:\n\t\t" + deleted.toString()
                        + "\n\tNow you have " + tasks.size() + " tasks in the list.\n";
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number "
                    + "after typing delete: delete <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid delete command: delete <taskNum>");
        }
    }

    /**
     * Returns an Iterable object that iterates over the current tasks list and calling Task.formatSave() on the Tasks.
     *
     * @return Iterable<? extends CharSequence> for saving to file.
     */
    public Iterable<? extends CharSequence> saveToFile() {
        Iterator<Task> it = this.tasks.iterator();
        return (Iterable<String>) () -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
            @Override
            public String next() {
                return it.next().formatSave();
            }
        };
    }
}
