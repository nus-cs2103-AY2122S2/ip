public abstract class Command {
    Storage storage = new Storage();
    Ui ui = new Ui();
    TaskManager taskManager = new TaskManager();
    String userInput = "";

    public Command(String userInput){
        this.userInput = userInput;
    }
    public Command(){}

    protected void save(Storage storage,Ui ui, TaskManager taskManager){
        try {
            storage.saveTaskManager(taskManager);
            ui.showSavingComplete();
        } catch (DukeException e){
            ui.showSavingFailed();
        }
    }

    public abstract boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException;

    public boolean isExit(){
        return false;
    }
}
