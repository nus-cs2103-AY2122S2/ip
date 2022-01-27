package duke.main;

import duke.exception.DukeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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

    public Ui() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        message = new StringBuilder();
    }

    public void appendMessage(String message) {
        this.message.append(message);
    }

    String readFullCommand() throws DukeException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new DukeException(DukeException.ERROR_IO_Input);
        }
    }

    void showResponse() {
        if (message.length() < 1) {
            return;
        }
        String respondMessage = message.toString();
        message.setLength(0);
        showMessageInBorder(respondMessage);
    }

    public void showErrorMessage(String errorMessage) {
        showMessageInBorder(errorMessage);
    }

    void showQuestionPrompt() {
        showMessageOutsideBorder(QUESTION_MESSAGE);
    }

    void showLoadingError() {
        showMessageOutsideBorder("Unable to read saved task from file.\nStarting with a new task list..");
    }

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
