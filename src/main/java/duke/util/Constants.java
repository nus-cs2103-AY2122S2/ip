package duke.util;

import java.util.List;

/**
 * Utility class to hold all common constants.
 */
public class Constants {
    public static final String LINE = "___________________________________________________________________________";
    public static final String GAP = "      ";
    public static final String LOGO =
            "████████▄  ███    █▄     ▄█   ▄█▄    ▄████████ \n"
                    + "███   ▀███ ███    ███   ███ ▄███▀   ███    ███ \n"
                    + "███    ███ ███    ███   ███▐██▀     ███    █▀  \n"
                    + "███    ███ ███    ███  ▄█████▀     ▄███▄▄▄     \n"
                    + "███    ███ ███    ███ ▀▀█████▄    ▀▀███▀▀▀     \n"
                    + "███    ███ ███    ███   ███▐██▄     ███    █▄  \n"
                    + "███   ▄███ ███    ███   ███ ▀███▄   ███    ███ \n"
                    + "████████▀  ████████▀    ███   ▀█▀   ██████████";
    public static final String DIR_PATH = "./data";
    public static final String STORAGE_PATH = "./data/duke.txt";
    public static final List<String> COMMANDS = List.of("list", "bye", "mark", "unmark", "delete",
            "todo", "event", "deadline", "save", "find", "reminder", "help");

    public static final String GREETINGS_GUI = "Hello There, my name is none other than \nDUKE!\n"
            + "I am a chat bot and I'm here to help you be productive :)\n"
            + "What can I do for you today?\n";
    public static final String BYE = "Bye. Hope to see you again soon!\n"
            + "Let your heart ablaze!\n";
    public static final String HELP = "Here are the list of all commands I can do!\n"
            + "1. list: List all of your task\n\n"
            + "2. todo <task info>: Add a common todo task\n\n"
            + "3. event <event info> /at <event place>: Add an event task with its place\n\n"
            + "4. deadline <deadline info> /by <date>: Add a deadline task with a date\n\n"
            + "5. delete <int:index>: Delete a task of index given\n\n"
            + "6. mark <int:index>: Mark a task of index given as done\n\n"
            + "7. unmark <int:index>: Mark a task of index given as not done\n\n"
            + "8. find <keyword>: Find all tasks with reference to the keyword\n\n"
            + "9. save: Save your tasks deliberately for future uses\n\n"
            + "10. reminder <int:num> <String:id>: Get the list of tasks that are due before a time range\n\n"
            + "Note: id can be in `days`, `weeks` or `months`\n\n"
            + "10. bye: Terminate the program\n";
}
