package chatbot.task;

import chatbot.util.Storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList extends ArrayList<Task> implements Serializable {
    private final String saveFile;

    public static TaskList create(String saveFile) {
        TaskList taskList = Storage.<TaskList>Load(saveFile);
        if (taskList == null) {
            taskList = new TaskList(saveFile);
        }
        return taskList;
    }

    public TaskList(String saveFile) {
        this.saveFile = saveFile;
    }

    @Override public boolean add(Task task) {
        boolean ret = super.add(task);
        Storage.Save(saveFile, this);
        return ret;
    }

    @Override public Task remove(int index) {
        Task ret = super.remove(index);
        Storage.Save(saveFile, this);
        return ret;
    }

    @Override public void clear() {
        super.clear();
        Storage.Save(saveFile, this);
    }
}