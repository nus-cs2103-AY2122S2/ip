package funbox.util;

import java.util.Scanner;
import funbox.task.Task;

public class Ui {

    public void greetUser() {
        System.out.println("Yo! I am FunBox [0 _ 0] \nWhat can I do for you?");
    }

    public void printListHeader() {
        System.out.println("Here are the tasks in your list:");
    }

    public void printBye() {
        System.out.println("B-b-bbye. Hope to see you again soon [0 n 0]");
    }

    public void printDirSuccess() {
        System.out.println("Directory 'data' has been created for you!");
    }

    public void printDirAlreadyExist() {
        System.out.println("Directory already exists!");
    }

    public void printFileSuccess() {
        System.out.println("I have created tasks.txt for you!");
    }

    public void printLoadData() {
        System.out.println("Loading saved task ... beep ... boop");
    }

    public void printTask(int index, Task task) {
        System.out.println(index + "." + task);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printNotice() {
        System.out.println("Noted! I've removed this task:");
    }

    public void printRemainingTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list");
    }

    public void printNoTasksFound() {
        System.out.println("No tasks found on this date! You are free!");
    }

    public void emptyList() {
        System.out.println("You have no tasks at the moment!");
    }

    public void printDeletedTask(Task task) {
        System.out.println(task);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        sc.close();
        return command;
    }


}
