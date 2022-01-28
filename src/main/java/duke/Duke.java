package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import duke.exception.DukeException;
import duke.misc.Parser;
import duke.misc.Storage;
import duke.misc.TaskList;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 9.0
 * @since 1.0
 */
public class Duke {
    private Storage storage;
    private TaskList listOfTasks;
    private Parser parser;

    public Duke() {
        storage = new Storage();
        listOfTasks = storage.initTaskList(100); // Assume there will be no more than 100 tasks
    }

    public String getResponse(String userInput) {
        try {
            return Parser.parse(userInput, listOfTasks);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public Storage getStorage() {
        return storage;
    }

}
