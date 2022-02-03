package duke.ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.tasklist.DukeList;

public class Ui {

    private final String Greeting = "\nDuke: Hello! I'm Duke\n      What can I do for you?\n";
    private final String Closing = "\nDuke:  Bye. Hope to see you again soon!";

    /**
     * Prints greeting message to the console.
     */
    public void showWelcome() {
        System.out.println(Greeting);
    }

    /**
     * Prints closing message to the console.
     */
    public void showClosing() {
        System.out.println(Closing);
    }

    /**
     * Prints the list of Tasks to the console.
     * @param a DukeList to be printed
     */
    public void printList(DukeList a) {
        try {
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
            w.write("\nDuke:\nHere are the tasks in your list:\n");
            w.write(a.toString());
            w.newLine();
            w.flush();
        } catch (IOException e) {
            System.out.println("Error when printing list");
        }
    }

    /**
     * Reads input from the console.
     * @return String of input from console
     */
    public String readInput() {
        Scanner s = new Scanner(System.in);
        String ans = "";
        try {
            ans = s.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Input a command!");
        }
        return ans;
    }

}
