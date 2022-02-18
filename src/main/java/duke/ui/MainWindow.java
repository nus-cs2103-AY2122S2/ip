package duke.ui;

import duke.controller.DukeCommandMatcher;
import duke.controller.MarkdownParser;
import duke.utility.Storage;
import duke.utils.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private HBox userPanel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sushi.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    private final ChangeListener<Boolean> inputFocusListener = this::handleFocusedInputField;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        ValidText greetingText = new ValidText(Constants.GREETING);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Arrays.asList(greetingText), dukeImage));
        userInput.focusedProperty().addListener(this.inputFocusListener);
    }

    @FXML
    private void handleFocusedInputField(ObservableValue<? extends Boolean> observableValue,
                                         Boolean oldVal, Boolean newVal) {
        if (newVal) {
            userInput.setStyle("-fx-background-radius: 12");
            userPanel.setPadding(new Insets(4, 4, 4, 4));
            userPanel.setSpacing(12);
            userInput.setPrefWidth(250);
        } else {
            userInput.setStyle("-fx-background-radius: 30");
            userPanel.setPadding(new Insets(3, 5, 3, 25));
            userInput.setPrefWidth(250);
            userPanel.setSpacing(30.0);
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!Objects.equals(input.trim(), "")) {
            ValidText inputText = new ValidText(input);
            dialogContainer.getChildren().add(DialogBox.getUserDialog(Arrays.asList(inputText), userImage));
            handleDukeOutput(input);
            userInput.clear();
        }
    }

    private void handleDukeOutput(String input) {
        try {
            Storage database = new Storage("storage/save.txt");
            DukeCommandMatcher parser = new DukeCommandMatcher(database);
            String response = parser.commandSwitcher(input);
            MarkdownParser mdParser = new MarkdownParser(response);
            List<Markdown> markdownList = mdParser.parse();
            DialogBox db;
            if (Objects.equals(response, Constants.EXIT)) {
                ValidText byeText = new ValidText(Ui.printBye());
                db = DialogBox.getDukeDialog(Arrays.asList(byeText), dukeImage);
                userInput.setEditable(false);
                userInput.setStyle("-fx-background-color: black;");
                userInput.focusedProperty().removeListener(this.inputFocusListener);
                userInput.setPromptText("Restart!");
            } else {
                assert response != Constants.EXIT : response;
                db = DialogBox.getDukeDialog(markdownList, dukeImage);
            }
            dialogContainer.getChildren().add(db);
        } catch (Exception e) {
            InvalidText exceptionText = new InvalidText(e.getMessage());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(Arrays.asList(exceptionText), dukeImage));
        }
    }

}