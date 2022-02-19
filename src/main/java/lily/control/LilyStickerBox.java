package lily.control;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

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
import javafx.scene.layout.Region;

/**
 * This control represents a dialog box consisting of an ImageView to 
 * represent the speaker's face and a label containing text from the speaker.
 * 
 * @@author ddx-510 Referenced Dai Tianle for using Region to resize textboxes, and setting colours
 */
public class LilyStickerBox extends HBox {
    @FXML
    private ImageView displayPicture;

    private final Image PARTY_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilyyay.png")));
    private final Image CLAP_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilyclap.png")));
    private final Image MOCK_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lilymocking.png")));

    private LilyStickerBox(String emotion) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LilyStickerBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch(emotion) {
        case "party":
            displayPicture.setImage(PARTY_IMAGE);
            break;

        case "clap":
            displayPicture.setImage(CLAP_IMAGE);
            break;

        case "mock":
            displayPicture.setImage(MOCK_IMAGE);
            break;

        default:
            displayPicture.setImage(CLAP_IMAGE);
            // throw error here
        }
    }

    /**
     * Constructs a sticker box for the User
     * 
     * @return a new DialogBox from the User
     */
    public static LilyStickerBox getSticker(String emotion) {
        var stk = new LilyStickerBox(emotion);
        stk.setMinHeight(Region.USE_PREF_SIZE);
        return stk;
    }
}