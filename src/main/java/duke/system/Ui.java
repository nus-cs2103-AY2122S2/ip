package duke.system;

import java.util.Scanner;

import duke.exceptions.DukeException;
/**
 * The Ui class will allow users to interface with Duke.
 *
 * @author  Melvin Chan Zijun
 */
public class Ui {
    /**
     * A String constant that is repeatedly used throughout the
     * whole Ui class.
     */
    private final String space = "     ";

    /**
     * This method prints a divider between messages.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(space + line);
    }

    /**
     * This method prints the greeting message.
     */
    public void showGreeting() {
        String greeting = space + "Hello! I'm Duke\n"
                + space + "Nice to meet you!";
        System.out.println(greeting);
    }

    /**
     * This method prints a tutorial on the available commands.
     */
    public void showTutorial() {
        String tutorial = space + "Here are my features!\n"
                + space + "To add a ToDo task, input: ToDo/<name>\n"
                + space + "To add a Event task, input: Event/<name>/<date>/<time>\n"
                + space + "To add a Deadline task, input: Deadline/<name>/<date>/<time>\n"
                + space + "To list all tasks, input: list\n"
                + space + "To mark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + space + "To unmark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + space + "To delete a task, "
                + "input: delete/<task number based on the most recent list call>\n"
                + space + "To search for a task by keyword, input: Find/<keyword>\n"
                + space + "To clear the tasklist, input: Clear\n"
                + space + "To exit duke, input: exit\n"
                + space + "To view this manual again, input: help\n"
                + space + "The first word of each command is not case-sensitive!\n"
                + space + "The format of the date should be in the format DDMMYYYY!\n"
                + space + "The format of the time should be in the format HHMM!\n"
                + space + "Please DO NOT use \"/\" in the task name as it will confuse Duke!";
        System.out.println(tutorial);
    }

    /**
     * This method prompts the user for their input command and returns
     * the command as a String.
     *
     * @return String - raw input command from user
     */
    public String promptCommand() {
        System.out.println(space + "Please input your command:");
        this.showLine();
        Scanner sc = new Scanner(System.in);
        String command = "";
        if (sc.hasNext()) {
            command = sc.nextLine();
        }
        return command;
    }

    /**
     * This method prints a confirmation that a task has been added.
     */
    public void showTaskAdded() {
        this.showLine();
        System.out.println(space + "Task has been added!");
    }

    /**
     * This method prints a confirmation that a task has been marked.
     */
    public void showTaskMarked() {
        this.showLine();
        System.out.println(space + "Task has been marked!");
    }

    /**
     * This method prints a confirmation that a task has been unmarked.
     */
    public void showTaskUnmarked() {
        this.showLine();
        System.out.println(space + "Task has been unmarked!");
    }

    /**
     * This method prints a confirmation that a task has been deleted.
     */
    public void showTaskDeleted() {
        this.showLine();
        System.out.println(space + "Task has been deleted!");
    }

    /**
     * This method prints all the tasks in the tasklist.
     *
     * @param tasks - list of tasks in tasklist consolidated
     *                into a single String
     */
    public void showList(String tasks) {
        this.showLine();
        if (tasks.isBlank()) {
            System.out.println(space + "There is nothing in the TaskList!");
        } else {
            System.out.println(space + "Here is a List of your Tasks:");
            System.out.print(tasks);
        }
    }

    /**
     * This method prints all the tasks that contains the keyword from the user.
     *
     * @param tasks - list of tasks in tasklist containing keyword
     *                from the user consolidated into a single String
     */
    public void showResult(String tasks) {
        this.showLine();
        if (tasks.isBlank()) {
            System.out.println(space
                    + "There is nothing in the TaskList that matches your search!");
        } else {
            System.out.println(space
                    + "Here is a List of your Tasks that matches your search:");
            System.out.print(tasks);
        }
    }

    /**
     * This method prints a confirmation that the tasklist has been cleared.
     */
    public void showClear() {
        System.out.println(space + "TaskList has been cleared!");
    }

    /**
     * This method prints the message DukeException that has been thrown.
     *
     * @param e - a DukeException that was thrown
     */
    public void showException(DukeException e) {
        this.showLine();
        System.out.println(e.toString());
    }

    /**
     * This method prints the message that there was an error loading old data.
     */
    public void showLoadingError() {
        System.out.println(space + "Error loading old data!");
    }

    /**
     * This method prints the exit message.
     */
    public void showExit() {
        this.showLine();
        System.out.println(space + "Bye! Hope to see you again soon!");
    }
}
