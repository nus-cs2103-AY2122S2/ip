package commands;

import tasks.Task;

public class WelcomeCommand extends Command{

    @Override
    public Task[] getList(){
        return new Task[0];
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
