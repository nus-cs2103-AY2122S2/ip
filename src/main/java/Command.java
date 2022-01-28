public class Command {
    protected String keyword;

    public Command(String keyword) {
       this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
    };

    public boolean isExit() {
        return false;
    }
}
