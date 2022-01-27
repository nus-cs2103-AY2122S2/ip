package duke.main;

import duke.exception.DukeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Ui is a utility class for reading and writing input to the Duke program.
 * <p/>
 * Ui is a wrapper around BufferedReader and PrintWriter that reads from System.in InputStream
 * and writes to System.out OutputStream.
 * </p>
 * An Ui instance also stores the message to be shown before showResponse flushes the message to the outputsteam.
 */
public class Ui {
    public static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";
    public static final String ADD_MESSAGE = "Charizard is ready to burn task:";
    public static final String REMOVE_MESSAGE = "Charizard got tired of waiting and deleted this task";
    public static final String MARK_MESSAGE = "Charizard breathe out fire and burned the task.";
    public static final String UNMARK_MESSAGE = "Oh no! The task was not burnt completely!";
    private static final int BORDER_LENGTH = 80;
    private static final String GREET_MESSAGE
            = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n" +
            "Which task shall we burn today?";
    private static final String QUESTION_MESSAGE = "What should Charizard do next?";
    private BufferedReader reader;
    private PrintWriter writer;
    private StringBuilder message;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        this.message = new StringBuilder();
    }

    /**
     * Appends the message to the stored message in Ui.
     *
     * @param message The message to be appended.
     */
    public void appendMessage(String message) {
        this.message.append(message);
    }

    /**
     * Returns the raw input command typed by the user.
     *
     * @return The raw input command typed by the user.
     * @throws DukeException If unable to read from the input.
     */
    String readFullCommand() throws DukeException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new DukeException(DukeException.ERROR_IO_Input);
        }
    }

    /**
     * Flushes the stored message in the Ui and displays the message to the user.
     */
    void showResponse() {
        if (message.length() < 1) {
            return;
        }
        String respondMessage = message.toString();
        message.setLength(0);
        showMessageInBorder(respondMessage);
    }

    /**
     * Displays an error message to the user. This method does not store the message in the Ui
     * instance, and instead immediately displays the error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showErrorMessage(String errorMessage) {
        showMessageInBorder(errorMessage);
    }

    /**
     * Displays a prompt to the user to signify request for user input.
     * This method does not store the message in the Ui instance,
     * and instead immediately displays the error message.
     */
    void showQuestionPrompt() {
        showMessageOutsideBorder(QUESTION_MESSAGE);
    }

    /**
     * Immediately displays loading error to the user.
     */
    void showLoadingError() {
        showMessageOutsideBorder("Unable to read saved task from file.\nStarting with a new task list..");
    }

    /**
     * Immediately displays the welcome message to the user.
     */
    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showMessageOutsideBorder("Hello from\n" + logo);
        showBorder(false);
        showMessageInBorder(GREET_MESSAGE);
        showBorder(true);
    }

    /**
     * Immediately draws the border surrounding the response to the user.
     *
     * @param haveEmptyLineAfter If true, displays an empty line after the border.
     */
    void showBorder(boolean haveEmptyLineAfter) {
        drawBorder(BORDER_LENGTH);
        if (haveEmptyLineAfter) {
            showMessageOutsideBorder("");
        }
    }

    private void showMessageOutsideBorder(String message) {
        writer.println(message);
        writer.flush();
    }

    private void showMessageInBorder(String message) {
        String[] lines = message.split("\n");
        for (String line: lines) {
            writer.println("    " + line);
        }
        writer.flush();
    }

    private void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            writer.print("*");
        }
        ;
        writer.println("");
        writer.flush();
    }
}
