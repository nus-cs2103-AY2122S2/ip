package yale.task;

import java.time.DateTimeException;
import java.util.ArrayList;

import yale.command.Ui;

/**
 * Customised ArrayList class called TaskList
 * to contain Task objects
 */
public class TaskList {
    /**
     * ArrayList that can contain
     * elements of type Task.
     */
    private ArrayList<Task> list;

    /**
     * Constructor method.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds Task item into the ArrayList
     * @param taskItem Task object to be added into list.
     */
    public void addToList(Task taskItem) {
        list.add(taskItem);
    }

    /**
     * Returns a String containing the tasks
     * that are in the list.
     * @return Returns String listing all tasks.
     */
    public String listOut() {
        String output = "";
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            output += i + 1 + ". "
                    + list.get(i) + "\n";
        }
        return output;
    }

    /**
     * Exports out tasks in a custom String format.
     * @return Returns customised format or tasks
     * that is written into specified file.
     */
    public String exportOut() {
        String output = "";
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            output += list.get(i).export() + "\n";
        }
        return output;
    }

    /**
     * Reads the tasks in the specified file and
     * creates them. These tasks are then added to
     * the list
     * @param fileData String containing contents of file.
     */
    public void importIn(String fileData) {
        if (fileData.equals("")) {
            Ui.isNotSaved();
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
            } else if (tokens[0].equals("DW")) {
                String startPeriod = tokens[3];
                String endPeriod = tokens[4];
                DoWithinPeriod newDoWithinPeriod = new DoWithinPeriod(name, isMarked, startPeriod, endPeriod);
                list.add(newDoWithinPeriod);
            }
        }
    }

    /**
     * Getter method to retrieve Item
     * from specific position in the list.
     * @param itemNo Integer assigned to Task.
     * @return Task at specified position in list.
     */
    public Task getTask(int itemNo) {
        return list.get(itemNo);
    }

    /**
     * Getter method to retrieve size of list.
     * @return Size of list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Deletes item at specified position in list.
     * @param itemNo
     */
    public void deleteTask(int itemNo) {
        list.remove(itemNo);
    }

    /**
     * Clears all Task objects from TaskList
     */
    private void clearTaskList() {
        list.clear();
    }

    /**
     * Calls listOut() method if there are any tasks in the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format of Tasks in list.
     */
    public String listFeature(String command, TaskList list) {
        boolean emptyList = list.getSize() == 0;
        if (emptyList) {
            return "You have no tasks at the moment!";
        } else {
            assert list.getSize() != 0 : "List size should be more than 0";
            return "Here are the tasks in your list:\n"
                    + list.listOut();
        }
    }

    /**
     * Removes all Task objects from Tasklist list.
     * @param list TaskList containing Task objects.
     * @return String response indicating clearance.
     */
    public String clearListFeature(TaskList list) {
        list.clearTaskList();
        String cleared = "Your list has been cleared.";
        return cleared + "\nNow you have "
                + list.getSize() + " tasks in the list.";
    }

    /**
     * Deletes a specific task from the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String response indicating that Task has been deleted.
     */
    public String deleteFeature(String command, TaskList list) {
        try {
            String[] commandArray = command.split(" ");
            int itemNo = Integer.parseInt(commandArray[1]);
            boolean isExceed = itemNo > list.getSize();
            boolean isInvalid = itemNo < 1;
            int taskNumber = itemNo - 1;
            // Edge cases
            if (isExceed || isInvalid) {
                return "Error: That task does not exist!";
            } else {
                assert itemNo <= list.getSize() : "Task number should be within list size";
                String noted = "Noted. I've removed this task:\n   "
                        + list.getTask(taskNumber).toString();
                list.deleteTask(taskNumber);
                return noted + "\nNow you have " + list.getSize() + " tasks in the list.";
            }
        } catch (NumberFormatException e) {
            return "Error: Please enter a valid task number";
        } catch (IndexOutOfBoundsException e) {
            return "Error: You forgot to indicate the task number!";
        }
    }

    /**
     * Marks a specific task in the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format indicating Task has been marked/unmarked.
     */
    public String markFeature(String command, TaskList list) {
        try {
            String[] commandArray = command.split(" ");
            String markStatus = commandArray[0];
            int itemNo = Integer.parseInt(commandArray[1]);
            boolean isExceed = itemNo > list.getSize();
            boolean isInvalid = itemNo < 1;
            int taskNumber = itemNo - 1;
            if (isExceed || isInvalid) { // Edge cases
                return "Error: That task does not exist!";
            } else if (markStatus.equals("mark")) { // Mark
                list.getTask(taskNumber).markTask();
                return "Nice! I've marked this task as done:\n    "
                        + list.getTask(taskNumber).toString();
            } else { // Unmark
                list.getTask(taskNumber).unmarkTask();
                return "OK, I've marked this task as not done yet:\n    "
                        + list.getTask(taskNumber).toString();
            }
        } catch (NumberFormatException e) {
            return "Error: Please enter a valid task number";
        } catch (IndexOutOfBoundsException e) {
            return "Error: You forgot to indicate the task number!";
        }
    }

    /**
     * Creates a ToDo object and adds it into the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format indicating ToDo Task has been added to list.
     */
    public String todoFeature(String command, TaskList list) {
        try {
            String task = command.split("todo ", 2)[1]; // Remove word
            ToDo newToDo = new ToDo(task, false);
            list.addToList(newToDo);
            return "Got it! I've added this task:\n    "
                    + newToDo.toString() + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: The description of a todo cannot be empty.";
        }
    }

    /**
     * Creates a Deadline object and adds it into the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format indicating Deadline Task has been added to list.
     */
    public String deadlineFeature(String command, TaskList list) {
        try {
            String removeDeadline = command.split("deadline ", 2)[1]; // Remove Deadline word
            String task = removeDeadline.split(" /by ", 2)[0]; // Retrieve task name
            String date = removeDeadline.split(" /by ", 2)[1]; // Retrieve date
            Deadline newDeadline = new Deadline(task, false, date);
            list.addToList(newDeadline);
            return "Got it! I've added this task:\n    "
                    + newDeadline.toString() + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: The description of an event cannot be empty.";
        } catch (DateTimeException e) {
            return "Error: Invalid date entered.";
        }
    }

    /**
     * Creates an Event object and adds it into the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format indicating Event Task has been added to list.
     */
    public String eventFeature(String command, TaskList list) {
        try {
            String removeEvent = command.split("event ", 2)[1]; // Remove Event word
            String task = removeEvent.split(" /at ", 2)[0]; // Retrieve task name
            String date = removeEvent.split(" /at ", 2)[1]; // Retrieve date
            Event newEvent = new Event(task, false, date);
            list.addToList(newEvent);
            return "Got it! I've added this task:\n    "
                    + newEvent.toString() + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: The description of an event cannot be empty.";
        } catch (DateTimeException e) {
            return "Error: Invalid date entered.";
        }
    }

    /**
     * Creates a DoWithin object and adds it into the list.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String format indicating DoWithin Task has been added to list.
     */
    public String doWithinPeriodFeature(String command, TaskList list) {
        try {
            String removeEvent = command.split("period ", 2)[1]; // Remove period word
            String task = removeEvent.split(" /between ", 2)[0]; // Retrieve task name
            String dates = removeEvent.split(" /between ", 2)[1]; // Retrieve date
            String startPeriod = dates.split(" and ", 2)[0];
            String endPeriod = dates.split(" and ", 2)[1];
            DoWithinPeriod newDoWithinPeriod = new DoWithinPeriod(task, false, startPeriod, endPeriod);
            list.addToList(newDoWithinPeriod);
            return "Got it! I've added this task:\n    "
                    + newDoWithinPeriod.toString() + "\n"
                    + "Now you have " + list.getSize() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "Error: The description of a DoWithinPeriod cannot be empty.";
        } catch (DateTimeException e) {
            return "Error: Invalid date entered.";
        }
    }

    /**
     * Retrieves Task objects that contain the specified keyword.
     * @param command User input of type String.
     * @param list List of Task objects.
     * @return String response to user.
     */
    public String findFeature(String command, TaskList list) {
        try {
            String inputName = command.split("find ", 2)[1]; // Retrieve task name
            int count = 1;
            String output = "Here are the matching tasks in your list:\n";
            int listSize = list.getSize();
            for (int i = 0; i < listSize; i++) {
                Task task = list.getTask(i);
                String taskName = task.name;
                boolean isMatched = taskName.contains(inputName);
                if (isMatched) {
                    output += count + ". " + list.getTask(i).toString() + "\n";
                    count += 1;
                }
            }
            boolean isNoMatch = output.equals("Here are the matching tasks in your list:\n")
                    || inputName.equals(" ");
            if (isNoMatch) {
                return "There are no matching tasks!";
            } else {
                return output;
            }
        } catch (IndexOutOfBoundsException e) {
            return "Error: You forgot to indicate the task to find!";
        }

    }

}

