package duke;

/**
 * This is the class that parses through inputs
 */
public class Parser {

    public Parser(){
        Storage.parser = this;
    }

    /**
     * Checks if input is 'bye'
     * @param input input entered by user into Duke
     * @return true if input is bye, false otherwise
     */
    public static boolean parseIsBye(String input){
        if (input.equals("bye")){
            return true;
        } else {
            parseInput(input);
            return false;
        }
    }

    /**
     * Checks and processes user input (provided that it is not 'bye')
     * @param input user input into Duke (after making sure it is not 'bye')
     */
    public static void parseInput(String input){
        if (input.equals("list")) {
            Ui.printAllTasks();
        } else if (input.startsWith("delete")) {
            TaskList.deleter(Integer.parseInt(input.substring(7)));
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            TaskList.markTask(input);
        } else if (input.startsWith("event") || input.startsWith("todo") || input.startsWith("deadline")){
            Task task = parseCreateNewTask(input);
            if (task != null){
                TaskList.tasklist.add(task);
            }
        } else {
            Ui.printWhatDoesThatMean();
        }
    }

    /**
     * Parse through input and create a new task
     * @param input user input into Duke
     * @return task based on input parameters
     */
    public static Task parseCreateNewTask(String input){
        Task task = null;
        try {
            if (input.startsWith("todo")) {
                task = new ToDo(input.substring(4), Task.totalTask, false);
            } else if (input.startsWith("deadline")){
                String[] inputArr = input.split("/by ");
                if (inputArr.length == 1) {
                    throw new EmptyDescriptorExceptions();
                }
                task = new Deadline(inputArr[0].substring(8),Task.totalTask, inputArr[1], false);
            } else {
                String[] inputArr = input.split("/at ");
                if (inputArr.length == 1) {
                    throw new EmptyDescriptorExceptions();
                }
                task = new Event(inputArr[0].substring(5), Task.totalTask, inputArr[1], false);
            }
        } catch (EmptyDescriptorExceptions e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * Parse data in file data
     * @param input one line of file data in the form of (Task Type---Task status---Task name---date)
     * @return task based on file data information provided
     */
    public static Task parseFileData(String input){
        if (input == null || input == ""){
            return null;
        }
        String[] stringArr = input.split("---");
        Task task;
        if (stringArr[0].equals("T")){
            task = new ToDo(stringArr[2],Task.totalTask, true);
        } else if (stringArr[0].equals("D")){
            task = new Deadline(stringArr[2],Task.totalTask, stringArr[3], true);
        } else {
            task = new Event(stringArr[2],Task.totalTask, stringArr[3], true);
        }
        if (stringArr[1].equals("true")){
            task.mark();
        }
        return task;
    }

}
