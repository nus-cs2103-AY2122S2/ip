package duke;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class contains the task list and deals with add/delete list operations.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TaskList {
    // ArrayList to store all your tasks
    private ArrayList<Task> list;

    // Constructor
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Deletes task from the list.
     *
     * @param index the position of the task.
     * @throws DukeException if position of the task exceeds what we have on the list.
     */
    public void deleteTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.remove(index);
            System.out.println("NOTED. I'VE REMOVED THIS TASK:");
            System.out.println(task);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        }
    }

    /**
     * Adds to do task into our current list.
     *
     * @param desc the task description entered by the user
     * @throws DukeException if task description is empty.
     */
    public void addToDo(String desc, boolean done) throws DukeException {
        if (desc == null) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        Task newTask = new ToDo(desc, done);
        list.add(newTask);
        System.out.println("GOT IT. I'VE ADDED THIS TASK:");
        System.out.println(newTask);
        System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
    }

    /**
     * Adds deadline task into our current list.
     *
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param done the current status of the task
     * @throws DukeException if task description or date is empty.
     */
    public void addDeadline(String desc, String date, boolean done) throws DukeException {
        if (desc == null) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (date == null) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        try {
            LocalDate date2 = LocalDate.parse(date);
            Task newTask = new Deadline(desc, date2, done);
            list.add(newTask);
            System.out.println("GOT IT. I'VE ADDED THIS TASK:");
            System.out.println(newTask);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE FORMAT.");
        }
    }

    /**
     * Adds event task into our current list.
     *
     * @param desc the task description entered by the user.
     * @param date the date entered by the user.
     * @param done the current status of the task
     * @throws DukeException if task description or date is empty.
     */
    public void addEvent(String desc, String date, boolean done) throws DukeException {
        if (desc == null) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (date == null) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        try {
            LocalDate date2 = LocalDate.parse(date);
            Task newTask = new Event(desc, date2, done);
            list.add(newTask);
            System.out.println("GOT IT. I'VE ADDED THIS TASK:");
            System.out.println(newTask);
            System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE FORMAT.");
        }
    }

    /**
     * Prints out all the tasks in the list.
     */
    public void listTask() {
        int num = 1;
        System.out.println("HERE ARE THE TASKS IN YOUR LIST:");
        for (Task task: list) {
            System.out.println(num + "." + task);
            num++;
        }
    }

    /**
     * Changes the completion status of the task
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public void markTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsDone();
            System.out.println("NICE! I'VE MARKED THIS TASK AS DONE:");
            System.out.println(task);
        }
    }

    /**
     * Changes the completion status of the task
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public void unMarkTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsNotDone();
            System.out.println("OKAY! I'VE MARKED THIS TASK AS NOT DONE:");
            System.out.println(task);
        }
    }

    /**
     * Finds a list of tasks with a keyword specified by the user.
     *
     * @param taskName the keyword entered by the user.
     */
    public void findTask(String taskName) {
        int num = 1;
        int matchNum = 0;
        System.out.println("HERE ARE THE MATCHING TASKS IN YOUR LIST:");
        for (Task task : list) {
            String str = task.getDesc();
            if (taskName == null) {
                break;
            } else if (str.contains(taskName)) {
                System.out.println(num + "." + task);
                matchNum++;
            }
            num++;
        }
        System.out.println("MATCH FOUND: " + matchNum);
    }

    /**
     * Gets a specific task from the list based on position.
     *
     * @param index the position of the task in the list.
     * @return a task object.
     */
    public Task getIndex(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Adds task into list without printing anything.
     */
    public void addTask(Task task) {
        list.add(task);
    }
}
