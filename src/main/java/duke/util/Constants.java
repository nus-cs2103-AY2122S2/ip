package duke.util;

import java.util.Arrays;
import java.util.List;

/**
 * Represents various constants that
 * are used throughout the program
 */
public class Constants {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static final String GREETINGS = "Hello there, I am Giga Chad Duke.\nHow can I help you?\nType help to list "
            + "all the commands.";

    public static final String BYE = "Bye, hope to see you again soon!";

    public static final List<String> TASKS = Arrays.asList("todo", "event",
            "deadline", "mark", "unmark", "delete", "find");

    public static final String DATA_DIRECTORY = "./data";

    public static final String FILE_NAME = "duke.txt";

    public static final String TEMP_FILE_NAME = "temp.txt";

    public static final int DELAY = 400;

    public static final String HELP_MESSAGE = "Here are the list of commands:\n"
            + "1. list: list out all of your tasks\n"
            + "2. todo <task>: add a todo task into your task list\n"
            + "3. deadline <task> /by <deadline>: add a deadline into your task list\n"
            + "4. event <task> /at <time>: add an event into your task list\n"
            + "5. mark <inde>: mark a task at specified index as done\n"
            + "6. unmark <index>: unmark a task at specified index as not done yet\n"
            + "7. delete <index>: delete a task at sepcified index\n"
            + "8. find <keyword>: find a task that containst keyword\n"
            + "9. undo: undo your last action\n"
            + "10. bye: termintate this program";
}
