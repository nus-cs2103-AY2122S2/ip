package Commands;

import Tasks.ToDo;
import main.DukeException;
import main.TaskList;
import main.Parser;

public class AddToDoCommand {
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
