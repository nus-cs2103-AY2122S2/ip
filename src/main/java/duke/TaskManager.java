package duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks and functions to carry out task-related functions such as marking, unmarking, deleting
 * and adding tasks
 */
public class TaskManager {
    private ArrayList<Task> taskList;

    TaskManager() {
        this.taskList = new ArrayList<>();
    }

    TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Obtain the list of tasks
     * @return <code>ArrayList</code> representing the list of tasks
     */
    ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Obtain the number of tasks
     * @return Integer representing the number of tasks
     */
    int countTasks() {
        return this.taskList.size();
    }

    /**
     * Listing out the tasks
     * @return String representing the list of tasks
     */
    public String list() {
        if (this.countTasks() == 0) {
            return "You have no tasks.";
        }
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= this.countTasks(); i++) {
            response += (i + ". " + this.taskList.get(i - 1));
            if (i != this.countTasks()) {
                response += "\n";
            }
        }
        return response;
    }

    /**
     * Marking a task as completed
     * @param index Index of task to be marked
     * @return String representing the response when a task is marked
     */
    public String mark(int index) {
        String response = "";
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.mark();
            response += "Nice! I've marked this task as done:\n";
            response += "    " + this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }
    /**
     * Deleting a task
     * @param index Index of task to be deleted
     * @return String representing the response when a task is deleted
     */
    public String delete(int index) {
        String response = "";
        try {
            Task taskToDelete = this.taskList.get(index);
            this.taskList.remove(index);
            response += "Noted. I've removed this task:\n";
            response += "    " + taskToDelete + "\n";
            response += String.format("Now you have %d tasks in the list.", this.countTasks());
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }

    /**
     * Adding a task
     * @param task Task to be added to the list
     * @return String representing the response when a task is added
     */
    public String add(Task task) {
        this.taskList.add(task);
        String response = "Got it. I've added this task:\n";
        response += "    " + task + "\n";
        response += String.format("Now you have %d tasks in the list.", this.countTasks());
        return response;
    }

    /**
     * Marking a task as not completed
     * @param index Index of task to be unmarked
     * @return String representing the response when a task is unmarked
     */
    public String unmark(int index) {
        String response = "";
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.unmark();
            response += "OK, I've marked this task as not done yet:\n";
            response += "    " + this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return response;
    }
}

