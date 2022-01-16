package commands;

public class WelcomeCommand extends Command{

    @Override
    public String[] getList(){
        return new String[0];
    }

    @Override
    public boolean ends(){
        return false;
    }

    @Override
    public void execute() {
       printFormatted(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }
}
