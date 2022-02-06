package karen;

import karen.command.Command;

/**
 * App interface for managing between UI, Parser, Storage.
 */
public class Karen {
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor for creating Karen
     */
    public Karen() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(ui);
    }

    /**
     * Receives input from user. Executes the command based on the input and returns the result to be displayed
     * to the GUI.
     *
     * @param fullInput input by the user
     * @return Response string
     */
    public String getResponse(String fullInput) {
        String response;
        try {
            Command cmd = parser.parseInput(fullInput);
            response = cmd.execute(ui, storage);
        } catch (KarenException err) {
            response = err.toString();
        }
        return response;
    }

}
