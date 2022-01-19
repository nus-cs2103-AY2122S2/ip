package duke.commands;

import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class WrongCommand extends Command<String> {
    public WrongCommand() {
        System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! I'm sorry, but I don't know what that means :-(")));
    }
    public void runCommand(TaskList todoList, String cmd) {
    }
}
