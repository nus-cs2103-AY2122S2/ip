public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + "." + tasks.getTaskStatement(i));
        }
    }

}
