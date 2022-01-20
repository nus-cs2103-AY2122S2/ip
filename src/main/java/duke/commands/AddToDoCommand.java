package duke.commands;

import duke.tasks.ToDo;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;
import duke.main.Storage;

import java.io.IOException;

public class AddToDoCommand extends Command<String> {
    private final Storage storage;

    public AddToDoCommand(TaskList toDoList, String cmd, Storage storage) throws DukeException {
        this.storage = storage;
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) throws DukeException {
        try {
            ToDo newToDo = new ToDo(cmd.split("todo")[1], false);
            toDoList.add(newToDo);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newToDo + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
            storage.writeFileContent(toDoList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Parser.formatMsg("☹ OOPS!!! The description of a todo cannot be empty."));
        } catch (IOException e) {
            throw new DukeException(Parser.formatMsg("IOException caught") + e);
        }
    }

    ;
}
