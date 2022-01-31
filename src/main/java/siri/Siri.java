package siri;

public class Siri {

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    public Siri(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        
        try {
            tasks = new TaskList(storage.load());
            ui.startUpSavedData();
            ui.startUp();
        } catch (SiriException se) {
            tasks = new TaskList();
            ui.startUp();
        }

        parser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Siri("../data/data.txt").runApp();
    }

    private static void runApp() {
        int continueToExecute = 1;

        while (continueToExecute == 1) {
            try {
                continueToExecute = parser.handleCommand(ui.takeInput());
            } catch (SiriException se) {
                System.out.println(se.getMessage());
            }
            ui.separator();
        }
        
        storage.save(tasks.saveData());
        ui.exit();

    }

}
