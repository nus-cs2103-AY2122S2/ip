import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private Storage storage;
    private TaskList tasks;

//    public Duke() {
//        this.storage = new Storage();
//        try {
//            this.tasks = storage.load();
//        } catch (IOException e) {
//            System.out.println("Error loading file");
//        }
//    }
//    private Ui ui;
//    public Duke(String filepath) {
//        ui = new Ui();
//        storage = new Storage(filepath);
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (Duke Exception e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }
//    public void run() {
//        ui.Greet();
//        boolean isExit = false;
//        while(!isExit) {
//            try {
//                  String fullCommand = ui.readCommand();
//                  ui.showLine();    // show the divider line ("__________")
//                  Command c = Parser.parse(fullCommand);
//                    c.execute(tasks, ui, storage);
//                    isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }

    private static final String BOT_NAME = "Feline";

    public static void greet() {
        System.out.println(String.format("Yoooo! My name is %s!\nHow can i help you bro?\n", BOT_NAME));
    }
    public static void farewell() {
        System.out.println("See you next time!");
    }


    private static String getCommands() {
        return "list, todo, deadline (using /by), event (using /at), mark, unmark, delete";
    }

    public static void main(String[] args) {
//        Duke bot = new Duke();
        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading file");
        }
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputWords = input.split("\\s");
                switch (input) {
                    case "bye":
                        Duke.farewell();
                        return;
                    case "list":
                        tasks.printList();
                        break;
                    default: // not the one word commands that we have. check further commands, else throw exception
                        String firstWord = inputWords[0];
                        switch (firstWord) {
                            case "mark":
                                tasks.mark(inputWords);
                                break;
                            case "unmark":
                                tasks.unmark(inputWords);
                                break;
                            case "delete":
                                tasks.delete(inputWords);
                                break;
                            case "todo":
                            case "deadline":
                            case "event":
                                tasks.addTask(firstWord, input);
                                break;
                            default:
                                throw new UnknownException("Unknown command! the follow are the commands: "
                                        + Duke.getCommands()); //can show help commands
                        }
                        storage.save(tasks);    //everytime TaskList is edited, save tasks to file. (writeToFile)
                }
            } catch (UnknownException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        Duke.farewell();
    }
}
