package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * TaskList contains all the types of taskscommands in the application.
 */
public class TaskList {
    /**
     * Marks a task as done.
     * 
     * 
     * @param input
     * @throws DukeException
     */
    public static void mark(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to mark?"
            );
        }
        int i = Integer.parseInt(arr.get(1));
        if (i <= Duke.list.size() && i > 0) {
            Task toBeMarked = Duke.list.get(Integer.parseInt(input.substring(5)) - 1);
            toBeMarked.markAsDone();
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
    public static void unmark(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to unmark?"
            );
        }
        int i = Integer.parseInt(arr.get(1));
        if (i <= Duke.list.size() && i > 0) {
            Task toBeMarked = Duke.list.get(Integer.parseInt(input.substring(7)) - 1);
            toBeMarked.markAsUndone();
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
    protected static void deadline(String input) throws DukeException {
        if (stripDescription(input)[0] == "") {
            throw new DukeException(
                    "Oops, the description of deadline cannot be empty!"
            );
        }

        if (stripDescription(input)[1] == "") {
            throw new DukeException("Oops, please tell me when is this due!");
        }

        LocalDate date = LocalDate.parse(stripDescription(input)[1]);

        Task deadline = new Deadline(stripDescription(input)[0], date);
        System.out.println("Got it. I've added this task:");
        Duke.list.add(deadline);
        System.out.println(deadline.toString());
        System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");
    }

    /**
     * Adds a task of Todo type into the list.
     * 
     * @param input
     * @throws DukeException
     */
    public static void todo(String input) throws DukeException {
        if (stripDescription(input)[0] == "") {
            throw new DukeException(
                "Oops, the description of todo cannot be empty! Please tell me what you want to do."
            );
        }
        Task toDo = new Todo(stripDescription(input)[0]);
        Duke.list.add(toDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(toDo.toString());
        System.out.println("Now you have " + Duke.list.size() + " in the list.");
    }

    /**
     * Adds a task of Event type into the list.
     * 
     * 
     * @param input
     * @throws DukeException
     */
    protected static void event(String input) throws DukeException {
        if (stripDescription(input)[0] == "") {
            throw new DukeException(
                    "Oops, the description of event cannot be empty!"
            );
        }

        if (stripDescription(input)[1] == "") {
            throw new DukeException(
                    "Oops, please tell me where this will occur!"
            );
        }

        LocalDate date = LocalDate.parse(stripDescription(input)[1]);
        Task event = new Event(stripDescription(input)[0], date);
        System.out.println("Got it. I've added this task:");
        Duke.list.add(event);
        System.out.println(event.toString());
        System.out.println("Now you have " + Duke.list.size() + " in the list.");
    }

    /**
     * Deletes a task from the list.
     * 
     * @param input
     * @throws DukeException
     */
    protected static void delete(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (arr.get(1) == "") {
            throw new DukeException(
                    "Oops! Which task would you like to delete?"
            );
        }

        int i = Integer.parseInt(arr.get(1));
        if (i <= Duke.list.size() && i > 0) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(Duke.list.get(i-1).toString());
            Duke.list.remove(i - 1);
            System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");

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
    protected static void find(String input) throws DukeException {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(input.split(" ")));
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
            System.out.println("Sorry! I didn't find anything with \"" + word + "\" in it. :(");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < tasksFound.size(); i++) {
                int count = i + 1;
                System.out.println(count + ". " + tasksFound.get(i));
            }
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
