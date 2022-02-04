package main;

import Control.DialogBox;
import duke.*;
import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;
import java.io.IOException;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Represents the Duke bot
 */
public class Duke {

    public Storage storage;
    public TaskList tasklist;
    public Ui ui;

//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private boolean isExit = false;
    private static final String FILEPATH =  "./duke.txt";


    public Duke() throws DukeException {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(FILEPATH);
        try {
            tasklist = new TaskList(storage.getAllTasks());
        } catch (DukeException e) {
            tasklist = new TaskList();
        } catch (IOException e) {
            System.exit(0);
        } catch (DukeDeadlineException e) {
            ui.showError(e.getMessage());
            tasklist = new TaskList();
        }
    }

    /* You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.clearPrint();
            Command c = Parser.parse(input);
            c.execute(tasklist, ui, storage);
            isExit = c.isExit();
            return ui.sendPrint();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } catch (DukeDeadlineException e) {
            ui.showError(e.getMessage());
        } catch (DukeEventException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
        return ui.sendPrint();
    }

//    public static void main(String[] args) throws DukeException {
//        new Duke(FILEPATH).run();
//    }

    public void run() {
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (DukeDeadlineException e) {
                ui.showError(e.getMessage());
            } catch (DukeEventException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
