import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Commands.HI.toString());
        ArrayList<Task> tasks = new ArrayList<Task>();
        //Task[] tasks = new Task[100];
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String comm = sc.nextLine();
            String[] processedCommand = comm.split(" ", 2);
            Integer taskIndex = null;
            switch (processedCommand[0]) {
            case "hi":
                System.out.println(Commands.HI.toString());
                break;
            case "bye":
                System.out.println(Commands.BYE.toString());
                isExit = true;
                break;
            case "list":
                System.out.println(Commands.LIST.toString());
                for (int i = 0; i < tasks.size(); i++) {
                    int indexToPrint = i + 1;
                    System.out.println(String.format(
                            "    %d.%s", indexToPrint, tasks.get(i).identify()));
                }
                break;
            case "mark":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                //Mark task
                tasks.get(taskIndex).setIsDone(true);
                System.out.println(String.format(
                    "%s    %s", Commands.MARK.toString(), tasks.get(taskIndex).identify()));
                break;
            case "unmark":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                //Unmark task
                tasks.get(taskIndex).setIsDone(false);
                System.out.println(String.format(
                    "%s      %s", Commands.UNMARK.toString(), tasks.get(taskIndex).identify()));
                break;
            case "deadline":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                } else if (!processedCommand[1].contains("/") || processedCommand[1].startsWith(("/"))) {
                    new DukeException();
                    break;
                }

                //Processing input
                String[] inputForDeadlineConstructor = processedCommand[1].split("/", 2);
                inputForDeadlineConstructor[0] = inputForDeadlineConstructor[0].subSequence(0,
                    inputForDeadlineConstructor[0].length() - 1).toString();

                //Create deadline
                Deadline deadline = new Deadline(inputForDeadlineConstructor[0], inputForDeadlineConstructor[1]);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), deadline.identify()));
                tasks.add(deadline);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                break;
            case "todo":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }

                //Create Todo
                ToDo todo = new ToDo(processedCommand[1]);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), todo.identify()));
                tasks.add(todo);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                break;
            case "event":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                } else if (!processedCommand[1].contains("/") || processedCommand[1].startsWith(("/"))) {
                    new DukeException();
                    break;
                }

                //Input processing
                String[] inputForEventConstructor = processedCommand[1].split("/", 2);
                inputForEventConstructor[0] = inputForEventConstructor[0].subSequence(0,
                    inputForEventConstructor[0].length() - 1).toString();

                //Create event
                Event event = new Event(inputForEventConstructor[0], inputForEventConstructor[1]);
                System.out.println(String.format(
                    "%s      %s", Commands.ADD.toString(), event.identify()));
                tasks.add(event);
                System.out.println(String.format("    Now you have %d tasks in the list.", tasks.size()));
                break;
            case "delete":
                //Exception catching
                if (processedCommand.length <= 1) {
                    new DukeException();
                    break;
                }
                try {
                    taskIndex = Integer.parseInt(processedCommand[1]) - 1;
                } catch (NumberFormatException e) {
                    new DukeException();
                    break;
                }

                Task deletedTask = tasks.remove((int)taskIndex);
                System.out.println(String.format(
                    "%s      %s\n    Now you have %d tasks in the list",
                        Commands.DELETE.toString(), deletedTask.identify(), tasks.size()));
                break;
            default:
                new DukeException();
            }
        }
        sc.close();
    }
}
