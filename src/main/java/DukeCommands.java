import java.util.*;

public class DukeCommands {

    public enum VALID_COMMANDS {
        todo, deadline, event, list, mark, unmark, delete, bye
    };

    public static boolean isDukeCommand(String string) {
        return Arrays.stream(VALID_COMMANDS.values()).anyMatch((command) -> command.name().equals(string));
    }

}
