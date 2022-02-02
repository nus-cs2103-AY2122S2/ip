package duke.chatbot.command;

import java.util.ArrayList;

/**
 * Command which causes the termination
 * of the ChatBot.
 */
public class ExitCommand extends Command {
    private static final String GOODBYE_STRING = "Goodbye!";

    public ExitCommand(String name, String args) {
        super(name, args);
    }

    /**
     * Signals the termination of the ChatBot by
     * returning true.
     *
     * @return ArrayList containing the goodbye string.
     */
    @Override
    public ArrayList<String> execute() {
        ArrayList<String> response = new ArrayList<>();
        response.add(GOODBYE_STRING);
        return response;
    }
}
