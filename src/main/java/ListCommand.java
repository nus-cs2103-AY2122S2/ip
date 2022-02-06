import java.util.ArrayList;

/**
 * Prints the current list of tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute() {
        ArrayList<Task> Storage = TaskList.taskList;

        // Formatting task list to be printed
        String formattedTaskList = formatTaskList(TaskList.taskList);
        Ui.printTaskList(formattedTaskList);
    }

    /**
     * Formats the task list for readability
     *
     * @param taskList ArrayList to be formatted
     * @return formattedTaskList Formatted task list
     */
    private String formatTaskList(ArrayList<Task> taskList) {
        int indexCounter = 1;
        String formattedTaskList = "";
        int taskListSize = taskList.size();

        for (Task i : taskList) {
            String item = String.valueOf(indexCounter) + ". " + i.toString();
            formattedTaskList += Duke.newLine(item);
            indexCounter += 1;
        }

        formattedTaskList = formattedTaskList.substring(0, formattedTaskList.length() - 1); // Remove last \n sequence

        return formattedTaskList;
    }


}
