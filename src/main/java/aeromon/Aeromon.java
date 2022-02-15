package aeromon;

import aeromon.command.Command;

import java.util.ArrayList;

//@@author cashewnade-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html

/**
 * Aeromon class that runs the Aeromon bot.
 */
public class Aeromon {

    private TaskArrayList taskList;
    private final Storage storage;

    private static final String STARTING_MESSAGE = "Hey, Aeromon here! Fancy a cup of tea?";

    /**
     * Constructs the Aeromon object.
     */
    public Aeromon() {
        storage = new Storage("data/localTasks.txt");

        try {
            taskList = new TaskArrayList(storage.getFile());
        } catch (AeromonException e) {
            e.printStackTrace();
            taskList = new TaskArrayList(new ArrayList<>());
        }
    }

    /**
     * Starts the Aeromon bot by greeting the user with the greeting message.
     * @return the greeting message in String.
     */
    public String start() {
        return STARTING_MESSAGE;
    }

    /**
     * Gets the response from the CommandManager.
     * @param input User input.
     * @return The response String.
     */
    public String getResponse(String input) {
        assert input != null : "Input is null";

        try {
            Command command = CommandManager.read(input);
            return command.execute(taskList, storage);
        } catch (AeromonException exception) {
            return exception.getMessage();
        }
    }
}
