package duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    /** Constructs an instance of Ui. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Prints a horizontal line. */
    public String horizontal() {
        return "--------------------------------------------------";
    }

    /** Prints the welcome message for the user. */
    public String welcome() {
        return "Greetings, NERD! I'm Duke\n" + "Fine, I'm programmed to be nice today. What can I do for you? :)";
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
    public String showTask(int taskNum, Task t) {
        return taskNum + ". " + t;
    }

    /** Prints the empty task message for the user. */
    public String showEmptyTask() {
        return "No tasks added yet!";
    }

    /**
     * Prints a message informing the user that the task is completed.
     *
     * @param t Task that has been completed.
     */
    public String showMarkTask(Task t) {
        return "Cool! You seemed to have been productive just like me! I've marked this task as done:\n" + t;
    }

    /**
     * Prints a message informing the user that a task has been successfully added.
     *
     * @param num Total number of tasks currently.
     * @param t Task that has been added.
     */
    public String showSucessfulAdd(Task t, int num) {
        return "Roger, I got you. I've added this task:\n" + t + "\nNow you have " + num + " tasks in the list.\n";
    }

    /**
     * Prints a message informing the user that a task has been successfully removed.
     *
     * @param num Total number of tasks currently.
     * @param t Task that has been removed.
     */
    public String showRemoveTask(Task t, int num) {
        return "Noted, I have removed this task:\n" + t + "\nNow you have " + num + " tasks in the list.";
    }

    /**
     * Prints a message informing the user that the task is unmarked as done.
     *
     * @param t Task that has been unmarked.
     */
    public String showUnmarkTask(Task t) {
        return "Did you mess up something? I'll mark it as undone -- but I believe you can do it!:\n" + t;
    }

    /**
     * Prints a message informing the user of the error encounted.
     *
     * @param type Type of error.
     */
    public String showError(String type) {
        switch (type) {
        case ("LoadingError"):
            return "Something went wrong with the loading of the file\n" +
                    "Try deleting the data/tasks.txt file, but this will wipe all previous tasks stored (if any).";

        case ("UnknownCommand"):
            return "I'm sorry, but I can't execute this command!";

        case ("TodoFormatError"):
            return "Follow this format:\n" + "todo YOUR_TASK";

        case ("DeadlineFormatError"):
            return "Follow this format:\n" +  "deadline YOUR_TASK /by yyyy-mm-dd TIME\n"
                    + "eg: deadline return book /by 2019-10-15 18:00";

        case ("EventFormatError"):
            return "Follow this format:\n" + "event YOUR_TASK /at yyyy-mm-dd TIME\n"
            + "eg: event project meeting /at 2019-10-15 18:00";

        case ("IOException"):
            return "Something went wrong with the writing to the file";

        case ("DateTimeParseException"):
            return "Please enter the correct format for Datetime! yyyy-mm-dd HH:mm";

        default:
            return "I don't understand this error!";
        }
    }

    /**
     * Prints a message informing the user of the tasks loaded.
     *
     * @param tasks Tasks that have been loaded from previous execution of Duke.
     */
    public String showTasksLoaded(TaskList tasks) {
        return "Here are the tasks we loaded up from your previous usage!";
//        ArrayList<Task> t = tasks.getTaskArr();
//        if (t.size() == 0) {
//            showEmptyTask();
//        }
//
//        for (int i = 0; i < t.size(); i++) {
//            showTask(i + 1, t.get(i));
//        }
    }

    /** Prints the exit statement to the user. */
    public String sayGoodbye() {
        return "Bye! I'm sure you'll start talking to a real human now. Haha...";
    }

    public String showKeywords() {
        return "Here are the matching tasks in your list:";
    }
}
