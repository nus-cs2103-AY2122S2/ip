package duke.ui;

import duke.task.Task;
import duke.task.TaskStore;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents an interface which will display messages to the user based on the inputs.
 */
public class Ui {
    public static final String TASK_MARKED = "Nice! I marked this task as done:\n %s";
    public static final String TASK_UNMARKED = "OK, I've marked this task as not done yet:\n %s";
    public static final String TASK_DELETE = "Noted. I've removed this task:\n\t %s\n";
    public static final String TASK_ADD = "Got it. I've added this task:\n\t %s\n";
    public static final String TASKS_SIZE = "Now you have %d tasks in the list";
    public static final String BYE = "Bye. Hope to see you again soon!";

    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaWaldo.png"));

    private final VBox container;

    public Ui(VBox c) {
        this.container = c;
    }

    /**
     * Sends a greeting dialog box to the user.
     */
    public void greet() {
        this.container.getChildren().addAll(DialogBox.getDukeDialog("Hello! I'm Waldo\nWhat can I do for you?",
                dukeImage));
    }

    public void printMessage(String inputTxt) {
        this.container.getChildren().addAll(DialogBox.getDukeDialog(inputTxt, dukeImage));
    }

    public String getTaskSizeString(TaskStore tasks) {
        return String.format(TASKS_SIZE, tasks.getSize());
    }

    /**
     * Generates an error dialog box to the user.
     * @param errorMsg The error message to display to the user
     */
    public void printError(String errorMsg) {
        String em = String.format("☹ OOPS!!! %s", errorMsg);
        this.container.getChildren().addAll(DialogBox.getDukeDialog(em, dukeImage));
    }

    /**
     * Generates a dialog box on task added. This will also display all tasks in the list.
     * @param task The newly added task
     * @param tasks The task list
     */
    public void printTaskAdd(Task task, TaskStore tasks) {
        String template = TASK_ADD + this.getTaskSizeString(tasks);
        String taskAddMessage = String.format(template, task, tasks.getSize());
        this.container.getChildren().addAll(DialogBox.getDukeDialog(taskAddMessage, dukeImage));
    }

    /**
     * Generates a dialog when a task has been marked as done/undone. This will also display all tasks in the list.
     * @param t The task that has been marked as done/undone
     */
    public void printTaskMarking(Task t) {
        if (t.getIsDone()) {
            this.container.getChildren().addAll(DialogBox.getDukeDialog(String.format(TASK_MARKED, t), dukeImage));
        } else {
            this.container.getChildren().addAll(DialogBox.getDukeDialog(String.format(TASK_UNMARKED, t), dukeImage));
        }
    }

    /**
     * Generates a dialog box when a task has been deleted. This will also display the remaining tasks in the list.
     * @param task The task that has been deleted
     * @param tasks The task list that contains the remaining tasks
     */
    public void printTaskDelete(Task task, TaskStore tasks) {
        String template = TASK_DELETE + this.getTaskSizeString(tasks);
        String taskDeleteMessage = String.format(template, task, tasks.getSize());
        this.container.getChildren().addAll(DialogBox.getDukeDialog(taskDeleteMessage, dukeImage));
    }

    public void bye() {
        this.container.getChildren().addAll(DialogBox.getDukeDialog(BYE, dukeImage));
    }
}
