package commands;

public class AddCommand extends Command {
    private static String input;
    private static String[] tasklist;
    private static int size;


    public AddCommand(String[] tasklist, int size,  String input){
        this.input = input;
        this.tasklist = tasklist;
        this.size = size;
    }

    @Override
    public String[] getList(){
        String newsize = String.valueOf(size + 1);
        tasklist[size] = newsize + ". " + input;
        return tasklist;
    };

    @Override
    public boolean ends(){
        return false;
    }

    @Override
    public void execute() {
        printFormatted(new String[]{"added: " + input});
    }
}
