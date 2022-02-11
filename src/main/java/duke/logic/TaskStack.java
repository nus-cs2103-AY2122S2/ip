package duke.logic;

import java.util.LinkedList;

public class TaskStack {
    private final LinkedList<TaskList> taskLists;
    private final int MAX_SIZE = 10;

    public TaskStack() {
        this.taskLists = new LinkedList<>();
    }

    public void push(TaskList taskList) {
        taskLists.addFirst(taskList);
        if (taskLists.size() > MAX_SIZE) {
            taskLists.pollLast();
        }
    }

    public TaskList pop() {
        if (!taskLists.isEmpty()) {
            return taskLists.pollFirst();
        }
        return null;
    }
}
