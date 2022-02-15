package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.task.Deadline;
import aeromon.task.Event;
import aeromon.TaskArrayList;
import aeromon.task.ToDo;

import java.time.LocalDate;

/**
 * AddCommand class that identifies the task type and adds it into the tastlist.
 */
public class AddCommand extends Command {

    private TaskType taskType;
    private String[] tokens;

    private static final String STARTING_MESSAGE = "Nicely! I've added for you: \n";

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
    public String execute(TaskArrayList taskArrayList, Storage storage) throws AeromonException {

        switch(taskType) {
        case TODO:
            ToDo toDo = new ToDo(tokens[0], false);
            taskArrayList.add(toDo);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + toDo + taskArrayList.getTasksStatus();

        case DEADLINE:
            assert tokens.length == 2 : "Wrong number of tokens";
            Deadline deadline = new Deadline(tokens[0].trim(), false, LocalDate.parse(tokens[1]));
            taskArrayList.add(deadline);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + deadline + taskArrayList.getTasksStatus();

        case EVENT:
            assert tokens.length == 2 : "Wrong number of tokens";
            Event event = new Event(tokens[0].trim(), false, LocalDate.parse(tokens[1]));
            taskArrayList.add(event);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + event + taskArrayList.getTasksStatus();

        }
        return "Ohnoz I couldn't execute the command, tHerE weRE somE ErrORss!";
    }
}
