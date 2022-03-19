package duke.ui;

import java.util.Scanner;

/**
 * Interactions with the user - greetings etc, command line arguments.
 * Abstract the handling of the command line arguments to Parser.java.
 */
public class Ui {

    // take note Ui is instantiated in the first line of Duke.java
    /**
     * I believe that this is going to cause the greeting,
     * then we can start accepting the command line arguments from there on.
     * I think it satisfies that once it is instantiated, we just go on to the
     * call the main method of Ui, to be able to accept CLA.
     */
    private Scanner s;
    public String lineBreak = "---------------";
    public String greeting = "FUNNY FELLA WEIIIIII! \nHow can I help Mr Singhhhhh?";
    public String bye = lineBreak + "\n Byeeeee, come back again ah!\n" + lineBreak;
    public String lineIntro = "Nah, here's your list";
    public String markMessage = "Power la Mr Bosssssss, mark alr bro!";
    public String unmarkMessage = "No probs bro, unmarked already!";

    /**
     * Ui's constructor
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Call for the next input into Scanner.
     * 
     * @return String of next input, whole line.
     */
    public String nextInput() {
        return s.nextLine();
    }

    /**
     * Show loading error method, meaning we need to show the error that theres
     * nothing in the local storage.
     */
    public void showLoadingError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
