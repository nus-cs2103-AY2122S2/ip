package duke.task;

import java.util.ArrayList;

/**
 * Represents a UI that deals with interactions with the user.
 */
public class Ui {
    /**
     * Returns a new instance of the <code>Ui</code> class.
     */
    public Ui() {
    }

    /**
     * Returns the greeting message
     * @return Greeting message is returned.
     */
    public String greeting() {
        String logo = "__  __ ____________\n"
                + "\\ \\/ /|_  /_  /_  /\n"
                + " >  <  / / / / / / \n"
                + "/_/\\_\\/___/___/___| \n";
        return logo + "Hello uwu! I'm xzzz, \n" + "You can check your schedwle here! \n";
    }

    /**
     * Returns the goodbye message.
     * @return Goodbye message is returned.
     */
    public String goodbye() {
        return "Cya later~\n";
    }


    /**
     * Returns the list for display.
     * @param toDoList List to be displayed.
     * @return A display of the list is returned.
     */
    public String displayList(ArrayList<Task> toDoList) {
        int number = 1;
        String result = "here are your tasks\n";
        for (Task item : toDoList) {
            result += number + ". " + item.toString() + "\n";
            number++;
        }
        if (toDoList.isEmpty()) {
            return "you have no tasks as of now! \n";
        } else {
            return result;
        }
    }

    /**
     * Returns the error caused by illegal argument.
     * @param ex IllegalArgument error.
     * @return The error message for illegal argument is returned.
     */
    public String showIllegalArgumentError(Exception ex) {
        return ex.toString();
    }

    /**
     * Returns the error caused by incomplete argument.
     * @return The error message for incomplete argument is returned.
     */
    public String showIncompleteArgumentError() {
        return "UH-OH!! you gotta fill in the description and deadline date "
                + "/ event date to create a valid task\n";
    }

    /**
     * Returns the confirmation for addition of a <code>Task</code> to the <code>toDoList</code>.
     * @param newTask Task to be added.
     * @param toDoList List for the task to be added to.
     * @return The confirmation for addition is returned.
     */
    public String confirmAddition(Task newTask, ArrayList<Task> toDoList) {
        return "okie!! i have added: \n"
                + newTask + "\n"
                + "now there are " + toDoList.size() + " tasks in the list! get to work \n";
    }

    /**
     * Returns a message to indicate the bot does not understand the command.
     * @return The default response for unrecognizable command is returned.
     */
    public String doNotUnderstand() {
        return "sowwy i don't understand what that means try something else pwease?? \n";
    }


    /**
     * Returns a confirmation that a <code>Task</code> at the index in the list has been marked as done.
     * @param toDoList List of tasks.
     * @param idx Index of the task that has been marked.
     * @return The confirmation of the marking is returned.
     */
    public String markAsDone(ArrayList<Task> toDoList, int idx) {
        return "yay!!! this task is now marked as done \n" + toDoList.get(idx - 1).toString();
    }

    /**
     * Returns a confirmation that a <code>Task</code> at the index in the list has been marked as undone.
     * @param toDoList List of tasks.
     * @param idx Index of the task that has been unmarked.
     * @return The confirmation of the unmarking is returned.
     */
    public String unmarkAsDone(ArrayList<Task> toDoList, int idx) {
        return "this task is now marked as not done yet... do it soon! \n" + toDoList.get(idx - 1).toString();
    }

    /**
     * Returns the confirmation for removal of a <code>Task</code> from the <code>toDoList</code>.
     * @param removed Task to be removed.
     * @param toDoList List for the task to be removed from.
     * @return The confirmation of the removal is returned.
     */
    public String confirmRemoval(Task removed, ArrayList<Task> toDoList) {
        return "OKI!! i have removed this task: \n" + removed + "\n"
                + "now there are " + toDoList.size() + " tasks in the list! get to work \n";
    }

    /**
     * Returns the error caused by invalid loading of the file.
     * @return The error message of a loading error is returned.
     */
    public String showLoadingError() {
        return "UH-OH!! seems like the file is not in the right format...\n"
                + "don't worry! I'll start a new file for you! \n";
    }

    /**
     * Returns the specified relevant tasks for the user.
     * @param relevantTasks List of relevant tasks containing a keyword.
     * @return The result of the find operation is returned.
     */
    public String showFindResult(ArrayList<Task> relevantTasks) {
        if (relevantTasks.isEmpty()) {
            return "There are no matching tasks \n";
        } else {
            String result = "Here are the matching tasks in the list : \n";
            int counter = 1;
            for (Task task : relevantTasks) {
                result += counter + ". " + task;
                counter++;
            }
            return result;
        }
    }
}
