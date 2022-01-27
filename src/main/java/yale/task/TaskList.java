package yale.task;

import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * Customised ArrayList class called TaskList
 * to contain Task objects
 */
public class TaskList {
    /**
     * ArrayList that can contain
     * elements of type Task
     */
    ArrayList<Task> list;

    /**
     * Constructor method
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds Task item into the ArrayList
     * @param taskItem
     */
    public void addToList(Task taskItem) {
        list.add(taskItem);
    }

    /**
     * Returns a String containing the tasks
     * that are in the list
     * @return List of tasks
     */
    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + "."
                    + list.get(i) + "\n";
        }
        return output;
    }

    /**
     * Exports out tasks in a custom String format
     * @return
     */
    public String exportOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).export() + "\n";
        }
        return output;
    }

    /**
     * Reads the tasks in the specified file and
     * creates them. These tasks are then added to
     * the list
     * @param fileData
     */
    public void importIn(String fileData) {
        if (fileData.equals("")) {
            return;
        }
        String[] lines = fileData.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] tokens = lines[i].split(" \\| ");
            String name = tokens[2];
            boolean isMarked = tokens[1].equals("1");
            if (tokens[0].equals("T")) {
                ToDo newTodo = new ToDo(name, isMarked);
                list.add(newTodo);
            } else if (tokens[0].equals("D")) {
                String by = tokens[3];
                Deadline newDeadline = new Deadline(name, isMarked, by);
                list.add(newDeadline);
            } else if (tokens[0].equals("E")) {
                String at = tokens[3];
                Event newEvent = new Event(name, isMarked, at);
                list.add(newEvent);
            }
        }
    }
    /**
     * Getter method to retrieve Item
     * from specific position in the list
     * @param itemNo
     * @return Task at specified position in list
     */
    public Task getTask(int itemNo) {
        return list.get(itemNo);
    }

    /**
     * Getter method to retrieve size
     * of list
     * @return Size of list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Deletes item at specified position
     * in list
     * @param itemNo
     */
    public void deleteTask(int itemNo) {
        list.remove(itemNo);
    }

    /**
     * Calls listOut() method if there are any tasks in the list.
     * @param command
     * @param list
     */
    public void listFeature(String command, TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list\n"
                    + list.listOut());
        }
    }

    /**
     * Deletes a specific task from the list
     * @param command
     * @param list
     */
    public void deleteFeature(String command, TaskList list) {
        try {
            String[] commandArray = command.split(" ");
            int itemNo = Integer.parseInt(commandArray[1]);
            // Edge cases
            if (itemNo > list.getSize() || itemNo < 1) {
                System.out.println("Error: That task does not exist!");
            } else {
                System.out.println("Noted. I've removed this task:\n   " +
                        list.getTask(itemNo-1).toString());
                list.deleteTask(itemNo-1);
                System.out.println("Now you have " + list.getSize() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: You forgot to indicate the task number!");
        }
    }

    /**
     * Marks a specific task in the list
     * @param command
     * @param list
     */
    public void markFeature(String command, TaskList list) {
        try {
            String[] commandArray = command.split(" ");
            String markStatus = commandArray[0];
            int itemNo = Integer.parseInt(commandArray[1]);
            if (itemNo > list.getSize() || itemNo < 1) { // Edge cases
                System.out.println("Error: That task does not exist!");
            } else if (markStatus.equals("mark")) { // Mark
                list.getTask(itemNo-1).markTask();
                System.out.println("Nice! I've marked this task as done:\n"
                        + list.getTask(itemNo-1).toString());
            } else { // Unmark
                list.getTask(itemNo-1).unmarkTask();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + list.getTask(itemNo-1).toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: You forgot to indicate the task number!");
        }
    }

    /**
     * Creates a ToDo object if the user input contains
     * "todo" and adds it into the list
     * @param command
     * @param list
     */
    public void todoFeature(String command, TaskList list) {
        try {
            String task = command.split("todo ", 2)[1]; // Remove word
            ToDo newToDo = new ToDo(task, false);
            list.addToList(newToDo);
            System.out.println("Got it! I've added this task:\n    " +
                    newToDo.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates a Deadline object if the user input contains
     * "deadline" and adds it into the list
     * @param command
     * @param list
     */
    public void deadlineFeature(String command, TaskList list) {
        try {
            String removeDeadline = command.split("deadline ", 2)[1]; // Remove Deadline word
            String task = removeDeadline.split(" /by ", 2)[0]; // Retrieve task name
            String date = removeDeadline.split(" /by ", 2)[1]; // Retrieve date
            Deadline newDeadline = new Deadline(task, false, date);
            list.addToList(newDeadline);
            System.out.println("Got it! I've added this task:\n    " +
                    newDeadline.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: The description of an event cannot be empty.");
        } catch (DateTimeException e) {
            System.out.println("Error: Invalid date entered.");
        }
    }

    /**
     * Creates an Event object if the user input contains
     * "event" and adds it into the list
     * @param command
     * @param list
     */
    public void eventFeature(String command, TaskList list) {
        try {
            String removeEvent = command.split("event ", 2)[1]; // Remove Event word
            String task = removeEvent.split(" /at ", 2)[0]; // Retrieve task name
            String date = removeEvent.split(" /at ", 2)[1]; // Retrieve date
            Event newEvent = new Event(task, false, date);
            list.addToList(newEvent);
            System.out.println("Got it! I've added this task:\n    " +
                    newEvent.toString() + "\n" +
                    "Now you have " + list.getSize() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: The description of an event cannot be empty.");
        } catch (DateTimeException e) {
            System.out.println("Error: Invalid date entered.");
        }
    }

    public void findFeature(String command, TaskList list) {
        String taskName = command.split("find ", 2)[1]; // Retrieve task name
        int count = 1;
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getTask(i).name.contains(taskName)) {
                output += count + "." + list.getTask(i).toString() + "\n";
                count += 1;
            }
        }
        if (output.equals("Here are the matching tasks in your list:\n") || taskName.equals(" ")) {
            System.out.println("There are no matching tasks!");
        } else {
            System.out.println(output);
        }
    }

}

