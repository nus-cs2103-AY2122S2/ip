package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parses through the user input to determine what command the user intends to call and invoke the command.
 */
public class Parser {
    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find"),
        BYE("bye");


        final String command;

        Commands(String command) {
            this.command = command;
        }
    }


    /**
     * Parses through the user input to process the command called. Starts the main functionalities of Duke.
     * @param input
     * @return a {@code String} informing user of process
     * @throws DukeException
     * @throws IOException
     */
    protected static String start(String input) throws DukeException, IOException {
        //to read user input
        String msg = "";

        //prints the list of items when user inputs list
        if (input.equals(Commands.BYE.command)) {
            return Ui.bye();
        }
        else if (input.equals(Commands.LIST.command)) {
            msg = printList(Duke.list);
        }

        //marks a task as done if the user inputs a string in format "mark xx"
        //NOTE: currently, inputting "mark" alone results in a task named "mark" to be added to the list of tasks
        else if (input.length() > 4 && Commands.MARK.command.equals(input.substring(0, 4))) {
            msg =  TaskList.mark(input);
        }

        //unmarks a task as undone if the user inputs a string in format "unmark xx"
        //NOTE: currently, inputting "unmark" alone results in a task named "unmark" to be added to the list of tasks
        else if (input.length() > 6 && Commands.UNMARK.command.equals(input.substring(0, 6))) {
            msg = TaskList.unmark(input);
        }

        //adds a task to the list if user inputs "todo xxx"
        else if (input.length() >= 4 && Commands.TODO.command.equals(input.substring(0, 4))) {
            msg = TaskList.todo(input);
        }

        //schedules an event and stores the location of the event in the list in the format
        //"event xxx /at xxx"
        else if (input.length() >= 5 && Commands.EVENT.command.equals(input.substring(0, 5))) {
            msg = TaskList.event(input);
        }

        //adds a deadline and stores the date/time of the deadline in the list in the format
        //"deadline xxx /by xxx"
        else if (input.length() >= 8 && Commands.DEADLINE.command.equals(input.substring(0, 8))) {
            msg = TaskList.deadline(input);
        }

        //delete a task from the list
        else if (input.length() >= 6 && Commands.DELETE.command.equals(input.substring(0, 6))) {
            msg = TaskList.delete(input);
        }

        //find a word in the tasks
        else if (input.length() >= 4 && Commands.FIND.command.equals(input.substring(0, 4))) {
            msg = TaskList.find(input);
        }


        //unrecognised input, throw exception
        else {
            throw new DukeException(
                    "Sorry I didn't recognise that command, please try again."
            );
        }

        saveFile();

        return msg;
    }

    /**
     * To print the current list of items.
     * @param arraylist
     * @return {@code String} printed list of tasks.
     */
    private static String printList(ArrayList<Task> arraylist) {
            String msg = "Here are the tasks in your list: \n";
            for (int i = 0; i < arraylist.size(); i++) {
                int count = i + 1;
                msg += count + ". " + arraylist.get(i).toString() + "\n";
            }

            return msg;
        }

        
    /**
     * Save file to the hard disk.
     * 
     * @throws IOException
     */
    private static void saveFile() throws IOException {
        String str = ReadFile.readFile(Duke.list);
        WriteFile.writeToFile(str);
    }
}
