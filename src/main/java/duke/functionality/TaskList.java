package duke.functionality;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the Tasks of the Duke project. A <code> TaskList </code> object corresponds
 * to the actions available on a Task object.
 */
public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static int numOfTask = 0;

    /**
     * Default constructor of TaskList class.
     */
    public TaskList() { }

    /**
     * Returns nothing, but deletes the task specified.
     * @param taskNum an indicator to the index of taskList.
     */
    public void deleteTask(int taskNum) {
        String message = "Noted. I've removed this task:\n";
        int actualTaskNum = taskNum - 1;
        System.out.println(message + taskList.get(actualTaskNum).toString());
        taskList.remove(actualTaskNum);
        numOfTask--;
        Storage.updateTextFile();
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
    }

    /**
     * Returns nothing, but marks the task specified as done.
     * @param taskNum an indicator to the index of taskList.
     */
    public void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done:\n";
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task task = taskList.get(actualTaskNum); // get the task from the array
        task.setTaskDone();
        Storage.updateTextFile();
        System.out.println(message + task);
    }

    /**
     * Returns nothing, but marks the task specified as not done.
     * @param taskNum an indicator to the index of taskList.
     */
    public void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet:\n";
        int actualTaskNum = taskNum - 1;
        Task task = taskList.get(actualTaskNum); // get the task from the array
        task.setTaskNotDone();
        Storage.updateTextFile();
        System.out.println(message + task);
    }

    /**
     * Returns nothing, but prints out all task in taskList.
     */
    public void printList() {
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for (int i = 0; i < numOfTask; i++) {
            String output = i + 1 + "." + taskList.get(i).toString();
            System.out.println(output);
        }
    }

    /**
     * Returns nothing, but adds the task specified into the taskList.
     * @param task the task created in Parser class.
     */
    public void addToList(Task task) {
        String message = "Got it. I've added this task:\n";
        taskList.add(task);
        numOfTask++;
        Storage.updateTextFile();
        System.out.println(message + task.toString() + "\nNow you have " + numOfTask + " tasks in the list.");
    }

    /**
     * Returns nothing, but prints all task that contains that matches the specified word.
     * @param word keyword input from user
     */
    public void findWord(String word) {
        String message = "Here are the matching tasks in your list:\n";
        System.out.print(message);
        int counter = 1;
        for (int i = 0; i < numOfTask; i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(word)) {
                String output = counter + "." + task;
                counter++;
                System.out.println(output);
            }
        }
        if (counter == 1) {
            System.out.println("OOPS!, there are no matching task with the word provided.");
        }
    }
}
