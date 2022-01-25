public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        
        try {
            new SetupCommand().execute(ui, taskList, storage);
        } catch (DukeException exception) {
            ui.showErrorMessage(exception.getMessage());
        }
        
        boolean isQuitting = false;
        while (!isQuitting) {
            try {
                String cmd = ui.getCommand();
                Command command = Parser.parse(cmd);
                command.execute(ui, taskList, storage);
                isQuitting = command.isBye();
            } catch (DukeException exception) {
                ui.showErrorMessage(exception.getMessage());
            }
        }
    }
}