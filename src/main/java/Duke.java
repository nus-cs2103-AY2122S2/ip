
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                wordList.storeTodo(value[0], false, true);
                break;
            case DEADLINE:
                wordList.storeDeadline(value[0], DateTimeManager.parseString(value[1]), false, true);
                break;
            case EVENT:
                wordList.storeEvent(value[0], DateTimeManager.parseString(value[1]), false, true);
                break;
            case BYE:
                break;
            case DELETE:
                wordList.removeItem(Integer.parseInt(value[0]));
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
