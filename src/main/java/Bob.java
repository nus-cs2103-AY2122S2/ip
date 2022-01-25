import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringTokenizer;

public class Bob {
    public Storage store;
    public Ui ui;
    private TaskList tasks;

    public static void main(String[] args) {
        new Bob().initializeBob();
    }

    public void initializeBob() {
        store = new Storage("Bob.txt");
        tasks = new TaskList(store.getSavedStore());
        ui = new Ui();
        greet();
        activeListener();
    }

    public void greet() {
        ui.logo();
        ui.greetMessage();
        store.readStore(ui);
        ui.userReply();
    }

    public void activeListener() {
        boolean isBye = false;
        while(!isBye) {
            String input = ui.readInput();
            if (input.isBlank()) {
                ui.badReply();
            } else {
                ui.line();
                try {
                    Command c = Parser.parse(input);
                    isBye = c instanceof ByeCommand;
                    c.execute(tasks, ui, store);
                } catch (BobException e) {
                    ui.showError(e.getBobReply());
                }
            }
            if (!isBye) {
                ui.userReply();
            }
        }
    }
}
