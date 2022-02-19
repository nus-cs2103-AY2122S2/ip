package gene.command;

import gene.component.*;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList geneTasks, Ui geneUi, TaskStorage geneTaskStorage, LocationList geneLocs, LocationStorage geneLocationStorage) {
        return "Hey there! Here is the list of commands available:\n"
                + "1. Add todo: todo task\n"
                + "2. Add event: event something /at  2/12/2019 1900\n"
                + "3. Add deadline: deadline something /by 12/02/2019 0928\n"
                + "4. Add location: location somewhere /at 138598, someplace\n"
                + "5. Mark/unmark task or location: mark task/location 0...\n"
                + "6. Delete task or location: delete task/location 1...\n"
                + "7. Find task/location: find keyword\n"
                + "8. List tasks/locations: list tasks/locations\n"
                + "9. Exit: exit";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
