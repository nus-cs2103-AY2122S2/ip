public class MarkDoneCommand extends MarkCommand{
    public MarkDoneCommand(String userInput){
        super(userInput);
    }

    @Override
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            if (taskManager.size() == 0) {
                ui.showMarkEmptyList();
                return false;
            } else {
                int index = Integer.parseInt(userInput.replaceFirst("mark", "").strip())-1;

                if (index < 0 || index >= taskManager.size()) {
                    ui.showMarkOutOfBounds();
                    return false;
                } else {
                    boolean isSuccess = taskManager.markTaskDone(index);
                    if (isSuccess) {
                        ui.showMarked(taskManager.getTask(index));
                        save(storage,ui,taskManager);
                    } else {
                        ui.showMarkNotNeeded(taskManager.getTask(index));
                    }
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number entered!");
        }
    }
}
