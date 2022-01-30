import java.util.Scanner;

public class Duke {
    TaskList tasks;
    Storage storage;
    Ui ui;

    public Duke(String savePath) {
        storage = new Storage(savePath);
        tasks = storage.load();
        ui = new Ui();
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        Command nextCmd;
        String response;

        ui.greet();

        while (true) {
            nextCmd = Parser.parse(userInput.nextLine());
            nextCmd.setData(tasks);
            response = nextCmd.execute();
            ui.say(response);

            if (nextCmd instanceof ExitCommand) {
                storage.save(tasks);
                break;
            }
        }

        userInput.close();
    }

    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
    }
}