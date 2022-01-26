import luke.commands.Command;
import luke.commands.CommandResult;
import luke.data.TaskList;
import luke.parser.Parser;
import luke.storage.StorageFile;
import luke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Luke {

    private Ui ui;
    private TaskList taskList;
    private StorageFile storageFile;

    Luke(String filePath) {
        ui = new Ui();
        taskList = new TaskList();
        try {
            storageFile = new StorageFile(filePath);
        } catch (IOException e) {
            ui.showError(e.getMessage() + "\n Goodbye...");
            System.exit(1);
        }
    }

    private void initializeStorage() {
        try {
            List<String> data = storageFile.load();
            if (!data.isEmpty()) {
                for (int i = 0; i < data.size(); i++) {
                    String str = data.get(i);
                    String[] inputs = str.split("\\|");
                    Command cmd = Parser.parse(inputs[0]);
                    cmd.execute(taskList);
                    if (Integer.parseInt(inputs[1].strip()) == 1) {
                        taskList.get(i).markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError(e.getMessage());
        }
    }

    public void start() {
        initializeStorage();
        boolean isExit = false;
        ui.greeting();
        while (!isExit) {
            try {
                Command cmd = Parser.parse(ui.readInput());
                CommandResult response = cmd.execute(taskList);
                ui.showOutput(response.getResult());
                isExit = cmd.isExitCmd();
                storageFile.save(taskList);
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        Luke luke = new Luke("data/luke.txt");
        luke.start();
    }
}
