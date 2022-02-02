package duke;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler {
        ui.println("Bye. Hope to see you again soon!");
    }

    public boolean isExit(){
        return true;
    }
}