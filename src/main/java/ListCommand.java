public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            ui.printMessage(Message.NO_TASK);
            return;
        }

        ui.printLine();
        System.out.println(Message.LIST_TASK);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            int index = i + 1;
            System.out.println("\t" + index + "." + tasks.getTaskByIndex(i).toString());
        }

        ui.printLine();
    }
}
