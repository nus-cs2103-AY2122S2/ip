package duke;

import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;


/**
 * Duke Chat Bot that allows users to track tasks.
 *
 * @author Benjamin Koh
 */

public class Duke  {

    private static TaskList taskList;

    /**
     * Main method. Takes in user input and updates list, interacts with user
     *
     */

    public Duke() {
        Ui.welcome();
        taskList = new TaskList();
        try {
            Storage.initialise();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Storage.loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    String getResponse(String input) {
        input = input.toLowerCase().trim();
        assert (!input.equals("")): "You need to enter an input.";
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            PrintStream oldStream = System.out;

            System.setOut(printStream);

            Parser.parse(input, taskList);

            System.out.flush();
            System.setOut(oldStream);
            return outputStream.toString();
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
        return "Invalid input.";
    }

}
