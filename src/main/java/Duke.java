import java.io.IOException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner; // Imported Scanner class

public class Duke {

    private Commands cmd;
    private DukeUi ui;
    private DukeStorage storage;
    private DukeHistory history;

    public Duke(String filePath) {
        cmd = new Commands();
        ui = new DukeUi();
        storage = new DukeStorage();
        history = new DukeHistory();

        String[] arr = filePath.split("\\\\");
        try {
            storage.startup(arr[0], arr[1]);
            storage.restore(history);
        } catch (IOException ex) {
            ui.printLoadError();
        }
    }

    public void run() {
        ui.printGreeting();
        Scanner io = new Scanner(System.in); // Scanner object created
        label:
        while (true) {
            String input = io.nextLine();
            String[] tokens = input.split("\\s");
            String keyword = tokens[0];
            switch (keyword) {
            case "bye":
                try {
                    if (tokens.length != 1) {
                        throw new DukeException("argument for bye detected");
                    }
                    cmd.bye();
                    storage.update(history);
                    break label;
                } catch (DukeException ex) {
                    ui.printFoundArgumentError();
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            case "list":
                try {
                    if (tokens.length != 1) {
                        throw new DukeException("argument for list detected");
                    }
                    cmd.list(history);
                    break;
                } catch (DukeException ex) {
                    ui.printFoundArgumentError();
                    break;
                }
            case "mark": {
                try {
                    int index = Integer.parseInt(tokens[1]);
                    cmd.mark(index - 1, history);
                    storage.update(history);
                    break;
                } catch (IndexOutOfBoundsException ex) {
                    ui.printInvalidArgumentError();
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "unmark": {
                try {
                    int index = Integer.parseInt(tokens[1]);
                    cmd.unmark(index - 1, history);
                    storage.update(history);
                    break;
                } catch (IndexOutOfBoundsException ex) {
                    ui.printInvalidArgumentError();
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "todo": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.todo(tokens, history);
                    storage.update(history);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    ui.printMissingArgumentError(keyword);
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "deadline": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.deadline(tokens, history);
                    storage.update(history);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    ui.printMissingArgumentError(keyword);
                    break;
                } catch (DukeException ex) {
                    ui.printMissingDateTimeArgumentError(keyword);
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "event": {
                try {
                    if (tokens.length <= 1) {
                        throw new MissingFormatArgumentException("no argument detected");
                    }
                    cmd.event(tokens, history);
                    storage.update(history);
                    break;
                } catch (MissingFormatArgumentException ex) {
                    ui.printMissingArgumentError(keyword);
                    break;
                } catch (DukeException ex) {
                    ui.printMissingDateTimeArgumentError(keyword);
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "delete": {
                try {
                    int index = Integer.parseInt(tokens[1]);
                    cmd.delete(index - 1, history);
                    storage.update(history);
                    break;
                } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
                    ui.printInvalidArgumentError();
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            case "update": {
                try {
                    storage.update(history);
                    break;
                } catch (IOException e) {
                    ui.printWriteError();
                }
            }
            default:
                try {
                    throw new MissingFormatArgumentException("invalid keywords");
                } catch (MissingFormatArgumentException ex) {
                    ui.printListOfCommands();
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}
