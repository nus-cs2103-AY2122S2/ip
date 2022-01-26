package duke;

import java.util.ArrayList;

public class TaskList {
    // divider
    String LINES = "    ---------------------------------";

    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int num) {
        return taskList.get(num);
    }


    public void list() {
        System.out.println(LINES);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String display = String.format("    %d.%s", i + 1, taskList.get(i).toString());
            System.out.println(display);
        }
        System.out.println(LINES);
    }

    public void removeTask(int num) {

        System.out.println(LINES);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + taskList.get(num - 1).toString());
        taskList.remove(num - 1);

        String taskRemainingString = String.format("    Now you have %d tasks in the list.", taskList.size());
        System.out.println(taskRemainingString);
        System.out.println(LINES);
    }

    public void addTask(Task task) {
        taskList.add(task);

        String displayTaskAmount = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(LINES);
        // display to do task
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task.toString());
        System.out.println("    " + displayTaskAmount);
        System.out.println(LINES);
    }

    public void setTaskAsDone(int taskToMark) {
        taskList.get(taskToMark - 1).markAsDone();

        System.out.println(LINES);
        System.out.println("    Nice! I've marked this task as done:");

        String taskMarkString = String.format("%s", taskList.get(taskToMark - 1).toString());
        System.out.println("    " + taskMarkString);
        System.out.println(LINES);
    }

    public void setTaskAsUnDone(int taskToUnmark) {
        taskList.get(taskToUnmark - 1).markAsNotDone();

        System.out.println(LINES);
        System.out.println("    OK, I've marked this task as not done yet:");

        String taskString = String.format("%s", taskList.get(taskToUnmark - 1).toString());
        System.out.println("    " + taskString);
        System.out.println(LINES);
    }

}
