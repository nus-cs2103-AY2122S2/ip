package Taskmaster.util;
import Taskmaster.Task.Task;
import java.util.ArrayList;

//Author: Tan Ting Yu
//Student Number: A218235J

/*
 * Tasklist encapsulates the list of tasks the user adds
 */


public class TaskList {
    private ArrayList<Task> tasklist;
    public int currentSize;
    
    /**
     * Constructor for tasklist
     */

    public TaskList() {
        this.tasklist = new ArrayList<>();
        this.currentSize = 0;
    }
    
    /**
     * Returns the task object at the index specified
     * @param index
     * @return Task object at the index specified
     */

    public Task get(int index) {
        return this.tasklist.get(index);
    }

    /**
     * Adds the task into the existing tasklist
     * @param Task object
     */

    public void add(Task task) {
        tasklist.add(task);
        currentSize+=1;

    }

    /**
     * Mark the task in the tasklist at the specified index
     * 
     * @param index index at which the task is to be marked
     */

    public void mark(int index) {
        this.tasklist.get(index).markTask();
    }

    /**
     * Unmark the task in the tasklist at the specified index
     * 
     * @param index index at which the task is to be unmarked
     */

    public void unmark(int index) {
        this.tasklist.get(index).unmarkTask();
    }

     /**
     * Delete the task in the tasklist at the specified index
     * 
     * @param index index at which the task is to be deleted
     */

    public void delete(int index) {
        this.tasklist.remove(index);
        currentSize -= 1;
    }

     /**
     * Display the list
     * 
     */

    public void list() {
        //Handle the case of list being empty
        if (currentSize == 0) {
            System.out.println("    \nYou haven't added any task, brat!\n");
        } else {
             //Display the task list
             System.out.println("    \nHere are the tasks in your list:");
             for (int i = 0; i < currentSize; i++)
                 System.out.println("    " + (i + 1) + ". " + this.tasklist.get(i));
            System.out.println("\n");
         
        }
    }

    public String listTasksInTextFormat() {
        String result = "";
        for (Task task:tasklist) {
            result = result.concat(task.saveToFileFormat());
            result = result.concat("\n");
        }

        return result.trim();
    }

    public void find(String strToFind) {
        int count = 1;
        for (Task task:tasklist) {
            if (task.containsKeyword(strToFind)) {
                System.out.println(count + ". " + task);
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No task with that keyword\n");
        }
    }
}