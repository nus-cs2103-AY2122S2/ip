package gene.command;

import java.util.ArrayList;

import gene.component.Storage;
import gene.component.TaskList;
import gene.component.Ui;
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
     * @param tasks the list of tasks
     * @param userInt the Ui class object
     * @param storage the storage class object
     */
    @Override
    public String execute(TaskList tasks, Ui userInt, Storage storage) {
        ArrayList<Task> tempTask = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) { //to edit in tasklist
            if (tasks.get(i).containsKeyword(this.keyword)) {
                tempTask.add(tasks.get(i));
            }
        }

        StringBuilder initList = new StringBuilder();

        if (tempTask.size() == 0) {
            return "Awwman, there are no matching tasks in your list";
        } else {
            for (int i = 1; i < tempTask.size() + 1; i++) { //to edit in tasklist
                initList.append(i).append(".");
                initList.append(tasks.get(i - 1));
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
