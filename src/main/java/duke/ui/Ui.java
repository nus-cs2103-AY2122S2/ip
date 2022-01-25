package duke.ui;

import duke.tasklist.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {

    public void showLine() {
        System.out.println("-------------------------------------------------" + "\n");
    }

    public void showWelcome() {
        System.out.println("-------------------------------------------------");
        System.out.println("Hi I'm Zen!\n" + "How can I help ?");
        showLine();
    }

    public void showClosing() {
        showLine();
        System.out.println("      " + "Bye! See you soon !");
        showLine();
    }

    public String readFullLine() {
        Scanner s = new Scanner(System.in);
        String res = s.nextLine();
        return res;
    }

    public void showCount(TaskList tasks) {
        System.out.println("There are " + tasks.getCount() + " tasks in your list.");
    }

    public String showSpace() {
        return "      ";
    }

    /**
     * Prints out all elemnets in an ArrayList.
     *
     * @param lenOfArray The length of the input ArrayList.
     * @param tasks The input ArrayList.
     */
    public void displayList(int lenOfArray, TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        if (lenOfArray == 0) {
            System.out.println("      " + "Nothing added yet!");
        }
        for (int i = 0; i < lenOfArray; i++) {
            Task t = tasks.get(i);
            System.out.println("      " + (i + 1) + ". " + t.toString());
        }
    }

}
