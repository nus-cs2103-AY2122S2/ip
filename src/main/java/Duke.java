import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/test.txt";
    private static final String ERROR_FILEIO = "OOPS!!! Error writing to file.";

    private static final String INTRO_MESSAGE = "Hello! I'm Duke\n     What can I do for you?";

    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    private static final String COMMAND_LIST = "list";
    private static final String MESSAGE_LIST = "Here are the tasks in your list:";

    private static final String COMMAND_MARK = "mark";
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";

    private static final String COMMAND_UNMARK = "unmark";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String MESSAGE_TASKADD = "Got it. I've added this task:";

    private static final String COMMAND_TASKDELETE = "delete";
    private static final String MESSAGE_TASKDELETE = "Noted. I've removed this task:";

    private static final String ERROR_EMPTY_MARK = "OOPS!!! Task to mark cannot be empty:(";
    private static final String ERROR_EMPTY_UNMARK = "OOPS!!! Task to unmark cannot be empty:(";
    private static final String ERROR_INVALID_MARK = "OOPS!!! Invalid task number, please select a valid task to mark using the task's number";
    private static final String ERROR_INVALID_UNMARK = "OOPS!!! Invalid task number, please select a valid task to unmark using the task's number";
    private static final String ERROR_INVALID_COMMAND = "OOPS!!! You have entered an invalid command :(";
    private static final String ERROR_INVALID_TODO_TITLE = "OOPS!!! The title of a todo cannot be empty :(";
    private static final String ERROR_INVALID_DEADLINETASK_TITLE = "OOPS!!! The title of a deadline task cannot be empty :(";
    private static final String ERROR_EMPTY_DEADLINETASK_DEADLINE = "OOPS!!! The deadline cannot be empty :( Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";
    private static final String ERROR_INVALID_EVENT_TITLE = "OOPS!!! The title of an event cannot be empty :(";
    private static final String ERROR_EMPTY_EVENT_TIME = "OOPS!!! The time of an event cannot be empty :( Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";
    private static final String ERROR_EMPTY_DELETE = "OOPS!!! Task to delete cannot be empty:(";
    private static final String ERROR_INVALID_DELETE = "OOPS!!! Invalid task number, please select a valid task to delete using the task's number";;

    private static final String ERROR_INVALID_TIME = "OOPS!!! The time is in the wrong format :( Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";

    public static void main(String[] args) {
        printContent(INTRO_MESSAGE);
        List<Task> tasks = Storage.loadFromFile(FILE_PATH);
        Scanner sc = new Scanner(System.in);
            while (true){
                try{
                    String line = sc.nextLine();
                    String[] splitted = line.split("\\s+");
                    String query = splitted[0];
                    if (query.compareTo(COMMAND_BYE) == 0){
                        printContent(MESSAGE_BYE);
                        break;
                    } else if (query.compareTo(COMMAND_LIST) == 0){
                        processList(tasks);
                        Storage.saveToFile(FILE_PATH, tasks);
                    } else if (query.compareTo(COMMAND_MARK) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_EMPTY_MARK);
                        else if (!checkNumericString(splitted[1]) || Integer.parseInt(splitted[1]) > tasks.size() || Integer.parseInt(splitted[1]) <= 0)
                            throw new DukeException(ERROR_INVALID_MARK);
                        Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                        thisTask.markAsDone();
                        Storage.saveToFile(FILE_PATH, tasks);
                        printContent(taskLine(thisTask, MESSAGE_MARK));
                    } else if (query.compareTo(COMMAND_UNMARK) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_EMPTY_UNMARK);
                        else if (!checkNumericString(splitted[1]) || Integer.parseInt(splitted[1]) > tasks.size() || Integer.parseInt(splitted[1]) <= 0)
                            throw new DukeException(ERROR_INVALID_UNMARK);
                        Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                        thisTask.markAsUndone();
                        Storage.saveToFile(FILE_PATH, tasks);
                        printContent(taskLine(thisTask, MESSAGE_UNMARK));
                    } else if (query.compareTo(COMMAND_TODO) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_INVALID_TODO_TITLE);
                        Task thisTask = new TodoTask(line.substring(5).trim());
                        tasks.add(thisTask);
                        Storage.saveToFile(FILE_PATH, tasks);
                        printAddDeleteTaskSuccess(tasks, thisTask, MESSAGE_TASKADD);
                    } else if (query.compareTo(COMMAND_DEADLINE) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_INVALID_DEADLINETASK_TITLE);
                        if (!line.contains("/by"))
                            throw new DukeException(ERROR_EMPTY_DEADLINETASK_DEADLINE);
                        String[] subSplit = line.split("/by");
                        if (subSplit.length == 1)
                            throw new DukeException(ERROR_EMPTY_DEADLINETASK_DEADLINE);
                        String[] dateTimeSplit = subSplit[1].substring(1).split(" ");
                        Task thisTask = null;
                        if (dateTimeSplit.length == 1){
                            thisTask = new DeadlineTask(subSplit[0].substring(9).trim(), dateTimeSplit[0]);
                        } else if (dateTimeSplit.length == 2){
                            thisTask = new DeadlineTask(subSplit[0].substring(9).trim(), dateTimeSplit[0], dateTimeSplit[1]);
                        } else {
                            throw new DukeException(ERROR_INVALID_TIME);
                        }
                        tasks.add(thisTask);
                        Storage.saveToFile(FILE_PATH, tasks);
                        printAddDeleteTaskSuccess(tasks, thisTask, MESSAGE_TASKADD);
                    } else if (query.compareTo(COMMAND_EVENT) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_INVALID_EVENT_TITLE);
                        if (!line.contains("/at"))
                            throw new DukeException(ERROR_EMPTY_EVENT_TIME);
                        String[] subSplit = line.split("/at");
                        if (subSplit.length == 1)
                            throw new DukeException(ERROR_EMPTY_EVENT_TIME);
                        String[] dateTimeSplit = subSplit[1].substring(1).split(" ");
                        Task thisTask = null;
                        if (dateTimeSplit.length == 1){
                            thisTask = new EventTask(subSplit[0].substring(6).trim(), dateTimeSplit[0]);
                        } else if (dateTimeSplit.length == 2) {
                            thisTask = new EventTask(subSplit[0].substring(6).trim(), dateTimeSplit[0], dateTimeSplit[1]);
                        } else {
                            throw new DukeException(ERROR_INVALID_TIME);
                        }
                        tasks.add(thisTask);
                        Storage.saveToFile(FILE_PATH, tasks);
                        printAddDeleteTaskSuccess(tasks, thisTask, MESSAGE_TASKADD);
                    } else if (query.compareTo(COMMAND_TASKDELETE) == 0){
                        if (splitted.length == 1)
                            throw new DukeException(ERROR_EMPTY_DELETE);
                        else if (!checkNumericString(splitted[1]) || Integer.parseInt(splitted[1]) > tasks.size() || Integer.parseInt(splitted[1]) <= 0)
                            throw new DukeException(ERROR_INVALID_DELETE);
                        int index = Integer.parseInt(splitted[1])-1;
                        Task thisTask = tasks.get(index);
                        tasks.remove(index);
                        Storage.saveToFile(FILE_PATH, tasks);
                        printAddDeleteTaskSuccess(tasks, thisTask, MESSAGE_TASKDELETE);
                    } else{
                        throw new DukeException(ERROR_INVALID_COMMAND);
                    }
                } catch (DukeException e){
                    printContent(e.getMessage());
                } catch (DateTimeParseException e){
                    printContent(ERROR_INVALID_TIME);
                } catch (IOException e){
                    printContent(ERROR_FILEIO);
                }
            }
            sc.close();
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void printAddDeleteTaskSuccess(List<Task> tasks, Task task, String message){
        String content = taskLine(task, message) + "\n";
        content += listSizeLine(tasks);
        printContent(content);
    }

    public static String taskLine(Task task, String message){
        return message + "\n       [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.toString();
    }

    public static String listSizeLine(List<Task> tasks){
        return "     Now you have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.";
    }

    public static void printContent(String text){
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }

    public static void processList(List<Task> tasks){
        String list = MESSAGE_LIST + "\n     ";
        if(tasks.size() == 0){
            list += "~~List is currently empty~~";
        }
        for (int i = 0; i < tasks.size(); i++){
            Task thisTask = tasks.get(i);
            list += (i+1) + ". " + "[" + thisTask.getType() + "]" +"[" + thisTask.getStatusIcon() + "] " + thisTask.toString();
            if (i != tasks.size()-1)
                list += "\n     ";
        }
        printContent(list);
    }

    public static Boolean checkNumericString(String string){
        try{
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}