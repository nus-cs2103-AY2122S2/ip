package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIALOG_START = "Meep: ";
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        out.print("You: ");
        String fullInputLine = in.nextLine();


        return fullInputLine;
    }

    public void printLogo() {
        printMsg(DIVIDER, Messages.MESSAGE_LOGO,Messages.MESSAGE_HI,DIVIDER);
    }

    public void printBye(){
        printMsg(DIVIDER, Messages.MESSAGE_BYE,DIVIDER);
    }

    public void printMsg(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public void print(String... message) {
        out.print(DIALOG_START);
        for (String m : message) {
            out.println(m);
        }
    }

}
