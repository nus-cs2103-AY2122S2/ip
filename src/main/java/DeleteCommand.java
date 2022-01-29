public class DeleteCommand extends Command{

    private int index;

    public DeleteCommand(int x){
        index = x;
    }

    @Override
    public void execute(Ui ui, DukeList tasks){
        tasks.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
