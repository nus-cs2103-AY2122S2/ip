import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Karen {
    ArrayList<Task> toDos;

    public Karen() {
        this.toDos = new ArrayList<Task>();
    }

    public void echo(String statement) {
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(statement);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public void addTask(Task item) {
        this.toDos.add(item);
    }
    public Task getTask(int index) {
        return this.toDos.get(index);
    }
    public void deleteTask(int index) {this.toDos.remove(index);}

    /**
     *
     * @param item Task object that has action applied to
     * @param action that is applied to Task object (etc. delete, added)
     * @return formatted string
     */
    public String formatTask(Task item, String action) {
        return String.format("Fine. Task %s:\n %s\nNow you have %d in total.",
                action, item.toString(), this.toDos.size());
    }

    /**
     *
     * @param command user input from the command line
     * @return boolean flag if correct input; echoes out error message and returns false if wrong
     */
    public Command validateCommand(String command){
        // extract first word
        String keyWord = command.contains(" ") ? command.split(" ")[0] : command;

        Command commandType;
        switch (keyWord) {
            case "list":
                commandType = Command.LIST;
                break;
            case "bye":
                commandType = Command.BYE;
                break;
            case "todo":
                commandType = Command.TODO;
                break;
            case "deadline":
                commandType = Command.DEADLINE;
                break;
            case "event":
                commandType = Command.EVENT;
                break;
            case "mark":
                commandType = Command.MARK;
                break;
            case "unmark":
                commandType = Command.UNMARK;
                break;
            case "delete":
                commandType = Command.DELETE;
                break;
            default:
                commandType = Command.NA;
                break;
        };
        return commandType;
    }

    public String executeCommand(String command, Command commandType) {
        String output = "";
        if (commandType==Command.NA) {
            return "";
        }
        else if (commandType==Command.BYE) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
        }
        else if (commandType==Command.LIST) {
            if (this.toDos.size()==0)
            {
                output = "Nothing is even added yet.";
            } else
            {
                output = "";
                int counter = 0;
                for (Task item: this.toDos) {
                    output = output.concat(String.format("%d.%s\n", counter+1, item.toString()));
                    counter++;
                }
            }
        }
        else if (commandType==Command.DELETE) {
            int index = Integer.valueOf(command.split(" ")[1]);
            try {
                Task item = this.getTask(index-1);
                this.deleteTask(index-1);
                output = this.formatTask(item, "removed");
            } catch (IndexOutOfBoundsException err) {
                output = String.format("Are you sure that [%d] is even in the 'list' command?", index);
            }
        }
        else if (commandType==Command.MARK || commandType==Command.UNMARK){
            int index = Integer.valueOf(command.split(" ")[1]);
            try {
                Task getTask = this.getTask(index-1);
                if (commandType==Command.MARK) {
                    getTask.markDone();
                    output = String.format("This task is finally done:\n  %s",getTask.toString());
                } else if (commandType==Command.UNMARK) {
                    getTask.markUndone();
                    output = String.format("This task is now incomplete - unacceptable:\n  %s",getTask.toString());
                }
            } catch (IndexOutOfBoundsException err){
                output = String.format("Are you sure that [%d] is even in the 'list' command?", index);
            }
        }
        else if (commandType==Command.TODO) {
            String item_descriptor = command.split(" ", 2)[1];
            ToDo item = new ToDo(item_descriptor);
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        else if (commandType==Command.DEADLINE) {
            Pattern p = Pattern.compile("deadline (.*) \\/by (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Deadline item = new Deadline(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        else if (commandType==Command.EVENT) {
            Pattern p = Pattern.compile("event (.*) \\/at (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Event item = new Event(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        return output;
    }

    /**
     * To execute commands based on input
     * @param inputCommand user input from the command line
     */
    public void processCommand(String inputCommand) {
        Command getCommandType = this.validateCommand(inputCommand);
        String result = "";
        try {
            boolean flag = getCommandType.isValid(inputCommand);
            if (flag) {
                result = this.executeCommand(inputCommand, getCommandType);
            }
        } catch (KarenException err) {
            result = err.toString();
        } finally {
            this.echo(result);
            if (getCommandType==Command.BYE) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karen manager = new Karen();

        manager.echo("Hello, my name is Karen.\nI'll be speaking (to) as your manager today.");
        while (true) {
            // take in input as commands
            String cmd = scanner.nextLine();
            manager.processCommand(cmd);
        }
    }
}
