import java.util.ArrayList;

public class Memory {
    private ArrayList<Task> taskMem;
    private int size;
    private Echo echo;

    public Memory() {
        this.taskMem = new ArrayList<>();
        this.size = 0;
        this.echo = new Echo();
    }

    public int getSize() {
        return size;
    }

    public String getString(int address) {
        // Simple error handling, should suffice but will update
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            return taskMem.get(address).toString();
        }
    }

    public Task getTask(int address) {
        return taskMem.get(address);
    }

    public void listAll() {
        if (size == 0) {
            echo.echoString("You've got nothing to do.");
        } else {
            for (int i = 1; i <= size; i++) {
                echo.echoString(i + ". " + this.getString(i-1));
            }
        }
    }

    public void addTask(String text) {
        taskMem.add(new Task(text));
        size++;
        echo.echoString("added task: " + getString(size - 1));
    }

    public void addDeadline(String name, String time) {
        taskMem.add(new Deadline(name, time));
        size++;
        echo.echoString("added deadline: " + getString(size - 1));
    }

    public void addEvent(String name, String time) {
        taskMem.add(new Event(name, time));
        size++;
        echo.echoString("added event: " + getString(size - 1));
    }

    public void setDone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setDone();
            echo.echoString("Cool! You've done this task:\n  " +
                    getString(address));
        }
    }

    public void setUndone(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setUndone();
            echo.echoString("This task is now undone:\n  " +
                    getString(address));
        }
    }

    public void deleteTask(int fakeAddress) {
        int address = fakeAddress - 1;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            echo.echoString("removed: " + getString(address));
            taskMem.remove(address);
            size--;
        }
    }
}
