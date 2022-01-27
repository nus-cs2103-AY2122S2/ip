package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void stylePrint(String str) {
        System.out.println("________\n" + str);
    }

    public void displayLine() {
        System.out.println("________\n");
    }

    public void displayWelcome() {
        String logo ="\n" +
                "    ___   ____________\n" +
                "   /   | / ____/ ____/\n" +
                "  / /| |/ /   / __/   \n" +
                " / ___ / /___/ /___   \n" +
                "/_/  |_\\____/_____/   \n" +
                "                      \n";
        stylePrint(logo + "\nHi, I'm Ace. What can I do for you?");
    }

    public void displayGoodbye() {
        stylePrint("See you later!");
    }

    public void displayAllTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            stylePrint("There are no tasks in your list.");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            stylePrint("Here are your tasks:\n" + strBuilder.toString());
        }
    }

    public void displayMatchingTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            stylePrint("There are no matching tasks in your list.");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String line = i + 1 + ". " + tasks.get(i) + '\n';
                strBuilder.append(line);
            }
            stylePrint("Here are the matching tasks:\n" + strBuilder.toString());
        }
    }

    public void displayError(String errorMessage) {
        stylePrint(errorMessage);
    }

    public void displayMarkedTask(Task task) {
        stylePrint("The following task has been marked as complete:\n" + task.toString());
    }

    public void displayUnmarkedTask(Task task) {
        stylePrint("The following task has been marked as incomplete:\n" + task.toString());
    }

    public void displayDeletedTask(Task task) {
        stylePrint("The following task has been deleted:\n" + task.toString());
    }

    public void displayAddedTask(Task task) {
        stylePrint("The following task has been added:\n" + task.toString());
    }

    public void displayNumberOfTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have 0 tasks in your list");
        } else if (tasks.size() == 1) {
            System.out.println("You have 1 task in your list");
        } else {
            System.out.println("You have " + tasks.size() + " tasks in your list");
        }
    }
}
