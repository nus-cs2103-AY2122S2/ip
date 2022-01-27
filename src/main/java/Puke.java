import java.util.Scanner;

public class Puke {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    public Puke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            storage.loadTasks(tasks);
        } catch (PukeException e) {
            ui.printError(e);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.printWelcomeMessage();

        String response = "";
        while (true) {
            ui.printCommandHead();

            try {
                response = parser.processInput(sc.nextLine(), tasks, ui);

                if (response == null) {
                    storage.saveTasks(tasks);
                    ui.printExit();
                    break;
                }
            } catch (PukeException e) {
                ui.printError(e);
                continue;
            }

            ui.printResponse(response);
        }
    }

    public static void main(String[] args) {
        new Puke("./../../../data/puke.txt").run();
    }
}
