import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private String description;
    private String time;

    public AddCommand(String command, String description) {
        super(command);
        this.description = description;
        this.time = "";
    }

    public AddCommand(String command, String description, String time) {
        super(command);
        this.description = description;
        this.time = time;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            LocalDate parsedTime;
            String formattedTime;
            switch (this.getFirstWord()) {
            case "todo":
                Task taskTodo = new Todo(this.description);
                tasks.add(taskTodo);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskTodo);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                Task taskDeadline = new Deadline(this.description, this.time);
                tasks.add(taskDeadline);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskDeadline);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                Task taskEvent = new Event(this.description, this.time);
                tasks.add(taskEvent);
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage("  " + taskEvent);
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                break;
            }
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            System.out.println("Please type a valid date! (Format: YYYY-MM-DD)");
        }
    }
}
