package seedu.command;

public class WelcomeCommand extends Command {
    public static String run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String result = "Hello I'm\n" + logo + "\nWhat can I do for you?\n";
        return result;
    }
}
