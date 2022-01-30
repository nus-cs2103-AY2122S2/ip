package instructions.taskinstructions;

import tasks.TaskList;
import tasks.ToDoTask;

/**
 * This class represents an instruction for a new To Do task.
 * Format: "to_do xyz" where xyz is the task description (omit the _).
 *
 * @author Ong Han Yang
 */
public class TodoInst extends NewTaskInst {
    /**
     * Constructs a To Do Instruction
     *
     * @param taskDesc
     */
    private TodoInst(String taskDesc) {
        super(taskDesc);
    }

    /**
     * Produces a To Do Instruction with the given description.
     *
     * @param taskDesc the description for the instruction.
     * @return the To Do Instruction.
     */
    public static TodoInst of(String taskDesc) {
        return new TodoInst(taskDesc);
    }

    /**
     * Adds a to do task to the given taskList.
     *
     * @param taskList the taskList to have a to do task added to.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        ToDoTask task = ToDoTask.of(super.getTaskDesc());
        taskList.add(true, task);
        return String.format("Okay, added this task:\n%s\nThere are %d tasks "
                        + "in the list now.", task, taskList.length());
    }
}
