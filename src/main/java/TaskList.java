import java.util.ArrayList;

public class TaskList<Task> {
    ArrayList<Task> arrayList;

    TaskList() {
        this.arrayList = new ArrayList<Task>();
    }

    public int length() {
        return this.arrayList.size();
    }

    public void add(Task item) {
        this.arrayList.add(item);
    }

    public void list() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println((i + 1) + ". " + arrayList.get(i).toString());
        }
    }

    public void mark(int index) {
        Task task = arrayList.get(index);
        if (task != null) {
            String taskName = "taskName";
//            String taskName = task.getTaskName();
            Task newTask = new Task(taskName, true);
            arrayList.add(index, newTask);
        }
    }

    public void unmark(int index) {
        Task task = arrayList.get(index);
        if (task != null) {
            String taskName = "taskName";
//             String taskName = task.getTaskName();
            Task newTask = new Task(taskName, false);
            arrayList.add(index, newTask);
        }
    }

    public Task getTask(int index) {
        return arrayList.get(index);
    }

}