public class Memory {
    private Task[] taskMem;
    private int size;
    private Echo echo;

    public Memory() {
        this.taskMem = new Task[100];
        this.size = 0;
        this.echo = new Echo();
    }

    public void addTask(String text) {
        taskMem[size] = new Task(text);
        size++;
        echo.echoString("added: " + text);
    }

    public void setDone(int address) {
        address--;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setDone();
            echo.echoString("Cool! You've done this task:\n  " +
                    getString(address));
        }
    }

    public void setUndone(int address) {
        address--;
        if (address >= size || address < 0) {
            echo.echoString("Memory address requested out of bounds!");
        } else {
            getTask(address).setUndone();
            echo.echoString("How did you undo this task?\n  " +
                    getString(address));
        }
    }

    public int getSize() {
        return size;
    }

    public String getString(int address) {
        // Simple error handling, should suffice but will update
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            return taskMem[address].toString();
        }
    }

    public Task getTask(int address) {
        return taskMem[address];
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
}
