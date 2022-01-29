public class ExitCommand extends Command{


    public ExitCommand(){
    }

    @Override
    public void execute(Ui ui, DukeList list){
       ui.showClosing();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
