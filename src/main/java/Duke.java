public class Duke {

    public Ui ui;

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public Duke() throws DukeException {
        ui = new Ui();
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.ui.greet();
        String userInput;
        boolean finished = false;
        while (!finished) {
            userInput = duke.ui.getInput();
            if (userInput.equals("bye")) {
                duke.ui.sayGoodbye();
                finished = true;
            } else {
                duke.ui.handle(userInput);
            }
        }
    }
}
