package duke.task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task item){
        tasks.add(item);
    }

    public Task removeTask(int j) {
        return tasks.remove(j);
    }

    public Task getTask(int i){
        return tasks.get(i);
    }

    public String findTasks(String detail){
        String output = "";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getDescription();
            String message = tasks.get(i).getTask();
            if (i == tasks.size() -1 && description.contains(detail)) {
                count++;
                output += count + ". " + message;
            } else if (description.contains(detail)) {
                count++;
                output += count + ". " + message + "\n";
            }
        }
        return output;
    }

    public String formatTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            String message = tasks.get(i).getDescription();
            String preStatus = tasks.get(i).getStatusIcon();
            String status = "";
            if (preStatus.equals("X")) {
                status = status + "1";
            } else {
                status = status + "0";
            }
            if (i == tasks.size()-1) {
                output = output + status + " | " + message;
            } else {
                output = output + status + " | " + message + "\n";
            }
        }
        return output;
    }

    public Integer getSize(){
        return tasks.size();
    }
}
