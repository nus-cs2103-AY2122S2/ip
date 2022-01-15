import java.util.List;
import java.util.ArrayList;

public class Task {
    private String name;
    private int status;
    private static String[] statusSymbols = new String[]{"[ ]", "[✓]"};

    public Task(String name) {
        this.name = name;
        this.status = 0;
    }

    public String getStatus() {
        return statusSymbols[this.status] + " " + this.name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
