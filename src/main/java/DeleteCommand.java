public class DeleteCommand extends Command{
    private final String taskBody;

    public DeleteCommand(String body) {
        this.taskBody = body;
    }

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        try {
            String[] tokens = taskBody.split(" ");
            String strIndex = tokens[1]; //error here
            int index = Integer.parseInt(strIndex);
            Task targetTask = tasks.get(index);
            tasks.remove(index); //error if empty
            storage.deleteLineToFile(index); //error if empty
            System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Noted. I've removed this task:\n"
                            + "  " + targetTask + "\n"
                            + "Now you have " + tasks.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
        } catch (Exception err) {
            System.out.println("File and list is already empty");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
