package duke.ui;

import duke.info.task.Task;
import duke.utils.Text;
import duke.info.task.Calendar;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    /* Creates an empty Ui handler */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println(Text.TEXT_WELCOME);
        showLine();
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showLine() {
        System.out.println(Text.TEXT_DIVIDER);
    }

    public void showLoadingError() {
        showLine();
        System.out.println(Text.TEXT_LOADING_ERROR);
        showLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showGoodbye() {
        System.out.println(Text.TEXT_GOODBYE_MESSAGE);
    }

    public void showCalendar(Calendar calendar) {
        System.out.println(calendar.toString());
    }

    public void showTaskAdded(Task addedTask, int numOfTasks) { System.out.println(String.format(Text.TEXT_TASK_ADDED, addedTask, numOfTasks)); }

    public void showTaskComplete(String taskString) { System.out.println(String.format(Text.TEXT_MARKED, taskString)); }

    public void showTaskIncomplete(String taskString) { System.out.println(String.format(Text.TEXT_UNMARKED, taskString)); }

    public void showTaskDeleted(String taskString) { System.out.println(String.format(Text.TEXT_DELETED, taskString)); }
}
