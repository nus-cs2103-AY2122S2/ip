package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Ui is a class that the user interacts with on the CLI
 */
public class Ui {

    TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        HELP("help");

        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    /**
     * This method will handle user input from the CLI, it has multiple commands such as
     * list, mark, unmark, todo, deadline, event, delete, bye
     */
    String run(String cmd) {

        Parser parser = new Parser(tasks);
        String response;

        if (cmd.startsWith(Ui.Commands.LIST.command)) {
            response = parser.listTasks();

        } else if (cmd.startsWith(Ui.Commands.MARK.command)) {
            try {
                int taskNumber = Integer.parseInt(cmd.split(" ")[1]);
                response = parser.mark(taskNumber);
            } catch (Exception e) {
                return "The format should be \"mark [insert task number]\"\n" +
                        "For example, \"mark 1\" to mark the first task";
            }


        } else if (cmd.startsWith(Ui.Commands.UNMARK.command)) {
            try {
                int taskNumber = Integer.parseInt(cmd.split(" ")[1]);
                response = parser.unmark(taskNumber);
            } catch (Exception e) {
                return "The format should be \"unmark [insert task number]\"\n" +
                        "For example, \"unmark 1\" to unmark the first task";
            }

        } else if (cmd.startsWith(Ui.Commands.TODO.command)) {
            try {
                String description = cmd.split(" ")[1];
                ToDo todo = new ToDo(description);
                response = parser.addToDo(todo);
            } catch (Exception e) {
                return "The format should be \"todo return book\"";
            }


        } else if (cmd.startsWith(Ui.Commands.DEADLINE.command)) {
            try {
                String info = cmd.split(" ", 2)[1];
                String[] split = info.split(" /by ");
                String description = split[0];
                String time = split[1];
                Deadline deadline = new Deadline(description, time);
                response = parser.addDeadline(deadline);
            } catch (Exception e) {
                return "The format should be \"deadline return book /by yyyy-mm-dd\"";
            }

        } else if (cmd.startsWith(Ui.Commands.EVENT.command)) {
            try {
                String info = cmd.split(" ", 2)[1];
                String[] split = info.split(" /at ");
                String description = split[0];
                String time = split[1];
                Event event = new Event(description, time);
                response = parser.addEvent(event);
            } catch (Exception e) {
                return "The format should be \"deadline attend lesson /at yyyy-mm-dd\"";
            }

        } else if (cmd.startsWith(Ui.Commands.DELETE.command)) {
            try {
                int taskNumber = Integer.parseInt(cmd.split("")[1]);
                response = parser.deleteTask(taskNumber);
            } catch (Exception e) {
                return "The format should be \"delete [insert task number]\"\n" +
                        "For example, \"delete 1\" to delete the first task";
            }

        } else if (cmd.startsWith(Ui.Commands.FIND.command)) {
            try {
                String keyword = cmd.split(" ")[1];
                response = parser.findTask(keyword);
            } catch (Exception e) {
                return "The format should be \"borrow book\"";
            }

        } else if (cmd.startsWith(Ui.Commands.HELP.command)) {
            String help = "";
            help += "[todo] | example: todo return book\n";
            help += "[deadline] | example: deadline return book /by yyyy-mm-dd\n";
            help += "[event] | example: event attend lecture /at yyyy-mm-dd\n";
            help += "[list] | no other command needed\n";
            help += "[mark] | example: mark 1\n";
            help += "[unmark] | example: unmark 1\n";
            help += "[delete] | example: delete 1\n";
            help += "[find] | example: find book\n";
            return help;

        } else {
            response = "OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(";
        }
        try {
            Storage.saveToFile(tasks);
        } catch (Exception e) {
            return "I'm sorry, I could not save that :(";
        }

//        System.out.println("Bye. Hope to see you again soon!");

        return response;
    }


}
