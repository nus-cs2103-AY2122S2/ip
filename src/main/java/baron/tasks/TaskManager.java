package baron.tasks;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<String> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    public String addTask(String newTask) {
        this.taskList.add(newTask);
        return newTask.toString();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.getTaskCount(); i++) {
            output += (i + 1) + ". " + this.taskList.get(i);

            if (i != this.getTaskCount() - 1) {
                output += "\n";
            }
        }

        return output;
    }
}
