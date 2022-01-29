package duke.ui;

import duke.tasklist.DukeList;
import duke.task.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    private String NAME = "\nDuke: ";
    private String GREETING = "\nDuke: Hello! I'm Duke\n      What can I do for you?\n";
    private String CLOSING = "\nDuke:  Bye. Hope to see you again soon!";

    /**
     * Prints greeting message to the console.
     */
    public void showWelcome(){
        System.out.println(GREETING);
    }

    /**
     * Prints closing message to the console.
     */
    public void showClosing(){
        System.out.println(CLOSING);
    }

    /**
     * Prints the list of Tasks to the console.
     * @param a DukeList to be printed
     */
    public void printList(DukeList a){
        try {
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
            w.write("\nDuke:\nHere are the tasks in your list:\n");
            w.write(a.toString());
            w.newLine();
            w.flush();
        } catch(IOException e) {
            System.out.println("Error when printing list");
        }
    }

    /**
     * Reads input from the console.
     * @return String of input from console
     */
    public String readInput(){
        Scanner s = new Scanner(System.in);
        String ans = "";
        try{
            ans = s.nextLine();
        } catch(NoSuchElementException e) {
            System.out.println("Input a command!");
        }
        return ans;
    }

}
