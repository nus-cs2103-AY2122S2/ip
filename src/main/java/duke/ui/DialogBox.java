package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    public DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        l.setTranslateY(5);
        l.setTranslateX(-10);
        l.setStyle(
                "-fx-text-fill:black;"
                + "-fx-font-size: 12;"
                + "-fx-padding: 5 10;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 0);"
                + "-fx-background-radius: 16;"
                + "-fx-background-color: lightgreen"
        );

        iv.setFitWidth(60.0);
        iv.setFitHeight(60.0);
        iv.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 0);");

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
        this.setPadding(new Insets(15, 10, 15, 10));
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip(l);
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public void flip(Label l) {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        l.setTranslateX(10);
        l.setStyle(
                "-fx-text-fill:black;"
                + "-fx-font-size: 12;"
                + "-fx-padding: 5 10;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 0);"
                + "-fx-background-radius: 16;"
                + "-fx-background-color: white"
        );
        this.getChildren().setAll(tmp);
    }
}
