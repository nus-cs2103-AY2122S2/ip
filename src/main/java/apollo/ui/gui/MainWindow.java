package apollo.ui.gui;

import static apollo.messages.Messages.EXIT_MESSAGE;
import static apollo.messages.Messages.MISSING_GUI_IMAGE;

import java.io.InputStream;

import apollo.Apollo;
import apollo.exceptions.ApolloIoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gui gui;

    private Image userImage;
    private Image apolloImage;

    /**
     * Initialises the main window and greeting message.
     *
     * @param greeting Greeting message from Apollo.
     */
    @FXML
    public void initialize(String greeting, Gui gui) {
        this.gui = gui;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, apolloImage)
        );
        try {
            String userImagePath = "/images/user.png";
            String apolloImagePath = "/images/apollo.png";
            userImage = loadImage(userImagePath);
            apolloImage = loadImage(apolloImagePath);
        } catch (ApolloIoException e) {
            System.out.println(e.getMessage());
        }
    }

    private Image loadImage(String path) throws ApolloIoException {
        InputStream imageStream = this.getClass().getResourceAsStream(path);
        if (imageStream == null) {
            throw new ApolloIoException(MISSING_GUI_IMAGE);
        }
        return new Image(imageStream);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Apollo.getResponse(input);

        if (response.equals(EXIT_MESSAGE)) {
            gui.stop();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, apolloImage)
        );
        userInput.clear();
    }
}
