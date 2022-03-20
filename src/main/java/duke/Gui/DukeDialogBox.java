package duke.Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * DukeDialogBox is a class that formats the Duke and User dialog box
 */
public class DukeDialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DukeDialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setMinHeight(Region.USE_PREF_SIZE);
        boolean b = this.getChildren().addAll(text, displayPicture);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10; -fx-padding: 10;");
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10; -fx-padding: 10;");
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DukeDialogBox getUserDialog(Label l, ImageView iv) {
        return new DukeDialogBox(l, iv);
    }

    public static DukeDialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DukeDialogBox(l, iv);
        db.setMinHeight(Region.USE_PREF_SIZE);
        db.flip();
        return db;
    }
}