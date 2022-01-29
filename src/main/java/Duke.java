import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) { //abstract to DukeException
            //ui.showloadingerror
            System.out.println("Error loading file");
            this.tasks = new TaskList();
        }
    }

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



    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputWords = input.split("\\s");
                switch (input) {
                    case "bye":
                        ui.farewell();
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
                                        + ui.getCommands()); //can show help commands
                        }
                        storage.save(tasks);    //everytime TaskList is edited, save tasks to file. (writeToFile)
                }
            } catch (UnknownException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        ui.farewell();
    }
}
