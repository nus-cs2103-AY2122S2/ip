package duke;

import java.util.ArrayList;

public class Ui {

    private TaskList taskList;

    public Ui (TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * prints the task in a list
     */
    public void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("        " + (i + 1) + " " + this.taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks task as done and prints message to acknowledge
     * @param task number in integer form
     */
    public void markAsDone(int taskNum) {
        int taskNumMinusOne = taskNum - 1;
        taskList.get(taskNumMinusOne).setAsMarked(); // setAsMarked
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskNum + "." + taskList.get(taskNumMinusOne));
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks task as not done and prints message to acknowledge
     * @param task number in integer form
     */
    public void markAsNotDone(int taskNum) {
        int taskNumMinusOne = taskNum - 1;
        taskList.get(taskNumMinusOne).setAsUnmarked(); // check here
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskNum + "." + taskList.get(taskNumMinusOne));
        System.out.println("____________________________________________________________");
    }

    /**
     * prints message informing that task x has been added to list.
     */
    public void printTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.format("Now you have %s tasks in the list.\n", taskList.size());
        System.out.println("____________________________________________________________");
    }

    /**
     * prints message regarding the fact that a task from the list has been deleted
     * @param Tasknumber in the form of integer
     */
    public void printDeletedTask(int taskNum) {
        int taskNumMinusOne = taskNum - 1;
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskNum + "." + taskList.get(taskNumMinusOne));
        System.out.println("Now you have " + (taskList.size() - 1)  +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    /**
     * prints task that matches description
     * @param ArrayList of Tasks
     */
    public void printFind(ArrayList<Task> res){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list: ");
        for (int j = 0; j < res.size(); j++) {
            System.out.println("        " + (j + 1) + " " + res.get(j));
        }
        System.out.println("____________________________________________________________");
    }
    /**
     * prints welcome message
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }
    /**
     * prints goodbye message
     */
    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}

