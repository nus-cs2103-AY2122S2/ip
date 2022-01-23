import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError(e);
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetings();
        while (true) {
            try {
                String[] input = sc.nextLine().strip().split(" ", 2);

                if (!Constants.TASKS.contains(input[0])) {
                    throw new CommandNotFoundException();
                }

                if (input[0].equals("bye")) {
                    break;
                } else if (input[0].equals("list")) {
                    ui.log(taskList.getTasks());
                } else {
                    if (input.length != 2) {
                        throw new InvalidArgumentException();
                    }

                    if (input[0].equals("mark") || input[0].equals("unmark")) {
                        int taskId = Integer.parseInt(input[1]);
                        ui.log(taskList.mark(taskId, input[0]));
                    } else if (input[0].equals("todo")) {
                        String toDo = input[1].strip();
                        ui.log(taskList.addTask(new ToDo(toDo)));
                    } else if (input[0].equals("event")) {
                        String[] eventDetails = input[1].strip().split(" /at ", 2);
                        ui.log(taskList.addTask(new Event(eventDetails[0], eventDetails[1])));
                    } else if (input[0].equals("deadline")) {
                        String[] deadlineDetails = input[1].strip().split(" /by ", 2);
                        ui.log(taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1])));
                    } else if (input[0].equals("delete")) {
                        int taskId = Integer.parseInt(input[1]);
                        ui.log(taskList.remove(taskId));
                    }
                }
                storage.update(taskList);
            } catch (Exception e) {
                ui.showLoadingError(e);
            }
        }
        ui.bye();
    }
}
