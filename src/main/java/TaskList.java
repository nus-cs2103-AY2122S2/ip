import java.util.ArrayList;

public class TaskList {
    private final String LIST_INIT = "Here are the task:\n";
    private ArrayList<Task> masterList;

    public TaskList(ArrayList<Task> list) {
        this.masterList = list;
    }

    public TaskList() {
        this.masterList = new ArrayList<>();
    }

    public int size() {
        return this.masterList.size();
    }

    public Task get(int index) {
        return this.masterList.get(index);
    }

    public void addTask(Task task) {
        masterList.add(task);
    }

    public String list() {
        String toPrint = LIST_INIT;
        for (int i = 0; i < masterList.size(); i++) {
            Task currTask = masterList.get(i);
            toPrint += currTask + "\n";
        }
        return toPrint;
    }

    public Task mark(int index) {
        Task currTask = masterList.get(index);
        currTask.markAsDone();
        masterList.set(index, currTask);
        return currTask;
    }

    public Task unmark(int index) {
        Task currTask = masterList.get(index);
        currTask.unmarkItem();
        masterList.set(index, currTask);
        return currTask;
    }

    public Task delete(int index) {
        Task currTask = masterList.get(index);
        masterList.remove(index);
        return currTask;
    }
}
