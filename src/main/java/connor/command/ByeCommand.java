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
    public String activate() {
        System.out.println(Connor.getGoodbye());
        Connor.setActive(false);
        return Connor.getGoodbye();
    }
}
