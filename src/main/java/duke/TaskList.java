package duke;

import gui.Output;

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

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     * @return String output of deleted Task
     */
    public String deleteTask(int num) {
        if (num > 0 && num <= Task.totalTask) {
            num--;
            String s = Output.printRemovedThisTask(num, this);
            this.tasklist.remove(num);
            Task.totalTask--;
            for(int i = num; i <Task.totalTask; i++) {
                this.tasklist.get(i).decrementNum();
            }
            s += Output.printTotalTasks();
            Storage.writeAllToFile(this);
            return s;
        } else {
            return "☹ OOPS!!! There is no such task found.";
        }
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param input Original input string that was entered
     * @return String output of marked task
     */
    public String markTask(String input) {
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        String s = "";
        Task curr = tasklist.get(taskNum);
        if (input.startsWith("mark")) {
            curr.mark();
            s += Output.printMarkTaskDone(curr);
        } else {
            curr.unmark();
            s += Output.printMarkTaskNotDone(curr);
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
        for(int i = 0; i < Task.totalTask; i++) {
            if (this.tasklist.get(i).name.contains(keyword) || this.tasklist.get(i).time.contains(keyword)) {
                matchTasks.tasklist.add(tasklist.get(i));
            }
        }
        return Output.printMatchTasks(matchTasks.tasklist);
    }

}
