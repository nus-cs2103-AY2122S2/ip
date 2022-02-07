package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.Deadline;
import aeromon.task.Event;
import aeromon.task.TaskArrayList;
import aeromon.task.ToDo;

import java.time.LocalDate;

/**
 * AddCommand class that identifies the task type and adds it into the tastlist.
 */
public class AddCommand extends Command {

    private TaskType taskType;
    private String[] tokens;

    public enum TaskType { TODO, DEADLINE, EVENT };

    /**
     * Constructs the AddCommand object.
     * @param taskType the type of the Task to be added.
     * @param tokens a String array that stores information of the task to be added.
     */
    public AddCommand(TaskType taskType, String[] tokens) {
        this.taskType = taskType;
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        System.out.println("Nicely! I've added for you:");

        switch(taskType) {
        case TODO:
            ToDo toDo = new ToDo(tokens[0]);
            taskArrayList.add(toDo);
            ui.print(toDo.toString());
            ui.print(taskArrayList.getTasksStatus());
            break;

        case DEADLINE:
            Deadline deadline = new Deadline(tokens[0].trim(), LocalDate.parse(tokens[1]));
            taskArrayList.add(deadline);
            ui.print(deadline.toString());
            ui.print(taskArrayList.getTasksStatus());
            break;

        case EVENT:
            Event event = new Event(tokens[0].trim(), LocalDate.parse(tokens[1]));
            taskArrayList.add(event);
            ui.print(event.toString());
            ui.print(taskArrayList.getTasksStatus());
            break;

        }
        storage.saveFile(taskArrayList.getTasks());
    }
}
