public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui userInt, Storage storage) {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < tasks.size() + 1; i++) { //to edit in tasklist
            initList.append(i).append(".");
            initList.append(tasks.get(i - 1));
            initList.append("\n");
        }
        Ui.print(initList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
