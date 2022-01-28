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

    public void find(String str) {
        printSearchList(generateList(str));
    }

    private ArrayList<Task> generateList(String str) {
        ArrayList<Task> output = new ArrayList<>();
        for (int m = 0; m < list.size(); m++) {
            String in = list.get(m).getDescription();
            if (in.equals(str)) {
                output.add(list.get(m));
            } else if (scan(in, str)){
                output.add(list.get(m));
            } else {
                // add nothing
            }
        }
        return output;
    }

    private boolean scan(String in, String str) {
        boolean isPresent = false;
        String[] y = in.split(" ");
        for(int n = 0; n < y.length; n++) {
            if (y[n].equals(str)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    private void printSearchList(ArrayList<Task> list) {
        UI.line();
        for(int m = 0; m < list.size(); m++) {
            System.out.println((m + 1) + "." + list.get(m).toString());
        }
        UI.line();
    }

}
