package duke.commands;

import duke.main.DukeException;
import duke.main.TaskList;

public class ListCommand extends Command<String> {
    private final String tabbedLine = "\t----------------------------------------------";
    public ListCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    public void runCommand(TaskList toDoList, String cmd)  {
        System.out.println(tabbedLine);
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + 1 + "." + toDoList.get(i).toString());
        }
        System.out.println(tabbedLine);
    };
}
