package mnsky;

import mnsky.exceptions.MnskyException;
import mnsky.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner con;

    public Ui() {
        con = new Scanner(System.in);
    }

    /**
     * Prints the passed message with "MNSKY: " before it.
     * @param msg The message that should be printed after "MNSKY: "
     */
    public void printMnsky(String msg) {
        System.out.print("MNSKY: ");
        System.out.println(msg);
    }

    /**
     * Retrieves user input from stdin.
     * @return The user's input
     */
    public String getInput() {
        System.out.println();
        System.out.print("> ");
        return this.con.nextLine();
    }

    /**
     * Prints the greeting message for the chatbot.
     */
    public void greeting() {
        this.printMnsky("Hi, I'm");
        this.printMnsky("MM      MM  NN      NN   SSSSSSS   KK     KK  YY      YY");
        this.printMnsky("MMMM  MMMM  NNNN    NN  SSSS       KK   KK      YY  YY");
        this.printMnsky("MM  MM  MM  NN  NN  NN    SSSSS    KKKKK          YY");
        this.printMnsky("MM      MM  NN    NNNN       SSSS  KK   KK        YY");
        this.printMnsky("MM      MM  NN      NN  SSSSSSS    KK     KK      YY");
        this.printMnsky("I can help!");
    }

    /**
     * Prints out the bye messages.
     */
    public void bye() {
        this.printMnsky("I can help!");
        System.out.println("[MNSKY has shut itself down]");
    }

    /**
     * Prints out the exception messages.
     * @param e The exception to be printed out.
     */
    public void printException(MnskyException e) {
        this.printMnsky("..?");
        System.out.println(e.getMessage());
    }

    /**
     * Prints out the string representation of the task.
     * @param task The task to be printed out.
     */
    public void printTask(Task task) {
        System.out.println(task.toString());
    }

    /**
     * Prints out the message for adding a new task to the list.
     * @param task The new task that was added.
     */
    public void printAddedTask(Task task) {
        System.out.printf("[MNSKY added task %s to their list]\n", task.getName());
    }

    /**
     * Prints out the message for deleting a task to the list.
     * @param task The task that was deleted.
     */
    public void printDeletedTask(Task task) {
        System.out.printf("[MNSKY deleted the task %s from the list.]\n", task.getName());
    }

    /**
     * Prints out the string representation of the whole task list.
     * @param taskList The task list.
     */
    public void printList(TaskList taskList) {
        System.out.print(taskList.toString());
    }
}
