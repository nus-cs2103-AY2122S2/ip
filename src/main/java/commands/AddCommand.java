package commands;
import tasks.*;

public class AddCommand extends Command {
    private String input;
    private Task[] tasklist;
    private int size;


    public AddCommand(Task[] tasklist, int size,  String input){
        this.input = input;
        this.tasklist = tasklist;
        this.size = size;
        tasklist[size] = new Task(input);
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
        printFormatted(new String[]{"added: " + input});
    }
}
