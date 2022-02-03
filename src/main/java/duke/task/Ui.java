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
     * Prints the greeting message.
     */
    public void greeting() {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");
    }

    /**
     * Prints the goodbye message.
     */
    public void goodbye() {
        System.out.println("Cya later~ ≧◉◡◉≦");
    }


    /**
     * Prints the list for display.
     * @param toDoList List to be displayed.
     */
    public void displayList(ArrayList<Task> toDoList) {
        int number = 1;
        System.out.println("here are your tasks ☜(ˆ▿ˆc)");
        for (Task item : toDoList) {
            System.out.println(number + ". " + item.toString());
            number++;
        }
    }

    /**
     * Print the error caused by illegal argument.
     * @param ex IllegalArgument error.
     */
    public void showIllegalArgumentError(Exception ex) {
        System.out.println(ex);
    }

    /**
     * Print the error caused by incomplete argument.
     */
    public void showIncompleteArgumentError() {
        System.out.println("UH-OH!! you gotta fill in the description and deadline date " +
                "/ event date to create a valid task (> <ლ)");
    }

    /**
     * Prints the confirmation for addition of a <code>Task</code> to the <code>toDoList</code>.
     * @param newTask Task to be added.
     * @param toDoList List for the task to be added to.
     */
    public void confirmAddition(Task newTask, ArrayList<Task> toDoList) {
        System.out.println("okie!! (✿◠‿◠)  i have added: \n" +
                newTask + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }

    /**
     * Prints a message to indicate the bot does not understand the command.
     */
    public void doNotUnderstand() {
        System.out.println("sowwy i don't understand what that means ಠ_ಥ try something else pwease??");
    }


    /**
     * Prints a confirmation that a <code>Task</code> at the index in the list has been marked as done.
     * @param toDoList List of tasks.
     * @param idx Index of the task that has been marked.
     */
    public void markAsDone(ArrayList<Task> toDoList, int idx) {
        System.out.println("yay!!! this task is now marked as done ٩(˘◡˘)۶");
        System.out.println(toDoList.get(idx - 1).toString());
    }

    /**
     * Prints a confirmation that a <code>Task</code> at the index in the list has been marked as undone.
     * @param toDoList List of tasks.
     * @param idx Index of the task that has been unmarked.
     */
    public void unmarkAsDone(ArrayList<Task> toDoList, int idx) {
        System.out.println("this task is now marked as not done yet... do it soon! ᕙ(`▿´)ᕗ");
        System.out.println(toDoList.get(idx - 1).toString());
    }

    /**
     * Prints the confirmation for removal of a <code>Task</code> from the <code>toDoList</code>.
     * @param removed Task to be removed.
     * @param toDoList List for the task to be removed from.
     */
    public void confirmRemoval(Task removed, ArrayList<Task> toDoList) {
        System.out.println("OKI!! i have removed this task: \n" +
                removed + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }

    /**
     * Print the error caused by invalid loading of the file.
     */
    public void showLoadingError() {
        System.out.println("UH-OH!! seems like the file is not in the right format... (⊙.⊙) \n" +
                "don't worry! I'll start a new file for you!");
    }

    /**
     * Prints the specified relevant tasks for the user.
     * @param relevantTasks List of relevant tasks containing a keyword.
     */
    public void showFindResult(ArrayList<Task> relevantTasks) {
        if (relevantTasks.isEmpty()) {
            System.out.println("There are no matching tasks (・_・)");
        } else {
            System.out.println("Here are the matching tasks in the list (｡◕‿◕｡) :");
            int counter = 1;
            for (Task task : relevantTasks) {
                System.out.println(counter + ". " + task);
                counter++;
            }
        }
    }
}
