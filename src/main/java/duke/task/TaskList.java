package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    // tasklist exception messages
    private final static String INDEX_NO_EXIST = "Yo yo yo, this task don't exist. Give a legitimate task number.";

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.valueOf(i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }

        return sb.toString();
    }

    public TaskList getTaskListWithKeyword(final String keyword) {
        boolean yes = tasks.get(0).getTaskDescription().contains(keyword);

        //predicate to check if task description has keyword
        Predicate<Task> filterPredicate = task -> {
            return task.getTaskDescription().toLowerCase().matches(keyword.toLowerCase());
        };

        //filter task
        ArrayList<Task> filteredTasks = tasks.stream().filter(filterPredicate).collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(filteredTasks);
    }
}
