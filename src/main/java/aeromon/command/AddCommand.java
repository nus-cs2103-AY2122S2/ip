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
    private String[] info;

    public enum TaskType { TODO, DEADLINE, EVENT };

    /**
     * Constructs the AddCommand object.
     * @param taskType the type of the Task to be added.
     * @param info a String array that stores information of the task to be added.
     */
    public AddCommand(TaskType taskType, String[] info) {
        this.taskType = taskType;
        this.info = info;
    }

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        System.out.println("Nicely! I've added for you:");

        switch(taskType) {
            case TODO: {
                ToDo task = new ToDo(info[0]);
                taskArrayList.add(task);
                ui.print(task.toString());
                ui.print(taskArrayList.getTasksStatus());
                break;
            }
            case DEADLINE: {
                Deadline task = new Deadline(info[0].trim(), LocalDate.parse(info[1]));
                taskArrayList.add(task);
                ui.print(task.toString());
                ui.print(taskArrayList.getTasksStatus());
                break;
            }
            case EVENT: {
                Event task = new Event(info[0].trim(), LocalDate.parse(info[1]));
                taskArrayList.add(task);
                ui.print(task.toString());
                ui.print(taskArrayList.getTasksStatus());
                break;
            }
        }
        storage.saveFile(taskArrayList.getTasks());
    }
}
