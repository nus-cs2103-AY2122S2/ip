package duke.command;

public class ByeCommand extends Command{
    public ByeCommand() {
        super("bye");
    }

    @Override
    public String toString(){
        return "bye";
    }
}
