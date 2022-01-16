package commands;

public class ByeCommand extends Command{

    @Override
    public String[] getList(){
        return new String[0];
    }

    @Override
    public boolean ends(){
        return true;
    }

    @Override
    public void execute() {
        printFormatted(new String[]{"Bye. Hope to see you again soon!"});
    }
}
