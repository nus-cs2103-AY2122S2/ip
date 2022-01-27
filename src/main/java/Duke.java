import user.Ui;

public class Duke {

    public Ui ui;

    public Duke() {
        ui = new Ui();
    }

    public static void main(String[] args) {
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
