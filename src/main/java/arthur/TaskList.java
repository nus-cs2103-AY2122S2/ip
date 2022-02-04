package arthur;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import arthur.task.Deadline;
import arthur.task.Event;
import arthur.task.Task;
import arthur.task.Todo;


/**
 * Handles the list of tasks and operations on it.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    /**
     * Constructs the taskList object
     * @param storage Storage object to use to store files
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.storage = storage;
        taskFiller();
    }

    /**
     * Lists out the tasks stored in the tasks Arraylist.
     * @return A string version of the list formatted with numbering.
     */
    public String listOut() {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list: \n");
        int tempCounter = 1;

        for (Task a : taskList) {
            temp.append(tempCounter).append(". ").append(a).append("\n");
            tempCounter++;
        }
        return temp.toString();
    }

    /**
     * Marks or Unmark the indicated task
     * @param e The instruction to follow with the task number
     * @return The string result of the instruction
     */
    public String marker(String e) {
        String[] temp = e.split(" ");

        try {
            Task currTask = taskList.get(Integer.parseInt(temp[1]) - 1);
            if (temp[0].equals("mark")) {
                currTask.mark();
                return "Good job! Task.Task Completed \n" + currTask;
            } else {
                currTask.unmark();
                return "Alright, I will unmark this \n" + currTask;
            }
        } catch (IndexOutOfBoundsException a) {
            return "Invalid task number. Please try again";
        }
    }

    /**
     * Initialises and adds new Task.Todo task to task list.
     * @param e The description of the todo task to be added
     * @return String conformation of the input
     */
    public String todo(String e) {
        Todo temp = new Todo(e);
        taskList.add(temp);
        return "Added a new Task.Todo task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with deadline to task list.
     * String after "/by " will be taken as the deadline info.
     * @param e The description of the task with deadline info to be added
     * @return String conformation of the input
     */
    public String deadline(String e) {
        Deadline temp;
        try {
            String[] tempArr = e.split("/by ");
            temp = new Deadline(tempArr[0], tempArr[1]);
        } catch (IndexOutOfBoundsException a) {
            return "Please add the deadline date";
        }
        taskList.add(temp);
        return "Added a new Task.Deadline task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with timing to task list.
     * String after "/at " will be taken as the date/time the task
     * would occur.
     * @param e The description of the task with timing info to be added
     * @return String conformation of the input
     */
    public String event(String e) {
        Event temp;
        try {
            String[] tempArr = e.split("/at ");
            temp = new Event(tempArr[0], tempArr[1]);
        } catch (IndexOutOfBoundsException a) {
            return "Please add the event Date and/or Time";
        }

        taskList.add(temp);
        return "Added a new Task.Event task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Generates a string about the number of tasks left in list
     * @return String info regarding num of tasks left
     */
    public String outstanding() {
        return "You have "
                + this.taskList.size()
                + " tasks in list at the moment.";
    }

    /**
     * Deletes the task from the list.
     * @param i The task number to delete
     * @return String conformation of the task deletion
     */
    public String deleter(int i) {
        try {
            Task currTask = taskList.remove(i - 1);
            return "Successfully removed this task: \n" + currTask
                    + "\n" + outstanding();
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number. Please try again";
        }
    }

    /**
     * Copies over tasks in the data file to the taskList.
     */
    private void taskFiller() {
        try {
            Scanner sc = new Scanner(storage.getTasks());
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                String taskInfo = temp.split(" >> ")[1];
                char taskType = temp.charAt(1);
                char marking = temp.charAt(4);
                switch (taskType) {
                case 'T':
                    todo(taskInfo);
                    break;
                case 'D':
                    taskInfo = taskInfo.replaceFirst("\\(By:", "/by");
                    taskInfo = taskInfo.replaceFirst("\\)", "");
                    deadline(taskInfo);
                    break;
                case 'E':
                    taskInfo = taskInfo.replaceFirst("\\(At:", "/at");
                    taskInfo = taskInfo.replaceFirst("\\)", "");
                    event(taskInfo);
                    break;
                default:
                    break;
                }

                if (marking == 'X') {
                    taskList.get(taskList.size() - 1).mark();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the Task.Task object at the given index from taskList.
     * @param num The index of the Task.Task object to retrieve
     * @return A Task.Task object
     * @throws IndexOutOfBoundsException Incorrect index needs to be handled properly
     */
    public Task getTask(int num) throws IndexOutOfBoundsException {
        return this.taskList.get(num);
    }

    /**
     * Gives the taskList size.
     * @return list size in int
     */
    public int tasksSize() {
        return this.taskList.size();
    }

    /**
     * Finds tasks matching the given str.
     * @param str Used to find task similar to this input.
     * @return A string of all the matching tasks.
     */
    public String find(String str) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list: \n");
        int tempCounter = 1;

        for (Task a : taskList) {
            if (a.toString().contains(str)) {
                temp.append(tempCounter).append(". ").append(a).append("\n");
                tempCounter++;
            }
        }
        return temp.toString();
    }
}
