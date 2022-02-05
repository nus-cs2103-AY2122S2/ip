package gene.command;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;

public class ListCommand extends Command{

    /**
     * The execute methods contains the instruction(s) for when the command is
     * to be executed. For this command, all the tasks present in Gene's
     * taskList will be printed.
     *
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < tasks.size() + 1; i++) { //to edit in tasklist
            initList.append(i).append(".");
            initList.append(tasks.get(i - 1));
            initList.append("\n");
        }
        
        userInt.print(
                "----------------------------" +
                        "----------------------------\n" +
                initList.toString() +
                        "--------------------------------------------------------\n"
        );
    }

    /**
     * The method to distinguish this command from an exit command
     *
     * @return must return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
