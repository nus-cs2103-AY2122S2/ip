public class EditCommand extends Command{
    private final String taskBody;
    private final String mark;

    public EditCommand(String body, String mark) {
        this.taskBody = body;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        String[] tokens = this.taskBody.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = tasks.get(index); //have to edit tasklist
        Task newTask;
        if (this.mark.equals("1")) {
            newTask = targetTask.markTask();
        } else {
            newTask = targetTask.unmarkTask();
        }

        tasks.set(index, newTask);
        storage.editFile(index, "1");
        ui.print();

        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Nice! I've marked this task as done:"
                        + "\n" + "  " + targetTask
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
