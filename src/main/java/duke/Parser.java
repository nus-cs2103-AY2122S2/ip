package duke;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class that specifically deals with input from the user and calls the respective class and methods
 */
public class Parser {

    // String messages for printing/output
    private final String BYE_MSG = "~BYE!~ Come back to Duke anytime";
    private final String RESET_MSG = "List of tasks has been resetted";
    private final String INVALID_CMD = "You have entered an invalid instruction";
    private final String MISSING_DATETIME = "Description of deadline must include a date/time!";

    /**
     * Empty constructor for the Parser class
     */
    public Parser() {}

    /**
     * Method to take in the input that is passed by the user and makes sense of what to do from the input
     *
     * @param input String input by the user
     * @param taskList Current taskList
     * @return a boolean to check when to stop taking inputs from the user
     * @throws DukeException checks for any invalid instructions that have been inputted by the user
     */
    public boolean takeInput(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            System.out.println(BYE_MSG);
            return true;
        } else if (input.equals("list")) { //Check if input == list
            taskList.printList();
        } else if (input.equals("reset")) { //Instruction to reset the arraylist
            taskList.reset();
            System.out.println(RESET_MSG);
        } else if (input.contains("unmark") || input.contains("delete") || input.contains("mark")) {
            //Check if input == unmark or delete or mark
            String[] splitString = input.split("\\s+");
            String instr = splitString[0];
            if (splitString.length < 2) {
                System.out.println("Did you miss out the index in your input?");
            } else {
                //Make sure the string input contains at least 2 parts, command and index
                assert splitString.length >= 2;
                try {
                    int index = Integer.parseInt(splitString[1]);
                    if (instr.equals("unmark")) {
                        taskList.unmarkTask(index);
                    } else if (instr.equals("mark")) {
                        taskList.markTask(index);
                    } else if (instr.equals("delete")) {
                        taskList.deleteTask(index);
                    } else {
                        throw new DukeException(INVALID_CMD);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        } else if (input.contains("find")) { //input is find
            String[] splitString = input.split(" ", 2);
            if (splitString.length < 2) {
                throw new DukeException("Please input the keyword(s) for find");
            }
            assert splitString.length >= 2;
            String command = splitString[0];
            String text = splitString[1];
            if (command.equals("find")) {
                taskList.find(text);
            } else {
                throw new DukeException(INVALID_CMD);
            }
        } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            //input is a new type of task
            //identify type of task
            String[] stringArray = input.split(" ", 2);

            //task has no task detail/name
            if (stringArray.length < 2) {
                throw new DukeException("Description of task cannot be empty!");
            }

            assert stringArray.length >= 2;
            String taskType = stringArray[0];
            String taskDetails = stringArray[1];

            Task newTask = new Task("");

            if (taskType.equals("todo")) {
                newTask = new Todo(taskDetails);
            } else if (taskType.equals("deadline")) {
                String[] stringSplit = taskDetails.split("/by");
                if (stringSplit.length < 2) {
                    throw new DukeException(MISSING_DATETIME + " Missed out a /by?");
                }
                String details = stringSplit[0].trim();
                String dateTime = stringSplit[1].trim();
                newTask = new Deadline(details, dateTime);
            } else if (taskType.equals("event")) {
                String[] splitString = taskDetails.split("/at");
                if (splitString.length < 2) {
                    throw new DukeException(MISSING_DATETIME + " Missed out a /at?");
                }
                String details = splitString[0].trim();
                String dateTime = splitString[1].trim();
                newTask = new Event(details, dateTime);
            }

            taskList.addTask(newTask);
        } else {
            throw new DukeException("no such task type");
        }
        Ui.printSeparator();
        return false;
    }

    /**
     * Takes an input string and returns the relevant details to be printed out by the GUI depending on instruction
     * @param input String input
     * @param taskList taskList of tasks
     * @return a string containing the details to be printed out
     * @throws DukeException
     */
    public String guiTakeInput(String input, TaskList taskList) throws DukeException {
        if (input.equals("bye")) {
            return BYE_MSG;
        } else if (input.equals("list")) { //Check if input == list
            return taskList.guiPrintList();
        } else if (input.equals("reset")) { //Instruction to reset the arraylist
            taskList.reset();
            return RESET_MSG;
        } else if (input.contains("unmark") || input.contains("delete") || input.contains("mark")) {
            //Check if input == unmark or delete or mark
            String[] splitString = input.split("\\s+");
            String instr = splitString[0];
            if (splitString.length < 2) {
                return "Did you miss out the index in your input?";
            } else {
                assert splitString.length >= 2;
                try {
                    int index = Integer.parseInt(splitString[1]);
                    return modifyTasks(instr, index, taskList);
                } catch (DukeException e) {
                    return e.toString();
                }
            }
        } else if (input.contains("find")) { //input is find
            String[] splitString = input.split(" ", 2);
            return parseFind(splitString, taskList);
        } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            //input is a new type of task
            String[] stringArray = input.split(" ", 2);
            return parseNewTask(stringArray, taskList);
        } else {
            throw new DukeException("No such task type");
        }
    }

