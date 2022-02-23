import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        loadTaskListFromFile(taskList);
        // Load task List
        Scanner input = new Scanner(System.in);
        String userinput = "";
        Task newTask = null;
        printMessage(Duke.DukeMessage.welcomeMsg);
        while(true) {
            userinput = input.nextLine();
            newTask = null;
            boolean requiredSaveToFile = false;
            if (userinput.equals(Duke.DukeCommand.bye)) {
                printMessage(Duke.DukeMessage.byeMsg);
                break;
            }
            else if (userinput.startsWith(Duke.DukeCommand.todo)){
                newTask = processTodo(userinput);
            }
            else if (userinput.startsWith(Duke.DukeCommand.deadline)){
                newTask = processDeadline(userinput);
            }
            else if (userinput.startsWith(Duke.DukeCommand.event)){
                newTask = processEvent(userinput);
            }
            else if (userinput.equals(Duke.DukeCommand.list)) {
                processPrintList(taskList);
            }
            else if (userinput.startsWith(Duke.DukeCommand.delete)){
                String taskStr = userinput.substring(Duke.DukeCommand.delete.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processDelete(taskStr, taskList);
            }
            else if (userinput.startsWith(Duke.DukeCommand.mark)){
                String taskStr = userinput.substring(Duke.DukeCommand.mark.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processMarkingTask(taskStr, taskList, true);
            }
            else if (userinput.startsWith(Duke.DukeCommand.unmark)) {
                String taskStr = userinput.substring(Duke.DukeCommand.unmark.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processMarkingTask(taskStr, taskList, false);
            }
            else {
                printMessage(Duke.DukeMessage.invalidCommandMsg);
            }

            if(newTask != null){
                taskList.add(newTask);
                printMessage(Duke.DukeMessage.getTaskInListMsg(newTask, taskList.size()));
                requiredSaveToFile = true;
            }

            if(requiredSaveToFile){
                saveTaskToFile(taskList);
            }
        }

        input.close();
        //save task list
    }
}
