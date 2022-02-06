package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    void addToList(Task task) {
        this.list.add(task);
    }

    Task deleteTask(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return task;
    }

    String getList() {
        String s = "";
        for (int i = 0; i < this.getListSize(); i++) {
            s = s + (i + 1) + ". " + this.list.get(i) + "\n";
        }
        return s;
    }

    ArrayList<Task> getArrayList() {
        return this.list;
    }

    int getListSize() {
        return this.list.size();
    }

    String mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index).toString();
    }

    String unMark(int index) {
        this.list.get(index).unMark();
        return this.list.get(index).toString();
    }

    /**
     * check all task in the list and print out all having same date as the input
     *
     * @param date the date to check
     */
    String checkDate(LocalDate date) {
        String validDates = "";
        for (int i = 0; i < this.getListSize(); i++) {
            Task currTask = this.list.get(i);
            if (currTask.getDate().equals(date)) {
                validDates += currTask.toString() + "\n";
            }
        }
        return validDates;
    }

    String checkWord(String word) {
        String validTasks = "";
        for (int i = 0; i < this.getListSize(); i++) {
            if (this.list.get(i).containsWord(word)) {
                validTasks += this.list.get(i).toString() + "\n";
            }
        }
        return validTasks;
    }
}
