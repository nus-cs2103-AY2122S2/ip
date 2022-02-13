package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.data.DukeException;
import duke.task.TaskList;

public class Ui {
    private static final String DIVIDER = "    ____________________________________________________________";
    private static final String WELCOME_MSG = "Hello! I'm Yae Guuji. What can I do for you?";
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";

    private final PrintStream out;
    private String outputString;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Represents Ui class.
     *
     * @param in  input Stream.
     * @param out output Stream.
     */
    public Ui(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);
        assert sc.hasNextLine() : "No new line found";
        this.out = out;
    }

    public static String output(String text) {
        return DIVIDER + "\n    " + text;
    }

    /**
     * Returns welcome message.
     */
    public static String showWelcome() {
        System.out.println(DIVIDER);
        String logo = "    d(`･ω･´)b";
        System.out.println(logo);
        System.out.println("    Hello! I'm duke.Duke by A0221330A.\n    What can I do for you?");
        return WELCOME_MSG;
    }

    public static String getExitMessage() {
        return EXIT_MSG;
    }

    /**
     * Returns error message.
     */
    public void showLoadingError() {
        out.println(output("Sorry initialized failed"));
        outputString = "Sorry initialized failed";
    }

    /**
     * Returns exit message.
     */
    public void showExitMessage() {
        out.println(output(EXIT_MSG));
        outputString = EXIT_MSG;
    }

    /**
     * Returns file message.
     */
    public void showFileCreated() {
        out.println(output("New file has been created for first time"));
        outputString = "New file has been created for first time";
    }

    /**
     * Returns IO message.
     */
    public void showIoException() {
        out.println(output("I/O error happened"));
        outputString = "I/O error happened";
    }

    /**
     * Returns index message.
     */
    public void showInvalidIndex() {
        DukeException exception = new DukeException("invalid index.");
        out.println(output(exception.toString()));
        outputString = exception.toString();
    }

    /**
     * Returns time parse message.
     */
    public void showDateTimeParseException() {
        out.println(output("Sorry, date time format wrong, please use yyyy-MM-dd"));
        outputString = "Sorry, date time format wrong, please use yyyy-MM-dd";
    }

    /**
     * Returns specific message.
     *
     * @param s input Stream.
     */
    public void showMessage(String s) {
        assert s.equals("") : "Empty output";
        out.println(output(s));
        outputString = s;
    }

    /**
     * Returns output for GUI message.
     */
    public String getOutput() {
        return outputString;
    }

    /**
     * Returns specific message for list.
     *
     * @param tasks   input TaskList.
     * @param message input of header message.
     */
    public void displayTaskList(TaskList tasks, String message) {
        StringBuilder text = new StringBuilder();
        text.append(message);
        for (int i = 0; i < tasks.getSize(); i++) {
            String index = String.valueOf(i + 1);
            text.append("    ").append(String.format("%1$3s", index)).append(". ")
                    .append(tasks.getByIndex(i))
                    .append("\n");
        }
        text.delete(text.length() - 1, text.length());
        showMessage(text.toString());
    }
}
