package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

/**
 *
 * A bot that helps you keep track of tasks like Todos, Deadlines, and Events.
 * @author Jian Rong
 *
 */
public class Duke {
    public TaskList taskList = new TaskList();
    public Storage storage = new Storage("data/duke.txt", taskList);


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert taskList != null;
        String result = Parser.parseInput(input, this.taskList);
        storage.write();
        return result;
    }


//    /**
//     * This is the main method of Duke, which calls the listen method.
//     * @param args Unused
//     * @return void
//     */
//    public static void main(String[] args) {
//        taskList = new TaskList();
//        storage =  new Storage("data/duke.txt", taskList);
//        listen();
//    }
//
//    private static void listen() {
//        Ui.printLogo();
//        boolean isRunning = true;
//        while (isRunning) {
//            isRunning = Ui.run(taskList);
//            storage.write();
//        }
//        System.out.println("__________________________________");
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println("__________________________________");
//    }

}
