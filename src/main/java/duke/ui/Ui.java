package duke.ui;

import duke.tasklist.TaskList;
import duke.task.Task;

import java.util.Scanner;

/**
 * Represents an Ui object that handles I/O requests.
 */
public class Ui {

    /**
     * Displays a line.
     */
    public void showLine() {
        System.out.println("-------------------------------------------------" + "\n");
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("-------------------------------------------------");
        System.out.println("Hi I'm Duke!\n" + "How can I help ?");
        showLine();
    }

    /**
     * Displays a closing message.
     */
    public void showClosing() {
        showLine();
        System.out.println("      " + "Bye! See you soon !");
        showLine();
    }

    /**
     * Reads a line of input text from a user.
     *
     * @return The input text as a string.
     */
    public String readFullLine() {
        Scanner s = new Scanner(System.in);
        String res = s.nextLine();
        return res;
    }

    /**
     * Displays how many tasks there are in the provided
     * TaskList object.
     *
     * @param tasks The input TaskList object.
     */
    public void showCount(TaskList tasks) {
        System.out.println("There are " + tasks.getCount() + " tasks in your list.");
    }

    /**
     * Prints out all elements in an TaskList object.
     *
     * @param lenOfArray The length of the input ArrayList.
     * @param tasks      The input ArrayList.
     */
    public void displayList(int lenOfArray, TaskList tasks) {
        if (lenOfArray == 0) {
            System.out.println("      " + "Nothing added yet!");
        }
        for (int i = 0; i < lenOfArray; i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.toString());
        }
    }

}
