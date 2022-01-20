import java.util.ArrayList;

/**
 * Prints the current list of tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute() {
        ArrayList<Task> taskList = TaskManager.taskList;

        // Formatting task list to be printed
        String formattedTaskList = formatTaskList(taskList);
        String formattedAnswer = Duke.formatAnswer(formattedTaskList);
        System.out.println(formattedAnswer);
    }

    /**
     * Formats the task list for readability
     * @param taskList ArrayList to be formatted
     * @return formattedTaskList Formatted task list
     */
    private String formatTaskList(ArrayList<Task> taskList) {
        int indexCounter = 1;
        String formattedTaskList = "";
        int taskListSize = taskList.size();

        for (Task i : taskList) {
            String item = String.valueOf(indexCounter) + ". " + i.toString();

            // Correcting formatting caused by Duke.formatAnswer()
            if (indexCounter == 1) {
                formattedTaskList += (item + "\n");
            } else if (indexCounter == taskListSize) {
                formattedTaskList += ("\t" + item);
            } else {
                formattedTaskList += ("\t" + item + "\n");
            }

            indexCounter += 1;
        }

        return formattedTaskList;
    }


}
