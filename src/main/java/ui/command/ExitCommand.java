package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * Command which causes the termination
 * of the ChatBot.
 */
public class ExitCommand extends Command {
    private static final String GOODBYE_STRING = "Goodbye!";

    public ExitCommand(String name, String args) {
        super(name, args);
    }

    @Override
    public boolean execute() {
        ArrayList<String> response = new ArrayList<>();
        response.add(GOODBYE_STRING);
        styledPrint(response);
        return true;
    }
}
