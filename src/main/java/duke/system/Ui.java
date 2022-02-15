package duke.system;

import duke.exceptions.DukeException;
/**
 * The Ui class will allow users to interface with Duke.
 *
 * @author  Melvin Chan Zijun
 */
public class Ui {
    /**
     * Returns greeting message.
     */
    public String showGreeting() {
        return "Hello! I'm Duke\n"
                + "Nice to meet you!";
    }

    /**
     * Returns a list of the available commands.
     */
    public String showTutorial() {
        return "Here are my features!\n"
                + "To add a ToDo task,\n"
                + "input: ToDo/<name>\n"
                + "To add a Event task,\n"
                + "input: Event/<name>/<date>/<time>\n"
                + "To add a Deadline task,\n"
                + "input: Deadline/<name>/<date>/<time>\n"
                + "To list all tasks,\n"
                + "input: list\n"
                + "To mark a task as completed,\n"
                + "input: mark/<task number based on the most recent list call>\n"
                + "To unmark a task as completed,\n"
                + "input: mark/<task number based on the most recent list call>\n"
                + "To delete a task,\n"
                + "input: delete/<task number based on the most recent list call>\n"
                + "To search for a task by keyword,\n"
                + "input: Find/<keyword>\n"
                + "To clear the task list,\n"
                + "input: Clear\n"
                + "To exit duke,\n"
                + "input: exit\n"
                + "To view this manual again,\nhelp"
                + "input: help\n"
                + "The first word of each command is not case-sensitive!\n"
                + "The format of the date should be in the format DDMMYYYY!\n"
                + "The format of the time should be in the format HHMM!\n"
                + "Please DO NOT use \"/\" in the task name as it will confuse Duke!";
    }

    /**
     * Returns message that the task has been added.
     */
    public String showTaskAdded() {
        return "Task has been added!";
    }

    /**
     * Returns message that the task has been marked.
     */
    public String showTaskMarked() {
        return "Task has been marked!";
    }

    /**
     * Returns message that the task has been unmarked.
     */
    public String showTaskUnmarked() {
        return "Task has been unmarked!";
    }

    /**
     * Returns message that the task has been deleted.
     */
    public String showTaskDeleted() {
        return "Task has been deleted!";
    }

    /**
     * Returns all the tasks in the task list.
     *
     * @param tasks duke's task list
     */
    public String showList(String tasks) {
        if (tasks.isBlank()) {
            return "There is nothing in the TaskList!";
        } else {
            return "Here is a list of your tasks:\n" + tasks;
        }
    }

    /**
     * Returns all the tasks that contains the keyword from the user.
     *
     * @param tasks list of tasks containing keyword
     */
    public String showResult(String tasks) {
        if (tasks.isBlank()) {
            return "There is nothing in the TaskList that matches your search!";
        } else {
            return "Here is a List of your Tasks that matches your search:\n" + tasks;
        }
    }

    /**
     * Returns message that the task list has been cleared.
     */
    public String showClear() {
        return "TaskList has been cleared!";
    }

    /**
     * Returns message from DukeException that has been thrown.
     *
     * @param e a DukeException that was thrown
     */
    public String showException(DukeException e) {
        return "/e " + e.getMessage();
    }

    /**
     * Returns message that there was an error loading old data.
     */
    public String showLoadingError() {
        return "Error loading old data!";
    }

    /**
     * Returns the exit message.
     */
    public String showExit() {
        return "Bye! Hope to see you again soon!";
    }
}
