package duke;
import java.io.IOException;
public class FindCommand extends Command {
    private String find;
    public FindCommand(String find){
        this.find = find;
    }

    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) throws Exception_handler, IOException {
        ui.showLine();
        ui.println(" Here are the matching tasks in your list:");
        int count = 0;
        for (Task t : listOfTasks.getListOfTasks()) {
            if(t.getDescription().contains(find)) {
                ui.println(t);
                count++;
            }
        }
        if (count == 0) {
            throw new Exception_handler("No matching tasks found");
        }
        ui.showLine();
    }

    public boolean isExit(){
        return false;
    }
}
