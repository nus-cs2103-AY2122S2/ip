package Conan;

import Helper.TaskList;
import Helper.Storage;
import Helper.Ui;
import Helper.CarryOn;

import Exceptions.InvalidYesOrNoException;
import Exceptions.IllegalCommandException;

import CommandSet.AddCommand;
import CommandSet.ByeCommand;
import CommandSet.CommandChecker;
import CommandSet.Commands;
import CommandSet.DeleteCommand;
import CommandSet.DueCommand;
import CommandSet.Marking;

/**
 * <h1>Conan</h1>
 * <p>
 * Conan class contains the implementation of the task manager.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
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
        Ui.printGreeting();

        this.username = EMPTY_STR_FILLER;
        this.taskList = new TaskList();
        this.storage = new Storage();

        Ui.printSeparator();
    }

    /**
     * updates the username, returns true if there was a similar user previously.
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

            if (CommandChecker.checkYesOrNo(userInput) == Commands.YES) {
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

    /**
     * implements the user command.
     * @param message the user input.
     * @return CarryOn.NEXT if the user wants to continue;
     * or CarryOn.STOP if the user wants to stop.
     */
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
                DueCommand.tasksDueBefore(message, this.taskList);
                break;
            case DUEON:
                DueCommand.tasksDueOn(message, this.taskList);
                break;
            case LIST:
                Ui.printMessage(this.taskList.toString());
                break;
            case MARK:
                Marking.markTask(message, this.taskList);
                break;
            case UNMARK:
                Marking.unmarkTask(message, this.taskList);
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
