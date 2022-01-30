package Duke;

import Duke.Exceptions.EmptyMessageException;
import Duke.Exceptions.WrongDateFormatException;

public class Commands {

    private final ListStorage myStorage;
    private final Printer myPrinter;

    /**
     * Constructor for Commands. Commands contain a ListStorage
     * and Printer class.
     *
     * @param listStorage Specifies a ListStorage to store commands
     * @param printer Printer to print commands.
     */
    public Commands(ListStorage listStorage, Printer printer) {
        this.myStorage = listStorage;
        this.myPrinter = printer;
    }

    /**
     * Prints goodbye message.
     */
    public void cmdBye() {
        myPrinter.printBye();
    }

    /**
     * Parses ToDo command. Parses command and description.
     *
     * @param cmd Command to be parsed.
     * @throws EmptyMessageException Throws exception when there is no description.
     */
    public void cmdTodo(String cmd) throws EmptyMessageException {
        String[] parsedCmd = Parser.parseCmdAndDes(cmd);
        if (cmd.length() == 4) {
            throw new EmptyMessageException("Todo Error");
        }
        assert (parsedCmd.length > 4);
        Task newTask = new ToDo(parsedCmd[1]);
        myStorage.addToList(newTask);
        myPrinter.printTask(newTask, myStorage.length());
    }

    /**
     * Parses Deadline command. Parses description and deadline.
     *
     * @param cmd Command to be parsed.
     * @throws EmptyMessageException Thrown when there is no description.
     * @throws WrongDateFormatException Thrown when date format is wrong.
     */
    public void cmdDeadline(String cmd) throws EmptyMessageException, WrongDateFormatException {
        if (cmd.length() == 8) {
            throw new EmptyMessageException("Deadline Error");
        }
        String[] parsedCmd = Parser.parseCmdAndDes(cmd);
        String[] deadline = Parser.splitDeadlineAndTime(parsedCmd[1]);
        String[] checkException = deadline[1].split("-", 3);
        System.out.println(checkException[1]);
        if (deadline[1].length() < 17 || deadline[1].length() > 18) {
            //not of correct length
            throw new WrongDateFormatException("Wrong date format");
        } else if (checkException[0].length() != 4) {
            //year was not put first
            throw new WrongDateFormatException("Wrong date format");
        } else if (!deadline[1].contains(":")) {
            throw new WrongDateFormatException("Wrong time format");
        } else if (!deadline[1].contains("AM") && !deadline[1].contains("PM")) {
            throw new WrongDateFormatException("Wrong time format");
        }
        //think about how to handle missing deadline exceptions
        Task newTask = new Deadline(deadline[0], deadline[1]);
        myStorage.addToList(newTask);
        myPrinter.printTask(newTask, myStorage.length());
    }

    /**
     * Parses Event command. Parses description and event timing.
     *
     * @param cmd Command to be parsed
     * @throws EmptyMessageException Thrown when there is no description.
     * @throws WrongDateFormatException Thwon when date format is wrong.
     */
    public void cmdEvent(String cmd) throws EmptyMessageException, WrongDateFormatException{
        if(cmd.length() == 5) {
            throw new EmptyMessageException("Event Error");
        }
        String[] parsedCmd = Parser.parseCmdAndDes(cmd);
        String[] event = Parser.splitEventAndTime(parsedCmd[1]);
        String[] checkException = event[1].split("-", 3);
        //think about how to handle missing at exceptions
        if (event[1].length() < 17 || event[1].length() > 18) {
            //not of correct length
            throw new WrongDateFormatException("Wrong date format");
        } else if (checkException[0].length() != 4) {
            //year was not put first
            throw new WrongDateFormatException("Wrong date format");
        } else if (!event[1].contains(":")) {
            throw new WrongDateFormatException("Wrong time format");
        } else if (!event[1].contains("AM") && !event[1].contains("PM")) {
            throw new WrongDateFormatException("Wrong time format");
        }
        Task newTask = new Event(event[0], event[1]);
        myStorage.addToList(newTask);
        myPrinter.printTask(newTask, myStorage.length());
    }

    /**
     * Parses list command. Handles empty lists and
     * lists with multiple tasks.
     */
    public void cmdList() {
        if (myStorage.length() == 0) {
            myPrinter.printEmptyList();
        } else {
            myPrinter.printList(myStorage);
        }
    }

    /**
     * Handles Unmark command.
     *
     * @param taskNumber Task number of task to be unmarked.
     */
    public void cmdUnmark(int taskNumber) {
        myStorage.findTask(taskNumber).unmark();
        myPrinter.printUnmark(myStorage, taskNumber);
    }

    /**
     * Handles mark command.
     *
     * @param taskNumber Task number of task to be marked.
     */
    public void cmdMark(int taskNumber) {
        myStorage.findTask(taskNumber).markAsDone();
        myPrinter.printMark(myStorage, taskNumber);
    }

    /**
     * Handles delete command.
     *
     * @param taskNumber Task number of task to be deleted.
     */
    public void cmdDelete(int taskNumber) {
        myPrinter.printDelete(myStorage, taskNumber);
        myStorage.deleteTask(taskNumber);
    }

    /**
     * Handles find command. Prints our a list of tasks
     * with keyword in description.
     *
     * @param cmd Command to find.
     */
    public void cmdFind(String cmd) {
        String[] keyword = Parser.parseCmdAndDes(cmd);
        ListStorage tempStorage = new ListStorage();
        for (int i = 1; i <= myStorage.length(); i++) {
            if(myStorage.findTask(i).description.contains(keyword[1])) {
                tempStorage.addToList(myStorage.findTask(i));
            }
        }
        myPrinter.printList(tempStorage);
    }
}
