import java.util.ArrayList;
import java.util.List;

public class ListStorage {
    public List<String> myStorage;
    public List<Task> myTasks;
    String spacing = "    ";

    public ListStorage() {
        myStorage = new ArrayList<String>();
        myTasks = new ArrayList<Task>();
    }

    public String addToList(Task task){
        myStorage.add(task.description);
        myTasks.add(task);
        return "added: " + task.description;
    }

    public String printList(){
        int i = 1;
        StringBuilder toPrint = new StringBuilder();
        for (Task item : myTasks) {
            toPrint.append(spacing)
                    .append(i).append(".[")
                    .append(item.getStatusIcon())
                    .append("] ")
                    .append(item.description)
                    .append("\n");
            //System.out.println(i + ". " + item);
            i++;
        }
        return toPrint.toString();
    }

    public String printTask(int taskNumber) {
        Task currentTask = myTasks.get(taskNumber - 1);
        return spacing
                + "  ["
                + currentTask.getStatusIcon()
                + "] "
                + currentTask.description
                + "\n";
    }

    public Task findTask(int taskNumber) {
        Task result = null;
        for (int i = 0; i < taskNumber; i++) {
            result = myTasks.get(i);
        }
        return result;
    }

}
