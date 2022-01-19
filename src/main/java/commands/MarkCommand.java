package commands;
import tasks.*;

import java.util.ArrayList;

public class MarkCommand extends Command{
    private ArrayList<Task> tasklist;
    private int pos;

    public MarkCommand(ArrayList<Task> tasklist, int pos){
        this.tasklist = tasklist;
        this.pos = pos - 1;
        Task marked = tasklist.get(this.pos);
        marked.mark();
        this.tasklist.set(this.pos, marked);
    }

    @Override
    public ArrayList<Task> getList(){
        return tasklist;
    }

    @Override
    public boolean ends(){
        return false;
    }

    @Override
    public void execute() {
        printFormatted(new String[]{"Nice! I've marked this task as done:", "  " + tasklist.get(this.pos)});
    }
}
