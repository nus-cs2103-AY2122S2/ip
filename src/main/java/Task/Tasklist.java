package Task;

import Duke.UI;
import java.util.ArrayList;

public class Tasklist {

    private ArrayList<Task> list;

    public Tasklist(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task, int n) {
        list.add(task);
        UI.printAddMessage(list.get(n).toString(), n);
    }

    public void delete(int no) {
        UI.deleteMessage(list.remove(no).toString());
    }

    public int size() {
        return list.size();
    }

    public void printTaskList() {
        int m = 0; // counter for list
        UI.line();
        while(m < list.size()) {
            System.out.println((m+1) + "." + list.get(m).toString());
            m+=1;
        }
        UI.line();
    }

    public void mark(int n) {
        list.get(n).markAsDone();
        UI.printMarked(list.get(n).toString());
    }

    public void unmark(int n) {
        list.get(n).markAsUnDone();
        UI.printUnMarked(list.get(n).toString());
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}

