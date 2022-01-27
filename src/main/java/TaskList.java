import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTasks(Task item){
        this.tasks.add(item);
    }

    public Task removeTask(int j) {
        return this.tasks.remove(j);
    }

    public Task getTask(int i){
        return this.tasks.get(i);
    }

    public String formatTasks() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
//            Task currentTask = this.tasks.get(i);
            String message = this.tasks.get(i).getDescription();
            String preStatus = this.tasks.get(i).getStatusIcon();
            String status = "";
            if (preStatus.equals("X")) {
                status = status + "1";
            } else {
                status = status + "0";
            }
            if (i == this.tasks.size()-1) {
                output = output + status + " | " + message;
            } else {
                output = output + status + " | " + message + "\n";
            }
        }
        return output;
    }

    public Integer getSize(){
        return this.tasks.size();
    }
}
