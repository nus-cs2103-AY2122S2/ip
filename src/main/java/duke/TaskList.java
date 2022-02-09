package duke;

import java.util.ArrayList;

class TaskList {
    private Storage storage;
    private Ui Ui;

    TaskList(Storage storage) {
        this.storage = storage;
        this.Ui = new Ui();
    }

    public void addTask(Task task) {
        storage.getList().add(task);
    }

    public void deleteTask(int index) { //might need to change
        this.getTaskArray().remove(index);
    }

    public ArrayList<Task> findTask(String input) {
        ArrayList<Task> containsInput = new ArrayList<Task>();
        for (Task task: this.getTaskArray()) {
            if (task.toString().contains(input)) {
                containsInput.add(task);
            }
        }
        return containsInput;
    }


    public void showTask() {
        Ui.showListMessage(this);
    }

    public ArrayList<Task> getTaskArray() {
        return storage.getList();
    }
    
}
