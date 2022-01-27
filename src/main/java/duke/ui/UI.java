package duke.ui;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.parser.Parser;

import java.io.IOException;
import java.util.Scanner;

/**
 * Displays messages for the user and takes in their input.
 */
public class UI {

    /**
     * Prints the welcome message when the Chat Bot runs.
     */
    public void printWelcome() {
        System.out.println("Welcome to Chi task bot nyan!!!");
    }

    /**
     * Takes in consecutive user inputs and prints a response to each one.
     *
     * @param tl The TaskList instance storing tasks locally in the program.
     * @param sge The Storage instance storing tasks on the hard-disk.
     * @param prs The Parser instance interpreting the message sent by the user.
     */
    public void requestInput(TaskList tl, Storage sge, Parser prs) {
        Scanner sn = new Scanner(System.in);
        String msg = sn.nextLine();
        while (!msg.equals("bye")) {
            try {
                System.out.println("=========================================");
                System.out.println(prs.processMessage(msg,tl,sge));
                System.out.println("=========================================");
            } catch (ChiException e) {
                this.printErrorMsg(e.toString());
            } catch (IOException e) {
                this.printErrorMsg("Something went wrong while storing task to file nyaan!");
            }
            msg = sn.nextLine();
        }
        sn.close();
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void printGoodbye() {
        System.out.println("Leaving already? Alright... see you soon nyan!!!");
    }

    /**
     * Customizes and prints the error message back to the user.
     *
     * @param errMsg The error message from an invalid user input.
     */
    public void printErrorMsg(String errMsg) {
        System.out.println("=V.V= " + errMsg + " =V.V=");
    }

}
