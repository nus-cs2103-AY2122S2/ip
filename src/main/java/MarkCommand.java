public class MarkCommand extends Command{
    private int index;
    public MarkCommand(int index){
       super("mark");
       this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(index < 0 || index >= tasks.size()){
            System.out.println("Invalid index, please try again.");
        } else {
            Task task = tasks.mark(index);
            ui.taskMarkedMessage(task);
            storage.save(tasks.list());
        }
    }
}
