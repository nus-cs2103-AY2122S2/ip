package duke;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.sonautil.DukeException;
import duke.sonautil.Parser;
import duke.sonautil.Storage;
import duke.sonautil.TaskList;
import duke.sonautil.Ui;


/**
 * The bot that responses to commands and collects tasks
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for object Duke
     *
     * @param filePath filePath of the tasklist
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.invalidFileMessage();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function to run Duke
     */
    public void run() {
        Ui.welcomeMessage();
        Scanner userInput = new Scanner(System.in).useDelimiter("\n");
        String userMessage = userInput.next();
        while (!userMessage.equals("bye")) {
            try {
                String[] command = new Parser().messageProcess(userMessage);
                tasks.executeCommand(command);
                storage.executeCommand(command);

            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println(Ui.dateTimeErrorMessage());
            }
            userMessage = userInput.next();
        }

        Ui.goodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").run();
    }
}
