import java.util.List;

public abstract class Command {
    public abstract void execute(List<Task> tasks, UI ui) throws DukeException;
    public boolean isExit(){
        return false;
    }
}
