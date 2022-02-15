package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class to make sense of user commands and execute the commands.
 */
class Parser {
    private TaskList taskList;

    /**
     * Constructor of Parser class.
     *
     * @param taskList TaskList object in which execution of the commands will be carried out on.
     */
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to handle the command being passed in.
     *
     * @param command String representation of the command that is passed in.
     * @return String to be printed after executing the command.
     * @throws SiriException if command structure is invalid, command is not recognised or
     * if there are missing or extra fields in command.
     */
    public String handleCommand(String command) throws SiriException {

        String[] inputSplit = command.split(" ", 2);
        String printString = "";

        if (inputSplit[0].equals("")) {
            throw new SiriException("Please ENTER something!!");
        } else {
            switch (inputSplit[0]) {
            case "mark":
                printString = handleMarkCommand(inputSplit);
                break;
            case "unmark":
                printString = handleUnmarkCommand(inputSplit);
                break;
            case "list":
                printString = handleListCommand(inputSplit);
                break;
            case "todo":
                printString = handleTodoCommand(inputSplit);
                break;
            case "deadline":
                printString = handleDeadlineCommand(inputSplit);
                break;
            case "event":
                printString = handleEventCommand(inputSplit);
                break;
            case "delete":
                printString = handleDeleteCommand(inputSplit);
                break;
            case "eprint":
                printString = handleEPrintCommand(inputSplit);
                break;
            case "dlprint":
                printString = handleDlPrintCommand(inputSplit);
                break;
            case "find":
                printString = handleFindCommand(inputSplit);
                break;
            case "bye":
                throw new SiriException("Bye!!");
            default:
                throw new SiriException("OPPS!! I did not understand what you had keyed!! Please try again!!");
            }
        }

        return printString;
    }

    private String handleMarkCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER the item number to mark!!");
        }

        try {
            ArrayList<Integer> indexArray = this.createMassOpsList(input[1].trim());
            return this.taskList.markItem(indexArray);
        } catch (NumberFormatException nfe) {
            throw new SiriException("Please ENTER a valid item number to mark!!");
        }

    }

    private String handleUnmarkCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER the item number to unmark!!");
        }

        try {
            ArrayList<Integer> indexArray = this.createMassOpsList(input[1].trim());
            return this.taskList.unmarkItem(indexArray);
        } catch (NumberFormatException nfe) {
            throw new SiriException("Please ENTER a valid item number to unmark!!");
        }
    }

    private String handleListCommand(String[] input) throws SiriException {
        if (input.length != 1 || input[1].trim().length() != 0) {
            throw new SiriException("OPPS!! list does not take in any parameter!!");
        }
        return this.taskList.print();
    }

    private String handleTodoCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("todo cannot be EMPTY!! Please ENTER something for todo!!");
        }

        ToDos todoTask = new ToDos(input[1].trim(), false);
        return this.taskList.addItem(todoTask);
    }

    private String handleDeadlineCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("deadline cannot be EMPTY!! Please ENTER something for deadline!!");
        }

        String[] dlSplit = input[1].split(" /by ", 2);

        if (dlSplit.length == 1 || dlSplit[1].trim().length() == 0) {
            throw new SiriException("deadline has no date/time!! Please ENTER a date/time for deadline!!");
        }

        try {
            Deadline dlTask;
            String[] dlDateTime = dlSplit[1].split(" ", 2);
            LocalDate dlDate = Parser.stringToDate(dlDateTime[0].trim());
            if (dlDateTime.length == 1 || dlDateTime[1].trim().length() == 0) {
                dlTask = new Deadline(dlSplit[0], false, dlDate);
            } else {
                LocalTime dlTime = Parser.stringToTime(dlDateTime[1].trim());
                dlTask = new Deadline(dlSplit[0], false, dlDate, dlTime);
            }
            return this.taskList.addItem(dlTask);
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("deadline date/time format is wrong!!\n"
                    + "Please ENTER your date time in DD-MM-YYYY HH:MM (if applicable) format!!");
        }
    }

    private String handleEventCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("event cannot be EMPTY!! Please ENTER something for event!!");
        }

        String[] eventSplit = input[1].split(" /at ", 2);

        if (eventSplit.length == 1 || eventSplit[1].trim().length() == 0) {
            throw new SiriException("event has no date/time!! Please ENTER a date and time for event!!");
        }

        try {
            String[] eventDateTime = eventSplit[1].split(" ", 2);
            LocalDate eDate = Parser.stringToDate(eventDateTime[0].trim());
            Event eventTask;
            if (eventDateTime.length == 1 || eventDateTime[1].trim().length() == 0) {
                throw new SiriException("Missing date/time field!!\n"
                        + "Please ENTER date your date time in DD-MM-YYYY HH:MM format!!");
            } else {
                LocalTime eTime = Parser.stringToTime(eventDateTime[1].trim());
                eventTask = new Event(eventSplit[0], false, eDate, eTime);
            }
            return this.taskList.addItem(eventTask);
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("event date/time format is wrong!!\n"
                    + "Please ENTER your date time in DD-MM-YYYY  HH:MM format!!");
        }


    }

    private String handleDeleteCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER the item number to delete!!");
        }

        if (this.taskList.size() == 0) {
            throw new SiriException("There is currently no tasks!!");
        }

        try {
            ArrayList<Integer> indexArray = this.createMassOpsList(input[1].trim());

            return this.taskList.deleteItem(indexArray);
        } catch (NumberFormatException nfe) {
            throw new SiriException("Please ENTER a valid item number to unmark!!");
        }

    }

    private String handleEPrintCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER a date!!");
        }

        try {
            LocalDate eCheckedDate = Parser.stringToDate(input[1].trim());
            return this.taskList.printEventOn(eCheckedDate);
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
        }

    }

    private String handleDlPrintCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER a date!!");
        }

        try {
            LocalDate dlCheckedDate = Parser.stringToDate(input[1].trim());
            return this.taskList.printDeadlineOn(dlCheckedDate);
        } catch (DateTimeParseException dtpe) {
            throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
        }
    }

    private String handleFindCommand(String[] input) throws SiriException {
        if (input.length == 1 || input[1].trim().length() == 0) {
            throw new SiriException("Please ENTER a word to find!!");
        }
        return this.taskList.find(input[1].trim());
    }

    private ArrayList<Integer> createMassOpsList(String input) throws SiriException {
        String[] inputSplit = input.split(" ");
        ArrayList<Integer> indexArray = new ArrayList<Integer>();
        HashSet<Integer> indexDuplicateChecker = new HashSet<Integer>();
        for (int i = 0; i < inputSplit.length; i++) {
            if (inputSplit[i].trim() == "") {
                continue;
            }

            int index = Integer.parseInt(inputSplit[i].trim()) - 1;

            if (indexDuplicateChecker.contains(index)) {
                throw new SiriException("Please do not ENTER duplicate index!!");
            }

            indexDuplicateChecker.add(index);
            indexArray.add(index);
        }
        return indexArray;
    }

    private static LocalDate stringToDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    private static LocalTime stringToTime(String timeString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(timeString, dtf);

        return lt;
    }

}
