package saitama;

import saitama.commands.Command;
import saitama.exceptions.SaitamaException;
import saitama.parser.Parser;
import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

public class Saitama {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a chatbot that can keep track of your tasks.
     *
     * @param filePath The file path of the stored task list.
     */
    public Saitama(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public String getResponse(String input) throws SaitamaException {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui, storage);
    }
}


