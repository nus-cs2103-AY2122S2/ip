import java.util.Scanner;

public class Commands {
    private String[] fullCommand;
    static Scanner scanner;

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SAVE, LOAD, INVALID
    }

    public Commands() {
        scanner = new Scanner(System.in);
    }

    private void next() {
        this.fullCommand = scanner.nextLine().trim().split(" ", 2);
    }

    public void response() throws ApolloException {
        next();
        Command command = Command.INVALID;
        try {
            command = Command.valueOf(this.fullCommand[0].toUpperCase());
        } catch (IllegalArgumentException error) {
            if (this.fullCommand[0].equals("")) {
                response();
            }
        }

        Task newTask;
        switch (command) {
        case BYE:
            Apollo.stop();
            break;
        case LIST:
            Apollo.printList();
            break;
        case MARK:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify which task to mark as done. \n"
                        + "  mark <task number>");
            }
            Apollo.mark(Integer.parseInt(this.fullCommand[1]), true);
            break;
        case UNMARK:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify which task to mark as not done yet. \n"
                        + "  unmark <task number>");
            }
            Apollo.mark(Integer.parseInt(this.fullCommand[1]), false);
            break;
        case TODO:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify the description of the task. \n"
                        + "  todo <description>");
            }
            newTask = new Todo(this.fullCommand[1]);
            Apollo.addTask(newTask);
            break;
        case DEADLINE:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify the description and deadline of the task. \n"
                        + "  deadline <description> /by <time>");
            }
            String[] descTime = this.fullCommand[1].split(" */[Bb][Yy] *", 2);
            if (descTime.length == 1) {
                throw new ApolloException("Please add the time that this task is due. \n"
                        + "  deadline <description> /by <time>");
            }
            newTask = new Deadline(descTime[0], descTime[1]);
            Apollo.addTask(newTask);
            break;
        case EVENT:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify the description and period of the task. \n"
                        + "  event <description> /at <period>");
            }
            String[] descPeriod = this.fullCommand[1].split(" */[Aa][Tt] *", 2);
            if (descPeriod.length == 1) {
                throw new ApolloException("Please add the period that this task happens. \n"
                        + "  event <description> /at <period>");
            }
            newTask = new Event(descPeriod[0], descPeriod[1]);
            Apollo.addTask(newTask);
            break;
        case DELETE:
            if (this.fullCommand.length == 1) {
                throw new ApolloException("Please specify which task to delete. \n"
                        + "  delete <task number>");
            }
            Apollo.deleteTask(Integer.parseInt(this.fullCommand[1]));
            break;
        default:
            throw new ApolloException("Apologies, I do not understand that. ");
        }
    }
}
