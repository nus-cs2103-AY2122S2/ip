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
     * Lists out tasks added to ArrayList
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
    public String exportOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += list.get(i).export() + "\n";
        }
        return output;
    }

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

    public void listFeature(String command, TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("You have no tasks at the moment!");
        } else {
            System.out.println("Here are the tasks in your list\n"
                    + list.listOut());
        }
    }
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
    public void markFeature(String command, TaskList list) {
        try {
            String[] commandArray = command.split(" ");
            String markStatus = commandArray[0];
            int itemNo = Integer.parseInt(commandArray[1]);
            // Edge cases
            if (itemNo > list.getSize() || itemNo < 1) {
                System.out.println("Error: That task does not exist!");
            }
            // Mark
            else if (markStatus.equals("mark")) {
                list.getTask(itemNo-1).markItem();
            }
            // Unmark
            else {
                list.getTask(itemNo-1).unmarkItem();
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: You forgot to indicate the task number!");
        }
    }

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

}

