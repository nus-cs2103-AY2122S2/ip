package duke;

import java.util.ArrayList;

public class Ui {

    public void start() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
    }

    // Can display all commands
    public void displayCommands() {
        System.out.println("DISPLAY LIST OF COMMANDS");
    }
    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void displayTasks(ArrayList<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }
    }

    public void displayTaskAmount(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file!");
    }

    public void readCommand() {

    }

    public void addTask(Task task) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
    }

    public void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.getStatusIcon() + " " + task.getDescription());
    }

    public void deleteTask(Task task) {
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(task);
    }
}
