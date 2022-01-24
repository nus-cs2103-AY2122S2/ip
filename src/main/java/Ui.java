import java.util.Scanner;

/**
 * Ui represents the user interface object that deals with user interaction
 *
 * @author Jan
 * @version 1.0
 */
public class Ui {
    /** The scanner used to read from system input */
    private Scanner in;

    /** The border for Sana's replies */
    private static final String border = "_____________________________________________";

    /**
     * Constructor for the Ui object
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the next line from the user input
     *
     * @return  the next user input
     */
    public String nextLine() {
        return in.nextLine();
    }

    /**
     * Closes the scanner used to read inputs
     */
    public void closeScanner() {
        in.close();
    }

    /**
     * This method prints to system IO how many tasks in user tasks
     */
    public void taskNumberText(int taskNumber) {
        String taskAmt = Integer.valueOf(taskNumber).toString();
        System.out.println("You have " + taskAmt + " things here.");
    }

    /**
     * This method prints to system IO Sana's greeting
     */
    public void greet() {
        border();
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        border();
    }

    /**
     * This method prints the border
     */
    public void border() {
        System.out.println(border);
    }

    /**
     * This method prints to system IO Sana's add task text
     */
    public void addTaskText() {
        System.out.println("I've added your task!");
    }

    /**
     * This method prints to system IO Sana's bye
     */
    public void bye() {
        System.out.println("See you next time!");
    }

}
