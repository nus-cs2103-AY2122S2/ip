package Duke;

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

    public int length() {
        return myTasks.size();
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
                    .append(i)
                    .append(".")
                    .append(item.toString())
                    .append("\n");
            //System.out.println(i + ". " + item);
            i++;
        }
        return toPrint.toString();
    }

    public String printTask(int taskNumber) {
        Task currentTask = myTasks.get(taskNumber - 1);
        return spacing
                + currentTask.toString()
                + "\n";
    }

    public Task findTask(int taskNumber) {
        return myTasks.get(taskNumber - 1);
    }

    public void deleteTask(int taskNumber) {
        myTasks.remove(taskNumber - 1);
    }

}
