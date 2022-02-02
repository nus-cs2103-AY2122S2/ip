import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DukeDialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DukeDialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        boolean b = this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DukeDialogBox getUserDialog(Label l, ImageView iv) {
        return new DukeDialogBox(l, iv);
    }

    public static DukeDialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DukeDialogBox(l, iv);
        db.flip();
        return db;
    }
}