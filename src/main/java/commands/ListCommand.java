package commands;

public class ListCommand extends Command{
    private static String[] tasklist;

    public ListCommand(String[] tasklist){
        this.tasklist = tasklist;
    }

    @Override
    public String[] getList(){
        return tasklist;
    }

    @Override
    public boolean ends(){
        return false;
    }

    @Override
    public void execute() {
        printFormatted(tasklist);
    }
}
