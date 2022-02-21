import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class DukeMessage{
        public final static String welcomeMsg = "Hello! I am Duke\nWhat can I do for you?";
        public final static String horizontalLine = "________________________________";
        public final static String byeMsg = "Bye! hope to see you again soon!";
        public final static String listMsg = "Here are a list of your tasks!";
        public final static String deleteMsg = "Alright! Let me delete this task:\n";
        public final static String invalidCommandMsg = "I do not understand your command!";
        public final static String addedTaskMsg = "Got it. I've added this task:\n";
        public final static String completedTaskMsg = "Task has been marked as completed.\n";
        public final static String notCompletedTaskMsg = "Task has been marked as not completed.\n";
        public final static String expectedDeadline = "Try typing it in the following format:\n deadline <desciption> /by 31-01-2022 1800";
        public final static String expectedEvent = "Try typing it in the following format:\n event <desciption> /at 31-01-2022 1800_to_31-01-2022 2100";
        public static String getTaskInListMsg(Task task, int numTask){
            return addedTaskMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
        }

        public static String getDeleteTaskInListMsg(Task task, int numTask){
            return deleteMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
        }

        public static String getCompleteMessage(boolean isCompleted){
            return (isCompleted)?completedTaskMsg:notCompletedTaskMsg;
        }
    }

    public static class DukeCommand{
        public final static String bye = "bye";
        public final static String list = "list";
        public final static String todo = "todo";
        public final static String deadline = "deadline";
        public final static String event = "event";
        public final static String delete = "delete";
        public final static String mark = "mark";
        public final static String unmark = "unmark";
        public final static DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }


    public static void printMessage(String msg) {
        System.out.println(DukeMessage.horizontalLine);
        System.out.println(msg);
        System.out.println(DukeMessage.horizontalLine);
    }

    public static void checkCommandData(String[] taskInfo, int expectedInfoNum) throws DukeInvalidCommandDataInput{
        if(taskInfo.length != expectedInfoNum){
            throw new DukeInvalidCommandDataInput();
        }
    }

    public static Task processTodo(String command) {
        String taskStr = command.substring(DukeCommand.todo.length());
        taskStr = taskStr.trim();
        String description = taskStr;
        //printMessage(DukeMessage.addedTaskMsg + "\n" + command);
        return new ToDoTask(description, false);
    }

    public static Task processEvent(String command) {
        String taskStr = command.substring(DukeCommand.event.length());
        taskStr = taskStr.trim();
        // event Ed Sheeran Concert /at 2-12-2019 1800_to_2300
        String[] taskInfo = taskStr.split("/at");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            printMessage(DukeMessage.invalidCommandMsg + "\n" + command);
            return null;
        }
        String description = taskInfo[0].trim();
        String[] fromToDateStr = taskInfo[1].trim().split("_to_"); // 2-12-2019 1800_to_2-12-2019 2300
        String fromDateStr = fromToDateStr[0].trim();
        String toDateStr = fromToDateStr[1].trim();
        LocalDateTime fromDt = null;
        LocalDateTime toDt = null;
        try{
            fromDt = LocalDateTime.parse(fromDateStr, DukeCommand.dtFormat);
            toDt = LocalDateTime.parse(toDateStr, DukeCommand.dtFormat);
        }
        catch(Exception e){
            printMessage(DukeMessage.invalidCommandMsg + command + "\n" + DukeMessage.expectedEvent);
            return null;
        }
        return new EventTask(description, false, fromDt, toDt);
    }

    public static Task processDeadline(String command)  {
        // deadline return book /by 2-12-2019 1800
        String taskStr = command.substring(DukeCommand.deadline.length());
        taskStr = taskStr.trim();
        String[] taskInfo = taskStr.split("/by");
        try{
            checkCommandData(taskInfo, 2);
        }
        catch(DukeInvalidCommandDataInput e){
            printMessage(DukeMessage.invalidCommandMsg + "\n" +command + "\n" + DukeMessage.expectedDeadline);
            return null;
        }
        String description = taskInfo[0].trim();
        String dateStr = taskInfo[1].trim();
        LocalDateTime dt = null;
        try{
            dt = LocalDateTime.parse(dateStr, DukeCommand.dtFormat);
        }
        catch(Exception e){
            printMessage(DukeMessage.invalidCommandMsg + command + "\n" + DukeMessage.expectedDeadline);
            return null;
        }

        return new DeadlineTask(description,false, dt);
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

    public static boolean processDelete(String delStrIndex, ArrayList<Task> taskList){
        int delIndex = getExistingIndex(delStrIndex, taskList);
        if(delIndex != -1) {
            Task deleteTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            printMessage(DukeMessage.getDeleteTaskInListMsg(deleteTask, taskList.size()));
            return true;
        }
        else{
            printMessage("Fail to delete Task!");
            return false;
        }
    }

    public static void processPrintList(ArrayList<Task> taskList){
        printMessage(DukeMessage.listMsg);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) +". " + currentTask);
        }
    }

    public static boolean processMarkingTask(String taskIndexStr, ArrayList<Task> taskList, boolean isCompleted){
        int markingIndex = getExistingIndex(taskIndexStr, taskList);
        if(markingIndex != -1) {
            taskList.get(markingIndex).setCompleted(isCompleted);
            printMessage(DukeMessage.getCompleteMessage(isCompleted));
            processPrintList(taskList);
            return true;
        }
        else{
            printMessage("Fail to set task complete status!");
            return false;
        }
    }

    public static void loadTaskListFromFile(ArrayList<Task> taskList){
        File f = new File("data/duke.txt");
        try {
            Scanner scanFile =  new Scanner(f);
            while(scanFile.hasNext()){
                String lineData = scanFile.nextLine();
                String[] taskData = lineData.split("\\|");
                String taskType = taskData[0].trim();
                boolean isCompleted = taskData[1].trim().equals("1");
                String desc = taskData[2].trim();
                Task newTask = null;
                switch(taskType){
                    case "T":
                        newTask = new ToDoTask(desc, isCompleted);
                        break;
                    case "D":
                        String byDateStr = taskData[3].trim();
                        LocalDateTime dt = LocalDateTime.parse(byDateStr, DukeCommand.dtFormat);
                        newTask = new DeadlineTask(desc, isCompleted, dt);
                        break;
                    case "E":
                        String fromDateStr = taskData[3].trim();
                        LocalDateTime fDt = LocalDateTime.parse(fromDateStr, DukeCommand.dtFormat);
                        String toDateStr = taskData[4].trim();
                        LocalDateTime tDt = LocalDateTime.parse(toDateStr, DukeCommand.dtFormat);
                        newTask = new EventTask(desc, isCompleted, fDt, tDt);
                        break;
                }
                if(newTask != null){
                    taskList.add(newTask);
                }
            }
        }
        catch(FileNotFoundException e){
            // do nothing, no file no load
        }
    }

    public static void saveTaskToFile(ArrayList<Task> taskList){
        File f = new File("data/duke.txt");
        try{
            if(!f.exists()) {
                // create data folder if not exist
                f.getParentFile().mkdirs();
                // create duke.txt
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            for(Task t: taskList){
                fw.write(t.toFileString()+"\n");
            }

            fw.close();
        }
        catch(SecurityException se){
            printMessage(se.getMessage());
        }
        catch(IOException e){
            printMessage("Unable to save to filepath data/duke.txt");
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        loadTaskListFromFile(taskList);
        // Load task List
        Scanner input = new Scanner(System.in);
        String userinput = "";
        Task newTask = null;
        printMessage(DukeMessage.welcomeMsg);
        while(true) {
            userinput = input.nextLine();
            newTask = null;
            boolean requiredSaveToFile = false;
            if (userinput.equals(DukeCommand.bye)) {
                printMessage(DukeMessage.byeMsg);
                break;
            }
            else if (userinput.startsWith(DukeCommand.todo)){
                newTask = processTodo(userinput);
            }
            else if (userinput.startsWith(DukeCommand.deadline)){
                newTask = processDeadline(userinput);
            }
            else if (userinput.startsWith(DukeCommand.event)){
                newTask = processEvent(userinput);
            }
            else if (userinput.equals(DukeCommand.list)) {
                processPrintList(taskList);
            }
            else if (userinput.startsWith(DukeCommand.delete)){
                String taskStr = userinput.substring(DukeCommand.delete.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processDelete(taskStr, taskList);
            }
            else if (userinput.startsWith(DukeCommand.mark)){
                String taskStr = userinput.substring(DukeCommand.mark.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processMarkingTask(taskStr, taskList, true);
            }
            else if (userinput.startsWith(DukeCommand.unmark)) {
                String taskStr = userinput.substring(DukeCommand.unmark.length());
                taskStr = taskStr.trim();
                requiredSaveToFile = processMarkingTask(taskStr, taskList, false);
            }
            else {
                printMessage(DukeMessage.invalidCommandMsg);
            }

            if(newTask != null){
                taskList.add(newTask);
                printMessage(DukeMessage.getTaskInListMsg(newTask, taskList.size()));
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
