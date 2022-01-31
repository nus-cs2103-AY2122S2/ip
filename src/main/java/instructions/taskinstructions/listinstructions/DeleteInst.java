package instructions.taskinstructions.listinstructions;

import exceptions.NoSuchTaskException;
import tasks.Task;
import tasks.TaskList;

/**
 * This class represents a Delete Task Instruction.
 * <br>
 * Format: {@code Delete < task number>}
 *
 * @author Ong Han Yang
 */
public class DeleteInst extends ModifyListedTaskInst {
    /**
     * Constructs a Delete Task Instruction.
     *
     * @param taskNum the task number to delete.
     */
    private DeleteInst(int taskNum) {
        super(taskNum);
    }

    /**
     * Produces a Delete Task Instruction.
     *
     * @param taskNum the task number to delete.
     * @return the Delete Task Instruction.
     */
    public static DeleteInst of(int taskNum) {
        return new DeleteInst(taskNum);
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param taskList the taskList to modify.
     * @return the feedback message after performing this instruction.
     * @throws NoSuchTaskException when there does not exist a task with the given index.
     */
    @Override
    public String doInst(TaskList taskList) throws NoSuchTaskException {
        Task deleted = taskList.delete(super.getTaskNum() - 1, true);
        return String.format("Okay, I've removed this task:\n%s\nThere are %d tasks left in the list!",
                deleted.toString(), taskList.length());
    }
}
