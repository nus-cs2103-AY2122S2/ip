package duke;

/**
 * Class that specifically deals with input from the user and calls the respective class and methods
 */
public class Parser {
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
            System.out.println("~BYE!~ Come back to Duke anytime");
            return true;
        } else if (input.equals("list")) { //Check if input == list
            taskList.printList();
        } else if (input.equals("reset")) { //Instruction to reset the arraylist
            taskList.reset();
            System.out.println("List of tasks has been resetted");
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
                        throw new DukeException("You have entered an invalid instruction");
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
                throw new DukeException("You have entered an invalid command");
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
                    throw new DukeException("Description of deadline must include a date/time! Missed out a /by?");
                }
                String details = stringSplit[0].trim();
                String dateTime = stringSplit[1].trim();
                newTask = new Deadline(details, dateTime);
            } else if (taskType.equals("event")) {
                String[] splitString = taskDetails.split("/at");
                if (splitString.length < 2) {
                    throw new DukeException("Description of event must include a date/time! Missed out a /at?");
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
            return "~BYE!~ Come back to Duke anytime";
        } else if (input.equals("list")) { //Check if input == list
            return taskList.guiPrintList();
        } else if (input.equals("reset")) { //Instruction to reset the arraylist
            taskList.reset();
            return "List of tasks has been resetted";
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
                    if (instr.equals("unmark")) {
                        return taskList.guiUnmarkTask(index);
                    } else if (instr.equals("mark")) {
                        return taskList.guiMarkTask(index);
                    } else if (instr.equals("delete")) {
                        return taskList.guiDeleteTask(index);
                    } else {
                        throw new DukeException("You have entered an invalid instruction");
                    }
                } catch (DukeException e) {
                    return e.toString();
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
                return taskList.guiFind(text);
            } else {
                throw new DukeException("You have entered an invalid command");
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
                    throw new DukeException("Description of deadline must include a date/time! Missed out a /by?");
                }
                String details = stringSplit[0].trim();
                String dateTime = stringSplit[1].trim();
                newTask = new Deadline(details, dateTime);
            } else if (taskType.equals("event")) {
                String[] splitString = taskDetails.split("/at");
                if (splitString.length < 2) {
                    throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                }
                String details = splitString[0].trim();
                String dateTime = splitString[1].trim();
                newTask = new Event(details, dateTime);
            }
            return taskList.guiAddTask(newTask);
        } else {
            throw new DukeException("no such task type");
        }
    }
}
