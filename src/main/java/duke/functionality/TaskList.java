package duke.functionality;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static int numOfTask = 0;

    public TaskList() { }

    public void deleteTask(int taskNum) {
        String message = "Noted. I've removed this task:\n";
        int actualTaskNum = taskNum - 1;
        System.out.println(message + taskList.get(actualTaskNum).toString());
        taskList.remove(actualTaskNum);
        numOfTask--;
        Storage.updateTextFile();
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
    }

    public void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done:\n" ;
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task task = taskList.get(actualTaskNum); // get the task from the array
        task.setTaskDone();
        Storage.updateTextFile();
        System.out.println(message + task.toString());
    }

    public void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet:\n";
        int actualTaskNum = taskNum - 1;
        Task task = taskList.get(actualTaskNum); // get the task from the array
        task.setTaskNotDone();
        Storage.updateTextFile();
        System.out.println(message + task.toString());
    }

    public void printList() {
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + taskList.get(i).toString();
            System.out.println(output);
        }
    }

    public void addToList(Task task) {
        String message = "Got it. I've added this task:\n";
        taskList.add(task);
        numOfTask++;
        Storage.updateTextFile();
        System.out.println(message + task.toString() + "\nNow you have " + numOfTask + " tasks in the list.");
    }
}
