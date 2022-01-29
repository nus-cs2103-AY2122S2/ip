package Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;
    private int count;

    public  TaskList () {
        tasks = new ArrayList<Task>();
    }
    public TaskList(List<Task> inputTks) {
        tasks = inputTks;
        count = inputTks.size();
    }

    public void addTask(Task tk) {
        tasks.add(tk);
        count++;
    }

    public void delete(int num) {
        tasks.remove(num -1);
        count--;
    }

    public void markTask(int num) {
        tasks.get(num - 1).markDone();
    }

    public void unmarkTask(int num) {
        tasks.get(num - 1).markNotDone();
    }

    public String getTaskStr(int num) {
        return tasks.get(num - 1).toString();
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
            s += "\n" + num + ". " + tasks.get(i).toString();
        }

        return s;

    }

    public String tasksToString() {
        //converts the list of tasks to string for file IO
        String strReturn = "";
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                strReturn = tasks.get(i).saveString();
            } else {
                strReturn += "\n" + tasks.get(i).saveString();
            }
        }
        return strReturn;
    }

}
