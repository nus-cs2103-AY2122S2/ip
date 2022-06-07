package duke.ui;

import java.util.ArrayList;

/**
 * This class acts as a interface between the user and the application.
 */
public class Ui {
    private final ArrayList<String> buffer;

    /**
     * Class constructor.
     */
    public Ui() {
        buffer = new ArrayList<>();
    }

    /**
     * Adds the message from the application to buffer.
     *
     * @param message message to be shown to user.
     */
    public void showMessage(String message) {
        buffer.add(message);
    }

    /**
     * Adds the error message from the application to buffer.
     *
     * @param message error message to be shown to user.
     */
    public void showErrorMessage(String message) {
        buffer.add("Uh oh! " + message + " :(");
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < buffer.size(); i++) {
            stringBuilder.append(buffer.get(i));
            stringBuilder.append("\n");
        }
        buffer.clear();
        return stringBuilder.toString();
    }
}
