package duke.exception;

import duke.exception.DukeException;

public class DukeManager {

    public DukeManager() {

    }

    public String test(String command) throws DukeException {
        if ((command.replaceAll("\\s+", "")).equals("list") ||
                (command.replaceAll("\\s+", "")).equals("bye")) {
            return command;
        }
        String[] keyWords = {"todo", "event", "deadline", "mark", "unmark", "delete", "find"};
        String[] splicedCommand = command.split(" ", 2);
        if (splicedCommand.length == 1) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
        }
        String key = splicedCommand[0];
        String text = splicedCommand[1];
        for (String s : keyWords) {
            if (key.equals(s)) {
                if (!text.isBlank()) {
                    return command;
                }
            }
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
    }
}
