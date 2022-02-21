package siri;

import java.util.ArrayList;

//Ui.java reused and edited from Brigette Santoso E0564307
/**
 * Ui provides String replies for the user to interact with
 */
public class Ui {
    private String output;

    public Ui() {
        this.output = "";
    }

    public void showWelcome() {
        output = "     Hi, I'm Siri, a simple list program. \n"
                + "     You can add 3 types of tasks to the list:\n"
                + "     1.todo \n     2.deadline \n     3.event \n\n"
                + "     Specify the type of task then it's description to add to the list.\n"
                + "     Only deadlines and events can accept dates and locations\n\n"
                + "     Examples:\n"
                + "     todo run a mile\n"
                + "     deadline return library book /by Sunday 2359\n"
                + "     event Jack's wedding /at Holiday Inn 1800\n\n"
                + "     What do you wish to take note of today?";
    }

    public void showExit() {
        output = "     Goodbye.\n";
    }

    public void showList(ArrayList<Task> tasks) {
        output = "     Tasks to do:\n";
        int numberOftasks = tasks.size();
        for (int i = 0; i < numberOftasks; i++) {
            String currTask = tasks.get(i).toString();
            output += "     " + (i + 1) + ". " + currTask + "\n";
        }
    }

    public void showMarkTask(Task task) {
        output = "     This task has been marked:\n       "
                + task.toString() + "\n";
    }

    public void showUnmarkTask(Task task) {
       output = "     This task has been unmarked:\n       "
                + task.toString() + "\n";
    }

    public void showAddTask(Task task, ArrayList<Task> tasks) {
        output = "     This task has been added to your list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n";
    }

    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        output = "     Sure. I've removed this task from the list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n";
    }

    public void showFind(ArrayList<Task> foundTasks) {
        output = "     Here are the matching tasks in your list:\n";
        int numberOfTasks = foundTasks.size();
        for (int i = 0; i < numberOfTasks; i++) {
            String currTask = foundTasks.get(i).toString();
            output +=  "     " + (i + 1) + ". " + currTask + "\n";
        }
    }

    public void showLoadingError(String message) {
        output = message;
    }

    public void showError(String message) {
        output = message;
    }

    public String toString() {
        return output;
    }
}