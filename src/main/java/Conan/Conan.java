package Conan;

/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Conan class.
 */
import Helper.TaskList;
import Helper.Storage;
import Helper.Ui;
import Helper.CarryOn;
import Exceptions.InvalidYesOrNoException;
import Exceptions.IllegalCommandException;
import CommandSet.*;


public class Conan {

    // used as a temporary value placeholder.
    private static final String EMPTY_STR_FILLER = "";

    // username is an instance variable that stores the name of the user.
    private String username;

    // instance of storage class to deal with storing user tasks.
    private final Storage storage;

    // instance of taskList
    private final TaskList taskList;

    /**
     * Constructor returns a new Conan object.
     * returns a new Conan instance.
     */
    public Conan() {
        Ui.printSeparator();
        Ui.greeting();

        this.username = EMPTY_STR_FILLER;
        this.taskList = new TaskList();
        this.storage = new Storage();

        Ui.printSeparator();
    }

    /**
     * updates the username, returns true if there was a simiar user previously.
     * @param username the name of the user.
     */
    public boolean tellName(String username) {

        boolean isSimilarUser = false;
        String previousUser = EMPTY_STR_FILLER;

        if (storage.isFilePresent()) {
            // the firstline of the file contains the username.
            previousUser = storage.getPreviousUser();
        }

        this.username = username.trim();
        Ui.printSeparator();

        if (!previousUser.equalsIgnoreCase(this.username)) {
            Ui.printSayHello(this.username);
        } else {
            Ui.printFoundSimilarName(previousUser);
            isSimilarUser = true;
        }

        Ui.printSeparator();
        return isSimilarUser;

    }

    /**
     * checks is the user wants to add the previous tasks to list
     * @param userInput the userInput
     * @return true is successful, false if the user typed invalid input.
     */
    public boolean continueFromLastTime(String userInput) {

        boolean isSuccessful = true;

        try {
            userInput = userInput.trim();

            if (CommandChecker.yesOrNoChecker(userInput) == Commands.YES) {
                AddCommand.add(this.taskList, this.storage);
            }
            Ui.printAsk(this.username);
            Ui.printSeparator();

        } catch (InvalidYesOrNoException e) {
            Ui.printSeparator();
            Ui.printMessage(e.toString());
            isSuccessful = false;

        } finally {
            return isSuccessful;
        }

    }

    public CarryOn tell(String message) {
        CarryOn carryOn = CarryOn.NEXT;
        try {
            Ui.printSeparator();
            message = message.trim();
            Commands userCommand = CommandChecker.findAndCheck(message);

            switch (userCommand) {
            case ADD:
                AddCommand.add(taskList, message);
                break;
            case BYE:
                ByeCommand.bye(this.username, this.storage, this.taskList);
                Ui.printFarewell(this.username);
                Ui.printSeparator();
                carryOn = CarryOn.STOP;
            case DELETE:
                DeleteCommand.delete(this.taskList, message);
                break;
            case DUEBEFORE:
                DueCommand.dueBefore(message, this.taskList);
                break;
            case DUEON:
                DueCommand.dueOn(message, this.taskList);
                break;
            case LIST:
                Ui.printMessage(this.taskList.toString());
                break;
            case MARK:
                Marking.mark(message, this.taskList);
                break;
            case UNMARK:
                Marking.unmark(message, this.taskList);
                break;
            default:
                // do nothing
            }

            Ui.printAsk(this.username);

        } catch (IllegalCommandException e) {
            Ui.printMessage(e.toString());
            Ui.printTryAgain();
        } finally {
            Ui.printSeparator();
            return carryOn;
        }

    }

}
