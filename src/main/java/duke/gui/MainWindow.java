package duke.gui;

import duke.command.Command;

import duke.ui.Duke;
import duke.ui.DukeException;
import duke.ui.Parser;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.Desktop;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/Images/profile.png")
            , 200, 200, false, false);
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/Images/red.png")
            , 200, 200, false, false);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hello!! I am Friendly Friend, your humble personal chatbot. "
                + "What can I do for you?";
        String cannotSeeFullListAdvice = " PS - If you cannot see your tasks, please go to " +
                "help -> my tasks";
        String helpMessage = "If you are new to this app, please type help to see full list of " +
                "commands";
        String fileLocationWarning = "Please create a file with the path:\n~/FFdata/tasks.txt" +
                " or there will be errors using the app.";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage + cannotSeeFullListAdvice, dukeImage),
                DialogBox.getDukeDialog(helpMessage, dukeImage),
                DialogBox.getDukeDialog(fileLocationWarning, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws URISyntaxException, IOException, InterruptedException {
        String input = userInput.getText();
        try {
            Command c = Parser.parse(input);
            String response = c.execute(duke.getTasks(), duke.getUi(), duke.getStorage());
            if (response.equals("Help has arrived!")) {
                provideAboutFunctionality();
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

            if (response.equals("Bye! Hope to see you again soon!")) {
                Platform.exit();
            }

        } catch (DukeException dukeError) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(dukeError.getMessage(), dukeImage)
            );
        } finally {
            userInput.clear();
        }
    }

    /**
     * Handle action related to "About" menu item.
     */
    @FXML
    private void handleAboutAction() throws URISyntaxException, IOException {
        provideAboutFunctionality();
    }

    /**
     * Handle action related to input (in this case specifically only responds to
     * keyboard event CTRL-A).
     *
     * @param event Input event.
     */
    @FXML
    private void handleKeyInput(final InputEvent event) throws URISyntaxException, IOException {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                provideAboutFunctionality();
            }
        }
    }

    /**
     * Handles action to open file containing all tasks recorded so far
     */
    @FXML
    private void handleMyTasks() throws IOException {
        openTaskFile();
    }

    /**
     * Leads the user away to the help page
     */
    private void provideAboutFunctionality() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://NicsunXnus.github.io/ip/"));
    }

    /**
     * Opens task file in directory C:/repos/ip/data/tasks.txt
     */
    private void openTaskFile() throws IOException {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "FFdata", "tasks.txt");
        Desktop.getDesktop().open(filePath.toFile());
    }


}