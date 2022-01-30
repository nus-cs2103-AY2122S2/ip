import java.io.IOException;
import java.util.Scanner;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;


public class Duke {

    private Storage storage;
    private Ui ui;

    private Scanner sc = new Scanner(System.in);

    public Duke() {

        ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
    }

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
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.startDuke();
    }




    }
