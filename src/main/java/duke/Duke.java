package duke;

import duke.tasklist.Storage;

public class Duke {
    private final Pikachu pikachu;
    private final Storage storage;

    //Constructor
    public Duke() {
        this.pikachu = new Pikachu();
        this.storage = new Storage("Tasklist.txt", pikachu);
    }

    //Accessor
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Gets response to user input.
     * Called by GUI.
     *
     * @param input Input from user.
     * @return Output from chatbot after parsing input.
     */
    public String getResponse(String input) {
        return pikachu.parseInput(input);
    }
}
