package myboss;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Represents a user interface.
 */
public class Ui {
    static final String INDENT = "    ";
    private Scanner scanner;

    /**
     * Creates a Ui Object with a scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs the greeting message of the chatbot.
     */
    public void outputGreeting() {
        myBossOutput(" Hello! I'm YourBoss.\n" +
                INDENT +
                " What can you do for me?");
    }

    /**
     * Outputs the farewell message of the chatbot.
     */
    public void outputFarewell() {
        myBossOutput(" Bye. Hope I never see you again!");
        scanner.close();
    }

    /**
     * Returns the next line of user input.
     *
     * @return next line of user input.
     */
    public String getUserCmd() {
        return scanner.nextLine();
    }

    /**
     * Outputs the given task list.
     *
     * @param taskList the specified list of tasks.
     */
    public void outputTaskList(ArrayList<Task> taskList) {
        StringBuilder tempOut = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            tempOut.append(" " + INDENT).append((i + 1) + ".").append(taskList.get(i).toString());
        }
        hLineBreak();
        printlnWithIndent(" Here are the tasks in your list:");
        System.out.print(tempOut); // newline in tempOut
        hLineBreak();
    }

    /**
     * Output the given task as marked.
     *
     * @param task the specified task marked as done.
     */
    public void outputMarked(Task task) {
        myBossOutput(task.markAsDone(true));
    }

    /**
     * Output the given task as unmarked.
     *
     * @param task the specified task marked as not done.
     */
    public void outputUnmarked(Task task) {
        myBossOutput(task.markAsDone(false));
    }

    /**
     * Outputs a formatted line break.
     */
    static void hLineBreak() {
        System.out.println(INDENT +"____________________________________________________________");
    }

    /**
     * Outputs the given input with the predefined indent.
     *
     * @param input input to print.
     */
    static void printlnWithIndent(String input) {
        System.out.print(INDENT);
        System.out.println(input);
    }

    /**
     * Output the given string in a standardized format.
     *
     * @param output string to be outputted.
     */
    static void myBossOutput(String output) {
        hLineBreak();
        printlnWithIndent(output);
        hLineBreak();
    }

    /**
     * Outputs the predefined output when adding a task.
     *
     * @param task task added.
     */
    void addTaskOutput(Task task) {
        hLineBreak();
        printlnWithIndent(" Got it. I've added this task:");
        System.out.print(INDENT + "   " + task);
        printlnWithIndent(" Now you have "+ TaskList.getSize() +" tasks in the list.");
        hLineBreak();
    }

    /**
     * Outputs the predefined output when deleting a task.
     *
     * @param task task deleted.
     */
    void outputDeleteTask(Task task) {
        hLineBreak();
        printlnWithIndent(" Noted. I've removed this task:");
        System.out.print(INDENT + "   " + task);
        printlnWithIndent(" Now you have "+ TaskList.getSize() +" tasks in the list.");
        hLineBreak();
    }
}
