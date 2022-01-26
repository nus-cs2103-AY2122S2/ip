package duke;

import java.util.ArrayList;

public class TaskList {
    // divider
    String LINES = "    ---------------------------------";

    protected ArrayList<Task> taskLists;

    public TaskList(ArrayList<Task> taskList) {
        this.taskLists = taskList;
    }

    public int size() {
        return taskLists.size();
    }

    public Task get(int num) {
        return taskLists.get(num);
    }


    public void list() {
        System.out.println(LINES);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskLists.size(); i++) {
            String display = String.format("    %d.%s", i + 1, taskLists.get(i).toString());
            System.out.println(display);
        }
        System.out.println(LINES);
    }

    public void removeTask(int num) {

        System.out.println(LINES);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + taskLists.get(num - 1).toString());
        taskLists.remove(num - 1);

        String taskRemainingString = String.format("    Now you have %d tasks in the list.", taskLists.size());
        System.out.println(taskRemainingString);
        System.out.println(LINES);
    }

    public void addTask(Task task) {
        taskLists.add(task);

        String displayTaskAmount = String.format("Now you have %d tasks in the list.", taskLists.size());

        System.out.println(LINES);
        // display to do task
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task.toString());
        System.out.println("    " + displayTaskAmount);
        System.out.println(LINES);
    }

    public void addWithoutPrint(Task task) {
        taskLists.add(task);
    }

    public void setTaskAsDone(int taskToMark) {
        taskLists.get(taskToMark - 1).markAsDone();

        System.out.println(LINES);
        System.out.println("    Nice! I've marked this task as done:");

        String taskMarkString = String.format("%s", taskLists.get(taskToMark - 1).toString());
        System.out.println("    " + taskMarkString);
        System.out.println(LINES);
    }

    public void setTaskAsUnDone(int taskToUnmark) {
        taskLists.get(taskToUnmark - 1).markAsNotDone();

        System.out.println(LINES);
        System.out.println("    OK, I've marked this task as not done yet:");

        String taskString = String.format("%s", taskLists.get(taskToUnmark - 1).toString());
        System.out.println("    " + taskString);
        System.out.println(LINES);
    }
}
