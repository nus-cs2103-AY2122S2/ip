import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private String taskType;
    private String textInput;

    public AddCommand(String type, String input) {
        this.textInput = input;
        this.taskType = type;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        if (this.taskType.equals("todo")) {
            try {
                Todo td = Todo.createTodo(this.textInput);
                tasks.addTask(td);
                stg.writeToFile(td.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description!");
                ui.showLine();
                return;
            }
        } else if (this.taskType.equals("deadline")) {
            try {
                Deadline dl = Deadline.createDeadline(this.textInput);
                tasks.addTask(dl);
                stg.writeToFile(dl.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description/date!");
                ui.showLine();
                return;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please enter a date (eg. /by 2019-12-12)!");
                ui.showLine();
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid date in YYYY-MM-DD format!");
                ui.showLine();
                return;
            }
        } else {
            try {
                Event ev = Event.createEvent(this.textInput);
                tasks.addTask(ev);
                stg.writeToFile(ev.formatText() + "\n");
            } catch (DukeException e) {
                System.out.println("Please enter a valid description/date!");
                ui.showLine();
                return;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please enter a date (eg. /at Monday 3-4pm)!");
                ui.showLine();
                return;
            }
        }
        ui.showCount(tasks);
        ui.showLine();
    }
}
