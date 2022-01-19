package commands;

import tasks.*;

public class DeleteCommand extends Command{
    private Task[] tasklist;
    private int size;
    private int deleteIndex;
    private Task deleted;

    public DeleteCommand(Task[] tasklist, int size, int deleteIndex){
        this.deleteIndex = deleteIndex - 1;
        this.size = size;
        this.tasklist = tasklist;
        this.deleted = tasklist[this.deleteIndex];
        for(int i = this.deleteIndex; i < size; i++) {
            if(i == 99){
                this.tasklist[i] = null;
                break;
            }
            this.tasklist[i] = this.tasklist[i+1];
        }
        this.size--;
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
        printFormatted(new String[]{
                "Noted. I've removed this task:",
                "  "+ deleted,
                "Now you have " + size + " tasks in the list"});
    }
}
