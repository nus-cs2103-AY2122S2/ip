package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.data.DukeException;

public class Ui {
    private static final String DIVIDER = "    ____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;
    private String outputString;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public static String output(String text) {
        return DIVIDER + "\n    " + text;
    }

    public static String showWelcome() {
        String logo = "    d(`･ω･´)b";
        System.out.println(logo);
        System.out.println(output("Hello! I'm duke.Duke by A0221330A.\n    What can I do for you?\n" + DIVIDER));
        return "Hello! I'm Yae Guuji\nWhat can I do for you?";
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String readCommand() {
        String fullInputLine = in.nextLine();
        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    public void showLoadingError() {
        out.println(output("Sorry initialized failed"));
        outputString = "Sorry initialized failed";
    }

    public void showExitMessage() {
        out.println(output("Bye. Hope to see you again soon!"));
        outputString = "Bye. Hope to see you again soon!";
    }

    public void showFileCreated() {
        out.println(output("New file has been created for first time"));
        outputString = "New file has been created for first time";
    }

    public void showIoException() {
        out.println(output("I/O error happened"));
        outputString = "I/O error happened";
    }

    public void showInvalidIndex() {
        DukeException exception = new DukeException("OOPS!!! invalid index.");
        out.println(output(exception.toString()));
        outputString = exception.toString();
    }

    public void showDateTimeParseException() {
        out.println(output("Sorry, date time format wrong, please use yyyy-MM-dd"));
        outputString = "Sorry, date time format wrong, please use yyyy-MM-dd";
    }

    public void showMessage(String s) {
        assert s.equals("") : "Empty output";
        out.println(output(s));
        outputString = s;
    }

    public String getOutput() {
        return outputString;
    }
}
