public class Duke {
    private Parser cmdParser;

    public Duke() {
    }

    public static void main(String[] args) {
        Duke Lumu = new Duke();

        Lumu.botInitialize();
    }

    private void botInitialize() {
        Storage storage = Storage.getInstance();
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui();
        ui.initialize(taskList);

    }

}
