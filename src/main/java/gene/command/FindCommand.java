package gene.command;

import java.util.ArrayList;

import gene.component.*;
import gene.location.Location;
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
        ArrayList<Task> tempTasks = new ArrayList<>();
        ArrayList<Location> tempLocs = new ArrayList<>();

        for (int i = 0; i < geneTasks.size(); i++) { //to edit in tasklist
            if (geneTasks.get(i).containsKeyword(this.keyword)) {
                tempTasks.add(geneTasks.get(i));
            }
        }

        for (int i = 0; i < geneLocs.size(); i++) { //to edit in tasklist
            if (geneLocs.get(i).containsKeyword(this.keyword)) {
                tempLocs.add(geneLocs.get(i));
            }
        }

        StringBuilder taskList = new StringBuilder();
        StringBuilder locList = new StringBuilder();

        if (tempTasks.size() == 0 && tempLocs.size() == 0) {
            return "Awwman, there are no matching tasks in your list";
        }
        for (int i = 1; i < tempTasks.size() + 1; i++) { //to edit in tasklist
            taskList.append(i).append(".");
            taskList.append(geneTasks.get(i - 1));
            taskList.append("\n");
        }

        for (int i = 1; i < tempLocs.size() + 1; i++) { //to edit in tasklist
            locList.append(i).append(".");
            locList.append(geneLocs.get(i - 1));
            locList.append("\n");
        }

        return Ui.showLine() + "\n"
        + "Here are the matching tasks in your list:\n"
                + taskList.toString() + "\n"
                + "And here are the matching locations:\n"
                + locList.toString()
                + Ui.showLine();
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
