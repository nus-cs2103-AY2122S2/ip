import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Karen {
    Task[] toDos;
    int runningIndex;
    int tasksLength;

    public Karen() {
        this.toDos = new Task[100];
        this.runningIndex = 0;
        this.tasksLength = 0;
    }

    public void echo(String statement) {
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println(statement);
        System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\n");
    }

    public void addTask(Task item) {
        this.toDos[this.runningIndex] = item;
        this.runningIndex++;
        this.tasksLength++;
    }

    public String formatAddTask(Task item) {
        return String.format("Fine. Task added:\n %s\nNow you have %d in total.",
                item.toString(), this.tasksLength);
    }

    public boolean validateCommand(String command){
        boolean flag = false;
        try {
            flag = this.validateHelper(command);
        } catch (KarenException err) {
            this.echo(err.toString());
        }
        return flag;
    }

    protected boolean validateHelper(String command) throws KarenException {
        System.out.println(command);
        if (command.equals("list") | command.equals("bye")) {
            return true;
        }
        else if (command.matches("(un)?mark.*")) {
            if (command.matches("(un)?mark \\d+")) {
                return true;
            }
            throw new KarenException("You can only mark/unmark with an integer index");
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
            if (this.tasksLength==0)
            {
                output = "Nothing is even added yet.";
            } else
            {
                int counter = 0;
                output = "";
                for (Task item: this.toDos) {
                    output = output.concat(String.format("%d.%s\n", counter+1, item.toString()));
                    counter++;
                    if (counter >= this.runningIndex) {
                        break;
                    }
                }
            }

        }
        //  commands that require params
        else if (command.matches("(un)?mark .*")) {
            int index = Integer.valueOf(command.split(" ")[1]);
            Task getTask = this.toDos[index-1];
            if (command.startsWith("mark")) {
                getTask.markDone();
                output = String.format("This task is finally done:\n  %s",getTask.toString());
            } else if (command.startsWith("unmark")) {
                getTask.markUndone();
                output = String.format("This task is now incomplete - unacceptable:\n  %s",getTask.toString());
            }
        }
        else if (command.matches("todo (.*)"))  {
            String item_descriptor = command.split(" ", 2)[1];
            ToDo item = new ToDo(item_descriptor);
            this.addTask(item);
            output = this.formatAddTask(item);
        }
        else if (command.matches("deadline (.*) \\/by (.*)")) {
            Pattern p = Pattern.compile("deadline (.*) \\/by (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Deadline item = new Deadline(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatAddTask(item);
        }
        else if (command.matches("event (.*) \\/at (.*)")) {
            Pattern p = Pattern.compile("event (.*) \\/at (.*)");
            Matcher m = p.matcher(command);
            m.find();
            Event item = new Event(m.group(1), m.group(2));
            this.addTask(item);
            output = this.formatAddTask(item);
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
