package duke.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * A custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayPicture.setClip(new Circle(50, 50, 50));
        this.setBackground(new Background(new BackgroundFill(Color.AQUA,
                CornerRadii.EMPTY, Insets.EMPTY)));

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String l, Image iv) {
        DialogBox db = new DialogBox(l, iv);

        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW,
                CornerRadii.EMPTY, Insets.EMPTY)));
        return db;
    }



//    public DialogBox(Label l, ImageView iv) {
//        text = l;
//        displayPicture = iv;
//
//        text.setWrapText(true);
//
//        // Display Picture Settings
//        displayPicture.setFitWidth(100.0);
//        displayPicture.setFitHeight(100.0);
//        displayPicture.setClip(new Circle(50, 50, 50));
//
//        this.setSpacing(10); // Spacing between elements
//        this.setPadding(new Insets(10)); // Padding for each dialog box
//
//        // Setting colour
//        this.setBackground(new Background(new BackgroundFill(Color.AQUA,
//                CornerRadii.EMPTY, Insets.EMPTY)));
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, displayPicture);
//    }
//

}
