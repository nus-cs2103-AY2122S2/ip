package duke.ui;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskStore;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents an interface which will display messages to the user based on the inputs.
 */
public class Ui {
    public static final String TASK_MARKED = "Nice! I marked the %d task(s) as done:\n %s";
    public static final String TASK_UNMARKED = "OK, I've marked %d task(s) as not done:\n %s";
    public static final String TASK_DELETE = "Noted. I've removed the %d task(s): \n%s";
    public static final String TASK_ADD = "Got it. I've added this task:\n\t %s\n";
    public static final String TASKS_SIZE = "Now you have %d tasks in the list";
    public static final String BYE = "Bye. Hope to see you again soon!";

    private static final String GREET = "Hello! I'm Waldo\nWhat can I do for you?";
    private static final String ERROR_TEMPLATE = "☹ OOPS!!! %s";
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaWaldo.png"));

    private final VBox container;

    public Ui(VBox c) {
        this.container = c;
    }

    /**
     * Sends a greeting dialog box to the user.
     */
    public void greet() {
        this.addDukeDialogBox(GREET);
    }

    /**
     * Generates a dialog box to reply to the user.
     * @param inputTxt The text which waldo will reply with.
     */
    public void printMessage(String inputTxt) {
        // this.container.getChildren().addAll(DialogBox.getDukeDialog(inputTxt, dukeImage));
        this.addDukeDialogBox(inputTxt);
    }

    public String getTaskSizeString(TaskStore tasks) {
        return String.format(TASKS_SIZE, tasks.getSize());
    }

    /**
     * Generates an error dialog box to the user.
     * @param errorMsg The error message to display to the user
     */
    public void printError(String errorMsg) {
        String error = String.format(ERROR_TEMPLATE, errorMsg);
        this.addDukeDialogBox(error);
    }

    /**
     * Generates a dialog box on task added. This will also display all tasks in the list.
     * @param task The newly added task
     * @param tasks The task list
     */
    public void printTaskAdd(Task task, TaskStore tasks) {
        String template = TASK_ADD + this.getTaskSizeString(tasks);
        String taskAddMessage = String.format(template, task, tasks.getSize());
        this.addDukeDialogBox(taskAddMessage);
    }

    public void printTaskDelete(ArrayList<Task> deletedTasks, TaskStore tasks) {
        int numTasksDeleted = deletedTasks.size();
        String template = TASK_DELETE + this.getTaskSizeString(tasks);
        String taskList = generateTaskInList(deletedTasks);
        String taskDeleteMessage = String.format(template, numTasksDeleted, taskList);
        this.addDukeDialogBox(taskDeleteMessage);
    }

    public void printTaskMarking(ArrayList<Task> tasks, boolean isDone) {
        String taskList = this.generateTaskInList(tasks);
        if (isDone) {
            this.addDukeDialogBox(String.format(TASK_MARKED, tasks.size(), taskList));
        } else {
            this.addDukeDialogBox(String.format(TASK_UNMARKED, tasks.size(), taskList));
        }
    }

    public void bye() {
        this.addDukeDialogBox(BYE);
    }

    public void addDukeDialogBox(String message) {
        this.container.getChildren().addAll(DialogBox.getDukeDialog(message, dukeImage));
    }

    public String generateTaskInList(ArrayList<Task> tasklist) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasklist.size(); i++) {
            String taskItem = String.format("\t%d.%s\n", (i + 1), tasklist.get(i).toString());
            sb.append(taskItem);
        }
        return sb.toString();
    }
}
