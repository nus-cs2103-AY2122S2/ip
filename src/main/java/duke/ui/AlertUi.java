package duke.ui;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

/**
 * A class for creating alert pop-ups in the GUI.
 */
public class AlertUi {

    /**
     * Create an alert for an error.
     * @param header Header for the alert.
     * @param content Content for the alert.
     * @return Error alert.
     */
    public static Alert makeErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        return alert;
    }

    /**
     * Create an alert for a confirmation.
     * @param header Header for the alert.
     * @param content Content for the alert.
     * @return Confirmation alert.
     */
    public static Alert makeConfirmationAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
    }

    /**
     * Create an alert for an information.
     * @param header Header for the alert.
     * @param content Content for the alert.
     * @return Information alert.
     */
    public static Alert makeInformationAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
        return alert;
    }
}
