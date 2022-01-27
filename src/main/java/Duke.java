import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Duke {
    private final Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    public void run() {
        this.ui.initialGreet();

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        List<String> commands = List.of("list", "bye", "mark", "unmark", "delete", "todo", "event", "deadConstants.LINE", "save");

        try {
            if (Files.exists(Paths.get(Constants.STORAGE_PATH))) {
                File f = new File(Constants.STORAGE_PATH);
                Scanner fileScanner = new Scanner(f);
                while(fileScanner.hasNextLine()) {
                    List<String> description = Arrays.asList(fileScanner.nextLine().split(" "));
                    Boolean isDone = description.get(0).equals("X");
                    Task task = Task.createTask(description.subList(1, description.size()));
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            ui.print(e.getMessage());
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        while (true) {
            try {
                String[] instruction = sc.nextLine().split(" ");
                if (instruction.length == 0 || !commands.contains(instruction[0])) {
                    throw new InvalidCommandException();
                }

                if (instruction[0].equals("bye")) {
                    ui.finalBye();
                    return;

                } else if (instruction[0].equals("list")) {
                    StringBuilder response = new StringBuilder("");

                    //String response = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        response.append(String.format((i + 1) + ". " + tasks.get(i) + "\n"));
                    }
                    ui.print(response.toString());

                } else if (instruction[0].equals("mark") || instruction[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(instruction[1]);

                    if (taskNum > tasks.size()) {
                        throw new InvalidIndexException();
                    }

                    String response;

                    if (instruction[0].equals("mark")) {
                        response = tasks.get(taskNum - 1).markAsDone();
                    } else {
                        response = tasks.get(taskNum - 1).markAsNotDone();
                    }

                    ui.print(response);

                } else if (instruction[0].equals("delete")) {
                    int taskNum = Integer.parseInt(instruction[1]);

                    if (taskNum > tasks.size()) {
                        throw new InvalidIndexException();
                    }

                    System.out.println(String.format(Constants.GAP + "Ok, I will remove this task: \n %s", tasks.get(taskNum - 1)));
                    System.out.println(Constants.LINE);
                    tasks.remove(taskNum - 1);

                } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadConstants.LINE")) {
                    Task newTask = Task.createTask(Arrays.asList(instruction));
                    tasks.add(newTask);
                    System.out.println(Constants.GAP + "Got it. I've added this task\n" + Constants.GAP +  newTask);
                    System.out.println(String.format(Constants.GAP + "You have %d tasks in the list", tasks.size()));
                    System.out.println(Constants.LINE);

                } else if (instruction[0].equals("save")) {
                    String output;
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    Path dir = Paths.get(Constants.DIR_PATH);
                    Path store = Paths.get(Constants.STORAGE_PATH);
                    if (!Files.exists(dir)) {
                        Files.createDirectory(dir);
                        Files.createFile(store);
                    } else if (!Files.exists(store)) {
                        Files.createFile(store);
                    }

                    FileWriter writer = new FileWriter(Constants.STORAGE_PATH);
                    for (Task task : tasks) {
                        writer.write(task.toStorageString() + "\n");
                    }
                    writer.close();
                }
            }
            catch (DukeException e){
                ui.print(e.getMessage());
            } catch (IOException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
