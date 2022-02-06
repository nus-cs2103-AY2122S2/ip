package duke;

import duke.commands.Command;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasklist.DukeList;
import duke.ui.Ui;


@SuppressWarnings("checkstyle:Regexp")
public class Duke {

    private Ui ui;
    private Storage storage;
    private DukeList list;

    /**
     * Creates a new Duke instance
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./src/main/data.txt");
        this.list = new DukeList(storage);
    }

    /**
     * Sends the input String to Duke to execute command, and returns the String reply from Duke
     * @param input Input from user
     * @return String reply from Duke
     */
    public String getResponse(String input) {
        Command c = DukeParser.parseInput(input);
        String response = c.execute(ui, list);
        return response;
    }

    /**
     * Returns the welcome message
     * @return Welcome message
     */
    public String printIntro() {
        return ui.showWelcome();
    }

}
