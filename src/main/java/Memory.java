public class Memory {
    private String[] textMem;
    private int size;
    private Echo echo;

    public Memory() {
        this.textMem = new String[100];
        this.size = 0;
        this.echo = new Echo();
    }

    public void AddString(String text) {
        textMem[size] = text;
        size++;
        echo.EchoString("added: " + text);
    }

    public int GetSize() {
        return size;
    }

    public String GetString(int address) {
        // Simple error handling, should suffice but will update
        if (address >= size || address < 0) {
            return "Memory address requested out of bounds!";
        } else {
            return textMem[address];
        }
    }

    public void ListAll() {
        if (size == 0) {
            echo.EchoString("You've got nothing to do.");
        } else {
            for (int i = 1; i <= size; i++) {
                echo.EchoString(i + ". " + this.GetString(i-1));
            }
        }
    }
}
