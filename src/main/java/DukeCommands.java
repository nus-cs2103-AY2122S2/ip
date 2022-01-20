import java.util.*;

public class DukeCommands {

    public enum DUKE_COMMANDS {
        todo, deadline, event, mark, unmark, delete
    };

    public static boolean isDukeCommand(String string) {
        return Arrays.stream(DUKE_COMMANDS.values()).anyMatch((command) -> command.name().equals(string));
    }

}
