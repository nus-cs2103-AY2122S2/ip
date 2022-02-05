import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }



    public void printIntroduction() {
        printDukeLogo();
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");

    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printDukeLogo();
    }

    public void printStorageIoError() {
        System.out.println("OOPS! An error occurred while attempting to create/retrieve storage file.");

    }

    public void printUpdateIoError(IOException ie) {
        System.out.println("OOPS! An error occurred while attempting to update storage file:\n"
                + ie.getMessage());

    }

    public void printDukeException(DukeException de) {
        System.out.println("OOPS! " + de.getMessage());

    }

    public void printTasks(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("" + i + ". " + taskList.get(i - 1).toString());
        }

    }

    public void printNumTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");

    }

    public void printAddTaskMessage(Task added, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + added.toString());
        printNumTasks(taskList);
    }

    public void printMarkDone(Task marked) {
        System.out.println("This task is marked as done:\n" + marked.toString());

    }

    public void printDeleteTask(Task deleted, TaskList taskList) {
        System.out.println("This task has been removed:\n" + deleted.toString());
        printNumTasks(taskList);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }
}