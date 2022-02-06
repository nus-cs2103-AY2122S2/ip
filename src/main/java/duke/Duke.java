package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
    }

    public void run() {
        ui.showWelcomeMessage();
        try {
            Scanner sc = new Scanner(storage.load());
            String input = sc.nextLine();
            Parser parser = new Parser(taskList);
            
            while (sc.hasNextLine()) {
                parser.execute(input);
                if (input.toLowerCase().equals("bye")) {
                    break;
                }
                input = sc.nextLine();
            }
        
        } catch (IOException e) {
            System.out.println("File is not found :(!");
        }

        storage.writeFile();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/data/tasks.txt");
        duke.run();
    }
    
}


