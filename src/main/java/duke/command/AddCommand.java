package duke.command;

import duke.*;
import duke.task.*;

import java.io.IOException;
import java.time.LocalDate;

public class AddCommand implements Command {

    private String fullCommand;
    private String[] splitFullCommand;
    private String taskType;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splitFullCommand = fullCommand.split(" ", 2);
        this.taskType = splitFullCommand[0];
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        switch (taskType) {
        case "todo":
            String todo = fullCommand.split("todo ", 2)[1];
            ToDo toDoTask = new ToDo(todo);
            tasks.addToList(toDoTask, ui, storage);
            break;
        case "deadline":
            String deadline = fullCommand.split("deadline ",
                    2)[1].split("/by ")[0];
            String by = fullCommand.split("deadline ",
                    2)[1].split("/by ")[1];
            LocalDate deadlineDate = LocalDate.parse(by,
                    Task.getInputDateFormat());
            Deadline deadlineTask = new Deadline(deadline, deadlineDate);
            tasks.addToList(deadlineTask, ui, storage);
            break;
        case "event":
            String event = fullCommand.split("event ",
                    2)[1].split("/at ")[0];
            String at = fullCommand.split("event ",
                    2)[1].split("/at ")[1];
            LocalDate eventDate = LocalDate.parse(at,
                    Task.getInputDateFormat());
            Event eventTask = new Event(event, eventDate);
            tasks.addToList(eventTask, ui, storage);
            break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
