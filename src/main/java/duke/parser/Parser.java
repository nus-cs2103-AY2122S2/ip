package duke.parser;


import duke.exceptions.InvalidTypeException;
import duke.exceptions.MissingNameException;
import duke.exceptions.MissingEventDateException;
import duke.exceptions.MissingDeadlineDateException;
import duke.main.Duke;
import duke.ui.Ui;
import duke.Task;

public class Parser {

    private String command;

    public Parser(String command) {
        this.command = command;
    }


    public static boolean processInput(String userInput) {
        if(userInput.equals("bye")) {
            return false;
        } else if (userInput.equals("list")) {
            String listTask = Task.printArray();
            String output = Ui.createLine()
                    + listTask + "\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("mark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markDone();

            String output = Ui.createLine()
                    + "       I have marked the following task as done! :D \n"
                    + "       " + task + "\n"
                    + Ui.createLine();

            System.out.println(output);
            return true;

        } else if (userInput.split(" ")[0].equals("unmark")) {
            String[] input = userInput.split(" ");
            Task task =  Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.markNotDone();

            String output = Ui.createLine()
                    + "       OK I have marked the following task as not done yet! :D \n"
                    + "       " + task + "\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else if (userInput.split(" ")[0].equals("delete")) {
            String[] input = userInput.split(" ");
            Task task = Task.getTaskList()[Integer.parseInt(input[1]) - 1];
            task.deleteTask(task);

            String output = Ui.createLine()
                    + "       OK I have delete the following task! :D \n"
                    + "       " + task + "\n"
                    + "       " + "Now you have " + Task.getCounter() + " tasks in your list.\n"
                    + Ui.createLine();
            System.out.println(output);

            return true;
        } else {

            try {
                String newTaskMessage = Duke.createTask(userInput);
                System.out.println(newTaskMessage);
            } catch (InvalidTypeException e) {
                String errorMsg = Ui.createLine()
                        + "       Opps, the command \"" + userInput + "\" is not supported :(\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingNameException e ) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include name after command!\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingEventDateException e) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include date after command!\n"
                        + "       Please follow format [event <name>/at <date>]\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            } catch (MissingDeadlineDateException e ) {
                String errorMsg = Ui.createLine()
                        + "       You have entered \"" + userInput + "\".\n"
                        + "       You have to include deadline after command!\n"
                        + "       Follow format [deadline <name>/by <date>]\n"
                        + Ui.createLine();
                System.out.println(errorMsg);
            }

            return true;
        }
    }

}
