public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index){
        super("delete");
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(index < 0 || index >= tasks.size()){
            System.out.println("Invalid index, please try again.");
        } else {
            Task task = tasks.remove(index);
            ui.taskDeleteMessage(task, tasks.size());
            storage.save(tasks.list());
        }
    }
}

