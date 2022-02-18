package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * List to store all the tasks
 *
 * @author brandonrhan
 * @version 0.0.0
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList
     *
     * @param list Old list of tasks
     */
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

    /**
     * Gets all task in the taskList
     *
     * @return String contains all tasks in the list
     */
    String getList() {
        String s = "";
        for (int i = 0; i < this.getListSize(); i++) {
            s = s + (i + 1) + ". " + this.list.get(i);
            if (i != this.getListSize() - 1) {
                s = s + "\n";
            }
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
        assert(this.list.get(index).isDone());
        return this.list.get(index).toString();
    }

    String unMark(int index) {
        this.list.get(index).unMark();
        assert(!this.list.get(index).isDone());
        return this.list.get(index).toString();
    }

    String tagTask(int index, String tagDetail) {
        this.list.get(index).tag(tagDetail);
        return this.list.get(index).toString();
    }

    String untagTask(int index) {
        this.list.get(index).untag();
        return this.list.get(index).toString();
    }
    /**
     * Checks all task in the list having same date as the input
     *
     * @param date the date to check
     * @return String of all tasks on the same date
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

    /**
     * Checks all tasks in the list contains the input word
     *
     * @param word The word to check
     * @return String of all tasks contains the word
     */
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
