import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import task.TaskList;
import util.Storage;
import util.Ui;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    private boolean isNewMessage = true;


    /**
     * initialise the util classes of Duke
     */


    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        File newFile = new File("data/duke.txt");
        tasks = new TaskList();

        try {
            if (newFile.exists()) {
                storage.loadFile(tasks.getTaskList());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please create the text file data/duke.txt");
        }


    }


    /**
     * Runs the program
     */

    public void run() {
        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();

        while (userInput.equals("bye")) {
            ui.processInput(userInput, tasks);
            userInput = sc.nextLine();
        }

        ui.exit();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     *
     * @param input command for Duke
     * @return response based on the input string
     */

    public String getResponse(String input) {

        if (isNewMessage) {
            isNewMessage = false;
            return ui.greet();
        } else if (input.equals("bye")) {
            try {
                storage.writeToFile(tasks.getTaskList());
                return ui.exit();
            } catch (IOException e) {
                return "IOException detected";
            }
        } else {
            return ui.processInput(input, tasks);
        }
    }






}




