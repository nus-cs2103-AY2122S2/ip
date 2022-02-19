package gene.command;

import gene.component.*;

public class ListCommand extends Command {
    private final String inputBody;
    public ListCommand(String body) {
        this.inputBody = body;
    }

    /**
     * The execute methods contains the instruction(s) for when the command is
     * to be executed. For this command, all the tasks present in Gene's
     * taskList will be printed.
     *
     * @param geneTasks the list of tasks
     * @param geneUi the Ui class task object
     * @param geneTaskStorage the tastorage class object
     * @param geneLocs the list of locations
     * @param geneLocationStorage the location storage class object
     */
    @Override
    public String execute(TaskList geneTasks, Ui geneUi,
                          TaskStorage geneTaskStorage,
                          LocationList geneLocs,
                          LocationStorage geneLocationStorage) {
        StringBuilder initList = new StringBuilder();
        String[] tokens = this.inputBody.split("list "); //error
        try {
            String listWhat = tokens[1];
            switch (listWhat) {
            case "tasks":
                for (int i = 1; i < geneTasks.size() + 1; i++) { //to edit in tasklist
                    initList.append(i).append(".");
                    initList.append(geneTasks.get(i - 1));
                    initList.append("\n");
                }
                break;
            case "locations":
                for (int i = 1; i < geneLocs.size() + 1; i++) { //to edit in tasklist
                    initList.append(i).append(".");
                    initList.append(geneLocs.get(i - 1));
                    initList.append("\n");
                }
                break;
            default:
                break;
            }
        } catch (Exception err) {
            initList.append("Hey! What do you want me to list?\n"
                    + "To list locations, type: \"list locations\"\n"
                    + "To list tasks, type: \"list tasks\"!"
            );
        }
        return Ui.showLine() + "\n" + initList.toString() + Ui.showLine();
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
