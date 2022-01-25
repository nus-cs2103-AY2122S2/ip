import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Ui ui = new Ui();
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static TaskList tasks = new TaskList();

    public Duke() {
        try {
            tasks.setTasks(Storage.readDataFromFile());
        } catch (IOException e) {
            exceptionHandler.handleOtherException(e);
        }
    }
    public static void run() {
        ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = ui.readCommand(sc);
            try {
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                exceptionHandler.handleDukeException(e);
            } catch (Exception e) {
                exceptionHandler.handleOtherException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }







}
