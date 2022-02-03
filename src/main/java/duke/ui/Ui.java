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

//    TODO Possibly change to public
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    //    TODO: Change if got time
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private VBox container;

    public Ui(VBox c) {
        this.container = c;
    }

    public void greet() {
//        String logo = "                _     _       \n"
//                + "               | |   | |      \n"
//                + " __      ____ _| | __| | ___  \n"
//                + " \\ \\ /\\ / / _` | |/ _` |/ _ \\ \n"
//                + "  \\ V  V / (_| | | (_| | (_) |\n"
//                + "   \\_/\\_/ \\__,_|_|\\__,_|\\___/ \n";
//        System.out.println("Hello from\n" + logo);
//
//
//        this.printMessage("Hello! I'm Waldo\nWhat can I do for you?");

        this.container.getChildren().addAll(DialogBox.getDukeDialog("Hello! I'm Waldo\nWhat can I do for you?",dukeImage));
    }

    public void printMessage(String inputTxt) {
//        System.out.println("____________________________________________________________");
//        System.out.println(inputTxt);
//        System.out.println("____________________________________________________________");
        this.container.getChildren().addAll(DialogBox.getDukeDialog(inputTxt,dukeImage));
    }

    public String getTaskSizeString(TaskStore tasks) {
        return String.format(TASKS_SIZE, tasks.getSize());
    }

    public void printError(String errorMsg) {
        String em = String.format("☹ OOPS!!! %s", errorMsg);
        this.container.getChildren().addAll(DialogBox.getDukeDialog(em,dukeImage));
//        this.printMessage(String.format("☹ OOPS!!! %s", errorMsg));
    }

    public void printTaskAdd(Task task, TaskStore tasks) {
        String template = TASK_ADD + this.getTaskSizeString(tasks);
        String taskAddMessage = String.format(template, task, tasks.getSize());
        this.container.getChildren().addAll(DialogBox.getDukeDialog(taskAddMessage,dukeImage));
//        this.printMessage(String.format(template, task, tasks.getSize()));
    }

    public void printTaskMarking(Task t) {
        if (t.getIsDone()) {
//            this.printMessage(String.format(TASK_MARKED, t));
            this.container.getChildren().addAll(DialogBox.getDukeDialog(String.format(TASK_MARKED, t),dukeImage));
        } else {
//            this.printMessage(String.format(TASK_UNMARKED, t));
            this.container.getChildren().addAll(DialogBox.getDukeDialog(String.format(TASK_UNMARKED, t),dukeImage));
        }
    }

    public void printTaskDelete(Task task, TaskStore tasks) {
        String template = TASK_DELETE + this.getTaskSizeString(tasks);
        String taskDeleteMessage = String.format(template, task, tasks.getSize());
        this.container.getChildren().addAll(DialogBox.getDukeDialog(taskDeleteMessage,dukeImage));
//        this.printMessage(String.format(template, task, tasks.getSize()));
    }

    public void bye() {
//        this.printMessage("Bye. Hope to see you again soon!");
        this.container.getChildren().addAll(DialogBox.getDukeDialog(BYE,dukeImage));
    }
}
