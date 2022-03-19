package siri;

import java.util.ArrayList;

//Ui.java reused and edited from Brigette Santoso E0564307

/**
 * Ui provides String replies for the user to interact with.
 */
public class Ui {
    private String output;

    public Ui() {
        this.output = "";
    }

    /**
     * Returns a string welcome message to user after the program loads the GUI window.
     */
    public void showWelcome() {
        output = "     Hi, I'm Siri, a simple list program. \n"
                + "     What do you wish to take note of today?";
    }

    /**
     * Returns a string output Goodbye message when user types bye.
     */
    public void showExit() {
        output = "     Goodbye.\n";
    }

    /**
     * Returns a string output of tasks currently inside the list.
     * @param tasks Tasks within the list.
     */
    public void showList(ArrayList<Task> tasks) {
        output = "     Tasks to do:\n";
        int numberOftasks = tasks.size();
        for (int i = 0; i < numberOftasks; i++) {
            String currTask = tasks.get(i).toString();
            output += "     " + (i + 1) + ". " + currTask + "\n";
        }
    }


    /**
     * Returns a string output of available commands and a description on how to use some of the commands
     */
    public void showHelpList() {
        output = "     Commands:\n"
                + "     1. list\n"
                + "     2. delete\n"
                + "     3. todo\n"
                + "     4. deadline\n"
                + "     5. event\n"
                + "     6. mark\n"
                + "     7. unmark\n"
                + "     8. find\n"
                + "     9. help\n"
                + "     10. bye\n\n"
                + "     You can add 3 types of tasks to the list:\n"
                + "     - todo \n     - deadline \n     - event \n\n"
                + "     Specify the type of task then it's description to add to the list.\n"
                + "     Only deadlines and events can accept dates and locations\n\n"
                + "     Examples:\n"
                + "     - todo run a mile\n"
                + "     - deadline return library book /by Sunday 2359\n"
                + "     - event Jack's wedding /at Holiday Inn 1800\n"
                + "     - delete 2\n"
                + "     - mark 3\n"
                + "     - find book";
    }

    /**
     * Returns a string output of the marked tasks
     * @param task Task that was marked
     */
    public void showMarkTask(Task task) {
        output = "     This task has been marked:\n       "
                + task.toString() + "\n";
    }

    /**
     * Returns a string output of the unmarked task
     * @param task Task that was unmarked
     */
    public void showUnmarkTask(Task task) {
       output = "     This task has been unmarked:\n       "
                + task.toString() + "\n";
    }

    /**
     * Returns a string output of the task added to your list and the number of tasks in your list.
     * @param task Task that is added to the list.
     * @param tasks Tasks that are currently in the list including the task that was just added.
     */
    public void showAddTask(Task task, ArrayList<Task> tasks) {
        output = "     This task has been added to your list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n";
    }

    /**
     * Returns a string output of the task deleted from the list and number of tasks left in the list.
     * @param task Task that is deleted from the list.
     * @param tasks Tasks currently inside the list after deletion.
     */
    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        output = "     Sure. I've removed this task from the list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n";
    }

    /**
     * Returns a string output of tasks that matches the given description.
     * @param foundTasks ArrayList of tasks that matches the given description.
     */
    public void showFind(ArrayList<Task> foundTasks) {
        output = "     Here are the matching tasks in your list:\n";
        int numberOfTasks = foundTasks.size();
        for (int i = 0; i < numberOfTasks; i++) {
            String currTask = foundTasks.get(i).toString();
            output +=  "     " + (i + 1) + ". " + currTask + "\n";
        }
    }

    /**
     * Returns a string output of the loading  error message
     * @param message Loading error message
     */
    public void showLoadingError(String message) {
        output = message;
    }

    /**
     * Returns a string output of the error message
     * @param message Error message
     */
    public void showError(String message) {
        output = message;
    }

    /**
     * Returns a string output
     */
    public String toString() {
        return output;
    }
}