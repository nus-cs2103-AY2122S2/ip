package duke;


import gui.Output;

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
    public static String parseIsBye(String input, TaskList tasklist){
        if (input.equals("bye")){
            return Output.printBye();
        } else {
            return parseInput(input, tasklist);
        }
    }

    /**
     * Checks and processes user input (provided that it is not 'bye')
     * @param input user input into Duke (after making sure it is not 'bye')
     * @param taskList current tasklist
     */
    public static String parseInput(String input, TaskList taskList) {
        String s;
        if (input.equals("list")) {
            s = Output.printAllTasks(taskList);
        } else if (input.startsWith("delete")) {
            s =  taskList.deleteTask(Integer.parseInt(input.substring(7)));
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            s = taskList.markTask(input);
        } else if (input.startsWith("event") || input.startsWith("todo") || input.startsWith("deadline")) {
            Task task = parseCreateNewTask(input);
            if (task != null) {
                taskList.tasklist.add(task);
                s = task.printFirstAddition;
            } else {
                s = Output.printEmptyDescriptionException();
            }
        } else if (input.startsWith("find ")){
            s = taskList.findTask(input.substring(6));
        } else {
            s = Output.printWhatDoesThatMean();
        }
        return s;
    }

    /**
     * Parse through input and create a new task
     * @param input user input into Duke
     * @return task based on input parameters
     */
    public static Task parseCreateNewTask(String input) {
        Task task = null;
        try {
            if (input.startsWith("todo")) {
                task = new ToDo(input.substring(4), Task.totalTask, false);
            } else if (input.startsWith("deadline")){
                String[] inputArr = input.split("/by ");
                if (inputArr.length == 1) {
                    throw new EmptyDescriptorExceptions();
                }
                task = new Deadline(inputArr[0].substring(8), Task.totalTask, inputArr[1], false);
            } else {
                String[] inputArr = input.split("/at ");
                if (inputArr.length == 1) {
                    throw new EmptyDescriptorExceptions();
                }
                task = new Event(inputArr[0].substring(5), Task.totalTask, inputArr[1], false);
            }
        } catch (EmptyDescriptorExceptions e) {
            Output.printEmptyDescriptionException();
        }

        return task;
    }

    /**
     * Parse data in file data
     * @param input one line of file data in the form of (Task_Type---Task_status---Task_name---date)
     * @return task based on file data information provided
     */
    public static Task parseFileData(String input) {
        if (input == null || input == ""){
            return null;
        }
        String[] stringArr = input.split("---");
        Task task;
        if (stringArr[0].equals("T")) {
            task = new ToDo(stringArr[2], Task.totalTask, true);
        } else if (stringArr[0].equals("D")) {
            task = new Deadline(stringArr[2],Task.totalTask, stringArr[3], true);
        } else {
            task = new Event(stringArr[2],Task.totalTask, stringArr[3], true);
        }
        if (stringArr[1].equals("true")) {
            task.mark();
        }
        return task;
    }

}
