package duke.tasks;

import duke.commands.Commands;
import duke.exceptions.InvalidCommandExcpetion;
import duke.exceptions.InvalidParameterException;


public class TaskManager {

    /**
     * @param taskName
     * @return
     * @throws InvalidCommandExcpetion
     * @throws InvalidParameterException
     */
    public static String generateAndAdd(String taskName) throws InvalidCommandExcpetion, InvalidParameterException {
        Task addedTask;
        taskName = taskName.trim();

        if (taskName.startsWith(Commands.todo.toString())) {
            addedTask = ToDoTask.ToDoTaskBuilder(taskName);
        } else if (taskName.startsWith(Commands.deadline.toString())) {
            addedTask = DeadlineTask.DeadlineTaskBuilder(taskName);
        } else if (taskName.startsWith(Commands.event.toString())) {
            addedTask = EventTask.EventTaskBuilder(taskName);
        } else {
            throw new InvalidCommandExcpetion("Could not recognise the command, please try again!");
        }

        return TaskList.add(addedTask);
    }


    public static String list() {
        return TaskList.list();
    }

    public static String mark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.mark();
        return ("Well done! I have marked this task as done.\n" + targetTask.toString()).trim();
    }

    public static String unmark(int index) {
        Task targetTask = TaskList.get(index);
        targetTask.unmark();
        return ("No worries, I have unmarked this task, good luck!\n" + targetTask.toString()).trim();
    }

    public static String delete(int index) {
        Task deletedTask = TaskList.delete(index);
        return String.format("Noted. I have removed the following task:\n%s\nYou now have %s", deletedTask.toString().trim(), TaskList.countTasks());
    }

    public static void save() {

        TaskList.save();
    }
    
    public static void reinitialise() {
        TaskList.reinitialise();
    }

    public static String find(String taskKeyword) {
        return TaskList.find(taskKeyword);
    }

    public static String findBetter(String taskKeyword) {
        return TaskList.findBetter(taskKeyword);
    }

}
