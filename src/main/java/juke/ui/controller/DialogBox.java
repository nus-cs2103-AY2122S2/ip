package juke.ui.controller;

import java.io.IOException;

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

/**
 * Dialog box controller for JavaFX.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView icon;

    /**
     * Constructor for a dialog box.
     *
     * @param text Main text.
     * @param icon Display icon.
     */
    public DialogBox(String text, Image icon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.icon.setImage(icon);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method that returns a user dialog box.
     *
     * @param text Main text.
     * @param icon Display icon.
     * @return Dialog box for user.
     */
    public static DialogBox getUserDialog(String text, Image icon) {
        return new DialogBox(text, icon);
    }

    /**
     * Factory method that returns a Juke dialog box.
     *
     * @param text Main text.
     * @param icon Display icon.
     * @return Dialog box for Juke.
     */
    public static DialogBox getJukeDialog(String text, Image icon) {
        var box = new DialogBox(text, icon);
        box.flip();
        return box;
    }
}
