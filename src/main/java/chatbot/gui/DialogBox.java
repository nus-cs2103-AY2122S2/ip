package chatbot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DialogBox extends VBox {
    @FXML
    private Label name;
    @FXML
    private Label message;

    public DialogBox(String name, String message, String fxmlFile) {
        // Since DialogBox is simple, combine the MVC into one.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource(fxmlFile));
            fxmlLoader.setController(this); // Only the controller has access to the FXML objects.
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.name.setText(name);
            this.message.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}