package connor.command;

import connor.Connor;

public class ByeCommand extends Command {
    public ByeCommand() {}

    @Override
    public void activate() {
        System.out.println("Farewell. See you next time!");
        Connor.setActive(false);
    }
}
