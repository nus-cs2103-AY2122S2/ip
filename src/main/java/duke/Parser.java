package duke;

import java.io.IOException;
import java.util.*;

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
        String msg = "";
        ArrayList<String> massOpsArray = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (input.equals(Commands.BYE.command)) {
            return Ui.bye();
        } else if (input.equals(Commands.LIST.command)) {
            msg = printList(Duke.list);
        } else if (input.length() > 4 && Commands.MARK.command.equals(input.substring(0, 4))) {
            msg = massOp(massOpsArray);
        } else if (input.length() > 6 && Commands.UNMARK.command.equals(input.substring(0, 6))) {
            msg = massOp(massOpsArray);
        } else if (input.length() >= 4 && Commands.TODO.command.equals(input.substring(0, 4))) {
            msg = TaskList.todo(input);
        } else if (input.length() >= 5 && Commands.EVENT.command.equals(input.substring(0, 5))) {
            msg = TaskList.event(input);
        } else if (input.length() >= 8 && Commands.DEADLINE.command.equals(input.substring(0, 8))) {
            msg = TaskList.deadline(input);
        } else if (input.length() >= 6 && Commands.DELETE.command.equals(input.substring(0, 6))) {
            msg = massOp(massOpsArray);
        } else if (input.length() >= 4 && Commands.FIND.command.equals(input.substring(0, 4))) {
            msg = massOp(massOpsArray);
        } else {
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

    private static String massOp(ArrayList<String> massOpsArray) throws DukeException {
        String command = massOpsArray.get(0);
        massOpsArray.remove(0);
        Collections.sort(massOpsArray);
        Collections.reverse(massOpsArray);

        String msg = "";
        if (Commands.MARK.command.equals(command)) {
            for (int i = 0; i < massOpsArray.size(); i++) {
                msg += TaskList.mark("mark " + massOpsArray.get(i)) + "\n";
            }
        } else if (Commands.UNMARK.command.equals(command)) {
            for (int i = 0; i < massOpsArray.size(); i++) {
                msg += TaskList.unmark("unmark " + massOpsArray.get(i)) + "\n";
            }
        } else if (Commands.DELETE.command.equals(command)) {
            for (int i = 0; i < massOpsArray.size(); i++) {
                msg += TaskList.delete("delete " + massOpsArray.get(i)) + "\n";
            }
        } else if (Commands.FIND.command.equals(command)) {
            for (int i = 0; i < massOpsArray.size(); i++) {
                msg += TaskList.find("find " + massOpsArray.get(i)) + "\n";
            }
        } else {}

        return msg;
    }


}
