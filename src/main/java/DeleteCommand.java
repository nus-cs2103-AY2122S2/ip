public class DeleteCommand extends Command {
    private String fullCommand;
    private String[] fullCommandArr;

    public DeleteCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(6).isBlank()) {                             // no argument
            throw new DeleteException("empty");
        } else if (Character.isLetter(fullCommand.charAt(7))) {               // contains letter instead of number
            throw new DeleteException("letter");
        } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) {   // out of bounds
            throw new DeleteException("OOB");
        } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
            throw new DeleteException("negative");
        }
        Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
        ui.deleteMessage(task);
        tasks.removeTask(task);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }
}
