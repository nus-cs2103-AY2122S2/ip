package duke;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import duke.controller.DialogBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.command.Commands;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the main program of a bot named DockerHawker. The main program is represented
 * by a <code>Ui</code> object, a <code>TaskList</code> object, and a <code>Storage</code> object.
 * These objects serve to facilitate, parse, and execute valid inputs provided by the user
 */
public class Duke {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String inputCommand) {
        Commands c = Parser.parse(inputCommand);
        tasks = new TaskList(storage.load());
        return c.execute(tasks, ui, storage).toString();
    }
}
