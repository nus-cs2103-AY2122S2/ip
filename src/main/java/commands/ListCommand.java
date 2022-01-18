package commands;

import tasks.Task;

public class ListCommand extends Command{
    private static Task[] tasklist;

    public ListCommand(Task[] tasklist){
        this.tasklist = tasklist;
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
        printFormatted(tasklist);
    }

    public static void printFormatted(Task[] tasklist){
        System.out.print(LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < tasklist.length; i++){
            if(tasklist[i]!=null) {
                System.out.println(INDENT + (i+1) + "." + tasklist[i]);
            }
        }
        System.out.println(LINE);
    }
}
