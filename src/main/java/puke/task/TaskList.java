package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import puke.exception.PukeException;

/**
 * Represents the list of task in the current session.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialise an ArrayList to store the tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the current session.
     *
     * @return Number of tasks.
     */
    public int getNoOfTasks() {
        return tasks.size();
    }

    /**
     * Checks if a task number given is valid.
     * A valid task number is between 1 and the number of tasks (inclusive).
     *
     * @param taskNo Task number to check validity.
     * @return true if the task number is valid; false otherwise.
     */
    public boolean isValidTask(int taskNo) {
        return taskNo >= 1 && taskNo <= this.getNoOfTasks();
    }

    /**
     * Returns a string representation of the list of tasks.
     *
     * @return String representation of the task list, or a message if the list is empty.
     * @throws PukeException If the list of task is empty.
     */
    public String listTasks() throws PukeException {
        if (this.getNoOfTasks() == 0) {
            throw new PukeException("You have no task right now!");
        }

        String result = "Here are the tasks you have:";
        int i = 1;
        for (Task t : tasks) {
            result += "\n" + i + "." + t.toString();
            i++;
        }
        return result;
    }

    /**
     * Marks a task as done given the task number.
     *
     * @param taskNo Task number of task to mark.
     * @return Success message.
     * @throws PukeException If the task number is invalid, or the task is already marked as done.
     */
    public String markTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        Task t = tasks.get(taskNo - 1);
        if (t.isDone()) {
            throw new PukeException(String.format("Task #%d is already marked as done!", taskNo));
        }

        t.mark();
        return "Kudos! I've marked this task as done:\n  " + t;
    }

    /**
     * Marks a task as undone given the task number.
     *
     * @param taskNo Task number of task to unmark.
     * @return Success message.
     * @throws PukeException If the task number is invalid, or the task is already marked as undone.
     */
    public String unmarkTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        Task t = tasks.get(taskNo - 1);
        if (!t.isDone()) {
            throw new PukeException(String.format("Task #%d is not yet marked as done!", taskNo));
        }

        t.unmark();
        return "Alright, I've marked this task as not done yet:\n  " + t;
    }

    /**
     * Adds a task to the list given the argument containing task name and date/time if applicable.
     *
     * @param type Type of task to add.
     * @param args Argument given by the user.
     * @return Success message.
     * @throws PukeException If the argument is invalid for the task type.
     */
    public String addTask(String type, String args) throws PukeException {
        if (args == null) {
            throw new PukeException("I'll need a description for the task..");
        }

        Task t;
        if (type.equals("todo")) {
            t = new Todo(args.trim());
        } else {
            String[] taskDetail = args.split("/");

            if (taskDetail.length < 2) {
                throw new PukeException("I'll need a date/time for this task..");
            }

            String dateTimeStr = taskDetail[1].split(" ", 2)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                if (dateTime.isBefore(LocalDateTime.now())) {
                    throw new PukeException("Why are you trying to create a task in the past?");
                }

                if (type.equals("deadline")) {
                    t = new Deadline(taskDetail[0].trim(), dateTime);
                } else {
                    t = new Event(taskDetail[0].trim(), dateTime);
                }
            } catch (DateTimeParseException e) {
                throw new PukeException("I'll need a valid date/time in the format yyyy-mm-dd hh:mm");
            }
        }

        tasks.add(t);

        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + this.getNoOfTasks()
                + (this.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Adds a task object directly to the task list.
     *
     * @param t Task to add to the list.
     */
    public void addTaskToList(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes a task given the task number.
     *
     * @param taskNo Task number of task to remove.
     * @return Success message.
     * @throws PukeException If the task number is invalid.
     */
    public String deleteTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        String taskInfo = tasks.get(taskNo - 1).toString();
        tasks.remove(taskNo - 1);
        return "There's no going back now. I've removed this task:\n  " + taskInfo
                + "\nNow you have " + this.getNoOfTasks()
                + (this.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.";
    }

    /**

     * Returns a list of task(s) with name containing the specified keyword.
     *
     * @param keyword Keyword to match in the task name.
     * @return List of matching tasks.
     * @throws PukeException If keyword is not provided.
     */
    public String findTasks(String keyword) throws PukeException {
        if (keyword == null) {
            throw new PukeException("I'll need a keyword to find..");
        }

        String result = "";
        int i = 1;
        for (Task t : tasks) {
            if (t.containKeyword(keyword)) {
                result += "\n" + i + "." + t;
                i++;
            }
        }

        if (result == "") {
            return "There is no matching task for '" + keyword + "'";
        } else {
            return "Here are the matching tasks for you:" + result;
        }
    }

    /**
     * Generates the information of tasks in the current session to save on the file.
     *
     * @return String representation of all tasks to save.
     */
    public String generateStorageData() {
        String result = "";

        for (int i = 0; i < this.getNoOfTasks(); i++) {
            result += tasks.get(i).toSaveString();
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }

        return result;
    }
}
