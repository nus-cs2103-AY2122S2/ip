package instructions;

import exceptions.InvalidActionException;
import exceptions.NoSuchTaskException;
import instructions.taskinstructions.listinstructions.ModifyListedTaskInst;
import tasks.TaskList;

/**
 * This class encapsulates a handler for instructions. It allows instructions to perform their
 * action on a given input.
 *
 * @author Ong Han Yang
 */
public class InstructionHandler {
    /** The task list that the handler is modifying */
    private TaskList taskList;

    /**
     * Constructs an Instruction Handler.
     *
     * @param taskList the tasklist to initialise the handler with.
     */
    private InstructionHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Produces an Instruction Handler.
     *
     * @param taskList the tasklist to initialise the handler with.
     * @return the Instruction Handler.
     */
    public static InstructionHandler of(TaskList taskList) {
        return new InstructionHandler(taskList);
    }

    /**
     * Allows the instruction to perform its function. Returns a feedback message about the
     * status of the action.
     *
     * @param inst the given instruction to perform.
     * @return the feedback message about the status of the action.
     */
    public String doInstruction(Instruction inst) {
        try {
            return inst.doInst(taskList);
        } catch (NoSuchTaskException e) {
            // This is okay as the only methods that will throw a NoSuchTaskException are the ones that
            // need to modify the tasks in the task list.
            assert inst instanceof ModifyListedTaskInst;
            ModifyListedTaskInst castedInst = (ModifyListedTaskInst) inst;
            return String.format("There is no task %d!", castedInst.getTaskNum());
        } catch (InvalidActionException e) {
            // Return the invalid action's exception message back to the user.
            return e.getMessage();
        }
    }
}
