package duke;

import java.util.Scanner;

public class Ui {
    private static final String INPUT_NAME = "You:";
    private static final String OUTPUT_NAME = "Duke:";
    private Scanner scanner;
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
    }

    public void startMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String msg = "Hello! I am duke.Duke.\n"
                + "Your wish is my command.\n\n";
        System.out.print(OUTPUT_NAME + "\n" + logo + msg);
    }

    public void listMessage() {
        String msg = "Here are the tasks in your list-\n" + taskList;
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public void endMessage() {
        String msg = "don't leave me don't leave me.";
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public void addTaskMessage() {
        String msg = "Got it. I have added this task-\n" + taskList.getTaskDescription(taskList.size()) + "\n";
        msg += "Now you have " + taskList.size() + " tasks.";
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public void deleteMessage(String toDelete) {
        String msg = "Noted. I've removed this task-\n" + toDelete + "\n";
        msg += "Now you have " + taskList.size() + " tasks.\n";
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public void markMessage(int taskId) {
        String msg = "I have marked this as done.\n" + taskList.getTaskDescription(taskId);
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public void unMarkMessage(int taskId) {
        String msg = "I have unmarked this task.\n" + taskList.getTaskDescription(taskId);
        System.out.println(OUTPUT_NAME + "\n" + msg);
    }

    public String readUserInput() {
        System.out.println(INPUT_NAME);
        return scanner.nextLine();
    }

    public void showErrorMessage(String error) {
        System.out.println(OUTPUT_NAME + "\n" + error + "\n");
    }

}
