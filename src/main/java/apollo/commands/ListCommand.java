package apollo.commands;

public class ListCommand extends Command {

    private String getIndexedList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.taskCount(); i++) {
            stringBuilder.append("\n\t").append(i+1).append(".");
            stringBuilder.append(taskList.getTaskString(i));
        }
        return stringBuilder.toString();
    }

    @Override
    public String execute() {
        if (taskList.taskCount() == 0) {
            return "You currently have no tasks. ";
        }
        return String.format("These are your current tasks. %s", getIndexedList());
    }
}
