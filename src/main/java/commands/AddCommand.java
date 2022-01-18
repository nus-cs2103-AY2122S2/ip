package commands;
import tasks.*;

public class AddCommand extends Command {
    private String input;
    private String type;
    private Task[] tasklist;
    private int size;
    private Task added;


    public AddCommand(Task[] tasklist, int size, String input){
        String[] processedInput = input.split(" ", 2);
        this.type = processedInput[0];
        this.input = processedInput[1];
        this.tasklist = tasklist;
        this.size = size;
        switch (type) {
            case "todo":
                this.added = new ToDos(input);
                break;
            case "event":
                processedInput = input.split("/at", 2);
                this.added = new Events(processedInput[0], processedInput[1]);
                break;
            case "deadline":
                processedInput = input.split("/by", 2);
                this.added = new Deadlines(processedInput[0], processedInput[1]);
                break;
        }
        tasklist[size] = added;
        this.size++;
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
                "Got it. I've added this task:",
                "  "+ added,
                "Now you have " + size + " tasks in the list"});
    }
}
