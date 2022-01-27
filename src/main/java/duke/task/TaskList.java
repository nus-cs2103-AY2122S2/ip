package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    // tasklist exception messages
    private final static String INDEX_NO_EXIST = "Yo yo yo, this task don't exist. Give a legitimate task number.";
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    // edit the marking of tasks
    public Task markTask(int taskIndex, boolean mark) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException(INDEX_NO_EXIST);
        }

        Task task = tasks.get(taskIndex);
        task.setIsDone(mark);

        return task;
    }

    // add new task
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    // remove task in tasklist
    public Task removeTask(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException(INDEX_NO_EXIST);
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        return task;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public String getTaskListStr() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
