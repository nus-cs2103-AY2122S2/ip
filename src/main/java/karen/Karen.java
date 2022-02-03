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
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(this.ui);
    }

    /**
     * Receives input from user. Executes the command based on the input
     * and returns the result to be displayed to the GUI.
     * @param fullInput input by the user
     * @return Response string
     */
    public String getResponse(String fullInput) {
        String response;
        try {
            Command cmd = this.parser.parseInput(fullInput);
            response = cmd.execute(this.ui, this.storage);
        } catch (KarenException err) {
            response = err.toString();
        }
        return response;
    }

}
