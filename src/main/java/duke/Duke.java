package duke;

/**
 * Main executable for the Duke program.
 */

public class Duke {


    private Storage taskStorage;
    private Storage noteStorage;
    private TaskList tasks;
    private NoteList notes;
    private Ui ui;


    public Duke(String taskListFilePath, String notesFilePath) {
        ui = new Ui();
        taskStorage = new Storage(taskListFilePath);
        noteStorage = new Storage(notesFilePath);

        try {
            tasks = new TaskList(taskStorage.loadTaskList());
            notes = new NoteList(noteStorage.loadNoteList());
        } catch (Exception e) {
            ui.showLoadingError();
            System.out.println(e);
            tasks = new TaskList();
            notes = new NoteList();
        }

    }

    public void saveDuke() {
        taskStorage.saveTasks(tasks);
        noteStorage.saveNotes(notes);
    }

    public String startDuke() {
        return ui.getStartOutput();
    }

    public String getResponse(String input) {
        return ui.getDukeOutput(input, tasks, notes);
    }

}
