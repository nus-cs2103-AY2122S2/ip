package duke.tasks;

public class Todo extends Task{

    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toSaveData() {
        return String.format("T|%s\n",super.toSaveData());
    }

    public static Todo createFromData(String savedData) {
        String[] parsedSavedData = savedData.split("\\|");
        Todo newTodo = new Todo(parsedSavedData[2]);
        if (parsedSavedData[1].equals("1")) {
            newTodo.markAsDone();
        }
        return newTodo;
    }
}
