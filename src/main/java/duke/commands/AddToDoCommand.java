package duke.commands;

import duke.tasks.ToDo;
import duke.main.DukeException;
import duke.main.TaskList;
import duke.main.Parser;

public class AddToDoCommand extends Command<String> {
    public AddToDoCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd) {
        try {
            ToDo newToDo = new ToDo(cmd.split("todo")[1], false);
            toDoList.add(newToDo);
            System.out.println(Parser.formatMsg("Got it. I've added this task:\n\t" + newToDo + "\n\tNow you have " + toDoList.size() + " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException(Parser.formatMsg("☹ OOPS!!! The description of a todo cannot be empty.")));
        }
    }

    ;
}
