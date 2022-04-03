package Duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public int getNumTasks(){
        return taskList.size();
    }

    /**
     * Process the printing of a task.
     */
    public String processPrintList(Ui uiPrinter){ //needs to be un-voided - return full string
        System.out.println("I got here");
        String fullListMessage = "";
        fullListMessage = uiPrinter.listMsg;
        fullListMessage += uiPrinter.listMsg;
        fullListMessage += uiPrinter.printTasks(taskList);
        return fullListMessage;
    }

    /**
     * Process the marking status of a task.
     */
    public String processMarkingTask(String taskIndexStr, boolean isCompleted,
                                      Ui uiPrinter) throws DukeException{
        int markingIndex = getExistingIndex(taskIndexStr);
        String returnMarkingTask = "";
        if(markingIndex != -1) {
            taskList.get(markingIndex).setCompleted(isCompleted);
            returnMarkingTask = uiPrinter.getCompleteMessage(isCompleted);
            // returnMarkingTask += processPrintList(uiPrinter);
            processPrintList(uiPrinter); //need to get list to return string
            return returnMarkingTask;
        }
        else{
            returnMarkingTask = "Fail to set task complete status!";
            return returnMarkingTask;
        }
    }

    /**
     * Processes a todo task from the user input provided.
     */
    public Task processTodo(String description) {
        return new ToDoTask(description, false);
    }

    public String processStats(String numDayStr, Ui uiPrinter)throws DukeException{ //needs to be un-voided
        checkIfNumber(numDayStr);
        LocalDateTime pastDays = LocalDateTime.now().minusDays(Integer.parseInt(numDayStr));
        ArrayList<Task> filterList =  new ArrayList<>();
        for(Task task: taskList){
            if(!task.isCompleted()){ continue; }
            if(task.getCreatedDate().isBefore(pastDays)){ continue; }
            filterList.add(task);
        }
        String returnStats = "";
        returnStats = uiPrinter.printTasks(filterList);
        return returnStats;
    }

    /**
     * Processes deletion of a task.
     */
    public String processDelete(String delStrIndex, Ui uiPrinter) throws DukeException{
        int delIndex = getExistingIndex(delStrIndex);
        String deleteReturn = "";
        if(delIndex != -1) {
            Task deleteTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            deleteReturn = uiPrinter.getDeleteTaskInListMsg(deleteTask, taskList.size());
            return deleteReturn;
        }
        else{
            deleteReturn = "Fail to delete Task!";
            return deleteReturn;
        }
    }

//    /**
//     * Processes deletion of a task.
//     */
//    public boolean processDelete(String delStrIndex, Ui uiPrinter) throws DukeException{
//        int delIndex = getExistingIndex(delStrIndex);
//        if(delIndex != -1) {
//            Task deleteTask = taskList.get(delIndex);
//            taskList.remove(delIndex);
////            uiPrinter.printMessage(Ui.getDeleteTaskInListMsg(deleteTask, taskList.size()));
//            return true;
//        }
//        else{
////            uiPrinter.printMessage("Fail to delete Task!");
//            return false;
//        }
//    }

    /**
     * Check if in put is a valid number.
     */
    public void checkIfNumber(String numStr) throws DukeException {
        try{
            Integer.parseInt(numStr);
        }
        catch(Exception e){
            throw new DukeException(numStr+" is not a number!");
        }
    }

    /**
     * Processes an Event task from the user input provided.
     */
    public Task processEvent(String description, LocalDateTime fromDt, LocalDateTime toDt ) {
        return new EventTask(description, false, fromDt, toDt);
    }

    /**
     * Processes a Deadline task from the user input provided.
     */
    public Task processDeadline(String description, LocalDateTime dt)  {
        return new DeadlineTask(description,false, dt);
    }

    /**
     * Ensures number selected is within the range of values the tasklist has.
     */
    public int getExistingIndex(String findStrIndex) throws DukeException{
        if(taskList.size() == 0){
            throw new DukeException("Task list is empty!");
        }

        checkIfNumber(findStrIndex);

        int findIndex = Integer.parseInt(findStrIndex)-1;
        if(findIndex >= 0 && findIndex < taskList.size()){
            return findIndex;
        }

        throw new DukeException("Invalid task number selected!");
    }

    /**
     * Finds tasks containing specified keyword.
     */
    public String processFind(String keyword, Ui uiPrinter) { //needs to be un-voided
        String returnFind = "";
        returnFind = uiPrinter.getTaskMsg;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                String temp = (i + 1) + ". " + taskList.get(i).toString() + "\n";
                returnFind += temp;
            }
        }
        return returnFind;
    }
}