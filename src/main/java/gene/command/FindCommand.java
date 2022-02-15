package gene.command;

import java.util.ArrayList;

import gene.component.*;
import gene.task.Task;


public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for find command
     * @param unparsedKey Raw input string
     */
    public FindCommand(String unparsedKey) {
        String[] keys = unparsedKey.split("find ");
        this.keyword = keys[1];
    }

    /**
     * The execute methods contains the instruction(s) for when the command is
     * to be executed. For this command, all the tasks present in Gene's
     * taskList that matches the given keyword will be printed.
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        ArrayList<Task> tempTask = new ArrayList<>();

        for (int i = 0; i < geneTasks.size(); i++) { //to edit in tasklist
            if (geneTasks.get(i).containsKeyword(this.keyword)) {
                tempTask.add(geneTasks.get(i));
            }
        }

        StringBuilder initList = new StringBuilder();

        if (tempTask.size() == 0) {
            return "Awwman, there are no matching tasks in your list";
        } else {
            for (int i = 1; i < tempTask.size() + 1; i++) { //to edit in tasklist
                initList.append(i).append(".");
                initList.append(geneTasks.get(i - 1));
                initList.append("\n");
            }

            return Ui.showLine()
            + "Here are the matching tasks in your list:\n"
                    + initList.toString()
                    + Ui.showLine();
        }
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
