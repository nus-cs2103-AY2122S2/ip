package duke.task;

import java.util.ArrayList;

/**
 * List that stores the tasks that the user has created through Ducky.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor that creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param item Task that the user would like to add (e.g. Event, Deadline, Todo).
     */
    public void addTask(Task item){
        tasks.add(item);
    }

    /**
     * Removes a task from the task list.
     *
     * @param id Index of the task within the task list.
     * @return returns the Task that was removed.
     */
    public Task removeTask(int id) {
        return tasks.remove(id);
    }

    /**
     * Gets the task from the task list.
     *
     * @param id Index of the the task within the task list.
     * @return Returns the task that matches the index within the task list.
     */
    public Task getTask(int id){
        return tasks.get(id);
    }


    /**
     * Finds the task that matches with the detail provided by the user.
     *
     * @param detail Detail to be found within each task's description.
     * @return Returns a formatted output string of tasks that have their descriptions match with the user input.
     */
    public String findTasks(String detail){
        String output = "";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getDescription();
            String message = tasks.get(i).getTask();
            if (i == tasks.size() -1 && description.contains(detail)) {
                count++;
                output += count + ". " + message;
            } else if (description.contains(detail)) {
                count++;
                output += count + ". " + message + "\n";
            }
        }
        return output;
    }

    /**
     * Formats the tasks within the Task List. Formatted string is used as what is to be saved in the text file.
     *
     * @return Formatted string consisting of the various tasks that the user has saved in Ducky.
     */
    public String formatTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            String message = tasks.get(i).getDescription();
            String preStatus = tasks.get(i).getStatusIcon();
            String status = "";
            if (preStatus.equals("X")) {
                status = status + "1";
            } else {
                status = status + "0";
            }
            if (i == tasks.size()-1) {
                output = output + status + " | " + message;
            } else {
                output = output + status + " | " + message + "\n";
            }
        }
        return output;
    }

    /**
     * Gets the number of tasks within the Task List.
     *
     * @return Number of tasks in the Task List.
     */
    public Integer getSize(){
        return tasks.size();
    }
}
