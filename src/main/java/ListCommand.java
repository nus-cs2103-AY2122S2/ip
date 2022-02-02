public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception_handler {
        if(taskList.getSize() <= 0){
            throw new Exception_handler("No tasks in the list");
        }
        int count = 1;
        for (Task t : taskList.getListOfTasks()) {
            ui.println(count + ". " + t);
            count++;
        }
    }

    public boolean isExit(){
        return false;
    }
}