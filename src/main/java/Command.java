public abstract class Command {
    Storage storage = new Storage();
    Ui ui = new Ui();
    TaskManager taskManager = new TaskManager();
    String userInput = "";

    public Command(String userInput){
        this.userInput = userInput;
    }
    public Command(){}

    public abstract boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException;

    public boolean isExit(){
        return false;
    }
}
