package utilities;

public class OutputFormatter {
    private StringBuilder stringBuilder;

    private OutputFormatter() {
        this.stringBuilder = new StringBuilder();
    }

    public static OutputFormatter getInstance() {
        return new OutputFormatter();
    }

    public void appendAll(Object... objectArray) {
        for (Object o : objectArray) {
            this.stringBuilder.append(o.toString());
        }
    }

    public void append(Object object) {
        this.stringBuilder.append(object.toString());
    }

    public String getFormattedOutput() {
        return this.stringBuilder.toString();
    }

    public String emptyString() {
        return "";
    }
}
