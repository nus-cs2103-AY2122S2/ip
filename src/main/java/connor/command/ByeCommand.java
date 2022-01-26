package connor.command;

import connor.Connor;

/**
 * Represents a Bye {@code Command}.
 */
public class ByeCommand extends Command {

    public ByeCommand() {}

    /**
     * Sets {@code Connor} to be inactive, exiting the program.
     */
    @Override
    public void activate() {
        System.out.println("Farewell. See you next time!");
        Connor.setActive(false);
    }
}