    /**
     * Abstracted method to deal with unmark, mark and delete command
     * @param cmd the string command passed in
     * @param index index of task within tasklist
     * @param taskList the tasklist itself
     * @return a String output of the current tasklist after modification
     * @throws DukeException for any invalid command given
     */
    public String modifyTasks(String cmd, Integer index, TaskList taskList) throws DukeException {
        if (cmd.equals("unmark")) {
            return taskList.guiUnmarkTask(index);
        } else if (cmd.equals("mark")) {
            return taskList.guiMarkTask(index);
        } else if (cmd.equals("delete")) {
            return taskList.guiDeleteTask(index);
        } else {
            throw new DukeException(INVALID_CMD);
        }
    }

    /**
     * Abstracted method call for find command
     * @param splitString input that has been split into command and text
     * @param taskList current list of tasks
     * @return String containing index and tasks matching the text input given
     * @throws DukeException for invalid commands and empty text to find
     */
    public String parseFind(String[] splitString, TaskList taskList) throws DukeException {
        if (splitString.length < 2) {
            throw new DukeException("Please input the keyword(s) for find");
        }
        assert splitString.length >= 2;
        String command = splitString[0];
        String text = splitString[1];
        if (command.equals("find")) {
            return taskList.guiFind(text);
        } else {
            throw new DukeException(INVALID_CMD);
        }
    }

    /**
     * Abstracted method call for adding new tasks
     * @param stringArray input that has been split into command and task details
     * @param taskList current list of tasks
     * @return String containing confirmation message and task that has been added
     * @throws DukeException for invalid commands/commands without their required details
     */
    public String parseNewTask(String[] stringArray, TaskList taskList) throws DukeException {
        //task has no task detail/name
        if (stringArray.length < 2) {
            throw new DukeException("Description of task cannot be empty!");
        }

        assert stringArray.length >= 2;
        String taskType = stringArray[0];
        String taskDetails = stringArray[1];

        Task newTask = new Task("");

        //identify new task
        if (taskType.equals("todo")) {
            newTask = new Todo(taskDetails);
        } else if (taskType.equals("deadline")) {
            String[] stringSplit = taskDetails.split("/by");
            if (stringSplit.length < 2) {
                throw new DukeException(MISSING_DATETIME + " Missed out a /by?");
            }
            String details = stringSplit[0].trim();
            String dateTime = stringSplit[1].trim();
            newTask = new Deadline(details, dateTime);
        } else if (taskType.equals("event")) {
            String[] splitString = taskDetails.split("/at");
            if (splitString.length < 2) {
                throw new DukeException(MISSING_DATETIME + " Missed out a /at?");
            }
            String details = splitString[0].trim();
            String dateTime = splitString[1].trim();
            newTask = new Event(details, dateTime);
        }

        for (Task t : taskList.getTasks()) {
            if (t.toString().equals(newTask.toString())) {
                throw new DukeException("This task already exists!");
            }
        }

        return taskList.guiAddTask(newTask);
    }
}
