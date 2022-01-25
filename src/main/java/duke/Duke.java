package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Chatbot that supports tracking tasks as added by the user. Created as part of CS2103T.
 *
 * @author Jet Tan
 */
public class Duke {
    /**
     * Driver method of the chatbot.
     */
    public static void main(String[] args) {
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
        Ui.greet();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine().toLowerCase().trim();
            try {
                Parser.process(input);
            } catch (DukeException | IOException e) {
                System.out.println(e);
            }
        }
    }
}