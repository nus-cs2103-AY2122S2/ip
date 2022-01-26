package duke;

public class Parser {

    public Parser(){
        Storage.parser = this;
    }

    public static boolean parseInput(String input) throws EmptyDescriptorExceptions{
        if (input.equals("bye")) {
            Ui.printBye();
            return true;
        } else {
            if (input.equals("list")) {
                Ui.printAllTasks();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                TaskList.markTask(input);
            } else if (input.startsWith("todo")) {
                TaskList.addTask(input.substring(4,input.length()), null, "T", false);
            } else if (input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("deadline")) {
                    String[] inputArr = input.split("/by ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    TaskList.addTask(inputArr[0].substring(8, inputArr[0].length()), inputArr[1], "D", false);
                } else {
                    String[] inputArr = input.split("/at ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    TaskList.addTask(inputArr[0].substring(5, inputArr[0].length()), inputArr[1], "E", false);
                }
            } else if (input.startsWith("delete")) {
                TaskList.deleter(Integer.parseInt(input.substring(7, input.length())));
            } else {
                Ui.printWhatDoesThatMean();
            }
            return false;
        }
    }

    public static void parseFileData(String input){
        if (input == null || input == ""){
            return;
        }
        String[] stringArr = input.split("---");
        int taskNum = Task.totalTask;
        if (stringArr[0].equals("T")){
            TaskList.addTask(stringArr[2], null, "T", true);
            TaskList.markTaskNum(taskNum, stringArr[1]);
        } else if (stringArr[0].equals("D")){
            TaskList.addTask(stringArr[2], stringArr[3], "D", true);
            TaskList.markTaskNum(taskNum, stringArr[1]);
        } else {
            TaskList.addTask(stringArr[2], stringArr[3], "E", true);
            TaskList.markTaskNum(taskNum, stringArr[1]);
        }
    }
}
