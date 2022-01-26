package Task;

import Duke.UI;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return list.size();
    }

    public void add(Task task, int n) {
        list.add(task);
        UI.printAddMessage(list.get(n).toString(), n);
    }

    public void delete(int no) {
        UI.deleteMessage(list.remove(no).toString());
    }

    public void mark(int n) {
        list.get(n).markAsDone();
        UI.printMarked(list.get(n).toString());
    }

    public void unMark(int n) {
        list.get(n).markAsUnDone();
        UI.printUnMarked(list.get(n).toString());
    }

    public void printTaskList() {
        UI.line();
        for(int m = 0; m < list.size(); m++) {
            System.out.println((m + 1) + "." + list.get(m).toString());
        }
        UI.line();
    }
}
