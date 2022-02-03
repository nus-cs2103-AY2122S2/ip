package chatbot.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DialogBox extends VBox {
    @FXML
    private Label name;
    @FXML
    private Label message;

    public DialogBox(String name, String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/BotDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.name.setText(name);
            this.message.setText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
