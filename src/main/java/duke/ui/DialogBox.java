package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label chat;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        chat.setText(text);
        displayPicture.setImage(image);
    }

    private void changeLeftToRight() {
        ObservableList<Node> current = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(current);
        getChildren().setAll(current);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox row = new DialogBox(text, image);
        row.changeLeftToRight();
        return row;
    }

    public static DialogBox getBotDialog(String text, Image image) {
        return new DialogBox(text, image);
    }
}
