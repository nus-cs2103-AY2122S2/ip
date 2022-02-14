package bobby;

import java.util.Scanner;

import bobby.task.Task;
import bobby.task.TaskList;

public class Ui {
    private Scanner sc;
    private final String logo =
             "─██████████████───██████████████─██████████████───██████████████───████████──████████─\n"
           + "─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██───██░░░░██──██░░░░██─\n"
           + "─██░░██████░░██───██░░██████░░██─██░░██████░░██───██░░██████░░██───████░░██──██░░████─\n"
           + "─██░░██──██░░██───██░░██──██░░██─██░░██──██░░██───██░░██──██░░██─────██░░░░██░░░░██───\n"
           + "─██░░██████░░████─██░░██──██░░██─██░░██████░░████─██░░██████░░████───████░░░░░░████───\n"
           + "─██░░░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██─────████░░████─────\n"
           + "─██░░████████░░██─██░░██──██░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n"
           + "─██░░██────██░░██─██░░██──██░░██─██░░██────██░░██─██░░██────██░░██───────██░░██───────\n"
           + "─██░░████████░░██─██░░██████░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n"
           + "─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██───────██░░██───────\n"
           + "─████████████████─██████████████─████████████████─████████████████───────██████───────\n";
    private final String line1 = "\t====================================================================\n";
    private final String line2 = "\t====================================================================";
    private final String greeting = "Howdy! I'm Bobby\t\t(｡◕‿‿◕｡)\nWhat can I do for you?";
    private final String goodbye = "Bye! Hope to see you again soon!\n(｡^‿‿^｡)";

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Wrapper method to print messages out.
     *
     * @param message The message to print.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints a long line for CLI.
     *
     * @param type Specifies the type of long line. Long line ends with a new line.
     */
    public void printLongLine(int type) {
        if (type == 1) {
            System.out.print(line1);
        } else {
            System.out.print(line2);
        }
    }

    /**
     * Prints the logo out.
     */
    public void printLogo() {
        System.out.println(logo);
    }

    /**
     * Prints the greeting message for CLI.
     */
    public void printGreeting() {
        printLongLine(1);
        System.out.println(greeting);
        printLongLine(2);
    }

    /**
     * Returns the goodbye message.
     * @return the goodbye message to be printed.
     */
    public String goodbyeMessage() {
        return goodbye;
    }

    /**
     * Reads in user input from the CLI.
     * @return user input as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns the message after marking a task.
     *
     * @param task The task that is marked.
     * @return The message after marking.
     */
    public String markMessage(Task task) {
        return "Finally... I've marked this task as done: " + task;
    }

    /**
     * Returns the message after unmarking a task.
     *
     * @param task The task that is unmarked.
     * @return The message after unmarking.
     */
    public String unmarkMessage(Task task) {
        return "Could you be any more lazy? I've marked this task as not done yet: " + task;

    }

    /**
     * Returns the message after adding a ToDo task.
     *
     * @param todo The ToDo task that is added.
     * @return The message after adding the task.
     */
    public String todoMessage(Task todo) {
        return "OK you better do this today, or else...\n(ㆆ _ ㆆ)\nAdded task: " + todo;
    }

    /**
     * Returns the message after adding a Deadline task.
     *
     * @param deadline The Deadline task that is added.
     * @return The message after adding the task.
     */
    public String deadlineMessage(Task deadline) {
        return "Oh boy, another deadline?\n(ㆆ _ ㆆ)\nAdded task:" + deadline;
    }

    /**
     * Returns the message after adding an Event task
     * @param event The Event task that is added.
     * @return The message after adding the task.
     */
    public String eventMessage(Task event) {
        return "Let's see... A new event!\nAdded task: " + event;
    }

    /**
     * Returns the message after deleting a task
     *
     * @param deleteTask The task that is to be deleted.
     * @return The message after deleting the task.
     */
    public String deleteMessage(Task deleteTask) {
        return "Alright I'm deleting this task:\n" + deleteTask;
    }

    /**
     * Returns the message after deleting all tasks
     *
     * @return The message after deleting all tasks.
     */
    public String deleteAllMessage() {
        return "Alright I'm deleting ALL tasks \n(*・‿・)ノ⌒*:･ﾟ✧";
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @param tasks The tasks list to be printed.
     * @return A string stating the number of tasks in the list.
     */
    public String printNumTasks(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " in the list.";
    }

    /**
     * Gets the tasks and returns messages with the task list.
     *
     * @param tasks The tasks list to be printed.
     * @return Messages with the task list.
     */
    public String printTaskList(TaskList tasks) {
        String replyMessage;
        if (tasks.isEmpty()) {
            replyMessage = "Wow you are very free now! Enjoy~ \n༼ つ ◕_◕ ༽つ";
        } else {
            replyMessage = "I've sorted and put the any deadlines/events to the top for you :)\n";
            replyMessage += taskListToString(tasks);
        }
        return replyMessage;
    }

    /**
     * Gets the tasks and returns messages with the task list.
     *
     * @param tasks The tasks list to be printed.
     * @return Messages with the task list.
     */
    public String printFindTaskList(TaskList tasks) {
        String replyMessage;
        if (tasks.isEmpty()) {
            replyMessage = "No matching tasks in your list (≧︿≦)";
        } else {
            replyMessage = "Here are the matching tasks in your list:\n";
            replyMessage += taskListToString(tasks);
        }
        return replyMessage;
    }

    /**
     * Gets the tasks and return a properly formatted list.
     *
     * @param tasks The tasks list to be printed.
     * @return A properly formatted string of the task list.
     */
    private String taskListToString(TaskList tasks) {
        String taskListString = "";
        Task currTask;
        for (int i = 0; i < tasks.getSize(); i++) {
            currTask = tasks.getIndex(i);
            int index = i + 1;
            taskListString += index + "." + currTask + "\n";
        }
        return taskListString;
    }

}
