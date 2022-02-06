package duke.storage;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Task;
import java.util.ArrayList;
public class TaskList {
    public ArrayList<Task> list;

    /**
     * Constructor for TaskList. Initialises an empty ArrayList<Task>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add Task to TaskList
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Remove Task from TaskList
     * @param index index of Task to be removed
     */
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * Get task by index
     * @param index index of Task to be retrieved
     * @return Retrieved Task
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Size of tasklist
     * @return integer size of TaskList
     */
    public int size() {
        return this.list.size();
    }
}
