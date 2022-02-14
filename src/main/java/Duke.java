
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

public class Duke {
    private WordList wordList;
    private JSONFileManager jsonFileManager;
    private DukeUI ui;

    public Duke() {
        this.ui = new DukeUI(new Scanner(System.in));
        this.jsonFileManager = new JSONFileManager();
        this.jsonFileManager.setUpSaveSystem();
        this.wordList = this.jsonFileManager.loadListFromJSONFile();
    }

    public Duke(String storagePath, String storageFileName) {
        this.ui = new DukeUI(new Scanner(System.in));
        this.jsonFileManager = new JSONFileManager(storagePath, storageFileName);
        this.jsonFileManager.setUpSaveSystem();
        this.wordList = this.jsonFileManager.loadListFromJSONFile();
    }

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

    public static void main(String[] args) {
        Duke dukeApp = new Duke("src/data/", "anotherTasks.json");
        dukeApp.run();
    }

    public void processInput(InputType inputType, String[] value) {
        WordListItem wordListItem;
        switch(inputType) {
            case LIST:
                wordList.printList();
                break;
            case MARK:
                wordList.markItem(Integer.parseInt(value[0]));
                break;
            case UNMARK:
                wordList.unmarkItem(Integer.parseInt(value[0]));
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

    public static void checkEmpty(String input) throws EmptyInputException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty!");
        }
    }
}
