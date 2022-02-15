package li.zhongfu.cs2103.chatbot.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import li.zhongfu.cs2103.chatbot.Duke;
import li.zhongfu.cs2103.chatbot.types.message.ChatMessage;
import li.zhongfu.cs2103.chatbot.types.message.Message;
import li.zhongfu.cs2103.chatbot.types.message.QuitMessage;
import li.zhongfu.cs2103.chatbot.types.message.SystemMessage;

/**
 * The interface between Duke and the user.
 */
public class TextUi {
    private static final String HLINE = "____________________________________________________________";

    private static final String MOTD = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private PrintStream output;
    private BufferedReader br;
    private Duke duke;

    /**
     * Creates a new UserInterface instance with the given InputStream and PrintStream as input and output.
     *
     * @param input the InputStream to read user input from
     * @param output the PrintStream to output messages to
     */
    public TextUi(Duke duke) {
        output = System.out;
        br = new BufferedReader(new InputStreamReader(System.in));
        this.duke = duke;
    }

    /**
     * Initializes the Duke instance and runs the REPL.
     *
     * @throws IOException if an error occurs e.g. while reading user input or loading saved tasks
     */
    public void run() throws IOException {
        printMotd();
        List<Message> messages = duke.init();

        try {
            handleMessages(messages);
        } catch (QuitException e) {
            // should never happen here
            throw new RuntimeException("Unexpected QuitMessage received during init");
        }

        loop();
    }

    /**
     * Prints the message of the day.
     */
    private void printMotd() {
        this.output.println(MOTD); // extra newline
    }

    /**
     * Prints the given lines in a dialog.
     *
     * Each array element represents a new line, and each element will additionally be split into multiple
     * lines if a newline character is present.
     *
     * @param lines an array containing the lines to print, split by lines
     */
    private void printDialog(String[] lines) {
        this.output.println("    " + HLINE);
        for (String line : lines) {
            if (line.contains("\n")) {
                String[] sublines = line.split("\n");
                for (String subline : sublines) {
                    this.output.println("    " + subline);
                }
            } else {
                this.output.println("    " + line);
            }
        }
        this.output.println("    " + HLINE + "\n");
    }

    /**
     * Prints the given string in a dialog.
     *
     * @param line the string to be printed
     */
    private void printDialog(String line) {
        this.printDialog(line.split("\n"));
    }

    /**
     * Runs the REPL loop for Duke.
     *
     * @throws IOException if an error occurs e.g. while reading user input or loading saved tasks
     */
    private void loop() throws IOException {
        while (true) {
            String input = br.readLine();
            List<Message> messages = duke.handleInput(input);
            try {
                handleMessages(messages);
            } catch (QuitException e) {
                break;
            }
        }
    }

    /**
     * Handles messages received from Duke: formats and prints messages out depending on message type, and exits the
     * program if a {@code QuitMessage} is received.
     *
     * @param messages the messages to be handled
     * @throws QuitException used to signal that the application should exit if a {@code QuitMessage} is received
     */
    private void handleMessages(List<Message> messages) throws QuitException {
        for (Message msg : messages) {
            if (msg instanceof ChatMessage) {
                printDialog(msg.getMessage());
            } else if (msg instanceof SystemMessage) {
                output.println(msg.getMessage());
            } else if (msg instanceof QuitMessage) {
                throw new QuitException(); // shouldn't be printing out any messages after this
            } else {
                throw new RuntimeException("Got unknown Message type");
            }
        }
    }
}

class QuitException extends Exception {
}
