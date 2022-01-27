import java.util.ArrayList;

class TaskList {
    protected ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    void addTask(Task item) {
        taskList.add(item);
    }

    void printTasks() {
        for (int index = 0; index < this.taskList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + taskList.get(index).toString());
        }
    }

    void printNoTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    void deleteFromIndex(int index) {
        this.taskList.remove(index - 1);
    }
}