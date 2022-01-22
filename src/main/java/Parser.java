public class Parser {

    public static boolean parseInput(String input) throws EmptyDescriptorExceptions{
        if (input.equals("bye")) {
            System.out.println("Bye. I hope to see you sometime soon! :)");
            return true;
        } else {
            if (input.equals("list")) {
                Duke.listAllTask();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                Duke.markTask(input);
            } else if (input.startsWith("todo")) {
                Duke.taskList.add(new ToDo(input.substring(4,input.length()), Task.totalTask, false));
            } else if (input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("deadline")) {
                    String[] inputArr = input.split("/by ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    Duke.taskList.add(new Deadline(inputArr[0].substring(8, inputArr[0].length()), Task.totalTask, inputArr[1], false));
                } else {
                    String[] inputArr = input.split("/at ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    Duke.taskList.add(new Event(inputArr[0].substring(5, inputArr[0].length()), Task.totalTask, inputArr[1], false));
                }
            } else if (input.startsWith("delete")) {
                Duke.deleter(Integer.parseInt(input.substring(7, input.length())));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what does that mean :-(");
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
            Duke.taskList.add(new ToDo(stringArr[2], taskNum, true));
            Duke.markTaskNum(taskNum, stringArr[1]);
        } else if (stringArr[0].equals("D")){
            Duke.taskList.add(new Deadline(stringArr[2], taskNum, stringArr[3], true));
            Duke.markTaskNum(taskNum, stringArr[1]);
        } else {
            Duke.taskList.add(new Event(stringArr[2], taskNum, stringArr[3], true));
            Duke.markTaskNum(taskNum, stringArr[1]);
        }
    }
}
