package instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.InvalidInputException;
import exceptions.NoSuchTaskException;
import instructions.listinstructions.DeleteInst;
import instructions.listinstructions.DisplayListInst;
import instructions.listinstructions.FindInst;
import instructions.listinstructions.MarkAsDoneInst;
import instructions.listinstructions.UnmarkInst;
import instructions.taskinstructions.TodoInst;

import tasks.TaskList;

/**
 * This class encapsulates tests done on the Instruction Handler, in particular the method
 * {@code doInstruction}.
 *
 * Indirectly, this tests the methods for TaskList too.
 *
 * @author Ong Han Yang
 */
public class InstructionHandlerTest {
    private static TaskList taskList = new TaskList("data", "JUnit-tests-tasklist.txt");
    private static InstructionHandler instructionHandler = InstructionHandler.of(taskList);

    /**
     * Tests the doInstruction method with a list instruction and an empty list.
     */
    @Test
    public void doInst_list_success() {
        instructionHandler = InstructionHandler.of(new TaskList());
        Instruction inst = DisplayListInst.of();
        assertEquals("", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with an exit instruction.
     */
    @Test
    public void doInst_bye_success() {
        Instruction inst = ExitInst.of();
        assertEquals("", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a delete instruction and an empty list.
     */
    @Test
    public void doInst_deleteWithEmptyList_noTaskSuccess() {
        instructionHandler = InstructionHandler.of(new TaskList());
        Instruction inst = DeleteInst.of(1);
        assertEquals("There is no task 1!", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a mark instruction and an empty list.
     */
    @Test
    public void doInst_markWithEmptyList_noTaskSuccess() {
        instructionHandler = InstructionHandler.of(new TaskList());
        Instruction inst = MarkAsDoneInst.of(1);
        assertEquals("There is no task 1!", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a unmark instruction and an empty list.
     */
    @Test
    public void doInst_unmarkWithEmptyList_noTaskSuccess() {
        instructionHandler = InstructionHandler.of(new TaskList());
        Instruction inst = UnmarkInst.of(1);
        assertEquals("There is no task 1!", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a find instruction and an empty list.
     *
     * @throws InvalidInputException never.
     */
    @Test
    public void doInst_findWithEmptyList_noTaskSuccess() throws InvalidInputException {
        instructionHandler = InstructionHandler.of(new TaskList());
        Instruction inst = FindInst.of("does not matter");
        assertEquals("", instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a valid to-do instruction.
     */
    @Test
    public void doInst_validTodo_success() throws NoSuchTaskException {
        taskList = new TaskList("data", "JUnit-tests-tasklist.txt");
        instructionHandler = InstructionHandler.of(taskList);
        Instruction inst = TodoInst.of("sample desc");
        String toCheck = instructionHandler.doInstruction(inst);
        assertEquals("Okay, added this task:\n"
                        + taskList.displayTask(0)
                        + "\nThere are 1 tasks in the list now.",
                toCheck);
    }

    /**
     * Tests the doInstruction method with a list instruction after adding 3 tasks.
     */
    @Test
    public void doInst_list3Task_success() {
        taskList = new TaskList("data", "JUnit-tests-tasklist.txt");
        instructionHandler = InstructionHandler.of(taskList);
        instructionHandler.doInstruction(TodoInst.of("sample task 1"));
        instructionHandler.doInstruction(TodoInst.of("sample task 2"));
        instructionHandler.doInstruction(TodoInst.of("sample task 3"));
        Instruction inst = DisplayListInst.of();
        assertEquals(taskList.toString(),
                instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a find instruction after adding 3 tasks.
     */
    @Test
    public void doInst_find3Task_success() throws InvalidInputException {
        taskList = new TaskList("data", "JUnit-tests-tasklist.txt");
        instructionHandler = InstructionHandler.of(taskList);
        instructionHandler.doInstruction(TodoInst.of("sample task 1"));
        instructionHandler.doInstruction(TodoInst.of("sample task 2"));
        instructionHandler.doInstruction(TodoInst.of("sample task 3"));
        Instruction inst = FindInst.of("find sample");
        assertEquals(taskList.toString(),
                instructionHandler.doInstruction(inst));
    }

    /**
     * Tests the doInstruction method with a find instruction after adding 3 tasks.
     */
    @Test
    public void doInst_find2_success() throws InvalidInputException {
        taskList = new TaskList("data", "JUnit-tests-tasklist.txt");
        instructionHandler = InstructionHandler.of(taskList);
        instructionHandler.doInstruction(TodoInst.of("sample desc 2"));
        instructionHandler.doInstruction(TodoInst.of("sample task 2"));
        instructionHandler.doInstruction(TodoInst.of("sample whee"));
        Instruction inst = FindInst.of("find 2");
        String expected = "1. [T][ ] sample desc 2\n" + "2. [T][ ] sample task 2\n";
        assertEquals(expected, instructionHandler.doInstruction(inst));
    }
}
