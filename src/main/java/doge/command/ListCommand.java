package doge.command;

import doge.*;
import doge.exception.DogeException;
import doge.exception.ListTasksDueException;
import doge.task.Deadline;
import doge.task.Event;
import doge.task.Task;

import java.time.LocalDateTime;

/**
 * Represents the "list" command. User can either list all tasks or only tasks that are due within a specified
 * duration.
 */
public class ListCommand extends Command {
    private String message;

    /**
     * Constructor for class ListCommand.
     *
     * @param details the details of the occurrence period of the tasks
     */
    public ListCommand(String details) {
        super(details);
    }

    /**
     * Executes the "list" command. It either lists all tasks or tasks that are due by a certain period if stated.
     *
     * @param tasks {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws DogeException if it fails to list the tasks
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        TaskList tempTaskList = new TaskList();

        if (this.details.isEmpty()) {
            this.message = list(tasks);
        } else {
            LocalDateTime dueDateTime;
            String[] tempStr = this.details.split(" ");

            try {
                dueDateTime = DateTime.getDateTime(tempStr[1].trim() + " " + tempStr[2].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new ListTasksDueException("Please state an appropriate duration for the occurrence?");
            }


            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.getTask(i);
                LocalDateTime currDateTime = null;

                if (currTask instanceof Deadline || currTask instanceof Event) {
                    currDateTime = currTask.getDateTime();
                } else {
                    continue;
                }

                if (checkDateTime(tempStr[0].trim(), currDateTime, dueDateTime)) {
                    tempTaskList.addTask(currTask);
                }
            }
            this.message = list(tempTaskList);
        }
    }

    public String list(TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(") ").append(tasks.getTask(i));
        }
        return output.toString();
    }

    public boolean checkDateTime(String limiter, LocalDateTime currDateTime, LocalDateTime dueDateTime) throws DogeException {
        assert currDateTime != null : "current date/time is null";
        assert dueDateTime != null : "due date/time is null";

        switch (limiter) {
        case "=":
            if (currDateTime.isEqual(dueDateTime)) {
                break;
            }
            return false;
        case ">":
            if (currDateTime.isAfter(dueDateTime)) {
                break;
            }
            return false;
        case "<":
            if (currDateTime.isBefore(dueDateTime)) {
                break;
            }
            return false;
        case ">=":
            if (currDateTime.isAfter(dueDateTime) || currDateTime.isEqual(dueDateTime)) {
                break;
            }
            return false;
        case "<=":
            if (currDateTime.isBefore(dueDateTime) || currDateTime.isEqual(dueDateTime)) {
                break;
            }
            return false;
        default:
            throw new DogeException("Invalid limiter specified!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return message;
    }
}
