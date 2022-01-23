import java.util.ArrayList;

public class TaskList {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
        System.out.println("OK...");
        System.out.println("The following task has been added to the list: ");
        System.out.println(task);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public void markTask(int index) {
        Task task = this.get(index);
        task.markAsDone();
    }

    public void unmarkTask(int index) {
        Task task = this.get(index);
        task.markAsUndone();
    }

    public void delete(int taskNumber) {
        System.out.println("OK...");
        System.out.println("The following task has been removed from the list: ");
        System.out.println(taskList.get(taskNumber - 1));
        taskList.remove(taskNumber - 1);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public Task get(int item) {
        return taskList.get(item - 1);
    }

    public int numOfTasks() {
        return taskList.size();
    }

    public void list() {
        if (numOfTasks() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task : taskList) {
            output += counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }

    private boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public ArrayList<Task> toArrayList() {
        return this.taskList;
    }
}
