package duke.instruction;

import duke.task.TaskManager;
import duke.ui.Ui;

final class Find extends Instruction {

    private final String keyword;

    /**
     * Constructs a Find instruction.
     *
     * @param instruction The raw instruction from user.
     * @param tasks The task manager to be used.
     * @throws InvalidInstructionException
     */
    protected Find(String instruction, TaskManager tasks) throws InvalidInstructionException {

        super("find", tasks);
        this.keyword = parseInstruction(instruction);

    }

    private static String parseInstruction(String instruction) throws InvalidInstructionException {

        String keyword;
        String[] words = instruction.split(" ", 2);
        if (words.length != 2) {
            throw new InvalidInstructionException("Please provide at least a keyword for searching!");
        }
        return words[1];
    }

    /**
     * Searches for tasks that contain the keyword, and prints the result out.
     *
     * @param ui The UI to be used by this instruction.
     */
    @Override
    public void act(Ui ui) {

        ui.printMessage(TaskManager.listToString(tasks.search(keyword)));
    }
}
