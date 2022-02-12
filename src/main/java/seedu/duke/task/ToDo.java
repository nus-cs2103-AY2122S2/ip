package seedu.duke.task;

public class ToDo extends Task {
    private final String taskType = "T";

    /**
     * used to construct a ToDo object.
     * @param taskName which specifies name of task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * used to construct a ToDo with a specified boolean.
     * @param oldToDo for a ToDo object
     * @param isDone to specify a boolean
     */
    ToDo(ToDo oldToDo, boolean isDone) {
        super(oldToDo.getTaskName(), isDone, null);
    }

    /**
     * Used to create a ToDo object.
     * @param taskName for name of task
     * @param isDone to denote whether the task is complete
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone, null);
    }

    /**
     * Used to update the ToDo object with a new Note.
     * @param toDo which contains information to transfer over to new ToDo
     * @param newNoteList which contains the updated notelist to store into ToDo
     */
    public ToDo(ToDo toDo, NoteList newNoteList) {
        super(toDo, newNoteList);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public Task editNoteList(int indexOfNote, String noteContent) {
        NoteList newNoteList = super.getNotes().editNote(indexOfNote, noteContent);
        return new ToDo(this, newNoteList);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public Task addNoteToNoteList(Note newNote) {
        NoteList newNoteList = super.getNotes().addNote(newNote);
        return new ToDo(this, newNoteList);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public Task deleteNoteFromNoteList(int indexOfNote) {
        NoteList newNoteList = super.getNotes().deleteNote(indexOfNote);
        return new ToDo(this, newNoteList);
    }


    /**
     * {inheritDoc}.
     */
    @Override
    public Task changeTaskStatus(boolean status) {
        return new ToDo(this, status);
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * {inheritDoc}.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
