import java.io.IOException;
import java.time.LocalDate;

public class AddCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private String taskType;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = fullCommand.split(" ", 2);
        this.taskType = splicedFullCommand[0];
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        switch (taskType) {
        case "todo":
            String toDoTaskDescription = fullCommand.split("todo ", 2)[1];
            ToDo toDoTask = new ToDo(toDoTaskDescription);
            tasks.addToList(toDoTask, ui, storage);
            break;
        case "deadline":
            String deadlineDescription = fullCommand.split("deadline ", 2)[1].split("/by ")[0];
            String deadlineDate = fullCommand.split("deadline ", 2)[1].split("/by ")[1];
            LocalDate deadlineTaskDate = LocalDate.parse(deadlineDate, Task.inputDateFormat);
            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineTaskDate);
            tasks.addToList(deadlineTask, ui, storage);
            break;
        case "event":
            String eventDescription = fullCommand.split("event ", 2)[1].split("/at ")[0];
            String eventDate = fullCommand.split("event ", 2)[1].split("/at ")[1];
            LocalDate eventTaskDate = LocalDate.parse(eventDate, Task.inputDateFormat);
            Event eventTask = new Event(eventDescription, eventTaskDate);
            tasks.addToList(eventTask, ui, storage);
            break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
