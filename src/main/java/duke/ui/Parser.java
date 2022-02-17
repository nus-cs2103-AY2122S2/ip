package duke.ui;

import duke.commands.Commands;
import duke.tasks.TaskManager;
import java.util.Scanner;

public class Parser {

    static final Scanner sc = new Scanner(System.in);
    static final String greetingMessage = "Hello! I am Morty, Rick's assistant.\nI keep track of your tasks, so you don't have to. What's up?";
    static final String farewellMessage = "Hope you had fun talking to me, and bye!\nHave a nice day ahead!";

    public static String process(String inputMessage) {
        if (inputMessage.trim().equals(Commands.list.toString())) {
            return TaskManager.list();
        } else if (inputMessage.startsWith(Commands.mark.toString())) {
            String returnableResponseOfAction = TaskManager.mark(Integer.parseInt(inputMessage.substring(Commands.mark.toString().length() + 1)));
            TaskManager.save();
            return returnableResponseOfAction;
        } else if (inputMessage.startsWith(Commands.unmark.toString())) {
            String returnableResponseOfAction = TaskManager.unmark(Integer.parseInt(inputMessage.substring(Commands.unmark.toString().length() + 1)));
            TaskManager.save();
            return returnableResponseOfAction;
        } else if (inputMessage.startsWith(Commands.delete.toString())) {
            String returnableResponseOfAction =TaskManager.delete(Integer.parseInt(inputMessage.substring(Commands.delete.toString().length() + 1)));
            TaskManager.save();
            return returnableResponseOfAction;
        } else if (inputMessage.startsWith(Commands.find.toString())) {
            return  TaskManager.find(inputMessage.substring(Commands.find.toString().length() + 1));
        }  else if (inputMessage.startsWith(Commands.findBetter.toString())) {
            return TaskManager.findBetter(inputMessage.substring(Commands.findBetter.toString().length() + 1));
        } else if (inputMessage.equals(Commands.bye.toString())) {
            return farewellMessage;
        } else {
            try {
                String returnableResponseOfAction = TaskManager.generateAndAdd(inputMessage);
                TaskManager.save();
                return  returnableResponseOfAction;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }
}
