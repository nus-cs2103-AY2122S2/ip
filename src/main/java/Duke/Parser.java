package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Parses user input and delegates tasks accordingly.
 */
class Parser {

    private TaskList taskList;

    public final static String bye = "bye";
    public final static String list = "list";
    public final static String todo = "todo";
    public final static String deadline = "deadline";
    public final static String event = "event";
    public final static String delete = "delete";
    public final static String mark = "mark";
    public final static String unmark = "unmark";
    public final static String find = "find";
    public final static String showStats = "stats";
    public final static DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");



    public void checkCommandData(String[] taskInfo, int expectedInfoNum) throws DukeInvalidCommandDataInput{
        if(taskInfo.length != expectedInfoNum){
            throw new DukeInvalidCommandDataInput();
        }
    }


    /**
     * Parses the input specified by the user
     */
    public String parseCommand(String userinput, TaskList taskList,
                                Ui uiPrinter, Storage storage) throws DukeException{
        Task newTask = null;
        boolean requiredSaveToFile = false;
        String returnValue = "";
        boolean taskSuccess = false;

        if (userinput.equals(bye)) {
            returnValue = uiPrinter.byeMsg;
        }
        else if (userinput.startsWith(todo)){
            String taskStr = userinput.substring(todo.length());
            taskStr = taskStr.trim();
            newTask = taskList.processTodo(taskStr);
        }
        else if (userinput.startsWith(deadline)){
            newTask = processDeadlineCommand(userinput, taskList);
        }
        else if (userinput.startsWith(event)){
            newTask = processEventCommand(userinput, taskList);
        }
        else if (userinput.equals(list)) {
            returnValue = taskList.processPrintList(uiPrinter); //printlist
        }
        else if (userinput.startsWith(delete)){
            String taskStr = userinput.substring(delete.length());
            taskStr = taskStr.trim();
            returnValue = taskList.processDelete(taskStr, uiPrinter);
//            if (taskSuccess == true) {
//                returnValue = uiPrinter.getDeleteTaskInListMsg(deleteTask, taskList.size());
//                requiredSaveToFile = true;
//            } else {
//                returnValue = "Fail to delete Task!";
//                requiredSaveToFile = false;
//            }
            if (!returnValue.equals("Fail to delete Task!")) {
                requiredSaveToFile = true;
            } else {
                requiredSaveToFile = false;
            }
        }
        else if (userinput.startsWith(mark)){
            String taskStr = userinput.substring(mark.length());
            taskStr = taskStr.trim();
            returnValue = taskList.processMarkingTask(taskStr, true, uiPrinter);
            if (!returnValue.equals("Fail to set task complete status!")) {
                requiredSaveToFile = true;
            } else {
                requiredSaveToFile = false;
            }
        }
        else if (userinput.startsWith(unmark)) {
            String taskStr = userinput.substring(unmark.length());
            taskStr = taskStr.trim();
            returnValue = taskList.processMarkingTask(taskStr, false, uiPrinter);
            if (!returnValue.equals("Fail to set task complete status!")) {
                requiredSaveToFile = true;
            } else {
                requiredSaveToFile = false;
            }
        }
        else if (userinput.startsWith(find)) {
            String findStr = userinput.substring(find.length()).trim();
            returnValue = taskList.processFind(findStr, uiPrinter); //needs to be un-voided
        }
        else if (userinput.startsWith(showStats)) {
            String numDayStr = userinput.substring(showStats.length());
            numDayStr = numDayStr.trim();
            returnValue = taskList.processStats(numDayStr, uiPrinter); //needs to be un-voided
        }
        else {
            throw new DukeException(Ui.invalidCommandMsg);
        }

        if(newTask != null){
            taskList.addTask(newTask);
            uiPrinter.printMessage(Ui.getTaskInListMsg(newTask, taskList.getNumTasks()));
            returnValue = uiPrinter.getTaskInListMsg(newTask, taskList.getNumTasks());
            requiredSaveToFile = true;
        }

        if(requiredSaveToFile){
            storage.saveTaskToFile(taskList.getTaskList(), uiPrinter);
        }
        return returnValue;
    }

    /**
     * Processes instance where user input specifies an Event task.
     */
    private Task processEventCommand(String command, TaskList taskList) throws DukeException {
        String taskStr = command.substring(event.length());
        taskStr = taskStr.trim();
        // event Ed Sheeran Concert /at 2-12-2019 1800_to_2300
        String[] taskInfo = taskStr.split("/at");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            throw new DukeException(Ui.invalidCommandMsg + "\n" + command);
        }

        String description = taskInfo[0].trim();
        String[] fromToDateStr = taskInfo[1].trim().split("_to_"); // 2-12-2019 1800_to_2-12-2019 2300
        String fromDateStr = fromToDateStr[0].trim();
        String toDateStr = fromToDateStr[1].trim();
        LocalDateTime fromDt = null;
        LocalDateTime toDt = null;
        try{
            fromDt = LocalDateTime.parse(fromDateStr, dtFormat);
            toDt = LocalDateTime.parse(toDateStr, dtFormat);
        }
        catch(Exception e){
            throw new DukeException(Ui.invalidCommandMsg + command + "\n" + Ui.expectedEvent);
        }

        return taskList.processEvent(description, fromDt, toDt);
    }

    /**
     * Processes instance where user input specifies an Event task.
     */
    private Task processDeadlineCommand(String command, TaskList taskList) throws DukeException {
        // deadline return book /by 2-12-2019 1800
        String taskStr = command.substring(deadline.length());
        taskStr = taskStr.trim();
        String[] taskInfo = taskStr.split("/by");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            throw new DukeException(Ui.invalidCommandMsg + "\n" +command + "\n" + Ui.expectedDeadline);
        }
        String description = taskInfo[0].trim();
        String dateStr = taskInfo[1].trim();
        LocalDateTime dt = null;
        try{
            dt = LocalDateTime.parse(dateStr, dtFormat);
        }
        catch(Exception e){
            throw new DukeException(Ui.invalidCommandMsg + "\n" +command + "\n" + Ui.expectedDeadline);
        }

        return taskList.processDeadline(description, dt);
    }
}