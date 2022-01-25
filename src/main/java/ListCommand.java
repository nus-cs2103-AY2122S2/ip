public class ListCommand extends Command{
    public ListCommand(){
        super();
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager){
        ui.showList(taskManager);
        return true;
    }
}
