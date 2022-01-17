package commands;

import tasks.Task;

public class UnMarkCommand extends Command {
    private Task[] tasklist;
    private int pos;

    public UnMarkCommand(Task[] tasklist, int pos){
        this.tasklist = tasklist;
        this.pos = pos - 1;
        tasklist[this.pos].unmark();
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
    public void execute() {
        printFormatted(new String[]{ "I've marked this task as not done yet:", "  " + tasklist[this.pos]});
    }
}
