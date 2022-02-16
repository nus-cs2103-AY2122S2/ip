package walle;

import gui.Ui;
import javafx.application.Platform;

/**
 * This is the class that parses through inputs
 */
public class Parser {


    public Parser() {
        Storage.parser = this;
    }

    /**
     * Checks if input is 'bye'
     *
     * @param input input entered by user into Walle
     * @return String to output to gui
     */
    public static String parseIsBye(String input, TaskList taskList) {
        if (input.equals("bye")) {
            Platform.exit();
            return Ui.printBye();
        } else {
            return parseInput(input, taskList);
        }
    }

    /**
     * Checks and processes user input (provided that it is not 'bye')
     *
     * @param input user input into Walle (after making sure it is not 'bye')
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
            s = parseMarkTask(input, taskList);
        } else if (input.startsWith("event") || input.startsWith("todo") || input.startsWith("deadline")) {
            s = parseTask(input, taskList);
        } else if (input.startsWith("find ")) {
            s = parseFind(input, taskList);
        } else {
            s = Ui.printWhatDoesThatMean();
        }
        return s;
    }

    /**
     * parses input to mark/unmark specified task
     *
     * @param input user input
     * @param taskList tasklist that task resides
     * @return output to display to GUI
     */
    public static String parseMarkTask(String input, TaskList taskList) {
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        if (taskNum < 0 || taskNum > taskList.get().size()) {
            return Ui.printNoSuchTask();
        }
        if (input.startsWith("unmark")) {
            return taskList.markTask(taskNum, false);
        }
        return taskList.markTask(taskNum, true);
    }

    /**
     * parses input to find specified task
     *
     * @param input user input
     * @param taskList tasklist that task resides
     * @return output to display to GUI
     */
    public static String parseFind(String input, TaskList taskList) {
        int firstIndexOfFindInput = 6;
        String keyword = input.substring(firstIndexOfFindInput);
        return taskList.findTask(keyword);
    }

    /**
     * parses input to delete specified task
     *
     * @param input user input
     * @param taskList tasklist that task resides
     * @return output to display to GUI
     */
    public static String parseDelete(String input, TaskList taskList) {
        int firstIndexOfNumericalInput = 7;
        int numericalInput = Integer.parseInt(input.substring(firstIndexOfNumericalInput));
        return taskList.deleteTask(numericalInput);
    }

    /**
     * Parses input to create a task
     *
     * @param input user input
     * @param taskList tasklist to add task to
     * @return output to display to GUI
     */
    public static String parseTask(String input, TaskList taskList) {
        Task task = parseCreateNewTask(input, taskList);
        if (task == null) {
            return Ui.printEmptyDescriptionException();
        }
        int duplicateEntry = taskList.addTaskToList(task);
        if (duplicateEntry != -1) {
            return Ui.printDuplicateTask(duplicateEntry);
        }
        return task.printFirstAddition;

    }

    /**
     * Parse through input and create a new task
     *
     * @param input user input into Walle
     * @return task based on input parameters
     */
    public static Task parseCreateNewTask(String input, TaskList taskList) {
        Task task = null;
        try {
            if (input.startsWith("todo")) {
                task = parseToDo(input, taskList);
            } else if (input.startsWith("deadline")) {
                task = parseDeadline(input, taskList);
            } else {
                task = parseEvent(input, taskList);
            }
        } catch (EmptyDescriptorException e) {
            Ui.printEmptyDescriptionException();
        }

        return task;
    }

    /**
     * Parse input and return a ToDo
     *
     * @param input user input
     * @param list tasklist to add ToDo
     * @return ToDo created based on user input
     * @throws EmptyDescriptorException
     */
    public static ToDo parseToDo(String input, TaskList list) throws EmptyDescriptorException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfTask = input.substring(4);
        return new ToDo(nameOfTask, list.getSize(), false);
    }

    /**
     * Parse input and return a Deadline
     *
     * @param input user input
     * @param list tasklist to add Deadline
     * @return Deadline created based on user input
     * @throws EmptyDescriptorException
     */
    public static Deadline parseDeadline(String input, TaskList list) throws EmptyDescriptorException {
        String[] inputArr = input.split("/by ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfDeadline = inputArr[0].substring(8);
        String timeOfDeadline = inputArr[1];
        return new Deadline(nameOfDeadline, timeOfDeadline, list.getSize(), false);
    }

    /**
     * Parse input and return an Event
     *
     * @param input user input
     * @param list tasklist to add Event
     * @return Event created based on user input
     * @throws EmptyDescriptorException
     */
    public static Event parseEvent(String input, TaskList list) throws EmptyDescriptorException {
        String[] inputArr = input.split("/at ");
        if (inputArr.length == 1) {
            throw new EmptyDescriptorException();
        }
        String nameOfEvent = inputArr[0].substring(5);
        String timeOfEvent = inputArr[1];
        return new Event(nameOfEvent, timeOfEvent, list.getSize(),false);
    }
    /**
     * Parse data in file data
     *
     * @param input one line of file data in the form of (Task_Type---Task_status---Task_name---date)
     * @return task based on file data information provided
     */
    public static Task parseFileData(String input, TaskList list) {
        if (input == null || input == "") {
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
                task = new Deadline(nameOfTask, time, list.getSize(), true);
            } else {
                task = new Event(nameOfTask, time, list.getSize(), true);
            }
        } else {
            task = new ToDo(nameOfTask, list.getSize(), true);
        }

        if (isMarked.equals("true")) {
            task.mark();
        }
        return task;
    }

}
