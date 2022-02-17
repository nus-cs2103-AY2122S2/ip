package chibot.tasklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a STUB of a TaskList for testing purposes.
 */
public class TaskListStub extends TaskList {

    /** Stores the tasks */
    private List<String> testTasks = new ArrayList<>();

    /**
     * Instantiates the new instance with 2 fixed tasks added in.
     */
    public TaskListStub() {
        this.testTasks.add("list item 1");
        this.testTasks.add("list item 2");
    }

    /**
     * Returns a concatenation of the two tasks stored in the Stub.
     *
     * @return A String of tasks for testing.
     */
    @Override
    public String getTasksMsg() {
        return "1. " + testTasks.get(0) + "\n" + "2. " + testTasks.get(1);
    }

    @Override
    public int getSize() {
        return 2;
    }
}
