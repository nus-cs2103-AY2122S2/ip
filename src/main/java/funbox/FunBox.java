package funbox;

import java.io.IOException;
import java.util.Scanner;

import funbox.task.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import funbox.util.Ui;
import funbox.util.Parser;
import funbox.util.Storage;
import funbox.util.TaskList;
import funbox.command.Command;
import funbox.exception.FunBoxExceptions;


/**
 * The FunBox class is used as the outer shell of FunBoxGear,
 * which contains the functionality of FunBox
 */
public class FunBox  {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Parser parser = new Parser();
    private Ui ui = new Ui();
    private Storage storage = new Storage(ui, parser);
    private TaskList taskList = storage.getTaskList();

    /**
     * Get input from the user and return a Result.
     */
    public Result getResponse(String input) {
        String result = "";
        boolean isExit = false;
        try {
            Command task = parser.parseCommand(input, taskList, ui);
            result = task.execute(taskList, ui, storage);
            isExit = task.isExit();
        } catch (FunBoxExceptions | IOException e) {
            result = e.getMessage();
        }

        return new Result(result, isExit);
    }
}
