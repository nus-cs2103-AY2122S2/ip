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

    public static void printFormatted(Task[] msg){
        System.out.print(LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < msg.length; i++){
            if(msg[i]!=null) {
                System.out.println(INDENT + (i+1) + "." + msg[i]);
            }
        }
        System.out.println(LINE);
    }
}
