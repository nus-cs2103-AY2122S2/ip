package van;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int counter;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = this.tasks.size();
        assert counter >= 0;
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void markDone(int taskNumber) {
        this.tasks.get(taskNumber - 1).setDone();
    }

    public void markUndone(int taskNumber) {
        this.tasks.get(taskNumber - 1).setUnDone();
    }

    public void deleteTask(int taskNumber) {
        this.tasks.remove(taskNumber - 1);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public void addTag(int taskNumber, String newTag) {
        this.tasks.get(taskNumber - 1).addTag(newTag);
    }

    public void deleteTag(int taskNumber, String newTag) {
        this.tasks.get(taskNumber - 1).deleteTag(newTag);
    }
}
