package duke;

import java.io.IOException;

import java.util.Scanner;

/**
 * This class deals with interactions from the user.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Ui {

    public static void startMessage() {
        System.out.println("IF YOU ARE NEW TO THIS PROGRAM, ENTER ? TO SEE A LIST OF AVAILABLE COMMANDS.");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("HELLO! I'M DUKE");
        System.out.println("WHAT CAN I DO FOR YOU?");
    }

    /**
     *  Prints out a list of available commands in this program.
     */
    public static void getHelp() {
        System.out.println("LIST OF AVAILABLE COMMANDS IN THIS PROGRAM      DESCRIPTION");
        System.out.println("list                                            List out all added tasks");
        System.out.println("todo {task description}                         Add ToDo into list");
        System.out.println("deadline {task description} /by {DATE}          Add Deadline into list");
        System.out.println("event {task description} /at {DATE}             Add Event into list");
        System.out.println("mark {Task ID}                                  Mark specific task as done");
        System.out.println("unmark {Task ID}                                Mark specific task as not done");
        System.out.println("delete {Task ID}                                Delete specific task from list");
        System.out.println("bye                                             End the Duke program");
    }

    public static void allowUserInput(TaskList list) throws DukeException, IOException {
        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] temp = input.split(" ");
            Parser parse = new Parser(input);
            if (parse.getCmd().equals("?")) {
                getHelp();
            } else if (parse.getCmd().equals("mark")) {
                int index = Integer.parseInt(parse.getDesc()) - 1;
                list.markTask(index);
            } else if (parse.getCmd().equals("unmark")) {
                int index = Integer.parseInt(parse.getDesc()) - 1;
                list.unMarkTask(index);
            } else if (parse.getCmd().equals("list")) {
                list.listTask();
            } else if (parse.getCmd().equals("todo")) {
                list.addToDo(parse.getDesc(), false);
            } else if (parse.getCmd().equals("deadline")) {
                list.addDeadline(parse.getDesc(), parse.getDate(), false);
            } else if (parse.getCmd().equals("event")) {
                list.addEvent(parse.getDesc(), parse.getDate(), false);
            } else if (parse.getCmd().equals("delete")) {
                int index = Integer.parseInt(parse.getDesc()) - 1;
                list.deleteTask(index);
            } else {
                notSpecified();
            }
            input = scanner.nextLine();
        }
        // Close program and write back to file
        Storage.writeFile(list);
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method handles the case where a random input is supplied by the user
     *
     * @throws DukeException if add type description is empty
     */
    public static void notSpecified() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
