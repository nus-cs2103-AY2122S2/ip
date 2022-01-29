package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * TaskList class handles the commands to the Tasks that are stored in an ArrayList.
 *
 * @author Justin Ng Jie Ern
 */
public class TaskList {
    /**
     * ArrayList to store the Tasks that are inputted by the user.
     */
    private ArrayList<Task> tasks;

    /**
     * Parser Object to help pass the input of the user.
     */
    Parser parser;

    /**
     * Constructor to create a TaskList Object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.parser = new Parser();
    }

    /**
     * Getter of the ArrayList that are storing the Tasks.
     *
     * @return An ArrayList of the Tasks inputted by the user.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Command to adds Tasks into the TaskList.
     *
     * @param task Task that will be added into the TaskList.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Command to help find the number of Tasks in the TaskList.
     *
     * @return The length of the ArrayList.
     */
    public int size() {
        return this.tasks.toArray().length;
    }

    /**
     * Command to signal to the program to close.
     */
    public void bye() {
        String bye = "GoodBye! I hope to see you again!";
        System.out.println(bye);
    }

    /**
     * Command to list out all of the Tasks in the TaskList.
     */
    public void list() {
        int leng = tasks.toArray().length;
        if (leng == 0) {
            System.out.println("There are no pending tasks!");
        } else {
            System.out.println();
            for (int i = 0; i < leng; i++) {
                Task task = tasks.get(i);
                int num = i + 1;
                System.out.println(num + ": " + task.toString());
            }
        }
    }

    /**
     * Command to "mark" or "unmark" a Task in the TaskList.
     *
     * @param taskStr String of command inputted by the User.
     */
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

    /**
     * Command to add a Todo Task into the TaskList.
     *
     * @param taskStr String of Todo Task instruction.
     */
    public void todo(String taskStr) {
        // eg to_do borrow book (without the _)
        String taskName = parser.parseToDo(taskStr);
        ToDo task = new ToDo(taskName, false, "T");
        this.tasks.add(task);
        System.out.println("Added to your tasks: \n\t" + task.toString());
        System.out.println("You now have " + tasks.toArray().length + " tasks in your list");
    }

    /**
     * Command to add a Deadline Task into the TaskList.
     *
     * @param taskStr String of Deadline Task instruction.
     */
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

    /**
     * Command to add a Event Task into the TaskLIst.
     *
     * @param taskStr String of Event Task instruction.
     */
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

    /**
     * Command to delete a Task from the TaskList.
     *
     * @param taskStr String of the Task that you are trying to delete.
     */
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