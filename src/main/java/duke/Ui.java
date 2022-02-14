package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        System.setOut(DukeGUI.outputStream);
    }


    /**
     * Waits for user input and returns it
     *
     * @return Next line of input from user
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Duke's standard output line
     *
     * @return String of a line
     */
    private static String line() {
        String line = "____________________________________________________________";
        return line;
    }

    /**
     * Formatted output for confirmation of a new added task
     *
     * @param task Newly added task
     */
    public static void printAddTask(Task task) {
        System.out.println("Added:");
        System.out.println(task.toString());
    }

    /**
     * Formatted output for confirmation of program start
     */
    public static void printIntro() {
        String INTRO = "Duke initialised";
        System.out.println(INTRO);
    }

    /**
     * Formatted output for confirmation of program exit
     */
    public static void printExit() {
        String EXIT = "Duke terminated";
        System.out.println(EXIT);
    }

    /**
     * Formatted output for printing the task list to the user
     *
     * @param formattedTaskList Formatted task list from the task list array
     */
    public static void printTaskList(String formattedTaskList) {
        System.out.println(formattedTaskList);
    }

    /**
     * Formatted output for confirmation of marking a task
     *
     * @param task Task
     */
    public static void printMarkTask(Task task) {
        System.out.println("Task marked as done:");
        System.out.println(task.toString());
    }

    /**
     * Formatted output for confirmation of deleting a task
     *
     * @param task Task
     */
    public static void printDeleteTask(Task task) {
        System.out.println("Task deleted:");
        System.out.println(task.toString());
    }

    /**
     * Formatted output for confirmation of unmarking a task
     *
     * @param task Task
     */
    public static void printUnmarkTask(Task task) {
        System.out.println("Task marked as not done:");
        System.out.println(task.toString());
    }

    /**
     * Formatted output for tasks found by a search term
     *
     * @param taskList List of found tasks
     */

    public static void printFindTask(ArrayList<Task> taskList) {
        String FOUND = "Matching tasks:";
        System.out.println(FOUND);

        for (Task t : taskList) {
            System.out.println(t.toString());
        }

    }
}
