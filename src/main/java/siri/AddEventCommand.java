package siri;

import java.time.LocalDate;

//AddEventCommand.java reused from Brigette Santoso E0564307
public class AddEventCommand extends Command {
    protected String description;
    protected String eventDate;
    protected LocalDate date;

    public AddEventCommand(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
    }
    public AddEventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        Task task;
        if (date == null) {
            task = new Event(description, "E", eventDate);
        }
        else {
            task = new Event(description, "E", date);
        }
        tasks.addTask(task);
        ui.showAddTask(task, tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
