package duke;

import gui.Ui;
import javafx.application.Platform;

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
     * @return String to output to gui
     */
    public static String parseIsBye(String input, TaskList tasklist){
        if (input.equals("bye")){
            Platform.exit();
            return Ui.printBye();
        } else {
            return parseInput(input, tasklist);
        }
    }

    /**
     * Checks and processes user input (provided that it is not 'bye')
     * @param input user input into Duke (after making sure it is not 'bye')
     * @param taskList current tasklist
     * @return String to output
     */
    public static String parseInput(String input, TaskList taskList) {
        String s;
        assert taskList.tasklist != null : "Invalid: Tasklist is null!";
        if (input.equals("list")) {
            s = Ui.printAllTasks(taskList);
        } else if (input.startsWith("delete")) {
            s =  parseDelete(input, taskList);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            s = taskList.markTask(input);
        } else if (input.startsWith("event") || input.startsWith("todo") || input.startsWith("deadline")) {
            s = parseTask(input, taskList);
        } else if (input.startsWith("find ")){
            s = parseFind(input, taskList);
        } else {
            s = Ui.printWhatDoesThatMean();
        }
        return s;
    }

    public static String parseFind(String input, TaskList taskList) {
        int firstIndexOfFindInput = 6;
        String keyword = input.substring(firstIndexOfFindInput);
        return taskList.findTask(keyword);
    }

    public static String parseDelete(String input, TaskList taskList) {
        int firstIndexOfNumericalInput = 7;
        int numericalInput = Integer.parseInt(input.substring(firstIndexOfNumericalInput));
        return taskList.deleteTask(numericalInput);
    }

    public static String parseTask(String input, TaskList taskList) {
        Task task = parseCreateNewTask(input);
        if (task == null) {
            return Ui.printEmptyDescriptionException();
        }
        taskList.get().add(task);
        return task.printFirstAddition;

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
                task = parseToDo(input);
            } else if (input.startsWith("deadline")){
                task = parseDeadline(input);
            } else {
                task = parseEvent(input);
            }
        } catch (EmptyDescriptorException e) {
            Ui.printEmptyDescriptionException();
        }

        return task;
    }

    public static ToDo parseToDo(String input) throws EmptyDescriptorException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfTask = input.substring(4);
        return new ToDo(nameOfTask, false);
    }

    public static Deadline parseDeadline(String input) throws EmptyDescriptorException {
        String[] inputArr = input.split("/by ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfDeadline = inputArr[0].substring(8);
        String timeOfDeadline = inputArr[1];
        return new Deadline(nameOfDeadline, timeOfDeadline, false);
    }

    public static Event parseEvent(String input) throws EmptyDescriptorException {
        String[] inputArr = input.split("/at ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfEvent = inputArr[0].substring(5);
        String timeOfEvent = inputArr[1];
        return new Event(nameOfEvent, timeOfEvent, false);
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
        String typeOfTask = stringArr[0];
        String isMarked = stringArr[1];
        String nameOfTask = stringArr[2];

        if (typeOfTask.equals("D") || typeOfTask.equals("E")) {
            String time = stringArr[3];
            if (typeOfTask.equals("D")) {
                task = new Deadline(nameOfTask, time, true);
            } else {
                task = new Event(nameOfTask, time, true);
            }
        } else {
            task = new ToDo(nameOfTask, true);
        }

        if (isMarked.equals("true")) {
            task.mark();
        }
        return task;
    }

}
