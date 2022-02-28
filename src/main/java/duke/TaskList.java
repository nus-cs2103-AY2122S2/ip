package duke;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Returns a string of all the tasks in the ArrayList line by line.
     * It will number each line respectively up to the array size.
     *
     * @param arr The given ArrayList of tasks that the user currently has.
     * @return List of tasks.
     */
    public String list(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            return "Sorry, there is nothing in your list!";
        }
        String listToPrint = "";
        for (int i = 0; i < arr.size(); i++) {
            listToPrint += i + 1 + ". " + arr.get(i).toString() + "\n";
        }
        return listToPrint;
    }

    /**
     * Returns a string of an acknowledgement if successful, else returns an edge case warning.
     * Marks or un-marks the respective index of the task in the ArrayList given by the input.
     *
     * @param input The respective index of the ArrayList needed to be mark or unmarked.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string.
     * @throws NumberFormatException If input is less than or equals to zero.
     */
    public String mark(String input, ArrayList<Task> arr) {
        try {
            String s = input.replaceAll("\\D+", "");
            int clean = Integer.parseInt(s) - 1; // Parse to find what number in list to toggle
            // Edge Case
            if (clean > arr.size()) {
                return "Error! No tasked added";
            } else if (input.toCharArray()[0] != 'u') {
                arr.get(clean).setMarked();
                return "Nice! I've marked this task as done:\n "
                        + "   " + arr.get(clean).toString();
            } else {
                arr.get(clean).setUnmarked();
                return "OK, I've marked this task as not done yet:\n "
                        + "   " + arr.get(clean).toString();
            }
        } catch (Exception e) {
            return "Don't be cheeky. Please write something that makes sense.";
        }
    }

    /**
     * Removes the respective index of the task in the ArrayList given by the input.
     * Returns an acknowledgement if successful, else returns an edge case warning.
     *
     * @param input The respective index of the ArrayList needed to be removed.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string.
     */
    public String delete(String input, ArrayList<Task> arr) {
        try {
            String s = input.replaceAll("\\D+", "");
            int delete = Integer.parseInt(s) - 1; // Parse to find what number in list to delete
            // Edge Case
            if (delete > arr.size()) {
                return "Error! Nothing to delete!";
            } else {
                String deleteStringToReturn = "";
                deleteStringToReturn += "Noted. I've removed this task: \n   "
                       + arr.get(delete).toString() + "\n";
                arr.remove(delete);
                deleteStringToReturn += "Now you have " + arr.size() + " tasks in the list.";
                return deleteStringToReturn;
            }
        } catch (Exception e) {
            return "Error! Nothing to delete!";
        }
    }

    /**
     * Creates a new Deadline class with the input detailing the class variables.
     * Adds the new Deadline class to the ArrayList.
     * Returns an acknowledgement if successful.
     *
     * @param input The information required for the initialization of the Deadline class.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string.
     */
    public String deadline(String input, ArrayList<Task> arr) {
        try {
            String unprocessed = input.split("deadline ", 2)[1]; // Remove instruction
            String task = unprocessed.split(" /by ", 2)[0]; // Split to task
            String date = unprocessed.split(" /by ", 2)[1]; // Split to date
            Deadline newDeadline = new Deadline(task, date);
            arr.add(newDeadline);
            return "Got it! I've added this task: \n    "
                   + newDeadline + "\n"
                   + "Now you have " + arr.size() + " tasks in the list.";
        } catch (Exception e) {
            return "Don't be cheeky. Give me a deadline to stress you over.";
        }
    }

    /**
     * Creates a new Event class with the input detailing the class variables.
     * Adds the new Event class to the ArrayList.
     * Returns an acknowledgement if successful.
     *
     * @param input The information required for the initialization of the Event class.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string.
     */
    public String event(String input, ArrayList<Task> arr) {
        try {
            String nonevent = input.split("event ", 2)[1]; // Remove instruction
            String task = nonevent.split(" /at ", 2)[0]; // Split to task
            String date = nonevent.split(" /at ", 2)[1]; // Split to date
            Event newEvent = new Event(task, date);
            arr.add(newEvent);
            return "Got it! I've added this task: \n    "
                   + newEvent + "\n"
                   + "Now you have " + arr.size() + " tasks in the list.";
        } catch (Exception e) {
            return "Don't be cheeky. Give me an event to record.";
        }
    }

    /**
     * Searches the array for the given word within input.
     * First, it strips the input to isolate the word that needs to be found.
     * Then, it searches the array for the specific word.
     * It will return an acknowledgment (either positive or negative) if the word is found.
     *
     * @param input The information required for the search.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string (found / not found).
     */
    public String find(String input, ArrayList<Task> arr) {
        try {
            String word = input.split(" ", 2)[1]; // Remove instruction
            int counter = 1;
            boolean hasFound = false;
            String stringToFind = ""; // contains all words that have been found / not found.
            for (Task task : arr) {
                if (task.getName().contains(word) && !word.equals("")) {
                    if (!hasFound) {
                        stringToFind += "I found the matching items you requested: \n";
                    }
                    stringToFind += counter + "." + task + "\n";
                    counter++;
                    hasFound = true;
                }
            }
            if (!hasFound) {
                return "Unfortunately, I couldn't find anything related to " + word;
            } else {
                return stringToFind;
            }
        } catch (Exception e) {
            return "Don't be cheeky. Give me something to look for";
        }
    }

    /**
     * Creates a new Task class with the input detailing the class variables.
     * Adds the new Task class to the ArrayList.
     * Returns an acknowledgement if successful.
     *
     * @param input The information required for the initialization of the Task class.
     * @param arr The ArrayList with the current list of tasks.
     * @return Acknowledgement string.
     */
    public String toDo(String input, ArrayList<Task> arr) {
        try {
            String word = input.split(" ", 2)[1]; // Remove instruction
            ToDo newToDo = new ToDo(word);
            arr.add(newToDo);
            return "Got it! I've added this task: \n "
                    + newToDo + "\n"
                    + "Now you have " + arr.size() + " tasks in the list";
        } catch (Exception e) {
            return "Don't be cheeky. Give me something to do.";
        }
    }

    /**
     * Returns a large string of text which details all commands in task list.
     *
     * @return Help Text
     */
    public String help() {
        return "Oh I see you need help! Fret not! I'm here to help. \n"
                + "Ok, so we have several options that you can type. Let's go over them. \n"
                + "\n"
                + "1. ToDo \n"
                + "Type in 'todo xxx', and 'xxx' will be added into the list \n"
                + "2. Event \n"
                + "Type in 'event xxx /at YYY-MM-DD, and the event will be added into the list \n"
                + "3. Deadline \n"
                + "Type in 'deadline xxx /by YYY-MM-DD, and the deadline will be added into the list \n"
                + "4. Find \n"
                + "Type in 'find xxx', and I'll search the list for everything containing 'xxx' \n"
                + "5. List \n"
                + "Type in 'list' to see all items \n"
                + "6. Mark / Unmark \n"
                + "Type in 'mark [index]' or 'unmark [index] to mark / unmark them respectively. \n"
                + "For example, type in mark 1 to mark the first item in the list as done \n"
                + "7. Delete \n"
                + "Type in 'delete [index]' to delete the item at specified index. \n"
                + "For example, type in delete 1 to delete the first item in the list \n"
                + "\n"
                + "I hope that answers your questions!";
    }
}
