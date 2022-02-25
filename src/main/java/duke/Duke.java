package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;


/**
 * Class for the duke.Duke app.
 */
public class Duke {

    private Storage storage;
    private Ui ui;

    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor for the duke class.
     */
    public Duke() throws IOException {

        ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        storage.loadData();
    }



    /**
     * Method to start the duke app.
     * @throws DukeException Exception thrown by invalid command.
     * @throws IOException Exception thrown by invalid file command.
     */
    public String startDuke(String input) throws DukeException, IOException {
        StringBuilder str = new StringBuilder();

        //assert false;
        //ui.displayHelloMessage(str);
        String[] commandAndDescription = input.split(" ",2);
        String command = commandAndDescription[0];

        String description = "";
        if (commandAndDescription.length > 1) {
            description = commandAndDescription[1];
        }
        return ui.executeCommand(command, description, str);
//        while(!command.equalsIgnoreCase("Bye")) {
//            ui.executeCommand(command, description, str);
//            //input = sc.nextLine();
////            if(!command.equalsIgnoreCase("Bye")) {
////                description = sc.nextLine();
////            }
//            storage.saveData();
//        }
//        ui.displayByeMessage();

    }

    /**
     *
     * @param input the input from the user
     * @return the response by the program
     * @throws DukeException
     */

    public String getResponse(String input) throws DukeException, IOException {
        try {

            return startDuke(input);
        }
        catch (DukeException e) {
            return e.getMessage();
        }

    }


    /**
     * Main command to run the app.
     * @param args Argument for the function.
     * @throws DukeException Exception thrown because of invalid command.
     * @throws IOException Exception thrown because of invalid file input.
     */
//    public static void main(String[] args) throws DukeException, IOException {
//        duke.Duke duke = new duke.Duke();
//        duke.startDuke();
//    }




    }
