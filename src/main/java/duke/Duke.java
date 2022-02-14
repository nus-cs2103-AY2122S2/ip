package duke;


import duke.commands.Command;
import duke.controllers.Parser;
import duke.exception.DukeException;

public class Duke {

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for class
     * @param filePath The path which to save and load taskList state from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parseCommand(userInput);
            String response = c.execute(taskList, storage);
            storage.saveList();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
        //TODO: throw end exception

    }

}