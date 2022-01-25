public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        if (tasks.isEmpty()) {
            ui.free();
        } else {
            ui.preListReply();
            for (int i = 1; i <= tasks.size(); i++) {
                ui.say("\t" + i + ". " + tasks.getTask(i - 1).printStatus());
            }
            ui.postListFace();
        }
    }
}
