package stevie.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import stevie.Stevie;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane dialogScrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button runButton;

    private Stevie stevie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/stock1.jpeg"));
    private Image stevieImage = new Image(this.getClass().getResourceAsStream("/images/stock2.jpg"));

    @FXML
    public void initialize() {
        dialogScrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setStevie(Stevie stevie) {
        this.stevie = stevie;
        showGreetings();
    }

    private void showGreetings() {
        String logo = "  .--.--.       ___\n"
                + " /  /    '.   ,--.'|_                        ,--,\n"
                + "|  :  /`. /   |  | :,'                     ,--.'|\n"
                + ";  |  |--`    :  : ' :                .---.|  |,\n"
                + "|  :  ;_    .;__,'  /     ,---.     /.  ./|`--'_       ,---.\n"
                + " \\  \\    `. |  |   |     /     \\  .-' . ' |,' ,'|     /     \\\n"
                + "  `----.   \\:__,'| :    /    /  |/___/ \\: |'  | |    /    /  |\n"
                + "  __ \\  \\  |  '  : |__ .    ' / |.   \\  ' .|  | :   .    ' / |\n"
                + " /  /`--'  /  |  | '.'|'   ;   /| \\   \\   ''  : |__ '   ;   /|\n"
                + "'--'.     /   ;  :    ;'   |  / |  \\   \\   |  | '.'|'   |  / |\n"
                + "  `--'---'    |  ,   / |   :    |   \\   \\ |;  :    ;|   :    |\n"
                + "               ---`-'   \\   \\  /     '---\" |  ,   /  \\   \\  /\n"
                + "                         `----'             ---`-'    `----'\n";
        String greetings = "Hello I'm \n" + logo
                + "Tell me about your upcoming activities!\n"
                + "Input \"help\" for instructions.";
        dialogContainer.getChildren().addAll(
                DialogBox.getStevieDialog(greetings, stevieImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Stevie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stevie.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStevieDialog(response, stevieImage)
        );
        userInput.clear();
        if (response.equals("Bye! Hope to see you again!")) {
            exit();
        }
    }
    private void exit() {
        System.exit(0);
    }
}
