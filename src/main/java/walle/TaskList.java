package walle;

import gui.Ui;

import java.util.ArrayList;

/**
 * This is the class that stores the task in a list format
 * and provide methods to modify the list
 */
public class TaskList {
    public ArrayList<Task> tasklist;

    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public int getSize() {
        return tasklist.size();
    }

    public ArrayList<Task> get() {
        return this.tasklist;
    }

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     * @return String output of deleted Task
     */
    public String deleteTask(int num) {
        if (num > 0 && num <= tasklist.size()) {
            num--;
            String s = Ui.printRemovedThisTask(num, this);
            this.tasklist.remove(num);
            for (int i = num; i < tasklist.size(); i++) {
                this.tasklist.get(i).decrementNum();
            }
            s += Ui.printTotalTasks(this.tasklist.size() - 1);
            Storage.writeAllToFile(this);
            return s;
        } else {
            return "OOPS!!! There is no such task found.";
        }
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param taskNum taskNumber to mark/unmark
     * @param isMark flag to check if task is to be marked or unmarked
     * @return String output of marked task
     */
    public String markTask(int taskNum, boolean isMark) {
        String s;
        Task curr = tasklist.get(taskNum);
        if (isMark) {
            curr.mark();
            s = Ui.printMarkTaskDone(curr);
        } else {
            curr.unmark();
            s = Ui.printMarkTaskNotDone(curr);
        }
        Storage.writeAllToFile(this);
        return s;
    }

    /**
     * Finds tasks based on keyword provided
     *
     * @param keyword matching word to search for in Tasklist
     * @return String output of tasks containing keyword provided
     */
    public String findTask(String keyword) {
        TaskList matchTasks = new TaskList();
        for (int i = 0; i < tasklist.size(); i++) {
            if (this.tasklist.get(i).name.contains(keyword) || this.tasklist.get(i).time.contains(keyword)) {
                matchTasks.tasklist.add(tasklist.get(i));
            }
        }
        return Ui.printMatchTasks(matchTasks.tasklist);
    }

    /**
     * Searches the TaskList to find if input task name exists
     *
     * @param keyword name of task to search for
     * @return task number if same task name is found in the tasklist. -1 otherwise
     */
    public int findDuplicateTask(String keyword) {
        assert tasklist != null : "Invalid: TaskList is not initialised!";
        for (int i = 0; i < tasklist.size(); i++) {
            if (this.tasklist.get(i).name.equals(keyword)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Attempts to add task to TaskList
     *
     * @param task task to add to TaskList
     * @return -1 if successful. taskNumber of duplicate task in the TaskList if unsuccessful
     */
    public int addTaskToList(Task task) {
        assert task.name != null : "Invalid: TaskName is null!";
        int dupTaskNum = findDuplicateTask(task.name);
        if (dupTaskNum != - 1) {
            return dupTaskNum + 1;
        }
        tasklist.add(task);
        return -1;
    }

}
