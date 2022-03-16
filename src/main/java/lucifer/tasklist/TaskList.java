package lucifer.tasklist;

import java.util.ArrayList;

import lucifer.task.Task;

/**
 * Class TaskList that handles the list of tasks.
 * @author Yu Meng
 */
public class TaskList {
    /** ArrayList which stores the tasks **/
    private final ArrayList<Task> list;
    /**
     * Constructor for Class Ui if no saved list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for Class Ui if there is a saved list.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Outputs the list of current tasks user has.
     */
    public String giveList() {
        if (list.size() == 0) {
            return "Your list is currently empty!\n"
                    + "Please input some commands my love.\n";
        } else {
            String output = "Here are the desires in your list my love:\n";
            for (int i = 0; i < this.list.size(); i++) {
                int order = i + 1;
                output += " " + order + ". " + this.list.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Returns list of tasks for JUnit test.
     *
     * @return return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return list;
    }
}
