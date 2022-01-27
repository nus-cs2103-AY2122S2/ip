public class Main {
    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList();
    private static final Storage storage = new Storage("../data");

    public static void main(String[] args) {
        Ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            Command c= ui.read();
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }
}
