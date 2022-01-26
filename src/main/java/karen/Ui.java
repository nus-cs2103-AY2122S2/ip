package karen;

import karen.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles receiving and formatting of input and output
 */
public class Ui {
    public static final String STD_DIVIDER = "~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static final String ERR_DIVIDER = "------------------------------------------------";
    public static final String WELCOME = "Hello, my name is Karen.\nI'll be speaking (to) as your manager today.";
    public static final String GOODBYE = "Goodbye - I'll be seeing your manager's manager next.\nI'll remember this.";
    public static final String NO_TASKS = "Nothing is even added yet.";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    protected String formatIndexItem(int index, String item) {
        return String.format("%d.%s\n", index, item);
    }

    public String formatCount(String action, Task item, int count) {
        return String.format("Fine. Task %s:\n %s\nNow you have %d in total.",
                action, item.toString(), count);
    }

    public String readInput() {
        String fullCommand = this.in.nextLine();
        return fullCommand;
    }

    public void showWelcome() {
        // more customisation and cleanup in future
        out.println(STD_DIVIDER);
        out.println(WELCOME);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    public void showGoodbye() {
        // more customisation and cleanup in future
        out.println(STD_DIVIDER);
        out.println(GOODBYE);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    public void displayUserInput(String message) {
        out.println(STD_DIVIDER);
        out.println(message);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    public void displayWarning(String message) {
        out.println(ERR_DIVIDER);
        out.println(message);
        out.println(ERR_DIVIDER);
        out.print("\n");

    }

    public void displayTaskList(ArrayList<Task> taskList) {
        StringBuilder formatString = new StringBuilder();
        int index = 1;
        for (Task item: taskList) {
            formatString.append(formatIndexItem(index, item.toString()));
            index++;
        }
        if (taskList.size()==0) {
            formatString.append(NO_TASKS);
        }

        displayUserInput(formatString.toString());
    }

}
