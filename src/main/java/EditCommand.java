public class EditCommand extends Command{
    private final String taskBody;
    private final String mark;
    private String toPrint;

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
            toPrint = "----------------------------" +
                    "----------------------------\n" +
                    "Nice! I've marked this task as done:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + "--------------------------------------------------------\n";
        } else {
            newTask = targetTask.unmarkTask();
            toPrint = "----------------------------" +
                    "----------------------------\n" +
                    "OK, I've marked this task as not done yet:"
                    + "\n" + "  " + targetTask
                    + "\n"
                    + "--------------------------------------------------------\n";
        }

        tasks.set(index, newTask);
        storage.updatesToFile(index, "1");

        userInt.print(toPrint);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
