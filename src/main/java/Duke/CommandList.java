package duke;

import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandList {

    /**
     * Executes user's input.
     *
     * @param input command to execute
     * @param list Arraylist of Tasks to manipulate
     * @param storage Storage object to load/store list of Tasks
     * @return String containing the reply from Duke to the user
     */
    public String execute(String input, ArrayList<Task> list, Storage storage) {
        String[] inputs = input.split(" ");
        String firstWord = inputs[0];

        String output = "";

        if (input.equals("list")) { // list all tasks
            output = listTasks(list);
        } else if (firstWord.equals("mark") || firstWord.equals("unmark")) { // mark/unmark task
            output = toggleStatus(list, inputs, firstWord, storage);
        } else if (firstWord.equals("delete")) { // delete task
            output = deleteTask(list, inputs, storage);
        } else if (firstWord.equals("todo")) { // adds a ToDo Task
            output = addToDo(list, inputs, input, storage);
        } else if (firstWord.equals("deadline")) { // adds a Deadline Task
            output = addDeadline(list, inputs, input, storage);
        } else if (firstWord.equals("event")) { // adds an Event Task
            output = addEvent(list, inputs, input, storage);
        } else if (firstWord.equals("find")) { // find tasks by a keyword
            output = find(list, inputs);
        } else if (firstWord.equals("update")) { // updates a Task
            output = updateTask(list, inputs, input, storage);
        } else { // print error message
            output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return output;
    }

    /**
     * List all Tasks.
     *
     * @param list Arraylist of Tasks to manipulate
     * @return String output of all tasks
     */
    public String listTasks(ArrayList<Task> list) {
        String output = "Here are the tasks in your list:\n";

        for (int i = 0; i < list.size(); i++) {
            output = output + ((i + 1) + "." + list.get(i)) + "\n";
        }

        return output;
    }

    /**
     * Mark/unmark a specified Task.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param firstWord First word from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output of whether a task is marked/unmarked
     */
    public String toggleStatus(ArrayList<Task> list, String[] inputs, String firstWord, Storage storage) {
        String secondWord = inputs[1];
        int index = Integer.parseInt(secondWord);
        Task targetTask = list.get((index - 1));
        String output = "";

        if (firstWord.equals("mark")) {
            output = targetTask.mark();
        } else {
            output = targetTask.unmark();
        }

        storage.store(list);
        return output;
    }

    /**
     * Deletes specified Task from list.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output stating that a Task has been deleted
     */
    public String deleteTask(ArrayList<Task> list, String[] inputs, Storage storage) {
        String secondWord = inputs[1];
        int index = Integer.parseInt(secondWord);

        Task targetTask = list.get((index - 1));
        list = targetTask.delete(list, (index - 1));
        String output = "Noted. I've removed this task: \n" + targetTask + "\n"
                + "Now you have " + list.size() + " tasks in your list.";

        storage.store(list);
        return output;
    }

    /**
     * Adds a new Task as a ToDo.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param input User's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output of ToDo successfully added
     */
    public String addToDo(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        if (inputs.length == 1) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }
        ToDo toDo = new ToDo(input.substring((5)));
        list.add(toDo);
        storage.store(list);
        return "Got it. I've added this task:\n" + toDo + "\nNow you have " + list.size() + " tasks in your list.";
    }

    /**
     * Adds a new Task as a Deadline.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param input User's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output of Deadline successfully added
     */
    public String addDeadline(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        String output;

        if (inputs.length == 1) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        }

        int start = input.indexOf("/");
        if (start == -1) { // if / char cannot be found
            return "Please enter a valid date!";
        }

        String date = input.substring(start + 4);
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formattedDate = dateFormatter.parse(date);
            String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

            Deadline deadline = new Deadline(input.substring(9, start - 1), dateToString);
            list.add(deadline);
            storage.store(list);
            output = "Got it. I've added this task:\n" + deadline + "\nNow you have " + list.size() +
                    " tasks in your list.";
        } catch (ParseException e) {
            output = "Please enter a valid date!";
        }

        return output;
    }

    /**
     * Adds a new Task as an Event.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param input User's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output of Event successfully added
     */
    public String addEvent(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        String output;

        if (inputs.length == 1) {
            return "☹ OOPS!!! The description of an event cannot be empty.";
        }

        int start = input.indexOf("/");
        if (start == -1) { // if / char cannot be found
            return "Please enter a valid date!";
        }

        String date = input.substring(start + 4);
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formattedDate = dateFormatter.parse(date);
            String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

            Event event = new Event(input.substring(6, start - 1), dateToString);
            list.add(event);
            storage.store(list);
            output = "Got it. I've added this task:\n" + event + "\nNow you have " + list.size() +
                    " tasks in your list.";
        } catch (ParseException e) {
            output = "Please enter a valid date!";
        }

        return output;
    }

    /**
     * Finds Tasks matching user's search input.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @return String output of Tasks matching search criteria
     */
    public String find(ArrayList<Task> list, String[] inputs){
        String output;
        String secondWord = inputs[1];
        List<Task> matches = new ArrayList<>();

        for(Task task: list) {
            if (task.toString().contains(secondWord)) {
                matches.add(task);
            }
        }

        if (matches.isEmpty()) {
            return "No matching tasks found.";
        }
        output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matches.size(); i++) {
            output = output + ((i + 1) + "." + matches.get(i)) + "\n";
        }
        return output;
    }

    /**
     * Updates specified Task from list.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output stating that a Task has been updated
     */
    public String updateTask(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        String output = "";
        String secondWord = inputs[1];
        int index = Integer.parseInt(secondWord);

        Task targetTask = list.get((index - 1));
        String taskType = targetTask.getTaskType();

        if (taskType.equals("T")) {
            output = updateToDo(list, inputs, input, storage);
        } else if (taskType.equals("D")) {
            output = updateDeadline(list, inputs, input, storage);
        } else if (taskType.equals("E")) {
            output = updateEvent(list, inputs, input, storage);
        }

        return output;
    }

    /**
     * Updates specified ToDo Task from list.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output based on whether Task update was successful
     */
    public String updateToDo(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        if (inputs.length == 2) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }

        String secondWord = inputs[1];
        int secondWordLength = secondWord.length();
        int index = Integer.parseInt(secondWord);
        int indexPosition = input.indexOf(secondWord);

        ToDo toDo = new ToDo(input.substring(indexPosition + secondWordLength));
        list.set((index - 1), toDo);
        storage.store(list);
        return "Noted. I've updated this task: \n" + toDo;
    }

    /**
     * Updates specified Deadline Task from list.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output based on whether Task update was successful
     */
    public String updateDeadline(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        String output;

        if (inputs.length == 2) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        }

        int start = input.indexOf("/");
        if (start == -1) { // if / char cannot be found
            return "Please enter a valid date!";
        }

        String date = input.substring(start + 4);
        try {
            String secondWord = inputs[1];
            int secondWordLength = secondWord.length();
            int index = Integer.parseInt(secondWord);
            int indexPosition = input.indexOf(secondWord);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formattedDate = dateFormatter.parse(date);
            String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

            Deadline deadline = new Deadline(input.substring(indexPosition + secondWordLength, start - 1),
                    dateToString);
            list.set((index - 1), deadline);
            output = "Noted. I've updated this task: \n" + deadline;
            storage.store(list);
        } catch (ParseException e) {
            output = "Please enter a valid date!";
        }

        return output;
    }

    /**
     * Updates specified Event Task from list.
     *
     * @param list Arraylist of Tasks to manipulate
     * @param inputs Array of String from user's input
     * @param storage Storage object to load/store list of Tasks
     * @return String output based on whether Task update was successful
     */
    public String updateEvent(ArrayList<Task> list, String[] inputs, String input, Storage storage) {
        String output;

        if (inputs.length == 2) {
            return "☹ OOPS!!! The description of an event cannot be empty.";
        }

        int start = input.indexOf("/");
        if (start == -1) { // if / char cannot be found
            return "Please enter a valid date!";
        }

        String date = input.substring(start + 4);
        try {
            String secondWord = inputs[1];
            int secondWordLength = secondWord.length();
            int index = Integer.parseInt(secondWord);
            int indexPosition = input.indexOf(secondWord);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date formattedDate = dateFormatter.parse(date);
            String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

            Event event = new Event(input.substring(indexPosition + secondWordLength, start - 1),
                    dateToString);
            list.set((index - 1), event);
            output = "Noted. I've updated this task: \n" + event;
            storage.store(list);
        } catch (ParseException e) {
            output = "Please enter a valid date!";
        }

        return output;
    }
}
