package component;

import exceptions.TaskException;

/**
 * A class that belongs to the DukeComponent Package.
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * @param tasks TaskList that is manipulated by the {@link component.Command} class.
     * @param storage Storage that is manipulated by the {@link component.Command} class.
     * @param userInput input that is given by the user.
     */
    public String generateDukeReply(TaskList tasks, Storage storage, String userInput) {
        try {
            return createDukeReply(tasks, storage, userInput);
        } catch (TaskException exception) {
            return exception.getMessage();
        }
    }

    private String createDukeReply(TaskList tasks, Storage storage, String userInput) throws TaskException {
        Parser parser = new Parser(userInput);
        String dukeReply = parser.executeCommand(tasks);
        storage.addTask(tasks);
        return dukeReply;
    }


    /**
     * Initialise the Duke Ui.
     */
    public String initUi() {
        //Statement to be printed when duke is initialised.
        return "Hello! I'm Dodo" + "\n" + "What can I do for you?";
    }

}
