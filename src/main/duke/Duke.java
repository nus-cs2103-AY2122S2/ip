package main.duke;

import main.duke.commands.Command;
import main.duke.io.Parser;
import main.duke.io.Storage;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String dirname, String filename) {
        this.storage = new Storage(dirname, filename);
        this.taskList = new TaskList();
        this.ui =  new Ui();
        this.parser = new Parser();
        this.storage.readFile(this.taskList);
    }

    public String getResponse(String input){
        try {
            Command command = this.parser.parse(input);
            String reply = command.runCommand(this.ui, this.taskList);
            this.storage.writeFile(this.taskList);
            assert (reply != null);
            return reply;
        }
        catch (DukeException e) {
            return e.getMessage();
        }
    }
}
