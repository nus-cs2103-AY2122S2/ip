package duke.gui;

import duke.UserInterface;

/**
 * Interfaces MainWindow to Duke's command processing logic.
 */
public class GuiDukeInterface {

    private UserInterface userInterface;

    public GuiDukeInterface(UserInterface ui) {
        userInterface = ui;
    }

    /**
     * Returns Duke's greeting.
     *
     * @return String of Duke's greeting.
     */
    protected String handleGuiStart() {
        return userInterface.guiStart();
    }

    /**
     * Returns appropriate String response from UserInterface class.
     *
     * @param input Raw text input from user.
     * @return Duke's response.
     */
    protected String getResponse(String input) {
        return userInterface.uiParse(input);
    }
}
