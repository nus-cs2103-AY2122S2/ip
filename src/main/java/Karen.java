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

    public void processCommand(String command) {
        String output = command;
        // commands with extra functionality
        if (command.equals("bye")) {
            output = "Goodbye - I'll be seeing your manager's manager next.";
            this.echo(output);
            System.exit(0);
        }
        else if (command.equals("list")) {
            if (this.tasksLength==0) {
                output = "";
            } else {
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
        else if (command.matches("(un)?mark \\d+")) {
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
        else if (command.matches("todo (.*)"))
        {
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
        } else {
            output = String.format("You gave me nothing I understood. Check again: [%s]", command);
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
