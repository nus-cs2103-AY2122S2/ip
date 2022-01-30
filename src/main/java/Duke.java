import java.io.IOException;
import java.util.Scanner;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;


/**
 * Class for the Duke app.
 */
public class Duke {

    private Storage storage;
    private Ui ui;

    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for the duke class.
     */
    public Duke() {

        ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
    }

    /**
     * Method to start the duke app.
     * @throws DukeException Exception thrown by invalid command.
     * @throws IOException Exception thrown by invalid file command.
     */
    public void startDuke() throws DukeException, IOException {
        storage.loadData();
        ui.displayHelloMessage();
        String command = sc.next();
        String description = sc.nextLine();
        while(!command.equalsIgnoreCase("Bye")) {
            ui.executeCommand(command, description);
            command = sc.next();
            if(!command.equalsIgnoreCase("Bye")) {
                description = sc.nextLine();
            }
            storage.saveData();
        }
        ui.displayByeMessage();
    }

    /**
     * Main command to run the app.
     * @param args Argument for the function.
     * @throws DukeException Exception thrown because of invalid command.
     * @throws IOException Exception thrown because of invalid file input.
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.startDuke();
    }




    }
