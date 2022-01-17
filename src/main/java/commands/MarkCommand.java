package commands;
import tasks.*;
public class MarkCommand extends Command{
    private Task[] tasklist;
    private int pos;

    public MarkCommand(Task[] tasklist, int pos){
        this.tasklist = tasklist;
        this.pos = pos - 1;
        tasklist[this.pos].mark();
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
        printFormatted(new String[]{"Nice! I've marked this task as done:", "  " + tasklist[this.pos]});
    }
}
