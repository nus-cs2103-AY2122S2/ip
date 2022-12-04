package duke.ui;

import duke.action.Action;
import duke.action.Deadline;
import duke.action.Event;
import duke.action.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Contains method parse needed for readfile method
 * in the ReadFile class to work.
 * <p>
 * <p>
 * The indexes under the constants are based on the reversed string.
 * <p>
 * Why reversed?
 * <p>
 * Since java does not allow for negative indexes, I had
 * to reverse the string to read the indexes from the
 * end.
 * I did this as I wanted the date's information
 * at the end. The values here are always true as due to
 * the way the toString() methods of Deadline, Event, Todo are
 * formatted, there is always 25 characters at the end reserved
 * for the date.
 * I then took advantage of this to split the
 * string into its action and date parts, which I then used
 * to recreate its previous incarnation.
 */
public class SavedDataParser {

    public static final int START_OF_ACTION_INDEX = 25;
    public static final int START_OF_DATE_INDEX = 1;
    public static final int END_OF_DATE_INDEX = 19;

    /**
     * Responsible for recreating and adding back to the list
     * the Event/Deadline/Todo objects,
     * from the strings read from the file from the previous session.
     *
     * @param task the task read directly from the file
     * @param list the tasklist to be added to
     */
    public static void parse(String task, ArrayList<Action> list) {
        boolean isDone = isMarkedTask(task);
        if (task.contains("[T]")) { //todo
            task = actionSymbolFilter(task);
            Action newTodoTask = new Todo(task);
            if (isDone) {
                newTodoTask = newTodoTask.setDone();
            }
            list.add(newTodoTask);
        } else { //deadline or event
            String action = actionFilter(task);
            String date = dateFilter(task);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, format);
            String dateReformatted = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
            if (task.contains("[D]")) { //deadline
                Action newDeadlineTask = new Deadline(action, dateReformatted);
                if (isDone) {
                    newDeadlineTask = newDeadlineTask.setDone();
                }
                list.add(newDeadlineTask);
            } else { //event
                Action newEventTask = new Event(action, dateReformatted);
                if (isDone) {
                    newEventTask = newEventTask.setDone();
                }
                list.add(newEventTask);
            }
        }
    }

    /**
     * Returns the task with the first two brackets containing
     * symbols removed.
     * etc; [T][X] ABC -> ABC
     *
     * @param task the task to be filtered
     * @return the task without the first two brackets containing the symbols
     */
    private static String actionSymbolFilter(String task) {
        task = task.replaceFirst("^\\[(.*?)]", "")
                .replaceFirst("\\[(.*?)]", "")
                .stripLeading();
        return task;
    }

    /**
     * Returns true if task is already marked.
     *
     * @param task the task to be checked
     * @return true if task is marked with an "X"
     */
    private static boolean isMarkedTask(String task) {
        if (task.contains("[T][X]") || task.contains("[D][X]") || task.contains("[E][X]")) {
            return true;
        }
        return false;
    }

    /**
     * Returns the task with just its action part.
     * In other words, the date information is removed.
     *
     * @param task the task to be filtered
     * @return the task without its date information
     */
    private static String actionFilter(String task) {
        String action = "";
        action = reverse(reverse(actionSymbolFilter(task)).substring(START_OF_ACTION_INDEX));
        return action;
    }

    /**
     * Returns the task with just its date part.
     *
     * @param task the task to be filtered
     * @return the task with just its date part.
     */
    private static String dateFilter(String task) {
        String date = "";
        date = reverse(reverse(actionSymbolFilter(task)).substring(START_OF_DATE_INDEX, END_OF_DATE_INDEX))
                .replaceAll(",", "");
        return date;
    }

    /**
     * Credit to GeeksForGeeks for this algorithm.
     * Returns a reversed string.
     *
     * @param task the task to be reversed
     * @return the reversed task
     */
    private static String reverse(String task) {
        String reversed = "";
        char ch;
        for (int i = 0; i < task.length(); i++) {
            ch = task.charAt(i); //extracts each character
            reversed = ch + reversed; //adds each character in front of the existing string
        }
        return reversed;
    }
}
