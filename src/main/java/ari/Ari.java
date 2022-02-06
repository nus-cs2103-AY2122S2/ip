package ari;

import ari.command.Command;
import ari.parser.Parser;
import ari.storage.Storage;
import ari.tasks.TaskList;
import ari.ui.Ui;

/**
 * Represents Ari functions
 */
public class Ari {
    private String filePath;

    private Parser parser;
    private Storage storage;
    private TaskList toDoList;
    private Ui ui;

    /**
     * Constructor of Ari
     *
     * @param path path to location of save file
     */
    public Ari(String path) {
        filePath = path;

        parser = new Parser();
        storage = new Storage();
        storage.setFile(filePath);
        ui = new Ui();

        toDoList = storage.load();
    }

    public String getResponse(String userInput) {
        Command command = parser.parse(userInput);
        command.setTaskList(toDoList);
        storage.save(toDoList);
        return command.execute();
    }

    public void save() {
        storage.save(toDoList);
    }
}
