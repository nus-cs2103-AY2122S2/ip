public class UnknownCommand extends Command{
    public UnknownCommand(){
        super("unknown");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Sorry, I don't understand that command :/");
    }
}
