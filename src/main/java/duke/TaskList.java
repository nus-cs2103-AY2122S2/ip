package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    /**
     * Marks a task as done.
     * 
     * 
     * @param input
     * @throws DukeException
     */
    protected static void mark(String input) throws DukeException {
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
    protected static void unmark(String input) throws DukeException {
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

    protected static void deadline(String input) throws DukeException {
        if (stripDescription(input)[0] == "") {
            throw new DukeException(
                    "Oops, the description of deadline cannot be empty!"
            );
        }

        if (stripDescription(input)[1] == "") {
            throw new DukeException(
                    "Oops, please tell me when is this due!"
            );
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
    protected static void todo(String input) throws DukeException {
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

    protected static void list() {

    }

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
