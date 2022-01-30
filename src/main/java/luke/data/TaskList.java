package luke.data;

import java.util.ArrayList;
import java.util.List;

import luke.data.tasks.Task;
import luke.storage.Storable;

public class TaskList implements Storable {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    @Override
    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (Task task : taskList) {
            list.add(String.format("%s | %s", task.getCommandString(),
                    task.isDone() ? "1" : "0"));
        }
        return list;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }
}
