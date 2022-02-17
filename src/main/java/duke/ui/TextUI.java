package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {

    private final Scanner in;
    private final PrintStream out;

    public TextUI() {
        this(System.in, System.out);
    }

    public TextUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String getUserCommand() {
        String currInput = in.nextLine().trim();
        while (shouldIgnore(currInput)) {
            currInput = in.nextLine().trim();
        }
        return currInput;
    }

    public void printMessage(String message) {
        String textBorder = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        String textToPrint = "\n" + textBorder + message + "\n" + textBorder + "\n";
        out.print(textToPrint);
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.print("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        out.println("\nGreetings from\n" + logo);
        out.println("How may I assist you?");
        out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
    }
}
