package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.task.Tasks;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddCommand extends Command {

    private Tasks taskType;
    private String description;
    private String time;
    private LocalDateTime localDateTime;

    public AddCommand(Tasks taskType, String description, String time) {
        super(false);
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }

    public AddCommand(Tasks taskType, String description) {
        super(false);
        this.taskType = taskType;
        this.description = description;
    }

    public AddCommand(Tasks taskType, String description, LocalDateTime localDateTime) {
        super(false);
        this.taskType = taskType;
        this.description = description;
        this.localDateTime = localDateTime;
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        if (taskType == Tasks.DEADLINE) {
            taskList.add(taskType, description, localDateTime);
        } else {
            taskList.add(taskType, description, time);
        }
        ui.printAddedTask(taskList);
        storage.writeToFile("data/duke.txt", taskList);
    }
}
