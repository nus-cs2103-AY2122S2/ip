import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    String markTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setDone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    String unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setUndone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    void printTasks() {
        for (int index = 0; index < this.taskList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + taskList.get(index).toString());
        }
    }

    void printNoTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    String deleteFromIndex(int index) {
        String deletedTask = this.taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        return deletedTask;
    }

    String updateDatabase() {
        String result = "";
        for (Task task: this.taskList) {
            result = result + task.updateIntoDatabase();
        }
        return result;
    }
}