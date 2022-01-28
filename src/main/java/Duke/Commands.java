package Duke;

import Duke.Exceptions.EmptyMessageException;
import Duke.Exceptions.WrongDateFormatException;

public class Commands {

    private final ListStorage myStorage;
    private final Printer myPrinter;

    public Commands(ListStorage listStorage, Printer printer) {
        this.myStorage = listStorage;
        this.myPrinter = printer;
    }

    public void cmdBye() {
        myPrinter.printBye();
    }

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

    public void cmdList() {
        if (myStorage.length() == 0) {
            myPrinter.printEmptyList();
        } else {
            myPrinter.printList(myStorage);
        }
    }

    public void cmdUnmark(int taskNumber) {
        myStorage.findTask(taskNumber).unmark();
        myPrinter.printUnmark(myStorage, taskNumber);
    }

    public void cmdMark(int taskNumber) {
        myStorage.findTask(taskNumber).markAsDone();
        myPrinter.printMark(myStorage, taskNumber);
    }

    public void cmdDelete(int taskNumber) {
        myPrinter.printDelete(myStorage, taskNumber);
        myStorage.deleteTask(taskNumber);
    }
}
