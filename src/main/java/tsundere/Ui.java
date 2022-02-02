package tsundere;

import java.util.Scanner;

/**
 * Display text by printing
 */
public class Ui {

    private static final String LINES = "------------------------------------------------------------------------";

    /**
     * Read the user input
     *
     * @return user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        System.out.println(LINES);
        return userInput;
    }

    /**
     * Wrap and print input string with Lines
     *
     * @param s Wrapped string
     */
    public void wrapText(String s) {
        System.out.println(LINES);
        System.out.println(s);
        System.out.println(LINES);
    }
    /**
     * Print the first statement when the program starts
     */
    public void showIntro() {
        wrapText("Hmph, it's you again...\n");
    }

    /**
     * wrap and show the error message
     *
     * @param e wrapped error message
     */
    public void showErrorMsg(String e) {
        wrapText(e);
    }

    /**
     * wrap the message for when save file cannot be read
     */
    public void showLoadingError() {
        wrapText("Save File Corrupted... some task(s) can't be read!");
    }
}


