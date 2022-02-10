package duke.system;

import duke.exceptions.DukeException;
/**
 * The Ui class will allow users to interface with Duke.
 *
 * @author  Melvin Chan Zijun
 */
public class Ui {
    /**
     * This method prints the greeting message.
     */
    public String showGreeting() {
        return "Hello! I'm Duke\n"
                + "Nice to meet you!";
    }

    /**
     * This method prints a tutorial on the available commands.
     */
    public String showTutorial() {
        return "Here are my features!\n"
                + "To add a ToDo task, input: ToDo/<name>\n"
                + "To add a Event task, input: Event/<name>/<date>/<time>\n"
                + "To add a Deadline task, input: Deadline/<name>/<date>/<time>\n"
                + "To list all tasks, input: list\n"
                + "To mark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + "To unmark a task as completed, "
                + "input: mark/<task number based on the most recent list call>\n"
                + "To delete a task, "
                + "input: delete/<task number based on the most recent list call>\n"
                + "To search for a task by keyword, input: Find/<keyword>\n"
                + "To clear the tasklist, input: Clear\n"
                + "To exit duke, input: exit\n"
                + "To view this manual again, input: help\n"
                + "The first word of each command is not case-sensitive!\n"
                + "The format of the date should be in the format DDMMYYYY!\n"
                + "The format of the time should be in the format HHMM!\n"
                + "Please DO NOT use \"/\" in the task name as it will confuse Duke!";
    }

    /**
     * This method prints a confirmation that a task has been added.
     */
    public String showTaskAdded() {
        return "Task has been added!";
    }

    /**
     * This method prints a confirmation that a task has been marked.
     */
    public String showTaskMarked() {
        return "Task has been marked!";
    }

    /**
     * This method prints a confirmation that a task has been unmarked.
     */
    public String showTaskUnmarked() {
        return "Task has been unmarked!";
    }

    /**
     * This method prints a confirmation that a task has been deleted.
     */
    public String showTaskDeleted() {
        return "Task has been deleted!";
    }

    /**
     * This method prints all the tasks in the tasklist.
     *
     * @param tasks - list of tasks in tasklist consolidated
     *                into a single String
     */
    public String showList(String tasks) {
        if (tasks.isBlank()) {
            return "There is nothing in the TaskList!";
        } else {
            return "Here is a List of your Tasks:\n" + tasks;
        }
    }

    /**
     * This method prints all the tasks that contains the keyword from the user.
     *
     * @param tasks - list of tasks in tasklist containing keyword
     *                from the user consolidated into a single String
     */
    public String showResult(String tasks) {
        if (tasks.isBlank()) {
            return "There is nothing in the TaskList that matches your search!";
        } else {
            return "Here is a List of your Tasks that matches your search:\n" + tasks;
        }
    }

    /**
     * This method prints a confirmation that the tasklist has been cleared.
     */
    public String showClear() {
        return "TaskList has been cleared!";
    }

    /**
     * This method prints the message DukeException that has been thrown.
     *
     * @param e - a DukeException that was thrown
     */
    public String showException(DukeException e) {
        return e.toString();
    }

    /**
     * This method prints the message that there was an error loading old data.
     */
    public String showLoadingError() {
        return "Error loading old data!";
    }

    /**
     * This method prints the exit message.
     */
    public String showExit() {
        return "Bye! Hope to see you again soon!";
    }
}
