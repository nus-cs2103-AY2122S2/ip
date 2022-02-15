package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.TaskArrayList;
import aeromon.task.Deadline;
import aeromon.task.Event;
import aeromon.task.ToDo;

import java.time.LocalDate;

/**
 * AddCommand class that identifies the task type and adds it into the taskArrayList.
 */
public class AddCommand extends Command {

    private TaskType taskType;
    private String[] tokens;

    private static final String STARTING_MESSAGE = "Nicely! I've added for you: \n";
    private static final String DUPLICATE_MESSAGE = "Me thinks the task already exists, I'm not going to add it" +
            "because I don't want you to do the same task twice! Twice! \n" +
            "To add the task regardless, re-enter the command with a 'f' in front, e.g. ftodo outing";

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

            if (taskArrayList.check(toDo)) {
                return DUPLICATE_MESSAGE;
            }

            taskArrayList.add(toDo);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + toDo + taskArrayList.getTasksStatus();

        case DEADLINE:
            assert tokens.length == 2 : "Wrong number of tokens";
            Deadline deadline = new Deadline(tokens[0].trim(), false, LocalDate.parse(tokens[1]));

            if (taskArrayList.check(deadline)) {
                return DUPLICATE_MESSAGE;
            }

            taskArrayList.add(deadline);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + deadline + taskArrayList.getTasksStatus();

        case EVENT:
            assert tokens.length == 2 : "Wrong number of tokens";
            Event event = new Event(tokens[0].trim(), false, LocalDate.parse(tokens[1]));

            if (taskArrayList.check(event)) {
                return DUPLICATE_MESSAGE;
            }

            taskArrayList.add(event);
            storage.saveFile(taskArrayList.getTasks());
            return STARTING_MESSAGE + event + taskArrayList.getTasksStatus();

        }
        return "Ohnoz I couldn't execute the command, tHerE weRE somE ErrORss!";
    }

    /**
     * Gets the task type.
     * @return the task type.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    public String[] getTokens() {
        return this.tokens;
    }
}
