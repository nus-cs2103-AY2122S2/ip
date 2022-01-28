package myboss;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    public static int size = 0;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.size = taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static int getSize() {
        return size;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
        size++;
    }

    public Task deleteTask(int index) {
        size--;
        return taskList.remove(index);
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            if (curr.taskName.contains(keyword)) {
                res.add(curr);
            }
        }
        return res;
    }
}
