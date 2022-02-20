package duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindCommand extends Command {
    private ArrayList<Task> taskList = TaskList.taskList;
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        ArrayList<Task> foundTasks = findTasks();
        Ui.printFindTask(foundTasks);
    }

    /**
     * Finds tasks in the arraylist that match the search term
     *
     * @return Arraylist of found tasks
     */
    private ArrayList<Task> findTasks() {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().contains(input)) {
                foundTasks.add(t);
            }
        }

        return foundTasks;
    }


}
