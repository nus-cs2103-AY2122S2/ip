import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public String getTasksInfo() {
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            String task = (i + 1 + ". " + taskList.get(i) + "\n");
            strBuilder.append(task);
        }
        return strBuilder.toString();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean checkValidTask(String suffix) {
        try {
            int taskNumber = Integer.parseInt(suffix);
            return taskNumber > 0 & taskNumber <= this.taskList.size();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Task getTaskByNum(int i) {
        return this.taskList.get(i - 1);
    }
}