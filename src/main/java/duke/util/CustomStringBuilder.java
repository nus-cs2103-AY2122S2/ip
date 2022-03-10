package duke.util;

public class CustomStringBuilder {
    private StringBuilder stringBuilder;

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public void bulkAppend(Object... args) {
        for (Object obj : args) {
            stringBuilder.append(obj.toString());
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
