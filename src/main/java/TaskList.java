import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void delete(int index, Ui ui) {
        Task temp = taskList.get(index);
        this.taskList.remove(index);
        ui.printNotice();
        ui.printDeletedTask(temp);
        ui.printRemainingTasks(this);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void setTaskDone(int index) {
        this.taskList.get(index).setDone();
    }

    public void setPreTaskDone(int index) {
        this.taskList.get(index).presetDone();
    }

    public void getTask(Ui ui, int index) {
        ui.printTask(index, this.taskList.get(index));
    }

    public void printTasks(Ui ui) {
        ui.printListHeader();
        for (int i = 0; i < this.taskList.size(); i++) {
            ui.printTask(i + 1, this.taskList.get(i));
        }
    }

    public void setTaskUndone(int index) {
        this.taskList.get(index).setUndone();
    }

    public int getSize() {
        return this.taskList.size();
    }
}
