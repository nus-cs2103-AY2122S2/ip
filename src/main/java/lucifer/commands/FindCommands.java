package lucifer.commands;

import java.util.ArrayList;

import lucifer.exceptions.WrongFormatException;
import lucifer.task.Task;
import lucifer.tasklist.TaskList;

/**
 * Class FindCommmands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class FindCommands extends ParseCommands {
    /**
     * list that stores all the task
     **/
    private final ArrayList<Task> list;

    /**
     * Constructor for Class MarkCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public FindCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();

    }

    /**
     * Marks the task from the list.
     *
     * @param input the user input
     */
    public String find(String input) {
        try {
            String[] split = input.split(" ");
            if (split.length == 1) {
                throw new WrongFormatException();
            } else {
                String description = split[1];
                String output = "Here are the matching desires in your list my love:\n";
                int order = 1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).saveFormat().contains(description)) {
                        output += " " + order + ". " + list.get(i) + "\n";
                        order++;
                    }
                }
                if (order == 1) {
                    return "You've got nothing like that in your current desires love!";
                }
                return output;
            }
        } catch (WrongFormatException e) {
            return e.getMessage();
        }
    }
}
