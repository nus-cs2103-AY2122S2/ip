package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        BYE("bye");


        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    protected static void start() throws DukeException, IOException {
        //to read user input
        Scanner scanned = new Scanner(System.in);
        System.out.println("");
        String input = scanned.nextLine();

        while (!input.equals(Commands.BYE.command)) {
            System.out.println("=======================================================================");

            //prints the list of items when user inputs list
            if (input.equals(Commands.LIST.command)) {
                printList(Duke.list);
            }

            //marks a task as done if the user inputs a string in format "mark xx"
            //NOTE: currently, inputting "mark" alone results in a task named "mark" to be added to the list of tasks
            else if (input.length() > 4 && Commands.MARK.command.equals(input.substring(0, 4))) {
                TaskList.mark(input);
            }

            //unmarks a task as undone if the user inputs a string in format "unmark xx"
            //NOTE: currently, inputting "unmark" alone results in a task named "unmark" to be added to the list of tasks
            else if (input.length() > 6 && Commands.UNMARK.command.equals(input.substring(0, 6))) {
                TaskList.unmark(input);
            }

            //adds a task to the list if user inputs "todo xxx"
            else if (input.length() >= 4 && Commands.TODO.command.equals(input.substring(0, 4))) {
                TaskList.todo(input);
            }

            //schedules an event and stores the location of the event in the list in the format
            //"event xxx /at xxx"
            else if (input.length() >= 5 && Commands.EVENT.command.equals(input.substring(0, 5))) {
                TaskList.event(input);
            }

            //adds a deadline and stores the date/time of the deadline in the list in the format
            //"deadline xxx /by xxx"
            else if (input.length() >= 8 && Commands.DEADLINE.command.equals(input.substring(0, 8))) {
                TaskList.deadline(input);
            }

            //delete a task from the list
            else if (input.length() >= 6 && Commands.DELETE.command.equals(input.substring(0, 6))) {
                TaskList.delete(input);
            }


            //unrecognised input, throw exception
            else {
                throw new DukeException(
                        "Sorry I didn't recognise that command, please try again."
                );
            }

            saveFile();

            //to continue reading user input
            scanned = new Scanner(System.in);
            System.out.println("");
            input = scanned.nextLine();
        }

        Ui.bye();
    }
    
        //to print the current list of items
        private static void printList(ArrayList<Task> arraylist) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < arraylist.size(); i++) {
                int count = i + 1;
                System.out.println(count + ". " + arraylist.get(i).toString());
            }
        }

        
    //save file to the hard disk
    private static void saveFile() throws IOException {
        String str = ReadFile.readFile(Duke.list);
        WriteFile.writeToFile(str);
    }
}
