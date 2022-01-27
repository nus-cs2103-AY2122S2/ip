package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Finding tasks that match a description
     * @param description String describing task
     * @return String representing tasks matching the description
     */
    public String find(String description) {
        Stream<Task> filteredTasks = taskList.stream().filter(task -> task.getDescription().contains(description));
        ArrayList<Task> tasks = new ArrayList<>(filteredTasks.collect(Collectors.toList()));
        if (tasks.size() == 0) {
            return "There are no tasks matching this description.";
        }
        String response = "Here are the tasks matching the description:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            response += (i + ". " + tasks.get(i - 1));
            if (i != tasks.size()) {
                response += "\n";
            }
        }
        return response;
    }

    /**
     * Finding tasks that match a given date
     * @param date Date supplied by user input
     * @return String representing tasks with given date
     */
    public String findByDate(String date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task: this.taskList) {
            if (task.getType() == "Deadline") {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getTime().equals(date)) {
                    tasks.add(deadlineTask);
                }
            }
            if (task.getType() == "Event") {
                Event eventTask = (Event) task;
                if (eventTask.getTime().equals(date)) {
                    tasks.add(eventTask);
                }
            }
        }
        if (tasks.size() == 0) {
            return "There are no tasks with this date.";
        }
        String response = "Here are the tasks with the given date date:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            response += (i + ". " + tasks.get(i - 1));
            if (i != tasks.size()) {
                response += "\n";
            }
        }
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

