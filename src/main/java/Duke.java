import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


    //constructor
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    //private Ui ui;
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage.load());
    }

    public void run(){
        ui.greet();
        String input = null;
        while(true) {
            try {
                input = ui.nextLine().trim();
                if(Parser.isExit(input)) {
                    ui.sayBye();
                    return;
                }
                String message = Parser.parseInputLine(input, taskList);
                ui.print(message);
                storage.save(taskList.getTaskList());

            }
            catch (DukeException e) {
                System.out.println(e.toString());
            }
        }

    }

    public static void main(String[] args){
        new Duke("data/tasks.txt").run();
    }
}
