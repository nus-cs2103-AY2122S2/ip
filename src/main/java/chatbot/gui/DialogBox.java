package chatbot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.BufferedInputStream;

public class DialogBox extends HBox {
    @FXML
    private Circle avatar;
    @FXML
    private Label message;

    public DialogBox(String fxmlFile, String avatarFile, String message) {
        // Since DialogBox is simple, combine the MVC into one.
        try {
            // Load FXML.
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            fxmlLoader.setController(this); // Only the controller has access to the FXML objects.
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

            // Set avatar.
            Image avatarImage = new Image(getClass().getResourceAsStream(avatarFile));
            avatar.setFill(new ImagePattern(avatarImage));

            // Set message.
            this.message.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}