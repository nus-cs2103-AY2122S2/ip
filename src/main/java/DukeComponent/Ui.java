package DukeComponent;

import Exceptions.TaskException;

/**
 * A class that belongs to the DukeComponent Package.
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * Runs the Ui for the Duke program.
     * @param tasks TaskList that is manipulated by the {@link DukeComponent.Command} class.
     * @param storage Storage that is manipulated by the {@link DukeComponent.Command} class.
     * @param userInput input that is given by the user.
     */
    public String run(TaskList tasks, Storage storage, String userInput) {
        try {
            Parser p = new Parser(userInput);
            String s = p.executeCommand(tasks);
            storage.write(tasks);
            return s;
        } catch (TaskException e) {
            return e.getMessage();
        }
    }


    /**
     * Initialise the Duke Ui.
     */
    public String initUi() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }


}
