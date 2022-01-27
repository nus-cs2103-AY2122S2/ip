public class Tesseract {
    static final String SCHEDULE_PATH = "src/main/Data/Schedule.txt";

    private Storage storage;
    private TaskList taskList;
    private TessUi ui;

    Tesseract(String filePath) {
        this.storage = new Storage(filePath); // try getstorage
        this.ui = new TessUi();
        try {
            taskList = new TaskList(storage.getStorage());
        } catch (TesseractException e) {
            ui.showError(e.getErrMsg());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Tesseract(SCHEDULE_PATH).run();
    }

    public void run() {
        // greet the user
        ui.sayHi();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCmd = ui.readCommand();
                Command.process(fullCmd, taskList.size()); // throw any possible error
                Command cmd = Command.generate(fullCmd); // command is guaranteed to be valid
                cmd.execute(taskList, ui, storage);
                isExit = cmd.isExit();
            } catch (TesseractException e) {
                ui.showError(e.getErrMsg());
            }
        }
    }
}