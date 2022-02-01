package duke.chatbot.command;

import java.util.ArrayList;

/**
 * Command which echoes the name of the
 * command written by user.
 */
class EchoCommand extends Command {
    public EchoCommand(String name, String args) {
        super(name, args);
    }

    /**
     * Echoes original command passed in by user.
     *
     * @return ArrayList containing one element, which is
     * the original command.
     */
    @Override
    public ArrayList<String> execute() {
        ArrayList<String> response = new ArrayList<>();
        response.add(super.getOriginalCommand());
        return response;
    }
}
