// Abstract out the messages

import java.util.Scanner;

public class Ui {
    private final TaskList taskList;
    private final Scanner scanner;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
        scanner = new Scanner(System.in);
    }

    public String readNextLine() {
        return scanner.nextLine();
    }

    public void sayText(String text) {
        System.out.println("Athena: " + text);
    }

    public void sayTaskAddingLines(int taskNumber) {
        sayText("Okay, I've added this task to your list.");
        showTask(taskNumber);
        showCurrentNumberOfTasks();
    }

    public void showTaskList() {
        sayText("Here's the current list of tasks:");
        System.out.println(taskList.toString());
    }

    public void showTask(int taskNumber) {
        System.out.println(taskList.getTaskString(taskNumber));
    }

    public void showCurrentNumberOfTasks() {
        if (this.taskList.getNumberOfTasks() == 1) {
            sayText("Now you have 1 task in your list.");
        } else {
            sayText(String.format("Now you have %d tasks in your list.", taskList.getNumberOfTasks()));
        }
    }
}
