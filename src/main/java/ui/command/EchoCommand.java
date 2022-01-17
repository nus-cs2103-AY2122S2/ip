package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which echoes the name of the
 * command written by user.
 */
public class EchoCommand extends Command {
    public EchoCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute() {
        ArrayList<String> response = new ArrayList<>();
        response.add(this.getName());
        Command.styledPrint(response);
        return false;
    }
}
