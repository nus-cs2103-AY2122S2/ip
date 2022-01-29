package duke;

import java.util.ArrayList;

/**
 * This is the class that stores the task in a list format
 * and provide methods to modify the list
 */

public class TaskList {
    public ArrayList<Task> tasklist;

    public TaskList(){
        tasklist = new ArrayList<Task>();
    }

    public void markTaskNum(int taskNum, String check){
        if (check.equals("true") && (taskNum > 0)) {
            this.tasklist.get(taskNum).mark();
        }
    }

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     */
    public void deleter(int num){
        if (num > 0 && num <= Task.totalTask){
            num--;
            Ui.printRemovedThisTask(num, this);
            this.tasklist.remove(num);
            Task.totalTask--;
            for(int i = num; i <Task.totalTask; i++){
                this.tasklist.get(i).decrementNum();
            }
            Ui.printTotalTasks();
        } else {
            System.out.println("☹ OOPS!!! There is no such task found.");
        }
        Storage.writeAllToFile(this);
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param input Original input string that was entered
     */
    public void markTask(String input){
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        Task curr = tasklist.get(taskNum);
        if (input.startsWith("mark")){
            curr.mark();
            Ui.printMarkTaskDone(curr);
        } else {
            curr.unmark();
            Ui.printMarkTaskNotDone(curr);
        }
        Storage.writeAllToFile(this);
    }


    public void addTask(String name, String time, String type, boolean isReading){
        Task task;
        if (type.equals("D")){
            task = new Deadline(name, Task.totalTask, time, isReading);
        } else if (type.equals("E")){
            task = new Event(name, Task.totalTask, time, isReading);
        } else {
            task = new ToDo(name, Task.totalTask, isReading);
        }
        tasklist.add(task);
    }

    public void findTask(String keyword){
        TaskList matchTasks = new TaskList();
        for(int i=0; i<Task.totalTask; i++){
            if (this.tasklist.get(i).name.contains(keyword)){
                matchTasks.tasklist.add(tasklist.get(i));
            }
        }
        Ui.printMatchTasks(matchTasks.tasklist);
    }

}
