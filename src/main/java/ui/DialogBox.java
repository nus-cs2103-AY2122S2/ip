package ui;

import java.io.IOException;

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

/** Constructs DialogBox and controls its formatting in the Java GUI. */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs the DialogBox.
     * @param message String to be stored in Label of the DialogBox.
     * @param image Image to be stored in the ImageView of the DialogBox.
     */
    public DialogBox(String message, Image image, String role) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        displayPicture.setImage(image);

        // @@author MonthPython28-reused
        // Reused from https://github.com/jonfoocy/ip/blob/master/src/main/java/DialogBox.java
        if (role.equals("User")) {
            dialog.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(20.0),
                    new Insets(0))));
        } else {
            dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(20.0),
                    new Insets(0))));
        }
    }

    /** Flips the dialog box such that the ImageView is on the left and text on the right. */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String message, Image image, String role) {
        return new DialogBox(message, image, role);
    }

    public static DialogBox getDukeDialog(String message, Image image, String role) {
        DialogBox dialogBox = new DialogBox(message, image, role);
        dialogBox.flip();
        return dialogBox;
    }
}
