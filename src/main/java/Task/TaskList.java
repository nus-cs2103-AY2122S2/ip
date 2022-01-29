package Task;

import Task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> aryLst;
    private int count;

    public  TaskList () {
        aryLst = new ArrayList<Task>();
    }
    public TaskList(List<Task> inputTks) {
        aryLst = inputTks;
        count = inputTks.size();
    }

    public void addTask(Task tk) {
        aryLst.add(tk);
        count++;
    }

    public void delete(int num) {
        aryLst.remove(num -1);
        count--;
    }

    public void markTask(int num) {
        aryLst.get(num - 1).markDone();
    }

    public void unmarkTask(int num) {
        aryLst.get(num - 1).markNotDone();
    }

    public String getTaskStr(int num) {
        return aryLst.get(num - 1).toString();
    }

    public int getCount() {
        return count;
    }

    public String listTasks() {
        if (count == 0) {
            return  "You got no task!!";
        }

        String s = "You forgetful baka... here are your tasks: ";

        for (int i = 0; i < count; i++) {
            int num = i + 1;
            s += "\n" + num + ". " + aryLst.get(i).toString();
        }

        return s;

    }

    public String tasksToString() {
        //converts the list of tasks to string for file IO
        String strReturn = "";
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                strReturn = aryLst.get(i).saveString();
            } else {
                strReturn += "\n" + aryLst.get(i).saveString();
            }
        }
        return strReturn;
    }

    public String tasksFinder(String toFind) {
        String s = "";
        int counter = 1;
        for (int i = 0; i < count; i++) {
            if (aryLst.get(i).description.contains(toFind)) {
                s += "\n" + counter + ". " + aryLst.get(i).toString();
                counter++;
            }
        }

        return s;
    }

}
