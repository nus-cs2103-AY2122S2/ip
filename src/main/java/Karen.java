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
    public boolean validateCommand(String command){
        boolean flag = false;
        try {
            flag = this.validateHelper(command);
        } catch (KarenException err) {
            this.echo(err.toString());
        }
        return flag;
    }

    /**
     * Helper function for validateCommand to store cases to validate user input
     * @param command user input from the command line
     * @return boolean flag if success
     * @throws KarenException if string input doesn't match what is needed
     */
    protected boolean validateHelper(String command) throws KarenException {
        if (command.equals("list") | command.equals("bye")) {
            return true;
        }
        else if (command.matches("((un)?mark|delete).*")) {
            if (command.matches("((un)?mark|delete) \\d+")) {
                return true;
            } else if (command.matches("^(un)?mark.*")) {
                throw new KarenException("You can only mark/unmark with an integer index");
            } else if (command.matches("^delete.*")) {
                throw new KarenException("You can only delete with an integer index");
            }

        }
        else if (command.matches("todo.*")) {
            if (command.matches("todo (.*)")) {
                return true;
            } else {
                throw new KarenException("I can't understand what todo you want to add.");
            }
        }
        else if (command.matches("deadline.*")) {
            if (command.matches("deadline (.*) \\/by (.*)")) {
                return true;
            }
            else if (command.matches("^((?!\\/by).)*$")) {
                throw new KarenException("You're missing an /by flag needed to add an deadline");
            }
            else {
                throw new KarenException("I can't understand what deadline you want to add.");
            }
        }
        else if (command.matches("event.*")) {
            if (command.matches("event (.*) \\/at (.*)")) {
                return true;
            }
            else if (command.matches("^((?!\\/at).)*$")) {
                throw new KarenException("You're missing an /at flag needed to add an event");
            }
            else {
                throw new KarenException("I can't understand what event you want to add.");
            }
        }
        throw new KarenException("I don't understand anything - I want to speak with your manager");
    }

    /**
     * To execute commands based on input
     * @param command user input from the command line
     */
    public void processCommand(String command) {
        String output = command;
        if (!validateCommand(command)) {
            return;
        }

        // commands that don't depend on user input/extra params
        if (command.equals("bye")) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
            this.echo(output);
            System.exit(0);
        }
        else if (command.equals("list")) {
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
        //  commands that require params
        else if (command.matches("(un)?mark .*")) {
            int index = Integer.valueOf(command.split(" ")[1]);
            try {
                Task getTask = this.getTask(index-1);
                if (command.startsWith("mark")) {
                    getTask.markDone();
                    output = String.format("This task is finally done:\n  %s",getTask.toString());
                } else if (command.startsWith("unmark")) {
                    getTask.markUndone();
                    output = String.format("This task is now incomplete - unacceptable:\n  %s",getTask.toString());
                }
            } catch (IndexOutOfBoundsException err){
                output = String.format("Are you sure that [%d] is even in the 'list' command?", index);
            }
        }
        else if (command.matches("^delete .*")) {
            int index = Integer.valueOf(command.split(" ")[1]);
            try {
                Task item = this.getTask(index-1);
                this.deleteTask(index-1);
                output = this.formatTask(item, "removed");
            } catch (IndexOutOfBoundsException err) {
                output = String.format("Are you sure that [%d] is even in the 'list' command?", index);
            }
        }
        else if (command.matches("todo (.*)"))  {
            String item_descriptor = command.split(" ", 2)[1];
            ToDo item = new ToDo(item_descriptor);
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        else if (command.matches("deadline (.*) \\/by (.*)")) {
            Pattern p = Pattern.compile("deadline (.*) \\/by (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Deadline item = new Deadline(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        else if (command.matches("event (.*) \\/at (.*)")) {
            Pattern p = Pattern.compile("event (.*) \\/at (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Event item = new Event(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatTask(item, "added");
        }
        this.echo(output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Karen manager = new Karen();

        // greet
        manager.echo("Hello, my name is Karen.\nI'll be speaking (to) as your manager today.");
        while (true) {
            // take in input as commands
            String cmd = scanner.nextLine();
            manager.processCommand(cmd);
        }
    }
}
