package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.DataFormatException;

/**
 * TaskList contains all the types of taskscommands in the application.
 */
public class TaskList {
    /**
     * Marks a task as done.
     * @param input
     * @return
     * @throws DukeException
     */
    public static String mark(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        assert arr.get(1) != "": "Task number to be marked must be given.";
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to mark?"
            );
        }
        int i = Integer.parseInt(arr.get(1));
        assert i <= Duke.list.size() & i > 0: "Task number should be <= Duke.list.size() & > 0";
        if (i <= Duke.list.size() && i > 0) {
            Task toBeMarked = Duke.list.get(Integer.parseInt(input.substring(5)) - 1);
            return toBeMarked.markAsDone();
        } else {
            throw new DukeException(
                    "That is not a valid task number!"
            );
        }
    }

    /**
     * Unmarks a task.
     * 
     * 
     * @param input
     * @throws DukeException
     */
    public static String unmark(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        assert arr.get(1) != "": "Task number to be unmarked must be given.";
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to unmark?"
            );
        }
        int i = Integer.parseInt(arr.get(1));
        assert i <= Duke.list.size() & i > 0: "Task number should be <= Duke.list.size() & > 0";
        if (i <= Duke.list.size() && i > 0) {
            Task toBeMarked = Duke.list.get(Integer.parseInt(input.substring(7)) - 1);
            return toBeMarked.markAsUndone();
        } else {
            throw new DukeException(
                    "That is not a valid task number!"
            );
        }
    }

    /**
     * Creates a deadline object to be added into the task list.
     * 
     * @param input user command.
     * @throws DukeException
     */
    protected static String deadline(String input) throws DukeException {
        String[] processedInput = stripDescription(input);
        assert processedInput[0] != "": "Description should not be empty.";
        if (processedInput[0] == "") {
            throw new DukeException(
                    "Oops, the description of deadline cannot be empty!"
            );
        }

        assert processedInput[1] != "": "Date must be provided for deadline.";
        if (processedInput[1] == "") {
            throw new DukeException("Oops, please tell me when is this due!");
        }

        try {
            LocalDate date = LocalDate.parse(stripDescription(input)[1]);
            Task deadline = new Deadline(stripDescription(input)[0], date);
            String msg = "Got it. I've added this task: \n";
            Duke.list.add(deadline);
            msg += deadline + "\n";
            msg += "Now you have " + Duke.list.size() + " tasks in the list.";
            return msg;
        } catch (DateTimeParseException e) {
            return "Please input a vaid date format YYYY-MM-DD";
        }
    }

    /**
     * Adds a task of Todo type into the list.
     * 
     * @param input
     * @throws DukeException
     */
    public static String todo(String input) throws DukeException {
        String[] processedInput = stripDescription(input);
        assert processedInput[0] != "": "Description cannot be empty.";
        if (processedInput[0] == "") {
            throw new DukeException(
                "Oops, the description of todo cannot be empty! Please tell me what you want to do."
            );
        }
        Task toDo = new Todo(processedInput[0]);
        Duke.list.add(toDo);
        String msg = "Got it. I've added this task: \n";
        msg += toDo + "\n";
        msg += "Now you have " + Duke.list.size() + " in the list.";
        return msg;
    }

    /**
     * Adds a task of Event type into the list.
     * 
     * 
     * @param input
     * @throws DukeException
     */
    protected static String event(String input) throws DukeException {
        String[] processedInput = stripDescription(input);
        assert processedInput[0] != "": "Description cannot be empty.";
        if (processedInput[0] == "") {
            throw new DukeException(
                    "Oops, the description of event cannot be empty!"
            );
        }

        assert processedInput[1] != "": "Date must be provided for event.";
        if (processedInput[1] == "") {
            throw new DukeException(
                    "Oops, please tell me where this will occur!"
            );
        }


        try {
            LocalDate date = LocalDate.parse(processedInput[1]);
            Task event = new Event(processedInput[0], date);
            String msg = "Got it. I've added this task: \n";
            Duke.list.add(event);
            msg += event + "\n";
            msg += "Now you have " + Duke.list.size() + " in the list.";
            return msg;
        } catch (DateTimeParseException e) {
            return "Please input a vaid date format YYYY-MM-DD";
        }
    }

    /**
     * Deletes a task from the list.
     * 
     * @param input
     * @throws DukeException
     */
    protected static String delete(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        assert arr.get(1) != "": "Task number must be provided.";
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to delete?"
            );
        }

        int i = Integer.parseInt(arr.get(1));
        assert i <= Duke.list.size() && i > 0: "Task number should be <= Duke.list.size() & > 0";
        if (i <= Duke.list.size() && i > 0) {
            String msg = "Noted. I've removed this task: \n";
            msg += Duke.list.get(i-1) + "\n";
            Duke.list.remove(i - 1);
            msg += "Now you have " + Duke.list.size() + " tasks in the list.";
            return msg;
        } else {
            throw new DukeException(
                    "That is not a valid task number!"
            );
        }
    }

    /**
     * Find tasks using keyword.
     * 
     * @param input
     * @throws DukeException
     */
    protected static String find(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        assert arr.get(1) != "": "Keyword should be provided.";
        assert Duke.list.size() > 0: "Tasklist size should be > 0";
        if (arr.get(1) == "") {
            throw new DukeException("Sorry, please tell me what you want to find!");
        }

        String word = arr.get(1);
        ArrayList<String> tasksFound = new ArrayList<>();
        for (Task task : Duke.list) {
            String description = task.toString();
            if (description.contains(word)) {
                tasksFound.add(description);
            }
        }

        if (tasksFound.size() < 1) {
            return "Sorry! I didn't find anything with \"" + word + "\" in it. :(";
        } else {
            String msg = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < tasksFound.size(); i++) {
                int count = i + 1;
                msg += count + ". " + tasksFound.get(i) + "\n";
            }
            return msg;
        }
    }

    /**
     * To process the input string and separate the description and date (if applicable).
     * 
     * @param s
     * @return a string array [description, date] where date="" if not applicable.
     * @throws DukeException
     */
    private static String[] stripDescription(String s) throws DukeException {

        ArrayList<String> arr = new ArrayList<>(Arrays.asList(s.split(" ")));
        if (arr.contains("/at")) {
            int i = arr.indexOf("/at");
            String str1 = String.join(" ", arr.subList(1, i));
            String str2 = String.join(" ", arr.subList(i + 1, arr.size()));
            String[] str = new String[2];



            str[0] = str1;
            str[1] = str2;
            return str;
        } else if (arr.contains("/by")) {
            int i = arr.indexOf("/by");
            String str1 = String.join(" ", arr.subList(1, i));
            String str2 = String.join(" ", arr.subList(i + 1, arr.size()));
            String[] str = new String[2];

            str[0] = str1;
            str[1] = str2;
            return str;
        } else {
            String str1 = String.join(" ", arr.subList(1, arr.size()));
            String str2 = "";
            String[] str = new String[2];
            str[0] = str1;
            str[1] = str2;
            return str;
        }

    }

}
