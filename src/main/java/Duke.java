import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.readFromStorage());
    }

    public void run() {
        this.ui.greet();
        String userInput = ui.askForInput();

        while (!userInput.equals("bye")) {
            String[] processedInput = Parser.parseInput(userInput);
            ui.say(Command.execute(processedInput, this.taskList));
            userInput = ui.askForInput();
        }
        storage.writeToStorage(this.taskList);
        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}


