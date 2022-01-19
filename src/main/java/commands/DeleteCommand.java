package commands;

import tasks.*;

public class DeleteCommand extends Command{
    private Task[] tasklist;
    private int size;

    public DeleteCommand(Task[] tasklist, int size, String input){

    }

    @Override
    public Task[] getList(){
        return tasklist;
    }

    @Override
    public boolean ends(){
        return false;
    }

    @Override
    public void execute() {}
}
