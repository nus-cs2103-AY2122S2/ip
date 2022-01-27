package mnsky;

import java.util.Scanner;

import mnsky.exceptions.MnskyException;
import mnsky.task.Task;

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
        return con.nextLine();
    }

    /**
     * Prints the greeting message for the chatbot.
     */
    public void printGreeting() {
        printMnsky("Hi, I'm");
        printMnsky("MM      MM  NN      NN   SSSSSSS   KK     KK  YY      YY");
        printMnsky("MMMM  MMMM  NNNN    NN  SSSS       KK   KK      YY  YY");
        printMnsky("MM  MM  MM  NN  NN  NN    SSSSS    KKKKK          YY");
        printMnsky("MM      MM  NN    NNNN       SSSS  KK   KK        YY");
        printMnsky("MM      MM  NN      NN  SSSSSSS    KK     KK      YY");
        printMnsky("I can help!");
    }

    /**
     * Prints out the bye messages.
     */
    public void printBye() {
        printMnsky("I can help!");
        System.out.println("[MNSKY has shut itself down]");
    }

    public void printException(MnskyException e) {
        printMnsky("..?");
        System.out.println(e.getMessage());
    }

    public void printTask(Task task) {
        System.out.println(task.toString());
    }

    public void printAddedTask(Task task) {
        System.out.printf("[MNSKY added task %s to their list]\n", task.getName());
    }

    public void printDeletedTask(Task task) {
        System.out.printf("[MNSKY has deleted the task %s from the list.]\n", task.getName());
    }

    public void printList(TaskList taskList) {
        System.out.print(taskList.toString());
    }
}
