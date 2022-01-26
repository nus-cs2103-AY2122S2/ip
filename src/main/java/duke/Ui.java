package main.java.duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner sc;

    /** Constructs an instance of Ui. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Prints a horizontal line. */
    public void horizontal() {
        System.out.println("--------------------------------------------------");
    }

    /** Prints the welcome message for the user. */
    public void welcome(){
        horizontal();
        System.out.println("Greetings, NERD! I'm Duke");
        System.out.println("Fine, I'm programmed to be nice today. What can I do for you? :)");
        horizontal();
    }

    /**
     * Reads the command that the user inputs.
     *
     * @return String with the user input.
     */
    public String readCommand() {
        String userinput = sc.nextLine();
        return userinput;
    }

    /**
     * Prints the task with its current position in the Tasklist.
     *
     * @param taskNum Current position of task in Tasklist.
     * @param t Task to be printed.
     */
    public void showTask(int taskNum, Task t) {
        System.out.println(taskNum + ". " + t);
    }

    /** Prints the empty task message for the user. */
    public void showEmptyTask() {
        System.out.println("No tasks added yet!");
    }

    /**
     * Prints a message informing the user that the task is completed.
     *
     * @param t Task that has been completed.
     */
    public void showMarkTask(Task t) {
        System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Prints a message informing the user that a task has been successfully added.
     *
     * @param num Total number of tasks currently.
     * @param t Task that has been added.
     */
    public void showSucessfulAdd(Task t, int num) {
        System.out.println("Roger, I got you. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    /**
     * Prints a message informing the user that a task has been successfully removed.
     *
     * @param num Total number of tasks currently.
     * @param t Task that has been removed.
     */
    public void showRemoveTask(Task t, int num) {
        System.out.println("Noted, I have removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    /**
     * Prints a message informing the user that the task is unmarked as done.
     *
     * @param t Task that has been unmarked.
     */
    public void showUnmarkTask(Task t) {
        System.out.println("Did you mess up something? Fine... I'll mark it as undone -- but I believe you can do it!:");
        System.out.println(t);
    }

    /**
     * Prints a message informing the user of the error encounted.
     *
     * @param type Type of error.
     */
    public void showError(String type) {
        switch (type) {
        case ("LoadingError"):
            System.out.println("Something went wrong with the loading of the file");
            System.out.println("Try deleting the data/tasks.txt file, but this will wipe all previous tasks stored (if any).");
            break;
        case ("UnknownCommand"):
            System.out.println("I'm sorry, but I can't execute this command!");
            break;
        case ("TodoFormatError"):
            System.out.println("Follow this format:");
            System.out.println("todo YOUR_TASK");
            break;
        case ("DeadlineFormatError"):
            System.out.println("Follow this format:");
            System.out.println("deadline YOUR_TASK /by yyyy-mm-dd TIME");
            System.out.println("eg: deadline return book /by 2019-10-15 18:00");
            break;
        case ("EventFormatError"):
            System.out.println("Follow this format:");
            System.out.println("event YOUR_TASK /at yyyy-mm-dd TIME");
            System.out.println("eg: event project meeting /at 2019-10-15 18:00");
            break;
        case ("IOException"):
            System.out.println("Something went wrong with the writing to the file");
            break;
        case ("DateTimeParseException"):
            System.out.println("Please enter the correct format for Datetime! yyyy-mm-dd HH:mm");
        }
    }

    /**
     * Prints a message informing the user of the tasks loaded.
     *
     * @param tasks Tasks that have been loaded from previous execution of Duke.
     */
    public void showTasksLoaded(TaskList tasks) {
        System.out.println("Here are the tasks we loaded up from your previous usage!");
        ArrayList<Task> t = tasks.getTaskArr();
        if (t.size() == 0) {
            showEmptyTask();
        }

        for (int i = 0; i < t.size(); i++ ) {
            showTask(i + 1, t.get(i));
        }
    }

    /** Prints the exit statement to the user. */
    public void sayGoodbye() {
        System.out.println("Bye! I'm sure you'll start talking to a real human now. Haha...");
    }
}
