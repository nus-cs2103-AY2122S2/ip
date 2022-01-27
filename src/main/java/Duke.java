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
    private TasksList taskslist;

    public Duke() {
        this.ui = new Ui();
        this.taskslist = new TasksList();
    }

    public void run() {
        this.ui.initialGreet();

        List<Task> tasks = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        List<String> commands = List.of("list", "bye", "mark", "unmark", "delete", "todo", "event", "deadline", "save");

        try {
            if (Files.exists(Paths.get(Constants.STORAGE_PATH))) {
                File f = new File(Constants.STORAGE_PATH);
                Scanner fileScanner = new Scanner(f);
                List<String> tasksStrings = new ArrayList<>();
                while(fileScanner.hasNextLine()) {
                    tasksStrings.add(fileScanner.nextLine());
                }
                String response = taskslist.importStorageStrings(tasksStrings);
                ui.print(response);
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
                    String response = taskslist.list();
                    ui.print(response.toString());

                } else if (instruction[0].equals("mark")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.mark(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.unmark(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("delete")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.deleteTask(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                    String response = taskslist.addTask(Arrays.asList(instruction));
                    ui.print(response);

                } else if (instruction[0].equals("save")) {
                    StringBuilder response = new StringBuilder("The following tasks will be saved: \n");
                    response.append(taskslist.list());

                    Path dir = Paths.get(Constants.DIR_PATH);
                    Path store = Paths.get(Constants.STORAGE_PATH);
                    if (!Files.exists(dir)) {
                        Files.createDirectory(dir);
                        Files.createFile(store);
                    } else if (!Files.exists(store)) {
                        Files.createFile(store);
                    }

                    FileWriter writer = new FileWriter(Constants.STORAGE_PATH);
                    for (String task : taskslist.toStorageStrings()) {
                        writer.write(task);
                    }
                    writer.close();

                    ui.print(response.toString());
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
