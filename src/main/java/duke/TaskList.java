package duke;
import duke.action.Action;

import java.util.ArrayList;

//contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private final Storage storage;
    private final ArrayList<Action> list = new ArrayList<>();

    public TaskList() {
        storage = new Storage();
    }

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public void add(Action action) {
        list.add(action);
    }

    public ArrayList<Action> getList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public void setDone(int taskNo) {
        list.set(taskNo, list.get(taskNo).setDone());
    }

    public void setUnDone(int taskNo) {
        list.set(taskNo, list.get(taskNo).setUnDone());
    }

    public Action getAction(int taskNo) {
        return list.get(taskNo);
    }

    public Action delete(int taskNo) {
        return list.remove(taskNo);
    }

    public void listOut() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        if (list.isEmpty()) {
            System.out.println("Oh, you have nothing to do, how free you are!");
        } else {
            for (Action act : list) {
                System.out.println(count + "." + act);
                count++;
            }
        }
    }
}
