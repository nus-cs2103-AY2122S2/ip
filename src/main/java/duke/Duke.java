package duke;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Chatbot that supports tracking tasks as added by the user. Created as part of CS2103T.
 *
 * @author Jet Tan
 */
public class Duke {

    /**
     * Constructor for an instance of the Chatbot.
     */
    public Duke() {
        try {
            Storage.initFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Storage.loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a String containing the response based on user input.
     *
     * @param input the user input
     * @return String containing the response based on user input
     */
    String getResponse(String input) {
        input = input.toLowerCase().trim();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            PrintStream oldStream = System.out;

            System.setOut(printStream);

            Parser.process(input);

            System.out.flush();
            System.setOut(oldStream);
            return outputStream.toString();
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
        return "Invalid input.";
    }
}