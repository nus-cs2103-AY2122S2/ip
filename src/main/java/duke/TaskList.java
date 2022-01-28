package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    Parser parser;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.parser = new Parser();

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.toArray().length;
    }

    public void bye() {
        String bye = "GoodBye! I hope to see you again!";
        System.out.println(bye);
    }

    public void list() {
        int leng = tasks.toArray().length;
        if (leng == 0) {
            System.out.println("There are no pending tasks!");
        } else {
            System.out.println("");
            for (int i = 0; i < leng; i++) {
                Task task = tasks.get(i);
                int num = i + 1;
                System.out.println(num + ": " + task.toString());
            }
        }
    }

    public void taskCheck(String taskStr) {
        try {
            String[] taskArr = taskStr.split(" ");
            int index = Integer.parseInt(taskArr[1]) - 1;
            Task task = this.tasks.get(index);
            if (taskArr[0].equals("mark")) {
                task.setChecked(true);
                System.out.println("Nice! I've marked this task as done: \n\t" + task.toString());
            } else {
                this.tasks.get(index).setChecked(false);
                System.out.println("Alright, I've marked this task as not done yet: \n\t" + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("An invalid task index has been inputted");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That index number is out of range! Please try again!");
        }
    }

    public void todo(String taskStr) {
        // eg to_do borrow book (without the _)
        String taskName = parser.parseToDo(taskStr);
        ToDo task = new ToDo(taskName, false, "T");
        this.tasks.add(task);
        System.out.println("Added to your tasks: \n\t" + task.toString());
        System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
    }

    public void deadline(String taskStr) {
        try {
            String[] taskDetails = parser.parseDeadline(taskStr);
            String taskName = taskDetails[0];
            String date = taskDetails[1];
            Deadline task = new Deadline(taskName, false, "D", date);
            this.tasks.add(task);
            System.out.println("Added to your tasks: \n\t" + task.toString());
            System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
        }

    }

    public void event(String taskStr) {
        try {
            String[] taskDetails = parser.parseEvent(taskStr);
            String taskName = taskDetails[0];
            String date = taskDetails[1];
            Event task = new Event(taskName, false, "E", date);
            this.tasks.add(task);
            System.out.println("Added to your tasks: \n\t" + task.toString());
            System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
        }
    }

    public void delete(String taskStr) {
        try {
            String[] taskArr = parser.splitLimitTwo(taskStr);
            String deleteIndStr = taskArr[1];
            int deleteInd = Integer.parseInt(deleteIndStr) - 1;
            Task task = this.tasks.get(deleteInd);
            System.out.println("Removed from your tasks: \n\t" + task.toString());
            int num = tasks.toArray().length - 1;
            System.out.println("You now have " + num + " tasks in your list");
            this.tasks.remove(deleteInd);
        } catch (NumberFormatException e) {
            System.out.println("An invalid task index has been inputted! PLease try again!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That index number is out of range! Please try again!");
        }
    }
}