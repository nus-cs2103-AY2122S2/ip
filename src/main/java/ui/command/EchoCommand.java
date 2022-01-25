package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which echoes the name of the
 * command written by user.
 */
class EchoCommand extends Command {
    public EchoCommand(String name, String args) {
        super(name, args);
    }

    @Override
    public boolean execute() {
        ArrayList<String> response = new ArrayList<>();
        response.add(super.getOriginalCommand());
        Command.styledPrint(response);
        return false;
    }
}
