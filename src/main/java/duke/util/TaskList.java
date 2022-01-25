package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int totalTasks;

    public TaskList(ArrayList<Task> currentTasks) {
        this.tasks = currentTasks;
        this.totalTasks = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.totalTasks = 0;
    }

    public String getListStatus() {
        if (this.totalTasks > 1) {
            return "There are " + totalTasks + " tasks in the list now.";
        } else {
            return "There is " + totalTasks + " task in the list now.";
        }
    }

    public String getList() {
        String list = "";
        if (totalTasks == 0) {
            list += "There are no tasks in your list.";
        } else {
            list += "Here are the tasks in your list:";
            int index = 1;
            for (int n = 0; n < totalTasks; n++) {
                Task t = tasks.get(n);
                list += "\n    "  + index + "." + t.toString();
                index++;
            }
        }
        return list;
    }

    public String formatListForSaving() {
        String list = "";
        for (Task t: tasks) {
            list = list + t.getType() + " | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription() + "\n";
        }
        return list;
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }

    public void add(Task task) {
        tasks.add(task);
        totalTasks++;
    }

    public Task delete(int taskNum) {
        Task taskToDelete = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        totalTasks--;
        return taskToDelete;
    }

    public Task mark(int taskNum) {
        Task taskToMark = tasks.get(taskNum - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public Task unmark(int taskNum) {
        Task taskToUnmark = tasks.get(taskNum - 1);
        taskToUnmark.markAsUndone();
        return taskToUnmark;
    }

    /**
     * Returns the list of matching tasks. If no task matches keyword, return not found message.
     *
     * @param keyword The keyword used to search for tasks.
     * @return String representation of matching tasks.
     */
    public String findTasksWithKeyword(String keyword) {
        String list = "Here are the matching tasks in your list:";
        int index = 1;
        int count = 0;

        for (int n = 0; n < totalTasks; n++) {
            Task task = tasks.get(n);
            if (task.hasKeyword(keyword)) {
                count++;
                list += "\n    "  + count + "." + task;
            }
            index++;
        }

        if (count > 0) {
            return list;
        } else {
            return "Sorry, there are no tasks that match keyword.";
        }
    }
}
