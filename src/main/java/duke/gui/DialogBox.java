package duke.gui;

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
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor to create a Dialog Box for Duke chatbot.
     * @param text text contained in the dialog box
     * @param img display picture of the sender
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        // setClipView(displayPicture);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #FFFFFF; -fx-line-spacing: 1.5; -fx-label-padding: 15; "
                + "-fx-border-radius: 15; -fx-border-width: 1.5; -fx-background-radius: 20; "
                + "-fx-background-insets: 0.5; -fx-border-color: #000;");
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    private void setClipView(ImageView img) {
        double width = img.getFitWidth();
        double height = img.getFitHeight();
        Circle circle = new Circle(width / 2, height / 2, Math.min(height, width) / 2);
        img.setClip(circle);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialogBox = new DialogBox(text, img);
        userDialogBox.dialog.setStyle("-fx-font-weight: bold; -fx-background-color: #e0e0e0; -fx-border-color: #000;"
                + "-fx-line-spacing: 1.5; -fx-label-padding: 15; -fx-border-radius: 15; -fx-border-width: 1.5;"
                + "-fx-background-radius: 20; -fx-background-insets: 0.5;");
        return userDialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setClipView(db.displayPicture);
        return db;
    }
}
