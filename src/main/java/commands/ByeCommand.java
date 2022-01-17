package commands;

import tasks.Task;

public class ByeCommand extends Command{

    @Override
    public Task[] getList(){
        return new Task[0];
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
