package duke;

import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                String command = Parser.parse(input);

                if (command.equals("")) {
                    ui.showCommandMessage(command, tasks);
                    continue;
                }
                input = Parser.handleInput(input);

                try {
                    switch (command) {
                    case "list":
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "do":
                        int i = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.get(i).markComplete();
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "undo":
                        int j = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.get(j).markIncomplete();
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "delete":
                        int k = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                        tasks.remove(k);
                        ui.showCommandMessage(command, tasks);
                        break;
                    case "todo":
                        Todo t = new Todo(input);
                        tasks.add(t);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(t);
                        break;
                    case "find":
                        ui.showCommandMessage(command, tasks);
                        System.out.println(tasks.find(input));
                        break;
                    case "deadline":
                        String datetime = input.replaceAll(".* by ", "");
                        input = input.replaceAll(" by .*", "");
                        Deadline d = new Deadline(input, datetime);
                        tasks.add(d);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(d);
                        break;
                    case "event":
                        String time = input.replaceAll(".* at ", "");
                        input = input.replaceAll(" at .*", "");
                        Event e = new Event(input, time);
                        tasks.add(e);
                        ui.showCommandMessage(command, tasks);
                        System.out.println(e);
                        break;
                    }

                    if (!command.equals("list") && !command.equals("bye")) {
                        storage.save(tasks);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }

                isExit = command.equals("bye");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/tasks.txt").run();
    }

}
