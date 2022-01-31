package duke;

import duke.exception.DukeCommandDoesNotExistException;
import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.UserInput;
import java.io.IOException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.Scanner;

/**
 * Duke is a task tracker interactive chatbot.
 *
 * @author Chen Yu An
 * @version v0.1
 */
public class Duke {
    private final Scanner scanner = new Scanner(System.in);
    private boolean endProgram = false; // state to terminate the program

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();

        try {
            this.storage = new Storage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke().startProgram();
    }

    private void startProgram() {
        storage.makeFile();
        storage.loadFile(taskList);
        ui.welcomeMessage();

        while (!endProgram) {
            try {
                String input = scanner.nextLine(); // user input

                // handle user input to command, description and time of task if applicable
                UserInput userInput = parser.parseInput(input);
                String command = userInput.getCommand();

                // exit program when user input "bye"
                if (command.equals("bye")) {
                    ui.endProgram();
                    endProgram = true;
                    break;
                }

                // list out the tasks
                if (command.equals("list")) {
                    taskList.listTask(userInput);
                    continue;
                }

                // mark certain task as done
                if (command.startsWith("mark")) {
                    taskList.markDone(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // mark certain task as not done yet
                if (command.startsWith("unmark")) {
                    taskList.markUndone(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // delete task
                if (command.startsWith("delete")) {
                    taskList.deleteTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create ToDoTask
                if (command.equals("todo")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create DeadlineTask
                if (command.equals("deadline")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create EventTask
                if (command.equals("event")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // find from list
                if (command.equals("find")) {
                    taskList.findTask(userInput);
                    continue;
                }

                // Invalid command inputs result
                throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");

            } catch (DukeException e) {
                ui.errorMessage(e);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        // close the scanner
        scanner.close();
    }
}
