public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UserInterface userInterface;

    public Duke(String filePath) {
        this.userInterface = new UserInterface();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.readFile(this.tasks);
        } catch (DukeException e) {
            userInterface.errorMessage(e);
        }
    }

    public void run() {
        userInterface.introMessage();
        String input = userInterface.readInput();
        while (!input.equals("bye")) {
            try {
                userInterface.lineDivider();
                Parser.parse(input, this.tasks);
                userInterface.lineDivider();
            } catch (DukeException errorMessage) {
                userInterface.errorMessage(errorMessage);
            }
            input = userInterface.readInput();
        }
        try {
            this.storage.writeFile(this.tasks);
        } catch (DukeException e) {
            userInterface.errorMessage(e);
        }
        userInterface.byeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
