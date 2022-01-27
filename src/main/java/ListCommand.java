public class ListCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.outputText("Currently the list is empty. There is nothing to output.");
        } else {
            String outputText = "";
            for (int i = 1; i <= taskList.getSize(); i++) {
                outputText = outputText.concat(i + "." + taskList.getTaskOutputStyle(i - 1));
                if (i != taskList.getSize()) outputText = outputText.concat("\n");
            }
            ui.outputText(outputText);
        }
    }
    public boolean isExit() {
        return false;
    }
}
