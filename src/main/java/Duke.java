import duke.managers.DateTimeManager;
import duke.parsers.InputParser;
import duke.parsers.InputType;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyInputException;
import duke.managers.JSONFileManager;
import duke.tasks.WordList;
import duke.tasks.WordListItem;
import duke.ui.DukeUI;

import java.util.Scanner;

/**
 * Duke is an app made for users with some technical background to manage their tasks.
 * Duke is highly customized for those used to the CLI.
 */
public class Duke {
    private WordList wordList;
    private JSONFileManager jsonFileManager;
    private DukeUI ui;

    /**
     * Constructs the app Duke with default settings.
     */
    public Duke() {
        this.ui = new DukeUI(new Scanner(System.in));
        this.jsonFileManager = new JSONFileManager();
        this.jsonFileManager.setUpSaveSystem();
        this.wordList = this.jsonFileManager.loadListFromJSONFile();
    }

    /**
     * Construct the app Duke with the given storagePath and storageFileName.
     * @param storagePath string of storage path
     * @param storageFileName string of storage file name (with .json extension)
     */
    public Duke(String storagePath, String storageFileName) {
        this.ui = new DukeUI(new Scanner(System.in));
        this.jsonFileManager = new JSONFileManager(storagePath, storageFileName);
        this.jsonFileManager.setUpSaveSystem();
        this.wordList = this.jsonFileManager.loadListFromJSONFile();
    }

    /**
     * Runs the Duke app.
     * Handles the logic and processes of the app.
     */
    public void run() {
        this.ui.replyWelcomeMessage();
        String input;

        while (true) {
            try {
                input = this.ui.waitForinput();
                checkEmpty(input);
                Object[] parseResult = InputParser.parseInput(input);
                InputType inputType = (InputType) parseResult[0];
                String[] value = (String[]) parseResult[1];

                processInput(inputType, value);
                if (inputType == InputType.BYE) {
                    break;
                }
                this.jsonFileManager.saveListToJSONFile(wordList);
            } catch (EmptyInputException e) {
                this.ui.replyError(e);
                continue;
            } catch (EmptyDescriptionException e) {
                this.ui.replyError(e);
            } catch (NumberFormatException e) {
                this.ui.replyError(e);
            } catch (Exception e) {
                this.ui.replyError(e);
            }
        }

        this.ui.replyBye();
    }

    /**
     * initiate Duke.java to start the app.
     * @param args
     */
    public static void main(String[] args) {
        Duke dukeApp = new Duke("src/data/", "anotherTasks.json");
        dukeApp.run();
    }

    /**
     * Process the input based on the InputType and values given.
     * @see InputType
     * @param inputType type of the input
     * @param value value of the input
     */
    public void processInput(InputType inputType, String[] value) {
        WordListItem wordListItem;
        switch(inputType) {
            case LIST:
                ui.display(wordList);
                break;
            case MARK:
                wordList.markItem(Integer.parseInt(value[0]));
                break;
            case UNMARK:
                wordList.unmarkItem(Integer.parseInt(value[0]));
                break;
            case FIND:
                WordListItem[] wordListItems = wordList.findItems(value[0]);
                ui.displayFoundItem(wordListItems, value[0]);
                break;
            case TODO:
                wordListItem = wordList.storeTodo(value[0], false);
                ui.echoAddedItem(wordListItem, wordList);
                break;
            case DEADLINE:
                wordListItem = wordList.storeDeadline(value[0], DateTimeManager.parseString(value[1]), false);
                ui.echoAddedItem(wordListItem, wordList);
                break;
            case EVENT:
                wordListItem = wordList.storeEvent(value[0], DateTimeManager.parseString(value[1]), false);
                ui.echoAddedItem(wordListItem, wordList);
                break;
            case BYE:
                break;
            case DELETE:
                wordListItem = wordList.removeItem(Integer.parseInt(value[0]));
                ui.echoRemovedItem(wordListItem, wordList);
            case NONE:
                break;
        }
    }

    private static void checkEmpty(String input) throws EmptyInputException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty!");
        }
    }
}
