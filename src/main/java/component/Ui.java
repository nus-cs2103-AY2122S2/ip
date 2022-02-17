package component;

import exceptions.TaskException;

/**
 * A class that belongs to the component package.
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * Generates Nexus reply.
     * @param tasks TaskList that is manipulated by the {@link component.Command} class.
     * @param storage Storage that is manipulated by the {@link component.Command} class.
     * @param userInput input that is given by the user.
     */
    public String generateNexusReply(TaskList tasks, Storage storage, String userInput) {
        try {
            return createNexusReply(tasks, storage, userInput);
        } catch (TaskException exception) {
            return exception.getMessage();
        }
    }

    /**
     * Creates Nexus reply and check for exception.
     * @param tasks TaskList that is manipulated by the {@link component.Command} class.
     * @param storage Storage that is manipulated by the {@link component.Command} class.
     * @param userInput input that is given by the user.
     * @return Nexus reply.
     * @throws TaskException Exception that is thrown if user input is in incorrect format.
     */
    private String createNexusReply(TaskList tasks, Storage storage, String userInput) throws TaskException {
        Parser parser = new Parser(userInput);
        String nexusReply = parser.executeCommand(tasks);
        storage.updateStorage(tasks);
        return nexusReply;
    }


    /**
     * Initializes the Nexus Ui.
     */
    public String initUi() {
        //Statement to be printed when Nexus is initialised.
        return "Hello! I'm Nexus" + "\n" + "What can I do for you?";
    }

}
