import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    public static void processPrintList(ArrayList<Task> taskList){
        printMessage(Duke.DukeMessage.listMsg);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) +". " + currentTask);
        }
    }

    public static boolean processMarkingTask(String taskIndexStr, ArrayList<Task> taskList, boolean isCompleted){
        int markingIndex = getExistingIndex(taskIndexStr, taskList);
        if(markingIndex != -1) {
            taskList.get(markingIndex).setCompleted(isCompleted);
            printMessage(Duke.DukeMessage.getCompleteMessage(isCompleted));
            processPrintList(taskList);
            return true;
        }
        else{
            printMessage("Fail to set task complete status!");
            return false;
        }
    }

    public static void checkCommandData(String[] taskInfo, int expectedInfoNum) throws DukeInvalidCommandDataInput{
        if(taskInfo.length != expectedInfoNum){
            throw new DukeInvalidCommandDataInput();
        }
    }

    public static Task processTodo(String command) {
        String taskStr = command.substring(Duke.DukeCommand.todo.length());
        taskStr = taskStr.trim();
        String description = taskStr;
        //printMessage(DukeMessage.addedTaskMsg + "\n" + command);
        return new ToDoTask(description, false);
    }

    public static boolean processDelete(String delStrIndex, ArrayList<Task> taskList){
        int delIndex = getExistingIndex(delStrIndex, taskList);
        if(delIndex != -1) {
            Task deleteTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            printMessage(Duke.DukeMessage.getDeleteTaskInListMsg(deleteTask, taskList.size()));
            return true;
        }
        else{
            printMessage("Fail to delete Task!");
            return false;
        }
    }

    public static boolean checkIfNumber(String numStr){
        try{
            Integer.parseInt(numStr);
            return true;
        }
        catch(Exception e){
            printMessage(numStr+" is not a number!");
            return false;
        }
    }

    public static Task processEvent(String command) {
        String taskStr = command.substring(Duke.DukeCommand.event.length());
        taskStr = taskStr.trim();
        // event Ed Sheeran Concert /at 2-12-2019 1800_to_2300
        String[] taskInfo = taskStr.split("/at");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            printMessage(Duke.DukeMessage.invalidCommandMsg + "\n" + command);
            return null;
        }
        String description = taskInfo[0].trim();
        String[] fromToDateStr = taskInfo[1].trim().split("_to_"); // 2-12-2019 1800_to_2-12-2019 2300
        String fromDateStr = fromToDateStr[0].trim();
        String toDateStr = fromToDateStr[1].trim();
        LocalDateTime fromDt = null;
        LocalDateTime toDt = null;
        try{
            fromDt = LocalDateTime.parse(fromDateStr, Duke.DukeCommand.dtFormat);
            toDt = LocalDateTime.parse(toDateStr, Duke.DukeCommand.dtFormat);
        }
        catch(Exception e){
            printMessage(Duke.DukeMessage.invalidCommandMsg + command + "\n" + Duke.DukeMessage.expectedEvent);
            return null;
        }
        return new EventTask(description, false, fromDt, toDt);
    }

    public static Task processDeadline(String command)  {
        // deadline return book /by 2-12-2019 1800
        String taskStr = command.substring(Duke.DukeCommand.deadline.length());
        taskStr = taskStr.trim();
        String[] taskInfo = taskStr.split("/by");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            printMessage(Duke.DukeMessage.invalidCommandMsg + "\n" +command + "\n" + Duke.DukeMessage.expectedDeadline);
            return null;
        }
        String description = taskInfo[0].trim();
        String dateStr = taskInfo[1].trim();
        LocalDateTime dt = null;
        try{
            dt = LocalDateTime.parse(dateStr, Duke.DukeCommand.dtFormat);
        }
        catch(Exception e){
            printMessage(Duke.DukeMessage.invalidCommandMsg + command + "\n" + Duke.DukeMessage.expectedDeadline);
            return null;
        }

        return new DeadlineTask(description,false, dt);
    }

    public static int getExistingIndex(String findStrIndex, ArrayList<Task> taskList){
        if(taskList.size() == 0){
            printMessage("Task list is empty!");
            return -1;
        }

        if(!checkIfNumber(findStrIndex)){
            return -1;
        }

        int findIndex = Integer.parseInt(findStrIndex)-1;
        if(findIndex >= 0 && findIndex < taskList.size()){
            return findIndex;
        }
        else{
            printMessage("Invalid task number selected!");
            return -1;
        }
    }
}
