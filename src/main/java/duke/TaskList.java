package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class contains the task list and deals with add/delete list operations.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TaskList {
    // ArrayList to store all your tasks
    private ArrayList<Task> list;

    // Constructor
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Deletes task from the list and returns
     * a string indicating that the task is deleted.
     *
     * @param index the position of the task.
     * @return a string indicating that the task is deleted
     */
    public String deleteTask(int index) {
        if (index >= list.size()) {
            return "Opps!!! I'm sorry, can't find task";
        } else {
            Task task = list.remove(index);
            return "Noted. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + list.size() + " tasks in the list";
        }
    }

    /**
     * Adds to do task into our current list and returns
     * a string indicating that the task is added.
     *
     * @param desc the task description entered by the user.
     * @return a string indicating that the task is added.
     */
    public String addToDo(String desc, boolean done) {
        if (desc == null) {
            return "Please include task description in your command.";
        }
        Task newTask = new ToDo(desc, done);
        list.add(newTask);
        return "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Adds deadline task into our current list and returns
     * a string indicating that the task is added.
     *
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param done the current status of the task
     * @return a string indicating that the task is added.
     */
    public String addDeadline(String desc, String date, boolean done) {
        if (desc == null) {
            return "Please include task description in your command.";
        }
        if (date == null) {
            return "Please include the date in your command.";
        }
        try {
            LocalDate date2 = LocalDate.parse(date);
            Task newTask = new Deadline(desc, date2, done);
            list.add(newTask);
            return "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } catch (DateTimeParseException ex) {
            return "Invalid date format";
        }
    }

    /**
     * Adds event task into our current list and returns
     * a string indicating that the task is added.
     *
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param done the current status of the task
     * @return a string indicating that the task is added.
     */
    public String addEvent(String desc, String date, boolean done) {
        if (desc == null) {
            return "Please include task description in your command.";
        }
        if (date == null) {
            return "Please include the date in your command.";
        }
        try {
            LocalDate date2 = LocalDate.parse(date);
            Task newTask = new Event(desc, date2, done);
            list.add(newTask);
            return "Got it. I've added this task:\n"
                    + newTask + "\n"
                    + "Now you have " + list.size() + " tasks in the list.";
        } catch (DateTimeParseException ex) {
            return "Invalid date format";
        }
    }

    /**
     * Returns a string containing all the tasks in the list.
     */
    public String listTask() {
        int num = 1;
        String str = "Here are the tasks in your list:\n";
        for (Task task: list) {
            str += num + "." + task + "\n";
            num++;
        }
        return str;
    }

    /**
     * Changes the completion status of the task and returns
     * a string indicating that the task is marked
     *
     * @param index position of the task in the list
     * @return string indicating that the task is marked
     */
    public String markTask(int index) {
        if (index >= list.size()) {
            return "Opps!!! I'm sorry, can't find task";
        } else {
            Task task = list.get(index);
            task.setAsDone();
            return "Nice! I've marked this task as done\n"
                    + task;
        }
    }

    /**
     * Changes the completion status of the task and returns
     * a string indicating that the task is marked
     *
     * @param index position of the task in the list
     * @return string indicating that the task is marked
     */
    public String unMarkTask(int index) {
        if (index >= list.size()) {
            return "Opps!!! I'm sorry, can't find task";
        } else {
            Task task = list.get(index);
            task.setAsNotDone();
            return "Nice! I've marked this task as not done\n"
                    + task;
        }
    }

    /**
     * Returns a list of tasks with a keyword specified by the user.
     *
     * @param taskName the keyword entered by the user.
     * @return a string that indicate matches found.
     */
    public String findTask(String taskName) {
        int num = 1;
        int matchNum = 0;
        String str = "Here are the matching tasks in your list:\n";
        for (Task task : list) {
            String temp = task.getDesc();
            if (taskName == null) {
                break;
            } else if (temp.contains(taskName)) {
                str += num + ". " + task + "\n";
                matchNum++;
            }
            num++;
        }
        str += "Match Found: " + matchNum;
        return str;
    }

    /**
     * Gets a specific task from the list based on position.
     *
     * @param index the position of the task in the list.
     * @return a task object.
     */
    public Task getIndex(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Adds task into list without printing anything.
     */
    public void addTask(Task task) {
        list.add(task);
    }
}
