import java.util.Scanner;

public class ClearCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showClearListConfirmationMessage();
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
        if (response.equals("y")) {
            tasks.clearTaskList();
        }
        ui.showClearListMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
