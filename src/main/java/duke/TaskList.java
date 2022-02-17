package duke;

import java.util.ArrayList;


/**
 * Represents a tasklist
 */
public class TaskList {
    ArrayList<Task> arrayList;

    TaskList() {
        this.arrayList = new ArrayList<Task>();
    }

    public int length() {
        return this.arrayList.size();
    }

    public void add(Task item) {
        this.arrayList.add(item);
    }

    public String list() {
        String str = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (i >= 1) {
                str += "\n";
            }
            str += (i + 1) + ". " + arrayList.get(i).toString();
        }
        return str;
    }

    public void mark(int index) {
        Task task = arrayList.get(index);
        if (task != null) {
            task.setCompleted();
        }
    }

    public void unmark(int index) {
        Task task = arrayList.get(index);
        if (task != null) {
            task.setUncompleted();
        }
    }

    public void delete(int index) {
        arrayList.remove(index);
    }

    public int getSize() {
        return this.arrayList.size();
    }

    public Task getTask(int index) {
        return arrayList.get(index);
    }

    public String saveText() {
        String s = "";
        for (int i = 0; i < arrayList.size(); i++) {
            Task task = arrayList.get(i);
            if (task instanceof DateTimeInterface) {
                DateTimeInterface dateTimeInterface = (DateTimeInterface) task;
                String taskString = String.format("%s | %d | %s | %s",
                        task.getType(), task.isCompleted() ? 1 : 0, task.getTaskName(), dateTimeInterface.getDate());
                if (i == 0) {
                    s = taskString;
                } else {
                    s = s + "\n" + taskString;
                }
            } else {
                String taskString = String.format("%s | %d | %s",
                        task.getType(), task.isCompleted() ? 1 : 0, task.getTaskName());
                if (i == 0) {
                    s = taskString;
                } else {
                    s = s + "\n" + taskString;
                }
            }

        }
        return s;
    }

    public String find(String string) {
        String lowercase = string.toLowerCase();
        for (int i = 0; i < arrayList.size(); i++) {
            Task task = arrayList.get(i);
            if (task.getTaskName().toLowerCase().contains(lowercase)) {
                return task.toString();
            }
        }
        return "No task found!";
    }

    public void sort() {
        this.arrayList.sort(new TaskNameComparator());
    }
}