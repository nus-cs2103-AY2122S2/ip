import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    public boolean isExitCmd() {
        return false;
    }

    public abstract CommandResult execute(TaskList tasklist);
}
