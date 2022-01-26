package duke;

/**
 * Duke is a simple virtual assistant typically used
 * to teach students about programming.
 */
public class Duke {

    /**
     * Runs Duke.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        // Create new variables
        Memory memory = new Memory();
        UserInterface ui = new UserInterface(memory);

        // Setup disk
        memory.setup();

        ui.uiLoop();

    }
}
