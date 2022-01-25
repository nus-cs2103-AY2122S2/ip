import java.util.List;

public class ByeCommand extends Command{
    
    @Override
    public void execute(List<Task> tasks, UI ui) throws DukeException{
        //do nothing as it is a bye command
    }

    public boolean isExit(){
        return true;
    }
}
