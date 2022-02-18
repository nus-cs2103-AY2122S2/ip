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
            "todo", "event", "deadline", "save", "find", "reminder");

    public static final String GREETINGS_GUI = "Hello There, my name is none other than \nDUKE!\n"
            + "I am a chat bot and I'm here to help you be productive :)\n"
            + "What can I do for you today?\n";
    public static final String BYE = "Bye. Hope to see you again soon!\n"
            + "Let your heart ablaze!\n";
}
