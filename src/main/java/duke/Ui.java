package duke;

import java.util.List;
import duke.task.Task;

public class Ui {
    private static final String MESSAGE_INTRO = "Hello! I'm Duke\n     What can I do for you?";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    public void showWelcome() {
        printContent(MESSAGE_INTRO);
    }

    public void showExitMessage() {
        printContent(MESSAGE_BYE);
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printContent(String text) {
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }

    public void printAddDeleteTaskSuccess(List<Task> tasks, Task task, String message) {
        String content = taskLine(task, message) + "\n";
        content += listSizeLine(tasks);
        printContent(content);
    }

    public String taskLine(Task task, String message) {
        return message + "\n       [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.toString();
    }

    public String listSizeLine(List<Task> tasks) {
        return "     Now you have " + tasks.size() + " task"
                + (tasks.size() != 1 ? "s" : "") + " in the list.";
    }
}
