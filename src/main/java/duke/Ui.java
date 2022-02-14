package duke;

import java.util.Scanner;

/**
 * Ui class that
 * is responsible to show actions that user undertakes
 */
public class Ui {

    public Ui() {
    }

    /**
     * shows welcome screen
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * reads text entered by user
     * @return String entered by user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Shows loading error
     */
    public void showLoadingError() {
        System.out.println("A loading error has occurred.");
    }

    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Shows user deadline has been added
     * @param deadline deadline that user adds
     * @param tasks list of tasks
     */
    public void showDeadline(Deadline deadline, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    /**
     * Shows user event has been added
     * @param event event that user adds
     * @param tasks list of tasks
     */
    public void showEvent(Event event, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    /**
     * Shows user todo has been added
     * @param todo todo that user adds
     * @param tasks list of tasks
     */
    public void showTodo(Todo todo, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    /**
     * Shows user that task has been deleted
     * @param task task that is to be deleted
     * @param tasks list of tasks
     */
    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        if (tasks.getSize() == 2) {
            System.out.println("Now you have " + (tasks.getSize() - 1) + " task in the list.");
        } else {
            System.out.println("Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
        }
    }

    /**
     * Displays exit screen
     * @param storage  Storage
     * @param tasks list of tasks saved
     * @throws DukeException
     */
    public void showExit(Storage storage, TaskList tasks) throws DukeException {
        storage.save(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * shows tasks in list of tasks
     * @param tasks list of tasks
     */
    public void showTasks(TaskList tasks) {
        if(tasks.getSize() == 0) {
            System.out.println("Now you have 0 tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.getSize(); i++) {
                System.out.println( Integer.toString(i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * shows error that occurs during program execution
     * @param errorMessage error message
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows user that task has been marked
     * @param task task that is to be marked
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * shows user that task has been unmarked
     * @param task task that is to be unmarked
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}
