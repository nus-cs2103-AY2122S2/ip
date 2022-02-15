package duke.logic;

import java.util.LinkedList;

/**
 * Represents a stack of tasks.
 *
 * @author Peter
 */
public class TaskStack {
    /**
     * Internally stored stack of task lists.
     */
    private final LinkedList<TaskList> taskLists;

    /**
     * Maximum capacity for stack of tasks lists.
     */
    private final int MAX_SIZE = 10;

    /**
     * Constructor for a task stack.
     */
    public TaskStack() {
        this.taskLists = new LinkedList<>();
    }

    /**
     * Pushes new task list to internal stack of task lists. Removes the oldest task list if
     * task stack exceeds maximum capacity.
     *
     * @param taskList Task List to be pushed on to internal stack of task lists.
     */
    public void push(TaskList taskList) {
        taskLists.addFirst(taskList);
        if (taskLists.size() > MAX_SIZE) {
            taskLists.pollLast();
        }
    }

    /**
     * Pops most recent task list from internal stack of task lists.
     *
     * @return Most recent task list from internal stack of task lists if said stack is not
     * empty, otherwise <code>null</code>.
     */
    public TaskList pop() {
        if (!taskLists.isEmpty()) {
            return taskLists.pollFirst();
        }
        return null;
    }
}
