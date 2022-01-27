package duke;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> tasklist;

    public TaskList(){
        tasklist = new ArrayList<Task>();
    }

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     */
    public static void deleter(int num){
        if (num > 0 && num <= Task.totalTask){
            num--;
            Ui.PrintRemoveThisTask(num);
            tasklist.remove(num);
            Task.totalTask--;
            for(int i = num; i <Task.totalTask; i++){
                tasklist.get(i).decrementNum();
            }
            Ui.printTotalTasks();
        } else {
            System.out.println("☹ OOPS!!! There is no such task found.");
        }
        Storage.writeAllToFile();
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param input Original input string that was entered
     */
    public static void markTask(String input){
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
        Storage.writeAllToFile();
    }

}
