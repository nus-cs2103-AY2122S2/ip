package duke.utils;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
        System.out.println("Hello from\n" + logo + "\nMy name is Cortana, what can I do for you?");
    }

    public void showLine() {
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();
        return input;
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void noTaskLeft() {
        System.out.println("You are done for the day, or are you?");
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void addedTask(TaskList tasks, Task task) {
        String taskOrTasks = tasks.tasksArrayList.size() <= 1 ? "duke/task" : "tasks";
        System.out.println("Got it. I've added this task: \n" + " " + task +
                "\nNow you have " + tasks.tasksArrayList.size() + " " + taskOrTasks + " in the list.");
    }

    public void deletedTask(TaskList tasks, Task taskDeleted) {
        String taskOrTasks = tasks.tasksArrayList.size() <= 1 ? "duke/task" : "tasks";
        System.out.println("Noted. I've removed this task: \n" + " " + taskDeleted + "\n" +
                "Now you have " + tasks.tasksArrayList.size() + " " + taskOrTasks + " in the list.");
    }

    public void deletedAll() {
        System.out.println("All tasks have been removed!");
    }

    public void exited() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listed(int index, Task task) {
        System.out.println(index + "." + task);
    }

    public void marked(Task task) {
        System.out.println("Nice! I've marked this task as done: \n " + task);
    }

    public void unmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n " + task);
    }

    public void foundTaskOnSameDate(int numberOfTasksOnSameDate, String time) {
        String taskOrTasks = numberOfTasksOnSameDate <= 1 ? "duke/task" : "tasks";
        System.out.printf("Found %d %s with date/time %s.\n", numberOfTasksOnSameDate, taskOrTasks, time);
    }
}
