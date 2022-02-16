package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String l, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(l);
        displayPicture.setImage(iv);


        Circle circle = new Circle(50,50,50);
        displayPicture.setClip(circle);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        dialog.setTranslateX(-dialog.getTranslateX());
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image iv) {
        return new DialogBox(text, iv);
    }

    public static DialogBox getDukeDialog(String text, Image iv) {
        var db = new DialogBox(text, iv);
        db.flip();
        return db;
    }
}